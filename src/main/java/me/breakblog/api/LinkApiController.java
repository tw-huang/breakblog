package me.breakblog.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.breakblog.dto.PageDTO;
import me.breakblog.entity.Link;
import me.breakblog.service.LinkService;
import me.breakblog.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: tw.huang
 * @DateTime: 2020-08-02 10:37
 * @Description: Api控制器
 */
@RestController
@RequestMapping("/api/v1")
public class LinkApiController {

    @Autowired
    private LinkService linkService;


    @GetMapping("/links")
    public Result links(PageDTO pageDTO) {
        Page<Link> page = linkService.page(new Page<>(pageDTO.getPageNum(), pageDTO.getPageSize()));
        return Result.success(page);
    }

    @GetMapping("/link/{id}")
    public Result getLink(@PathVariable Integer id) {
        Link link = linkService.getById(id);
        return Result.success(link);
    }

    @PostMapping("/link")
    public Result postLink(Link link) {
        boolean save = linkService.save(link);
        if (save) {
            return Result.success();
        }
        return Result.failure();
    }

    @PutMapping("/link")
    public Result putLink(Link link) {
        boolean update = linkService.updateById(link);
        if (update) {
            return Result.success();
        }
        return Result.failure();
    }

    @DeleteMapping("/link/{id}")
    public Result deleteLink(@PathVariable String id) {
        boolean remove = linkService.removeById(id);
        if (remove) {
            return Result.success();
        }
        return Result.failure();
    }
}
