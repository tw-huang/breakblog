package me.breakblog.controller;

import me.breakblog.entity.Admin;
import me.breakblog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Admin admin, HttpSession session, Model model) {
        Admin adminDb = adminService.findAdmin();
        if (admin.getUsername().equals(adminDb.getUsername()) && admin.getPassword().equals(adminDb.getPassword())) {
            session.setAttribute("admin", adminDb);
            return "redirect:/";
        }
        model.addAttribute("msg", "Login failed, please try again!");
        return "blog/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("admin");
        return "redirect:/";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String settingsGet() {
        return "admin/settings";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.POST)
    public String settingsPost(Admin admin, Model model, HttpSession session) {
        int i = adminService.updateAdmin(admin);
        if (i == 1) {
            session.removeAttribute("admin");
            return "redirect:/about";
        }
        model.addAttribute("msg", "Settings error!");
        return "admin/settings";
    }
}
