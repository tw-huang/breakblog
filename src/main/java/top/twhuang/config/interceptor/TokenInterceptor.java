package top.twhuang.config.interceptor;

import com.auth0.jwt.interfaces.Claim;
import lombok.extern.slf4j.Slf4j;
import top.twhuang.config.exception.TokenException;
import top.twhuang.util.JwtUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: tw.huang
 * @DateTime: 2020-08-01 22:28
 * @Description: Token拦截器
 */
@Configuration
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Token");
        if (token == null) {
            log.error("Token is null");
            throw new TokenException("Token is null");
        }
        Map<String, Claim> stringClaimMap = JwtUtil.verifyToken(token);
        if (stringClaimMap == null) {
            log.error("Token error");
            throw new TokenException("Token error");
        }
        return true;
    }
}
