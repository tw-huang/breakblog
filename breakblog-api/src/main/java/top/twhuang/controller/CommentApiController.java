package top.twhuang.controller;

import lombok.AllArgsConstructor;
import top.twhuang.dto.PageDTO;
import top.twhuang.entity.Comment;
import top.twhuang.service.CommentService;
import top.twhuang.util.Result;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: tw.huang
 * @DateTime: 2020-08-02 10:42
 * @Description: Api控制器
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CommentApiController {

    private CommentService commentService;

    @GetMapping("/comments")
    public Result comments(PageDTO pageDTO, @RequestParam(value = "postId") Integer postId) {
        Map map = commentService.getPage(pageDTO, postId);
        return Result.success(map);
    }

    @GetMapping("/comment/{id}")
    public Result getComment(@PathVariable Integer id) {
        Comment comment = commentService.getById(id);
        return Result.success(comment);
    }

    @PostMapping("/comment")
    public Result postComment(@RequestBody Comment comment) {
        boolean save = commentService.save(comment);
        if (save) {
            return Result.success();
        }
        return Result.failure();
    }

    @PutMapping("/comment")
    public Result putComment(@RequestBody Comment comment) {
        boolean update = commentService.updateById(comment);
        if (update) {
            return Result.success();
        }
        return Result.failure();
    }

    @DeleteMapping("/comment/{id}")
    public Result deleteComment(@PathVariable Integer id) {
        boolean remove = commentService.removeById(id);
        if (remove) {
            return Result.success();
        }
        return Result.failure();
    }
}
