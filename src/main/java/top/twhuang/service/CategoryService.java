package top.twhuang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.twhuang.dto.PageDTO;
import top.twhuang.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService extends IService<Category> {

    List<Category> getList();

    Map getPage(PageDTO pageDTO);

    Map<String, Object> categoryReport();

    void cacheEvict();
}
