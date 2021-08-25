package top.twhuang.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;


import java.util.Date;

/**
 * @Author: tw.huang
 * @DateTime: 2020-08-02 11:47
 * @Description: MybatisPlus 字段自动填充功能
 */
@Slf4j
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.fillStrategy(metaObject, "createTime", new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 起始版本 3.3.0(推荐使用)
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        this.fillStrategy(metaObject, "updateTime", new Date());
    }
}
