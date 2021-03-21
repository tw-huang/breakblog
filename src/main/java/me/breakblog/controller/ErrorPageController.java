package me.breakblog.controller;

import me.breakblog.entity.Category;
import me.breakblog.entity.Link;
import me.breakblog.service.CategoryService;
import me.breakblog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/error")
public class ErrorPageController {
    private LinkService linkService;
    private CategoryService categoryService;

    @Autowired
    public ErrorPageController(LinkService linkService, CategoryService categoryService) {
        this.linkService = linkService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/{code}")
    public String getErrorPath(@PathVariable int code, Model model) {
        List<Link> links = linkService.list();
        List<Category> categories = categoryService.getList();
        model.addAttribute("links", links);
        model.addAttribute("categories", categories);
        return "error/" + code;
    }
}
