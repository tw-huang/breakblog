package top.twhuang.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import top.twhuang.dto.PageDTO;
import top.twhuang.entity.Category;
import top.twhuang.entity.Post;
import top.twhuang.mapper.CategoryMapper;
import top.twhuang.service.CategoryService;
import top.twhuang.service.PostService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private PostService postService;

    @Override
    @Cacheable(cacheNames = "category", key = "'categories'")
    public List<Category> getList() {
        return categoryMapper.getList();
    }

    @Override
    public Integer getCategories() {
        return categoryMapper.getCategories();
    }

    @Override
    public Map getPage(PageDTO pageDTO) {
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Category> qw = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(pageDTO.getKeyword())) {
            qw.like("name", pageDTO.getKeyword());
        }
        Page<Category> page = categoryMapper.selectPage(new Page<>(pageDTO.getPageNum(), pageDTO.getPageSize()), qw);
        for (Category c : page.getRecords()) {
            HashMap<String, Object> hashMap = new HashMap<>();
            int views;
            int counts = postService.count(new QueryWrapper<Post>().eq("category_id", c.getId()));
            List<Post> postList = postService.list(new QueryWrapper<Post>().eq("category_id", c.getId()));
            views = postList.stream().mapToInt(Post::getPageView).sum();
            hashMap.put("id", c.getId());
            hashMap.put("categoryName", c.getName());
            hashMap.put("counts", counts);
            hashMap.put("views", views);
            list.add(hashMap);
        }
        map.put("list", list);
        map.put("total", page.getTotal());
        map.put("size", page.getSize());
        return map;
    }

    @Override
    public Map<String, Object> categoryReport() {
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<BigDecimal> clicks = new ArrayList<>();
        List<Map> maps = categoryMapper.categoryReport();
        for (Map m : maps) {
            names.add((String) m.get("name"));
            clicks.add((BigDecimal) m.get("clicks"));
        }
        map.put("name", names);
        map.put("click", clicks);
        return map;
    }

    @Override
    @CacheEvict(cacheNames = "category", key = "'categories'")
    public void cacheEvict() {
        log.info("categories 缓存清空");
    }
}