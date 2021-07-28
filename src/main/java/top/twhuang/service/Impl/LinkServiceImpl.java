package top.twhuang.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import top.twhuang.entity.Link;
import top.twhuang.mapper.LinkMapper;
import top.twhuang.service.LinkService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
@Slf4j
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Resource
    private LinkMapper linkMapper;

    @Override
    @Cacheable(cacheNames = "link", key = "'links'")
    public List<Link> getList() {
        return linkMapper.selectList(new QueryWrapper<Link>().lambda().orderByAsc(Link::getId));
    }

    @Override
    @CacheEvict(cacheNames = "link", key = "'links'")
    public void cacheEvict() {
        log.info("links 缓存清空");
    }

}
