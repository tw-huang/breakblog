package top.twhuang.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import top.twhuang.dto.PageDTO;
import top.twhuang.entity.Category;
import top.twhuang.entity.Post;
import top.twhuang.service.CategoryService;
import top.twhuang.service.PostService;
import top.twhuang.util.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: tw.huang
 * @DateTime: 2020-08-01 18:52
 * @Description: Api控制器
 */
@RestController
@RequestMapping("/api")
public class PostApiController {

    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;

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
        Page<Post> page = postService.page(new Page<>(pageNum, pageSize), qw);
        page.getRecords().forEach(post -> post.setCategory(categoryService.getById(post.getCategoryId())));
        return Result.success(page);
    }

    @GetMapping("/posts")
    public Result posts(PageDTO pageDTO) {
        Map map = postService.getPage(pageDTO);
        return Result.success(map);
    }

    @GetMapping("/post/{id}")
    public Result getPost(@PathVariable Integer id) {
        Post post = postService.getById(id);
        return Result.success(post);
    }

    @PostMapping("/post")
    public Result postPost(@RequestBody Post post) {
        post.setTimestamp(new Date());
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

    @GetMapping("/post/new/categories")
    public Result categories() {
        List<Category> categoryList = categoryService.list();
        ArrayList<Map> list = new ArrayList<>();
        categoryList.forEach(x -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", x.getId());
            map.put("name", x.getName());
            list.add(map);
        });
        return Result.success(list);
    }

}
