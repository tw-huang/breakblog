package me.breakblog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.breakblog.dto.PageDTO;
import me.breakblog.entity.Comment;

import java.util.Map;

public interface CommentService extends IService<Comment> {

    Page getPageByPostId(int id, int page, int size);

    Map getPage(PageDTO pageDTO);
}
