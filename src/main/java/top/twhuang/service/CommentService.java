package top.twhuang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.twhuang.dto.PageDTO;
import top.twhuang.entity.Comment;

import java.util.Map;

public interface CommentService extends IService<Comment> {

    Page getPageByPostId(int id, int page, int size);

    Map getPage(PageDTO pageDTO);
}
