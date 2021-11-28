package top.twhuang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.twhuang.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import top.twhuang.vo.StatisticVO;

/**
 * @Author: tw.huang
 * @DateTime: 2020-07-19 2:08
 * @Description: TODO
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 获取博客（文章/分类/浏览量）信息
     *
     * @return StatisticVO
     */
    StatisticVO getBlogStatistic();
}
