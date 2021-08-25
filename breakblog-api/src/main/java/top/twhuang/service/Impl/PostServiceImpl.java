package top.twhuang.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.twhuang.dto.PageDTO;
import top.twhuang.entity.Category;
import top.twhuang.entity.Comment;
import top.twhuang.entity.Post;
import top.twhuang.mapper.PostMapper;
import top.twhuang.service.CategoryService;
import top.twhuang.service.CommentService;
import top.twhuang.service.PostService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Resource
    private PostMapper postMapper;

    @Resource
    private CategoryService categoryService;

    @Resource
    private CommentService commentService;

    @Override
    public Page getPage(int page, int size) {
        QueryWrapper<Post> qw = new QueryWrapper<>();
        Page<Post> postPage = postMapper.selectPage(new Page<>(page, size), qw.orderByDesc("id"));
        for (Post p : postPage.getRecords()) {
            Category category = categoryService.getById(p.getCategoryId());
            p.setCategory(category);
        }
        return postPage;
    }

    @Override
    public Page getPageByCategoryId(int id, int page, int size) {
        QueryWrapper<Post> qw = new QueryWrapper<>();
        Page<Post> postPage = postMapper.selectPage(new Page<>(page, size), qw.eq("category_id", id).orderByDesc("id"));
        for (Post p : postPage.getRecords()) {
            Category category = categoryService.getById(p.getCategoryId());
            p.setCategory(category);
        }
        return postPage;
    }

    @Override
    public Post getPostById(int id) {
        Post post = postMapper.selectById(id);
        post.setCategory(categoryService.getById(post.getCategoryId()));
        return post;
    }

    @Override
    public Map getPage(PageDTO pageDTO) {
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Post> qw = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(pageDTO.getKeyword())) {
            qw.like("title", pageDTO.getKeyword());
        }
        qw.orderByDesc("id");
        Page<Post> page = postMapper.selectPage(new Page<>(pageDTO.getPageNum(), pageDTO.getPageSize()), qw);
        for (Post p : page.getRecords()) {
            HashMap<String, Object> hashMap = new HashMap<>();
            Category category = categoryService.getById(p.getCategoryId());
            int counts = commentService.count(new QueryWrapper<Comment>().eq("post_id", p.getId()));
            hashMap.put("id", p.getId());
            hashMap.put("title", p.getTitle());
            hashMap.put("categoryName", category.getName());
            hashMap.put("views", p.getPageView());
            hashMap.put("counts",counts);
            list.add(hashMap);
        }
        map.put("list", list);
        map.put("total", page.getTotal());
        map.put("size", page.getSize());
        return map;
    }

    @Override
    public void updatePageView(int id) {
        postMapper.updatePageView(id);
    }
}