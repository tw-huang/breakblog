package me.breakblog.dao;

import me.breakblog.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PostDao {

    List<Post> findAll();

    List<Post> findByCategoryId(int id);

    Post findById(int id);

    int addPost(Post post);

    int updatePost(Post post);

    int deletePost(int id);

    void addPageViews(int id);
}
