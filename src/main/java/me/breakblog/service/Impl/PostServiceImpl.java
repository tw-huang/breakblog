package me.breakblog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.breakblog.entity.Category;
import me.breakblog.entity.Post;
import me.breakblog.mapper.CategoryMapper;
import me.breakblog.mapper.PostMapper;
import me.breakblog.service.PostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;


@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Resource
    private PostMapper postMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public Page getPage(int page, int size) {
        QueryWrapper<Post> qw = new QueryWrapper<>();
        Page<Post> postPage = postMapper.selectPage(new Page<>(page, size), qw.orderByDesc("id"));
        for (Post p : postPage.getRecords()) {
            Category category = categoryMapper.selectById(p.getCategoryId());
            p.setCategory(category);
        }
        return postPage;
    }

    @Override
    public Page getPageByCategoryId(int id, int page, int size) {
        QueryWrapper<Post> qw = new QueryWrapper<>();
        Page<Post> postPage = postMapper.selectPage(new Page<>(page, size), qw.eq("category_id", id).orderByDesc("id"));
        for (Post p : postPage.getRecords()) {
            Category category = categoryMapper.selectById(p.getCategoryId());
            p.setCategory(category);
        }
        return postPage;
    }

    @Override
    public Post getPostById(int id) {
        Post post = postMapper.selectById(id);
        post.setCategory(categoryMapper.selectById(post.getCategoryId()));
        return post;
    }
}
