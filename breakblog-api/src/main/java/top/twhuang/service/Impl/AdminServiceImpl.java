package top.twhuang.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import top.twhuang.entity.Admin;
import top.twhuang.mapper.AdminMapper;
import top.twhuang.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.twhuang.service.CategoryService;
import top.twhuang.service.PostService;
import top.twhuang.vo.InfoVO;
import top.twhuang.vo.StatisticVO;


@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private PostService postService;
    @Autowired
    private CategoryService categoryService;

    @Override
    public Admin getAdminByUsername(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().lambda().eq(Admin::getUsername, username));
    }

    @Override
    public StatisticVO getBlogStatistic() {
        StatisticVO vo = new StatisticVO();
        vo.setPosts(postService.getCountPages());
        vo.setPageviews(postService.getSumPageViews());
        vo.setCategories(categoryService.getCategories());
        return vo;
    }

    @Override
    public InfoVO getBlogInfo() {
        InfoVO vo = new InfoVO();
        Admin admin = adminMapper.selectById(1);
        BeanUtils.copyProperties(admin, vo);
        return vo;
    }
}
