package me.breakblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.breakblog.entity.Admin;

public interface AdminService extends IService<Admin> {

    Admin getAdminByUsername(String username);

}
