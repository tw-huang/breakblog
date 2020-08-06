package me.breakblog.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.breakblog.dto.PageDTO;
import me.breakblog.entity.Post;
import me.breakblog.service.PostService;
import me.breakblog.util.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public Result postPost(Post post) {
        boolean save = postService.save(post);
        if (save) {
            return Result.success();
        }
        return Result.failure();
    }

    @PutMapping("/post")
    public Result putPost(Post post) {
        boolean update = postService.updateById(post);
        if (update) {
            return Result.success();
        }
        return Result.failure();
    }

    @DeleteMapping("/post/{id}")
    public Result deletePost(@PathVariable String id) {
        boolean remove = postService.removeById(id);
        if (remove) {
            return Result.success();
        }
        return Result.failure();
    }

}
