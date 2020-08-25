package me.breakblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.breakblog.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: tw.huang
 * @DateTime: 2020-07-19 2:53
 * @Description: TODO
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
