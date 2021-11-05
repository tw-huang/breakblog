package top.twhuang.config;

import top.twhuang.config.interceptor.ClientIPInterceptor;
import top.twhuang.config.interceptor.CorsInterceptor;
import top.twhuang.config.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Autowired
    private CorsInterceptor corsInterceptor;

    @Autowired
    private ClientIPInterceptor clientIPInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 跨域拦截器需放在最上面
        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
        // 客户端IP拦截器
        registry.addInterceptor(clientIPInterceptor).addPathPatterns("/**");
        // Token拦截器
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/api/**")
                .excludePathPatterns("/api/login")
                .excludePathPatterns("/api/blog/**");
    }
}
