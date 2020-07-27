package me.breakblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.breakblog.entity.Category;
import me.breakblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/category/new", method = RequestMethod.GET)
    public String newCategoryGet() {
        return "admin/newCategory";
    }

    @RequestMapping(value = "/category/new", method = RequestMethod.POST)
    public String newCategoryPost(Category category, Model model) {
        QueryWrapper<Category> qw = new QueryWrapper<>();
        Category hasCategory = categoryService.getOne(qw.eq("name", category.getName()));
        if (hasCategory == null) {
            boolean save = categoryService.save(category);
            if (save) {
                return "redirect:/admin/category/manage";
            }
            model.addAttribute("msg", "New Category Error！");
        } else {
            model.addAttribute("msg", "Already Has This Category！");
        }
        return "forward:/admin/category/manage";
    }

    @RequestMapping(value = "/category/edit/{id}", method = RequestMethod.GET)
    public String editCategoryGet(@PathVariable("id") int id, Model model) {
        Category category = categoryService.getById(id);
        model.addAttribute("category", category);
        return "admin/editCategory";
    }

    @RequestMapping(value = "/category/edit/{id}", method = RequestMethod.POST)
    public String editCategoryPost(@PathVariable("id") int id, Category category, Model model) {
        category.setId(id);
        boolean update = categoryService.updateById(category);
        if (update) {
            return "redirect:/admin/category/manage";
        }
        model.addAttribute("msg", "Edit Category Error！");
        return "forward:/admin/category/manage";
    }

    @RequestMapping(value = "/category/delete/{id}", method = RequestMethod.POST)
    public String deleteCategoryPost(@PathVariable("id") int id, Model model) {
        boolean remove = categoryService.removeById(id);
        if (remove) {
            return "redirect:/admin/category/manage";
        }
        model.addAttribute("msg", "Delete Error！");
        return "forward:/admin/category/manage";
    }

    @RequestMapping(value = "/category/manage", method = {RequestMethod.GET, RequestMethod.POST})
    public String manageLink(Model model) {
        List<Category> categories = categoryService.getList();
        model.addAttribute("categories", categories);
        return "admin/manageCategory";
    }
}