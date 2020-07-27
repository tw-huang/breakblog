package me.breakblog.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.breakblog.entity.Admin;
import me.breakblog.mapper.AdminMapper;
import me.breakblog.service.AdminService;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
