package top.twhuang.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import top.twhuang.dto.PageDTO;
import top.twhuang.entity.Category;
import top.twhuang.entity.Post;
import top.twhuang.service.CategoryService;
import top.twhuang.service.PostService;
import top.twhuang.util.Result;

import org.springframework.web.bind.annotation.*;
import top.twhuang.vo.PostVO;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: tw.huang
 * @DateTime: 2020-08-01 18:52
 * @Description: Api控制器
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PostApiController {

    private PostService postService;
    private CategoryService categoryService;

    @GetMapping("/blog/info")
    public Result blogInfo() {
        return Result.success(postService.getBlogInfo());
    }

    @GetMapping("/blog/posts/hot")
    public Result blogPostsHot() {
        List<Post> list = postService.list(new QueryWrapper<Post>().lambda().orderByDesc(Post::getPageView).last("LIMIT 5"));
        list.forEach(post -> post.setCategory(categoryService.getById(post.getCategoryId())));
        return Result.success(list);
    }

    @GetMapping("/blog/posts")
    public Result blogPosts(@RequestParam(name = "keyword", required = false) String keyword,
                            @RequestParam(name = "categoryId", required = false) Integer categoryId,
                            @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Post> qw = new QueryWrapper<Post>().lambda();
        if (StringUtils.isNoneBlank(keyword)) {
            qw.like(Post::getTitle, keyword);
        }
        if (!Objects.isNull(categoryId)) {
            qw.eq(Post::getCategoryId, categoryId);
        }
        qw.orderByDesc(Post::getTimestamp);
        Page<Post> page = postService.page(new Page<>(pageNum, pageSize), qw);
        page.getRecords().forEach(post -> post.setCategory(categoryService.getById(post.getCategoryId())));
        return Result.success(page);
    }

    @GetMapping("/blog/posts/archive")
    public Result blogPostsArchive(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Post> qw = new QueryWrapper<Post>().lambda().orderByDesc(Post::getTimestamp);
        Page<Post> page = postService.page(new Page<>(pageNum, pageSize), qw);

        ArrayList<HashMap<String, Object>> archivesMap = new ArrayList<>();
        // 分组并按月份排序
        page.getRecords().stream().collect(Collectors.groupingBy(
                x -> DateUtil.format(x.getTimestamp(), "yyyy-MM"), LinkedHashMap::new, Collectors.toList())).forEach((k, v) -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("date", k);
            map.put("posts", v);
            archivesMap.add(map);
        });
        HashMap<String, Object> map = new HashMap<>();
        map.put("records", archivesMap);
        map.put("pages", page.getPages());
        map.put("total", page.getTotal());
        return Result.success(map);
    }

    @GetMapping("/blog/post/{id}")
    public Result blogPost(@PathVariable Integer id) {
        PostVO postVO = new PostVO();
        Post post = postService.getById(id);
        if (!Objects.isNull(post)) {
            Category category = categoryService.getById(post.getCategoryId());
            post.setCategory(category);
        }
        BeanUtils.copyProperties(post, postVO);

        Post prevPost = postService.getPrevPost(id);
        Post nextPost = postService.getNextPost(id);
        postVO.setPrevPostId(Objects.isNull(prevPost) ? null : prevPost.getId());
        postVO.setPrevPostTitle(Objects.isNull(prevPost) ? null : prevPost.getTitle());
        postVO.setNextPostId(Objects.isNull(nextPost) ? null : nextPost.getId());
        postVO.setNextPostTitle(Objects.isNull(nextPost) ? null : nextPost.getTitle());
        // 增加文章浏览量
        postService.updatePageView(id);
        return Result.success(postVO);
    }

    @GetMapping("/posts")
    public Result posts(PageDTO pageDTO) {
        Map map = postService.getPage(pageDTO);
        return Result.success(map);
    }

    @GetMapping("/posts/list")
    public Result posts() {
        List<Post> postList = postService.list();
        ArrayList<Map> list = new ArrayList<>();
        postList.forEach(x -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", x.getId());
            map.put("title", x.getTitle());
            list.add(map);
        });
        return Result.success(list);
    }

    @GetMapping("/post/{id}")
    public Result getPost(@PathVariable Integer id) {
        Post post = postService.getById(id);
        return Result.success(post);
    }

    @PostMapping("/post")
    public Result postPost(@RequestBody Post post) {
        post.setTimestamp(new Date());
        // 默认关闭评论
        post.setCanComment(false);
        boolean save = postService.save(post);
        if (save) {
            categoryService.cacheEvict();
            return Result.success();
        }
        return Result.failure();
    }

    @PutMapping("/post")
    public Result putPost(@RequestBody Post post) {
        boolean update = postService.updateById(post);
        if (update) {
            categoryService.cacheEvict();
            return Result.success();
        }
        return Result.failure();
    }

    @DeleteMapping("/post/{id}")
    public Result deletePost(@PathVariable Integer id) {
        boolean remove = postService.removeById(id);
        if (remove) {
            categoryService.cacheEvict();
            return Result.success();
        }
        return Result.failure();
    }
}
