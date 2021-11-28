package top.twhuang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.twhuang.dto.BlogCommentDTO;
import top.twhuang.dto.PageDTO;
import top.twhuang.entity.Comment;

import java.util.Map;

public interface CommentService extends IService<Comment> {

    /**
     * 后台评论
     * @param pageDTO
     * @param postId
     * @return
     */
    Map getPage(PageDTO pageDTO, Integer postId);

    /**
     * 博客文章评论
     *
     * @param blogCommentDTO
     * @return
     */
    IPage<Comment> getBlogPage(BlogCommentDTO blogCommentDTO);
}
