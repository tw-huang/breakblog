package me.breakblog.service.Impl;

import com.github.pagehelper.PageHelper;
import me.breakblog.dao.PostDao;
import me.breakblog.entity.Post;
import me.breakblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private PostDao postDao;

    @Autowired
    public PostServiceImpl(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public List<Post> findAll(int page, int size) {
        PageHelper.startPage(page, size);
        return postDao.findAll();
    }

    @Override
    public List<Post> findByCategoryId(int id, int page, int size) {
        PageHelper.startPage(page, size);
        return postDao.findByCategoryId(id);
    }

    @Override
    public Post findById(int id) {
        postDao.addPageViews(id);
        return postDao.findById(id);
    }

    @Override
    public int addPost(Post post) {
        return postDao.addPost(post);
    }

    @Override
    public int updatePost(Post post) {
        return postDao.updatePost(post);
    }

    @Override
    public int deletePost(int id) {
        return postDao.deletePost(id);
    }

}
