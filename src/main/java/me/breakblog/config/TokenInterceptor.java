package me.breakblog.config;

import com.auth0.jwt.interfaces.Claim;
import me.breakblog.config.exception.CommonException;
import me.breakblog.config.exception.TokenException;
import me.breakblog.util.JwtUtil;
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
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Token");
        if (token == null) {
            throw new TokenException("Token is null");
        }
        Map<String, Claim> stringClaimMap = JwtUtil.verifyToken(token);
        if (stringClaimMap == null) {
            throw new CommonException("Token error");
        }
        return true;
    }
}
