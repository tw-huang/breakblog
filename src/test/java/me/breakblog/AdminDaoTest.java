package me.breakblog;

import me.breakblog.dao.AdminDao;
import me.breakblog.entity.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminDaoTest {
    @Autowired
    private AdminDao adminDao;

    @Test
    void findAdminTest() {
        Admin admin = adminDao.findAdmin();
        System.out.println(admin);
    }

    @Test
    void updateAdminTest() {
        Admin admin = new Admin();
        admin.setAbout("Hello World!");
        int i = adminDao.updateAdmin(admin);
        System.out.println(i);
    }
}
