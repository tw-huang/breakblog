package me.breakblog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.breakblog.entity.Comment;
import me.breakblog.mapper.CommentMapper;
import me.breakblog.service.CommentService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public Page getPageByPostId(int id, int page, int size) {
        QueryWrapper<Comment> qw = new QueryWrapper<>();
        Page<Comment> commentPage = commentMapper.selectPage(new Page<>(page, size), qw.eq("post_id", id));
        for (Comment c : commentPage.getRecords()) {
            c.setComment(commentMapper.selectById(c.getRepliedId()));
        }
        return commentPage;
    }

    @Override
    public Page getPage(int page, int size) {
        QueryWrapper<Comment> qw = new QueryWrapper<>();
        Page<Comment> commentPage = commentMapper.selectPage(new Page<>(page, size), null);
        for (Comment c : commentPage.getRecords()) {
            c.setComment(commentMapper.selectById(c.getRepliedId()));
        }
        return commentPage;
    }
}
