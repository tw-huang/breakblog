package me.breakblog.service;

import java.util.List;

import me.breakblog.entity.Category;

public interface CategoryService {

    List<Category> findAll();

    Category findById(int id);

    Category findByName(String name);

    int addCategory(Category category);

    int updateCategory(Category category);

    int deleteCategory(int id);
}
