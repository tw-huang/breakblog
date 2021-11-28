package top.twhuang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.twhuang.entity.Admin;
import top.twhuang.vo.InfoVO;
import top.twhuang.vo.StatisticVO;

public interface AdminService extends IService<Admin> {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    Admin getAdminByUsername(String username);

    /**
     * 获取博客（文章/分类/浏览量）信息
     *
     * @return StatisticVO
     */
    StatisticVO getBlogStatistic();

    /**
     * 获取博客（标题/副标题/作者/头像/邮件）
     *
     * @return InfoVO
     */
    InfoVO getBlogInfo();

    /**
     * 清空缓存
     */
    void cacheEvict();


}
