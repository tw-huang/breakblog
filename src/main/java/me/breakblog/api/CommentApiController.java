package me.breakblog.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.breakblog.dto.PageDTO;
import me.breakblog.entity.Comment;
import me.breakblog.service.CommentService;
import me.breakblog.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: tw.huang
 * @DateTime: 2020-08-02 10:42
 * @Description: Api控制器
 */
@RestController
@RequestMapping("/api/v1")
public class CommentApiController {
    @Autowired
    private CommentService commentService;


    @GetMapping("/comments")
    public Result comments(PageDTO pageDTO) {
        Page<Comment> page = commentService.page(new Page<>(pageDTO.getPageNum(), pageDTO.getPageSize()));
        return Result.success(page);
    }

    @GetMapping("/comment/{id}")
    public Result getComment(@PathVariable Integer id) {
        Comment comment = commentService.getById(id);
        return Result.success(comment);
    }

    @PostMapping("/comment")
    public Result postComment(Comment comment) {
        boolean save = commentService.save(comment);
        if (save) {
            return Result.success();
        }
        return Result.failure();
    }

    @PutMapping("/comment")
    public Result putComment(Comment comment) {
        boolean update = commentService.updateById(comment);
        if (update) {
            return Result.success();
        }
        return Result.failure();
    }

    @DeleteMapping("/comment/{id}")
    public Result deleteComment(@PathVariable String id) {
        boolean remove = commentService.removeById(id);
        if (remove) {
            return Result.success();
        }
        return Result.failure();
    }
}
