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

    void updatePageView(@Param("id") Integer id);

    Post selectPrevPost(@Param("id") Integer id);

    Post selectNextPost(@Param("id") Integer id);
}
