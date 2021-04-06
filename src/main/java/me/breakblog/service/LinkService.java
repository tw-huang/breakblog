package me.breakblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.breakblog.entity.Link;

import java.util.List;

public interface LinkService extends IService<Link> {

    List<Link> getList();

}
