package me.breakblog.service;

import me.breakblog.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAllByPostId(int id, int page, int size);

    List<Comment> findAll(int page, int size);

    int addComment(Comment comment);

    int deleteComment(int id);

    int updateComment(int id, int reviewed);
}
