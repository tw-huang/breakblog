package me.breakblog.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.breakblog.dto.LoginDTO;
import me.breakblog.entity.Admin;
import me.breakblog.service.AdminService;
import me.breakblog.util.JwtUtil;
import me.breakblog.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
