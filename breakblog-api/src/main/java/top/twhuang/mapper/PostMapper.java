package top.twhuang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.twhuang.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: tw.huang
 * @DateTime: 2020-07-19 2:54
 * @Description: TODO
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {

    /**
     * 更新文章浏览量
     *
     * @param id 文章ID
     */
    void updatePageView(@Param("id") Integer id);

    /**
     * 文章总浏览量
     *
     * @return Integer
     */
    Integer selectSumPageViews();

    /**
     * 文章数量
     *
     * @return Integer
     */
    Integer selectCountPages();

    /**
     * 根据文章ID选择前一文章
     *
     * @param id 文章ID
     * @return Post
     */
    Post selectPrevPost(@Param("id") Integer id);

    /**
     * 根据文章ID选择后一文章
     *
     * @param id 文章ID
     * @return Post
     */
    Post selectNextPost(@Param("id") Integer id);

}
