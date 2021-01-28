package me.breakblog.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.breakblog.dto.PageDTO;
import me.breakblog.entity.Post;

import java.util.Map;

public interface PostService extends IService<Post> {

    Page getPage(int page, int size);

    Page getPageByCategoryId(int id, int page, int size);

    Post getPostById(int id);

    Map getPage(PageDTO pageDTO);

    void updatePageView(int id);
}
