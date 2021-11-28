package top.twhuang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.twhuang.dto.PageDTO;
import top.twhuang.entity.Category;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface CategoryService extends IService<Category> {

    /**
     * 获取文章分类
     *
     * @return List
     */
    List<Category> getList();

    /**
     * 分类数量
     */
    Integer getCategories();

    /**
     * 后台分类
     * @param pageDTO
     * @return
     */
    Map getPage(PageDTO pageDTO);

    /**
     *
     * @return
     */
    Map<String, Object> categoryReport();

    /**
     * 清空缓存
     */
    void cacheEvict();
}
