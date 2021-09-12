package top.twhuang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.twhuang.dto.PageDTO;
import top.twhuang.entity.Comment;

import java.util.Map;

public interface CommentService extends IService<Comment> {

    Map getPage(PageDTO pageDTO, Integer postId);
}
