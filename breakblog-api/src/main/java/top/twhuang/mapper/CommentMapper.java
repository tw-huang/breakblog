package top.twhuang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import top.twhuang.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: tw.huang
 * @DateTime: 2020-07-19 3:25
 * @Description: TODO
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    IPage<Comment> selectBlogCommentPage(IPage<Comment> page, @Param("postId") Integer postId);
}
