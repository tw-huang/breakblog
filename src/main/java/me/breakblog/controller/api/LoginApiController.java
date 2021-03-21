package me.breakblog.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.breakblog.dto.LoginDTO;
import me.breakblog.entity.Admin;
import me.breakblog.service.AdminService;
import me.breakblog.util.JwtUtil;
import me.breakblog.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tw.huang
 * @DateTime: 2020-08-02 3:01
 * @Description: TODO
 */
@RestController
@RequestMapping("/api/v1")
public class LoginApiController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login/account")
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

    @GetMapping("/login/currentUser")
    public Result currentUser(HttpServletRequest request) {
        String userName = JwtUtil.getUserName(request);
        if (StringUtils.isNotEmpty(userName)) {
            Admin admin = adminService.getOne(new QueryWrapper<Admin>().lambda()
                    .eq(Admin::getUsername, userName));
            return Result.success(admin, "当前用户！");
        }
        return Result.failure("获取当前用户失败！");
    }

    @GetMapping("/login/outLogin")
    public Result logout(HttpServletRequest request) {
        return Result.success();
    }

}
