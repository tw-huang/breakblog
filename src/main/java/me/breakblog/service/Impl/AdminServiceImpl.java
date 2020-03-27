package me.breakblog.service.Impl;

import me.breakblog.dao.AdminDao;
import me.breakblog.entity.Admin;
import me.breakblog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao;

    @Autowired
    public AdminServiceImpl(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public Admin findAdmin() {
        return adminDao.findAdmin();
    }

    @Override
    public int updateAdmin(Admin admin) {
        return adminDao.updateAdmin(admin);
    }
}
