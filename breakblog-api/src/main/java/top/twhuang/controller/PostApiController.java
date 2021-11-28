package top.twhuang.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import top.twhuang.dto.PageDTO;
import top.twhuang.entity.Post;
import top.twhuang.service.CategoryService;
import top.twhuang.service.PostService;
import top.twhuang.util.Result;

import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: tw.huang
 * @DateTime: 2020-08-01 18:52
 * @Description: Api控制器
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class PostApiController {

    private PostService postService;

    private CategoryService categoryService;


    @GetMapping("/posts")
    public Result posts(PageDTO pageDTO) {
        Map<String, Object> map = postService.getPage(pageDTO);
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
