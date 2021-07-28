package top.twhuang.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: tw.huang
 * @DateTime: 2020-07-19 3:54
 * @Description: MybatisPlus配置
 */
@Configuration
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        //解决 mybatis-plus 分页total获取不到的问题
        return new PaginationInterceptor();
    }
}
