package top.twhuang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.twhuang.dto.PageDTO;
import top.twhuang.entity.Post;

import java.util.Map;

public interface PostService extends IService<Post> {

    Map getPage(PageDTO pageDTO);

    void updatePageView(Integer id);

    /**
     * 获取前一篇文章
     *
     * @param id 文章ID
     * @return
     */
    Post getPrevPost(Integer id);

    /**
     * 获取后一篇文章
     *
     * @param id 文章ID
     * @return
     */
    Post getNextPost(Integer id);

}
