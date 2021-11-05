package top.twhuang.service.Impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
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

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Resource
    private PostMapper postMapper;

    @Resource
    private CategoryService categoryService;

    @Resource
    private CommentService commentService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public final static String POST_ID_ZSET = "post_id_zset::";

    @Override
    public Post getById(Serializable id) {
        return postMapper.selectById(id);
    }

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

    @Override
    public List<Post> getPostHot() {
        List<Post> list;
        //先从redis读取今日访问量最高的五篇文章
        Set<String> postHotSet = redisTemplate.opsForZSet().reverseRange(POST_ID_ZSET + DateUtil.format(new Date(), "yyyy-MM"), 0, -1);
        if (!Objects.isNull(postHotSet) && postHotSet.size() >= 5) {
            Set<Integer> collect = postHotSet.stream().map(Integer::valueOf).collect(Collectors.toSet());
            list = postMapper.selectPostHot(collect);
        } else {
            list = postMapper.selectList(new QueryWrapper<Post>().lambda().orderByDesc(Post::getPageView).last("LIMIT 5"));
            list.forEach(post -> post.setCategory(categoryService.getById(post.getCategoryId())));
        }
        return list;
    }
}
