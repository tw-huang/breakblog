package top.twhuang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.twhuang.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: tw.huang
 * @DateTime: 2020-07-19 2:53
 * @Description: TODO
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    List<Map> categoryReport();

    /**
     * 获取文章分类
     *
     * @return List
     */
    List<Category> getList();

    /**
     * 分类数量
     */
    Integer getCategories();

}
