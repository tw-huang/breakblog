package me.breakblog.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.breakblog.entity.Admin;
import me.breakblog.entity.Comment;
import me.breakblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/comment/new/{pid}", method = RequestMethod.POST)
    public String newCommentPost(@PathVariable("pid") int pid, Comment comment, HttpSession session,
                                 @RequestParam(name = "reply", required = false) Integer rid) {
        comment.setPostId(pid);
        comment.setTimestamp(new Timestamp(new java.util.Date().getTime()));
        if ((rid != null)) {
            comment.setRepliedId(rid);
        }
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            comment.setFromAdmin(0);
            comment.setReviewed(0);

        } else {
            comment.setAuthor(admin.getName());
            comment.setEmail(admin.getEmail());
            comment.setFromAdmin(1);
            comment.setReviewed(1);
        }
        commentService.save(comment);
        return "redirect:/post/" + pid;
    }

    @RequestMapping(value = "/admin/comment/edit/{id}",method = RequestMethod.GET)
    public String editCommentGet(@PathVariable("id") int id, @RequestParam(name = "read") Integer reviewed, Model model) {
        reviewed = reviewed == 0 ? 1 : 0;
        Comment comment = new Comment();
        comment.setId(id);
        comment.setReviewed(reviewed);
        boolean update = commentService.updateById(comment);
        if (update) {
            return "redirect:/admin/comment/manage";
        }
        model.addAttribute("msg", "Update Error！");
        return "forward:/admin/comment/manage";
    }


    @RequestMapping(value = "/admin/comment/delete/{id}")
    public String deleteCommentPost(@PathVariable("id") int id, Model model) {
        boolean remove = commentService.removeById(id);
        if (remove) {
            return "redirect:/admin/comment/manage";
        }
        model.addAttribute("msg", "Delete Error！");
        return "forward:/admin/comment/manage";
    }

    @RequestMapping(value = "/admin/comment/manage", method = {RequestMethod.GET, RequestMethod.POST})
    public String manageComment(@RequestParam(name = "page", defaultValue = "1") int page,
                                @RequestParam(name = "size", defaultValue = "15") int size, Model model) {
        Page commentPage = commentService.getPage(page, size);
        model.addAttribute("page", commentPage);
        return "admin/manageComment";
    }

}
