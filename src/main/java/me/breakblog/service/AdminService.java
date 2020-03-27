package me.breakblog.service;

import me.breakblog.entity.Admin;

public interface AdminService {

    Admin findAdmin();

    int updateAdmin(Admin admin);
}
