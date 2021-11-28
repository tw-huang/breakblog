package top.twhuang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import top.twhuang.dto.PageDTO;
import top.twhuang.entity.Link;
import top.twhuang.service.LinkService;
import top.twhuang.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;


/**
 * @Author: tw.huang
 * @DateTime: 2020-08-02 10:37
 * @Description: Api控制器
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class LinkApiController {

    private LinkService linkService;

    @GetMapping("/links")
    public Result links(PageDTO pageDTO) {
        QueryWrapper<Link> qw = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(pageDTO.getKeyword())) {
            qw.like("name", pageDTO.getKeyword());
        }
        Page<Link> page = linkService.page(new Page<>(pageDTO.getPageNum(), pageDTO.getPageSize()), qw);
        return Result.success(page);
    }

    @GetMapping("/link/{id}")
    public Result getLink(@PathVariable Integer id) {
        Link link = linkService.getById(id);
        return Result.success(link);
    }

    @PostMapping("/link")
    public Result postLink(@RequestBody Link link) {
        boolean save = linkService.save(link);
        if (save) {
            linkService.cacheEvict();
            return Result.success();
        }
        return Result.failure();
    }

    @PutMapping("/link")
    public Result putLink(@RequestBody Link link) {
        boolean update = linkService.updateById(link);
        if (update) {
            linkService.cacheEvict();
            return Result.success();
        }
        return Result.failure();
    }

    @DeleteMapping("/link/{id}")
    public Result deleteLink(@PathVariable Integer id) {
        boolean remove = linkService.removeById(id);
        if (remove) {
            linkService.cacheEvict();
            return Result.success();
        }
        return Result.failure();
    }
}
