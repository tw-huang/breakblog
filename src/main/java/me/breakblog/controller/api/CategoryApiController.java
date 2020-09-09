package me.breakblog.controller.api;

import me.breakblog.dto.PageDTO;
import me.breakblog.entity.Category;
import me.breakblog.service.CategoryService;
import me.breakblog.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: tw.huang
 * @DateTime: 2020-08-02 10:27
 * @Description: Api控制器
 */
@RestController
@RequestMapping("/api/v1")
public class CategoryApiController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/categories")
    public Result categories(PageDTO pageDTO) {
        Map map = categoryService.getPage(pageDTO);
        return Result.success(map);
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
            return Result.success();
        }
        return Result.failure();
    }

    @PutMapping("/category")
    public Result putCategory(@RequestBody Category category) {
        boolean update = categoryService.updateById(category);
        if (update) {
            return Result.success();
        }
        return Result.failure();
    }

    @DeleteMapping("/category/{id}")
    public Result deleteCategory(@PathVariable Integer id) {
        boolean remove = categoryService.removeById(id);
        if (remove) {
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
