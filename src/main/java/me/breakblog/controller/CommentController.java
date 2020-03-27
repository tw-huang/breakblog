package me.breakblog.controller;

import com.github.pagehelper.PageInfo;
import me.breakblog.dao.CommentDao;
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
    public String newCommentPost(@PathVariable("pid") int pid, Comment comment, Model model, HttpSession session,
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
        System.out.println(comment);
        int i = commentService.addComment(comment);
        System.out.println(i);
        return "redirect:/post/" + pid;
    }

    @RequestMapping(value = "/admin/comment/edit/{id}",method = RequestMethod.GET)
    public String editCommentGet(@PathVariable("id") int id, @RequestParam(name = "read") Integer reviewed, Model model) {
        reviewed = reviewed == 0 ? 1 : 0;
        int i = commentService.updateComment(id, reviewed);
        if (i == 1) {
            return "redirect:/admin/comment/manage";
        }
        model.addAttribute("msg", "Update Error！");
        return "forward:/admin/comment/manage";
    }


    @RequestMapping(value = "/admin/comment/delete/{id}")
    public String deleteCommentPost(@PathVariable("id") int id, Model model) {
        int i = commentService.deleteComment(id);
        if (i == 1) {
            return "redirect:/admin/comment/manage";
        }
        model.addAttribute("msg", "Delete Error！");
        return "forward:/admin/comment/manage";
    }

    @RequestMapping(value = "/admin/comment/manage", method = {RequestMethod.GET, RequestMethod.POST})
    public String manageComment(@RequestParam(name = "page", defaultValue = "1") int page,
                                @RequestParam(name = "size", defaultValue = "15") int size, Model model) {
        List<Comment> comments = commentService.findAll(page, size);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        model.addAttribute("comments", comments);
        model.addAttribute("page", pageInfo);
        return "admin/manageComment";
    }

}
