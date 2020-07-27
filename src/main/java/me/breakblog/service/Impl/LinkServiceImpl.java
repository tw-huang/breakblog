package me.breakblog.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.breakblog.entity.Link;
import me.breakblog.mapper.LinkMapper;
import me.breakblog.service.LinkService;
import org.springframework.stereotype.Service;


@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {
}
