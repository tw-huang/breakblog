package me.breakblog.dao;

import me.breakblog.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CategoryDao {

    List<Category> findAll();

    Category findById(int id);

    int addCategory(Category category);

    int updateCategory(Category category);

    int deleteCategory(int id);

    Category findByName(String name);
}
