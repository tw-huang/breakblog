package top.twhuang.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import top.twhuang.dto.BlogCommentDTO;
import top.twhuang.dto.BlogHomeDTO;
import top.twhuang.entity.*;
import top.twhuang.service.*;
import top.twhuang.util.Result;
import top.twhuang.vo.PostVO;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/blog")
@AllArgsConstructor
public class BlogApiController {

    private AdminService adminService;

    private CategoryService categoryService;

    private CommentService commentService;

    private PostService postService;

    private LinkService linkService;


    @GetMapping("/info")
    public Result blogInfo() {
        return Result.success(adminService.getBlogInfo());
    }

    @GetMapping("/statistic")
    public Result blogStatistic() {
        return Result.success(adminService.getBlogStatistic());
    }

    @GetMapping("/about")
    public Result blogAbout() {
        Admin admin = adminService.getById(1);
        if (ObjectUtils.isNotEmpty(admin)) {
            admin.setUsername(null);
            admin.setPassword(null);
            admin.setPhone(null);
            return Result.success(admin);
        }
        return Result.failure();
    }

    @GetMapping("/categories")
    public Result blogCategories() {
        List<Category> list = categoryService.getList();
        return Result.success(list);
    }

    @GetMapping("/comments")
    public Result blogComments(@RequestParam(name = "postId") Integer postId,
                               @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        BlogCommentDTO blogCommentDTO = new BlogCommentDTO();
        blogCommentDTO.setPageNum(pageNum);
        blogCommentDTO.setPageSize(pageSize);
        blogCommentDTO.setPostId(postId);
        return Result.success(commentService.getBlogPage(blogCommentDTO));
    }

    @PostMapping("/comment")
    public Result blogComment(@RequestBody Comment comment) {
        Post post = postService.getById(comment.getPostId());
        if (post.getCanComment()) {
            comment.setTimestamp(new Date());
            comment.setReviewed(false);
            boolean save = commentService.save(comment);
            if (save) {
                return Result.success();
            }
            return Result.failure();
        }
        return Result.failure("文章关闭评论");
    }

    @GetMapping("/links")
    public Result blogLinks() {
        List<Link> list = linkService.getList();
        return Result.success(list);
    }

    @GetMapping("/posts/hot")
    public Result blogPostsHot() {
        List<Post> list = postService.getPostHot();
        return Result.success(list);
    }

    @GetMapping("/posts")
    public Result blogPosts(@RequestParam(name = "keyword", required = false) String keyword,
                            @RequestParam(name = "categoryId", required = false) Integer categoryId,
                            @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        BlogHomeDTO blogHomeDTO = new BlogHomeDTO();
        blogHomeDTO.setKeyword(keyword);
        blogHomeDTO.setCategoryId(categoryId);
        blogHomeDTO.setPageNum(pageNum);
        blogHomeDTO.setPageSize(pageSize);
        IPage<Post> page = postService.getBlogPage(blogHomeDTO);
        return Result.success(page);
    }

    @GetMapping("/posts/archive")
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

    @GetMapping("/post/{id}")
    public Result blogPost(@PathVariable Integer id) {
        PostVO postVO = new PostVO();
        Post post = postService.getPostById(id);
        BeanUtils.copyProperties(post, postVO);

        Post prevPost = postService.getPrevPost(id);
        Post nextPost = postService.getNextPost(id);
        postVO.setPrevPostId(Objects.isNull(prevPost) ? null : prevPost.getId());
        postVO.setPrevPostTitle(Objects.isNull(prevPost) ? null : prevPost.getTitle());
        postVO.setNextPostId(Objects.isNull(nextPost) ? null : nextPost.getId());
        postVO.setNextPostTitle(Objects.isNull(nextPost) ? null : nextPost.getTitle());
        return Result.success(postVO);
    }

    @PutMapping("/post/{id}")
    public Result blogPostPut(@PathVariable Integer id) {
        // 增加文章浏览量
        postService.updatePageView(id);
        return Result.success("ok");
    }

}
