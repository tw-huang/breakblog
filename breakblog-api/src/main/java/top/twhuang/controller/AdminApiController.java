package top.twhuang.controller;

import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.twhuang.dto.AdminDTO;
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

    @PutMapping("/admin")
    public Result putLink(@RequestBody AdminDTO adminDTO) {
        if (StringUtils.isNotEmpty(adminDTO.getPassword()) && StringUtils.isNotEmpty(adminDTO.getNewPassword())) {
            Admin admin = adminService.getById(1);
            if (admin.getPassword().equals(adminDTO.getPassword())) {
                BeanUtil.copyProperties(adminDTO, admin);
                admin.setPassword(adminDTO.getNewPassword());
                boolean update = adminService.updateById(admin);
                if (update) {
                    adminService.cacheEvict();
                    return Result.success("修改密码成功！");
                } else {
                    return Result.failure("修改密码失败！");
                }
            }
            return Result.failure("原密码输入不正确！");
        }
        Admin admin = new Admin();
        BeanUtil.copyProperties(adminDTO, admin);
        admin.setPassword(null);
        boolean update = adminService.updateById(admin);
        if (update) {
            adminService.cacheEvict();
            return Result.success("修改成功！");
        }
        return Result.failure("修改失败！");
    }
}
