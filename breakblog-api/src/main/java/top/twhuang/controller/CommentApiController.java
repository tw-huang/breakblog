package top.twhuang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import top.twhuang.dto.PageDTO;
import top.twhuang.entity.Comment;
import top.twhuang.entity.Post;
import top.twhuang.service.CommentService;
import top.twhuang.service.PostService;
import top.twhuang.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

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

    private PostService postService;

    @GetMapping("/blog/comments")
    public Result blogComments(@RequestParam(name = "postId") Integer postId,
                               @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        QueryWrapper<Comment> qw = new QueryWrapper<>();
        qw.lambda().eq(Comment::getPostId, postId).orderByDesc(Comment::getTimestamp);
        Page<Comment> page = commentService.page(new Page<>(pageNum, pageSize), qw);
        page.getRecords().forEach(x -> {
            if (!Objects.isNull(x.getRepliedId())) {
                Comment replyComment = commentService.getById(x.getRepliedId());
                x.setComment(replyComment);
            }
        });
        return Result.success(page);
    }

    @PostMapping("/blog/comment")
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


    @GetMapping("/comments")
    public Result comments(PageDTO pageDTO) {
        Map map = commentService.getPage(pageDTO);
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
