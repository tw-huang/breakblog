package top.twhuang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.twhuang.entity.Link;

import java.util.List;

public interface LinkService extends IService<Link> {

    /**
     * 获取友链
     *
     * @return List
     */
    List<Link> getList();

    /**
     * 清空缓存
     */
    void cacheEvict();

}
