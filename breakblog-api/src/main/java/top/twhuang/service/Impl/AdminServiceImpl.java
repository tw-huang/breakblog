package top.twhuang.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import top.twhuang.entity.Admin;
import top.twhuang.mapper.AdminMapper;
import top.twhuang.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.twhuang.vo.InfoVO;
import top.twhuang.vo.StatisticVO;


@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin getAdminByUsername(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().lambda().eq(Admin::getUsername, username));
    }

    @Override
    public StatisticVO getBlogStatistic() {
        return adminMapper.getBlogStatistic();
    }

    @Override
    @Cacheable(cacheNames = "blogInfo", key = "'blogInfo'")
    public InfoVO getBlogInfo() {
        InfoVO vo = new InfoVO();
        Admin admin = adminMapper.selectById(1);
        BeanUtils.copyProperties(admin, vo);
        return vo;
    }

    @Override
    @CacheEvict(cacheNames = "blogInfo", key = "'blogInfo'")
    public void cacheEvict() {
        log.info("blogInfo 缓存清空");
    }
}
