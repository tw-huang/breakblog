package top.twhuang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.twhuang.entity.Admin;

public interface AdminService extends IService<Admin> {

    Admin getAdminByUsername(String username);

}
