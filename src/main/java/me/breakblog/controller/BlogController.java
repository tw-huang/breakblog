package me.breakblog.controller;

import com.github.pagehelper.PageInfo;
import me.breakblog.entity.*;
import me.breakblog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class BlogController {
    private LinkService linkService;
    private CategoryService categoryService;
    private PostService postService;
    private AdminService adminService;
    private CommentService commentService;

    @Autowired
    public BlogController(LinkService linkService, CategoryService categoryService, PostService postService,
                          AdminService adminService, CommentService commentService) {
        this.linkService = linkService;
        this.categoryService = categoryService;
        this.postService = postService;
        this.adminService = adminService;
        this.commentService = commentService;
    }

    @ModelAttribute
    public void baseModel(Model model) {
        List<Link> links = linkService.findAll();
        List<Category> categories = categoryService.findAll();
        Admin admin = adminService.findAdmin();
        model.addAttribute("links", links);
        model.addAttribute("categories", categories);
        model.addAttribute("blogTitle", admin.getBlogTitle());
        model.addAttribute("blogSubtitle", admin.getBlogSubtitle());
        model.addAttribute("blogAuthor", admin.getName());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size, Model model) {
        List<Post> posts = postService.findAll(page, size);
        PageInfo<Post> pageInfo = new PageInfo<>(posts);
        model.addAttribute("posts", posts);
        model.addAttribute("page", pageInfo);
        return "blog/index";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Model model) {
        Admin admin = adminService.findAdmin();
        model.addAttribute("about", admin.getAbout());
        return "blog/about";
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public String post(@PathVariable("id") int id, Model model,
                       @RequestParam(name = "reply", required = false) Integer rid,
                       @RequestParam(name = "author", required = false) String author,
                       @RequestParam(name = "page", defaultValue = "1") int page,
                       @RequestParam(name = "size", defaultValue = "10") int size) {
        Post post = postService.findById(id);
        List<Comment> comments = commentService.findAllByPostId(id, page, size);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        if (rid != null && author != null) {
            model.addAttribute("replyId", rid);
            model.addAttribute("replyAuthor", author);
        }
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        model.addAttribute("page", pageInfo);
        return "blog/post";
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public String category(
            @PathVariable("id") int id,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size, Model model) {
        List<Post> posts = postService.findByCategoryId(id, page, size);
        PageInfo<Post> pageInfo = new PageInfo<>(posts);
        Category categoryById = categoryService.findById(id);
        model.addAttribute("posts", posts);
        model.addAttribute("page", pageInfo);
        model.addAttribute("categoryById", categoryById);
        return "blog/category";
    }

    @RequestMapping(value = "/admin/login", method = RequestMethod.GET)
    public String login(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            return "redirect:/";
        }
        return "blog/login";
    }
}

