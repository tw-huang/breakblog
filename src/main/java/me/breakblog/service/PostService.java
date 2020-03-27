package me.breakblog.service;

import me.breakblog.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> findAll(int page, int size);

    List<Post> findByCategoryId(int id, int page, int size);

    Post findById(int id);

    int addPost(Post post);

    int updatePost(Post post);

    int deletePost(int id);

}
