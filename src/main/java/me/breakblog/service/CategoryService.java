package me.breakblog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.breakblog.dto.PageDTO;
import me.breakblog.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService extends IService<Category> {

    List<Category> getList();

    Map getPage(PageDTO pageDTO);

    Map<String, Object> categoryReport();
}
