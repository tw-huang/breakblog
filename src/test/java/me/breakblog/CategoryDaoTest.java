package me.breakblog;

import me.breakblog.dao.CategoryDao;
import me.breakblog.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CategoryDaoTest {
    @Autowired
    private CategoryDao categoryDao;

    @Test
    void findAllTest() {
        List<Category> all = categoryDao.findAll();
        for (Category c : all) {
            System.out.println(c);
            System.out.println(c.getPosts().size());
        }
    }
}
