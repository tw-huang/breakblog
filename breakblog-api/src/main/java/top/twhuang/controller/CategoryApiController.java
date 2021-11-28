package top.twhuang.controller;

import lombok.AllArgsConstructor;
import top.twhuang.dto.PageDTO;
import top.twhuang.entity.Category;
import top.twhuang.service.CategoryService;
import top.twhuang.util.Result;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: tw.huang
 * @DateTime: 2020-08-02 10:27
 * @Description: Api控制器
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CategoryApiController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public Result categories(PageDTO pageDTO) {
        Map map = categoryService.getPage(pageDTO);
        return Result.success(map);
    }

    @GetMapping("/categories/list")
    public Result categories() {
        List<Category> categoryList = categoryService.list();
        ArrayList<Map> list = new ArrayList<>();
        categoryList.forEach(x -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", x.getId());
            map.put("name", x.getName());
            list.add(map);
        });
        return Result.success(list);
    }

    @GetMapping("/category/{id}")
    public Result getCategory(@PathVariable Integer id) {
        Category category = categoryService.getById(id);
        return Result.success(category);
    }

    @PostMapping("/category")
    public Result postCategory(@RequestBody Category category) {
        boolean save = categoryService.save(category);
        if (save) {
            categoryService.cacheEvict();
            return Result.success();
        }
        return Result.failure();
    }

    @PutMapping("/category")
    public Result putCategory(@RequestBody Category category) {
        boolean update = categoryService.updateById(category);
        if (update) {
            categoryService.cacheEvict();
            return Result.success();
        }
        return Result.failure();
    }

    @DeleteMapping("/category/{id}")
    public Result deleteCategory(@PathVariable Integer id) {
        boolean remove = categoryService.removeById(id);
        if (remove) {
            categoryService.cacheEvict();
            return Result.success();
        }
        return Result.failure();
    }

    @GetMapping("/category/report")
    public Result categoryReport(){
        Map<String, Object> map = categoryService.categoryReport();
        return Result.success(map);
    }
}
