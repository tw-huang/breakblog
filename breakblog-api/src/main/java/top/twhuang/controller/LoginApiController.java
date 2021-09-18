package top.twhuang.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import top.twhuang.dto.LoginDTO;
import top.twhuang.entity.Admin;
import top.twhuang.service.AdminService;
import top.twhuang.util.JwtUtil;
import top.twhuang.util.Result;
import top.twhuang.vo.MenuVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tw.huang
 * @DateTime: 2020-08-02 3:01
 * @Description: TODO
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class LoginApiController {

    private AdminService adminService;

    @PostMapping("/login")
    public Result login(@RequestBody @Validated LoginDTO loginDTO) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Admin> qw = new QueryWrapper<>();
        qw.eq("username", loginDTO.getUsername()).eq("password", loginDTO.getPassword());
        Admin admin = adminService.getOne(qw);
        if (admin != null) {
            String token = JwtUtil.createToken(admin);
            map.put("token", token);
            return Result.success(map, "登录成功");
        }
        return Result.failure("用户名或密码不正确");
    }

    @GetMapping("/currentUser")
    public Result currentUser(HttpServletRequest request) {
        String userName = JwtUtil.getUserName(request);
        Admin admin = adminService.getAdminByUsername(userName);
        if (ObjectUtils.isNotEmpty(admin)) {
            return Result.success(admin, "当前用户");
        }
        return Result.failure("获取当前用户失败");
    }

    @GetMapping("/logout")
    public Result logout() {
        return Result.success();
    }

    @GetMapping("/menus")
    public Result menu() {
        //模拟菜单数据
        JSONArray jsonArray = new JSONArray();

        MenuVO category = new MenuVO();
        category.setId(1);
        category.setName("分类管理");
        category.setPath("category");
        category.setIcon("el-icon-collection");
        MenuVO categories = new MenuVO();
        categories.setId(101);
        categories.setName("分类列表");
        categories.setPath("categories");
        categories.setIcon("el-icon-arrow-right");
        ArrayList<MenuVO> categoryList = new ArrayList<>();
        categoryList.add(categories);
        category.setChildren(categoryList);

        MenuVO post = new MenuVO();
        post.setId(2);
        post.setName("文章管理");
        post.setPath("post");
        post.setIcon("el-icon-notebook-2");
        MenuVO posts = new MenuVO();
        posts.setId(201);
        posts.setName("文章列表");
        posts.setPath("posts");
        posts.setIcon("el-icon-arrow-right");
        MenuVO newPost = new MenuVO();
        newPost.setId(202);
        newPost.setName("编写文章");
        newPost.setPath("post");
        newPost.setIcon("el-icon-arrow-right");
        ArrayList<MenuVO> postList = new ArrayList<>();
        postList.add(posts);
        postList.add(newPost);
        post.setChildren(postList);

        MenuVO comment = new MenuVO();
        comment.setId(3);
        comment.setName("评论管理");
        comment.setPath("comment");
        comment.setIcon("el-icon-chat-line-square");
        MenuVO comments = new MenuVO();
        comments.setId(301);
        comments.setName("评论列表");
        comments.setPath("comments");
        comments.setIcon("el-icon-arrow-right");
        ArrayList<MenuVO> commentList = new ArrayList<>();
        commentList.add(comments);
        comment.setChildren(commentList);

        MenuVO link = new MenuVO();
        link.setId(4);
        link.setName("友链管理");
        link.setPath("link");
        link.setIcon("el-icon-link");
        MenuVO links = new MenuVO();
        links.setId(401);
        links.setName("友链列表");
        links.setPath("links");
        links.setIcon("el-icon-arrow-right");
        ArrayList<MenuVO> linkList = new ArrayList<>();
        linkList.add(links);
        link.setChildren(linkList);

        MenuVO report = new MenuVO();
        report.setId(5);
        report.setName("数据统计");
        report.setPath("report");
        report.setIcon("el-icon-data-analysis");
        MenuVO reports = new MenuVO();
        reports.setId(501);
        reports.setName("数据报告");
        reports.setPath("reports");
        reports.setIcon("el-icon-arrow-right");
        ArrayList<MenuVO> reportList = new ArrayList<>();
        reportList.add(reports);
        report.setChildren(reportList);

        MenuVO admin = new MenuVO();
        admin.setId(6);
        admin.setName("博客管理");
        admin.setPath("admin");
        admin.setIcon("el-icon-setting");
        MenuVO admins = new MenuVO();
        admins.setId(601);
        admins.setName("管理博客");
        admins.setPath("admin");
        admins.setIcon("el-icon-arrow-right");
        MenuVO about = new MenuVO();
        about.setId(602);
        about.setName("关于页面");
        about.setPath("about");
        about.setIcon("el-icon-arrow-right");
        ArrayList<MenuVO> adminList = new ArrayList<>();
        adminList.add(admins);
        adminList.add(about);
        admin.setChildren(adminList);

        jsonArray.add(category);
        jsonArray.add(post);
        jsonArray.add(comment);
        jsonArray.add(link);
        jsonArray.add(report);
        jsonArray.add(admin);

        return Result.success(jsonArray, "用户菜单");

    }


}
