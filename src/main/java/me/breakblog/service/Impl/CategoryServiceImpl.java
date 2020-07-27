package me.breakblog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.breakblog.entity.Category;
import me.breakblog.entity.Post;
import me.breakblog.mapper.CategoryMapper;
import me.breakblog.mapper.PostMapper;
import me.breakblog.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private PostMapper postMapper;

    @Override
    public List<Category> getList() {
        List<Category> categories = categoryMapper.selectList(null);
        categories.forEach(x -> {
            QueryWrapper<Post> qw = new QueryWrapper<>();
            List<Post> postList = postMapper.selectList(qw.eq("category_id", x.getId()));
            x.setPosts(postList);
        });
        return categories;
    }
}