package top.twhuang.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.twhuang.dto.PageDTO;
import top.twhuang.entity.Post;

import java.util.Map;

public interface PostService extends IService<Post> {

    Page getPage(int page, int size);

    Page getPageByCategoryId(int id, int page, int size);

    Post getPostById(int id);

    Map getPage(PageDTO pageDTO);

    void updatePageView(int id);
}
