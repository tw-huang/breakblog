package me.breakblog.Api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.breakblog.dto.LoginDTO;
import me.breakblog.dto.PageDTO;
import me.breakblog.entity.Admin;
import me.breakblog.entity.Post;
import me.breakblog.service.AdminService;
import me.breakblog.service.PostService;
import me.breakblog.util.JwtUtil;
import me.breakblog.util.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
        Page page = postService.getPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        return Result.success(page);
    }

    @GetMapping("/post/{id}")
    public Result getPost(@PathVariable Integer id) {
        Post post = postService.getPostById(id);
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
    public Result putPost(@PathVariable String id) {
        boolean remove = postService.removeById(id);
        if (remove) {
            return Result.success();
        }
        return Result.failure();
    }


}
