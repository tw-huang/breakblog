package me.breakblog.controller.api;

import me.breakblog.dto.PageDTO;
import me.breakblog.entity.Category;
import me.breakblog.entity.Post;
import me.breakblog.service.CategoryService;
import me.breakblog.service.PostService;
import me.breakblog.util.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: tw.huang
 * @DateTime: 2020-08-01 18:52
 * @Description: Api控制器
 */
@RestController
@RequestMapping("/api/v1")
public class PostApiController {

    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;


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
            return Result.success();
        }
        return Result.failure();
    }

    @PutMapping("/post")
    public Result putPost(@RequestBody Post post) {
        boolean update = postService.updateById(post);
        if (update) {
            return Result.success();
        }
        return Result.failure();
    }

    @DeleteMapping("/post/{id}")
    public Result deletePost(@PathVariable Integer id) {
        boolean remove = postService.removeById(id);
        if (remove) {
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
