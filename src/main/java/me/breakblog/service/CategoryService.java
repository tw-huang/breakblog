package me.breakblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.breakblog.entity.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {

    List<Category> getList();
}
