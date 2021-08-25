package top.twhuang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.twhuang.entity.Link;

import java.util.List;

public interface LinkService extends IService<Link> {

    List<Link> getList();

    void cacheEvict();

}
