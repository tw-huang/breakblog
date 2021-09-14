package top.twhuang.controller;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import top.twhuang.entity.Admin;
import top.twhuang.service.AdminService;
import top.twhuang.util.Result;

/**
 * @Author: tw.huang
 * @DateTime: 2021-09-14 15:54
 * @Description: Api控制器
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AdminApiController {

    private AdminService adminService;

    @GetMapping("/blog/info")
    public Result blogInfo() {
        return Result.success(adminService.getBlogInfo());
    }

    @GetMapping("/blog/statistic")
    public Result blogStatistic() {
        return Result.success(adminService.getBlogStatistic());
    }

    @GetMapping("/blog/about")
    public Result blogAbout() {
        Admin admin = adminService.getById(1);
        if (ObjectUtils.isNotEmpty(admin)) {
            admin.setUsername(null);
            admin.setPassword(null);
            admin.setPhone(null);
            return Result.success(admin);
        }
        return Result.failure();
    }
}
