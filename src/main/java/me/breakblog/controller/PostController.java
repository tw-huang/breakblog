package me.breakblog.controller;

//import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.breakblog.entity.Category;
import me.breakblog.entity.Post;
import me.breakblog.service.CategoryService;
import me.breakblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class PostController {
    private PostService postService;
    private CategoryService categoryService;

    @Autowired
    public PostController(PostService postService, CategoryService categoryService) {
        this.postService = postService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/post/new", method = RequestMethod.GET)
    public String newPostGet(Model model) {
        List<Category> categories = categoryService.getList();
        model.addAttribute("categories", categories);
        return "admin/newPost";
    }

    @RequestMapping(value = "/post/new", method = RequestMethod.POST)
    public String newPostPost(Post post, Model model) {
        post.setTimestamp(new Timestamp(new java.util.Date().getTime()));
        boolean save = postService.save(post);
        if (save) {
            return "redirect:/admin/post/manage";
        }
        model.addAttribute("msg", "New Post Error！");
        return "forward:/admin/post/manage";
    }

    @RequestMapping(value = "/post/edit/{id}", method = RequestMethod.GET)
    public String editPostGet(@PathVariable("id") int id, Model model) {
        Post post = postService.getById(id);
        List<Category> categories = categoryService.getList();
        model.addAttribute("post", post);
        model.addAttribute("categories", categories);
        return "admin/editPost";
    }

    @RequestMapping(value = "/post/edit/{id}", method = RequestMethod.POST)
    public String editPostPost(@PathVariable("id") int id, Post post, Model model) {
        post.setId(id);
        boolean update = postService.updateById(post);
        if (update) {
            return "redirect:/admin/post/manage";
        }
        model.addAttribute("msg", "Edit Post Error！");
        return "forward:/admin/post/manage";
    }

    @RequestMapping(value = "/post/delete/{id}", method = RequestMethod.POST)
    public String deletePostPost(@PathVariable("id") int id, Model model) {
        boolean remove = postService.removeById(id);
        if (remove) {
            return "redirect:/admin/post/manage";
        }
        model.addAttribute("msg", "Delete Error！");
        return "forward:/admin/post/manage";
    }

    @RequestMapping(value = "/post/manage", method = {RequestMethod.GET, RequestMethod.POST})
    public String managePost(@RequestParam(name = "page", required = true, defaultValue = "1") int page,
                             @RequestParam(name = "size", required = true, defaultValue = "15") int size, Model model) {
        Page postPage = postService.getPage(page, size);
        model.addAttribute("page", postPage);
        return "admin/managePost";
    }
}
