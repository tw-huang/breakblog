package me.breakblog.dao;

import me.breakblog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentDao {

    List<Comment> findAllByPostId(int id);

    List<Comment> findAll();

    Comment findById(int id);

    int addComment(Comment comment);

    int deleteComment(int id);

    int updateComment(@Param("id") int id, @Param("reviewed") int reviewed);
}
