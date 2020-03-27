package me.breakblog.service.Impl;

import me.breakblog.dao.CategoryDao;
import me.breakblog.entity.Category;
import me.breakblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryDao.findById(id);
    }

    @Override
    public Category findByName(String name) {
        return categoryDao.findByName(name);
    }

    @Override
    public int addCategory(Category category) {
        return categoryDao.addCategory(category);
    }

    @Override
    public int updateCategory(Category category) {
        return categoryDao.updateCategory(category);
    }

    @Override
    public int deleteCategory(int id) {
        return categoryDao.deleteCategory(id);
    }
}
