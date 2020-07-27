package me.breakblog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.breakblog.entity.Comment;

public interface CommentService extends IService<Comment> {

    Page getPageByPostId(int id, int page, int size);

    Page getPage(int page, int size);
}
