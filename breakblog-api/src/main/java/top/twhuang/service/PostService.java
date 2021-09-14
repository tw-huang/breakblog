package top.twhuang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.twhuang.dto.PageDTO;
import top.twhuang.entity.Post;

import java.util.Map;

public interface PostService extends IService<Post> {

    Map getPage(PageDTO pageDTO);

    /**
     * 更新文章浏览量
     *
     * @param id 文章ID
     */
    void updatePageView(Integer id);

    /**
     * 获取前一篇文章
     *
     * @param id 文章ID
     * @return Post
     */
    Post getPrevPost(Integer id);

    /**
     * 获取后一篇文章
     *
     * @param id 文章ID
     * @return Post
     */
    Post getNextPost(Integer id);

    /**
     * 文章数量
     *
     * @return Integer
     */
    Integer getCountPages();

    /**
     * 文章总浏览量
     *
     * @return Integer
     */
    Integer getSumPageViews();
}
