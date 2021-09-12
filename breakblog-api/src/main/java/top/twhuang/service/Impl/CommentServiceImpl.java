package top.twhuang.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import top.twhuang.dto.PageDTO;
import top.twhuang.entity.Comment;
import top.twhuang.entity.Post;
import top.twhuang.mapper.CommentMapper;
import top.twhuang.service.CommentService;

import top.twhuang.service.PostService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Autowired
    private PostService postService;

    @Override
    public Map getPage(PageDTO pageDTO, Integer postId) {
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Comment> qw = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(pageDTO.getKeyword())) {
            qw.lambda().like(Comment::getAuthor, pageDTO.getKeyword()).or().like(Comment::getBody, pageDTO.getKeyword());
        }
        if (ObjectUtils.isNotEmpty(postId)) {
            qw.lambda().eq(Comment::getPostId, postId);
        }
        qw.lambda().orderByDesc(Comment::getTimestamp);
        Page<Comment> page = commentMapper.selectPage(new Page<>(pageDTO.getPageNum(), pageDTO.getPageSize()), qw);
        for (Comment c : page.getRecords()) {
            HashMap<String, Object> hashMap = new HashMap<>();
            Post post = postService.getById(c.getPostId());
            hashMap.put("id", c.getId());
            hashMap.put("author", c.getAuthor());
            hashMap.put("email", c.getEmail());
            hashMap.put("site", c.getSite());
            hashMap.put("postTitle", post.getTitle());
            hashMap.put("body", c.getBody());
            hashMap.put("reviewed", c.getReviewed());
            list.add(hashMap);
        }
        map.put("list", list);
        map.put("total", page.getTotal());
        map.put("size", page.getSize());
        return map;
    }
}
