package top.twhuang.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import top.twhuang.dto.BlogHomeDTO;
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
import top.twhuang.vo.InfoVO;

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
    public IPage<Post> getPage(BlogHomeDTO blogHomeDTO) {
        Page<Post> page = new Page<>(blogHomeDTO.getPageNum(), blogHomeDTO.getPageSize());
        return postMapper.selectBlogPostPage(page, blogHomeDTO.getKeyword(), blogHomeDTO.getCategoryId());
    }

    @Override
    public Map<String, Object> getPage(PageDTO pageDTO) {
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
            hashMap.put("views", p.getPageView());
            hashMap.put("timestamp", p.getTimestamp());
            hashMap.put("canComment", p.getCanComment());
            hashMap.put("image", p.getImage());
            hashMap.put("counts", counts);
            hashMap.put("categoryName", category.getName());
            list.add(hashMap);
        }
        map.put("list", list);
        map.put("total", page.getTotal());
        map.put("size", page.getSize());
        return map;
    }

    @Override
    public void updatePageView(Integer id) {
        postMapper.updatePageView(id);
    }

    @Override
    public Post getPrevPost(Integer id) {
        return postMapper.selectPrevPost(id);
    }

    @Override
    public Post getNextPost(Integer id) {
        return postMapper.selectNextPost(id);
    }

    @Override
    public Integer getCountPages() {
        return postMapper.selectCountPages();
    }

    @Override
    public Integer getSumPageViews() {
        return postMapper.selectSumPageViews();
    }
}
