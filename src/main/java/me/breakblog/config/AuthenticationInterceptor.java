package me.breakblog.config;

import com.auth0.jwt.interfaces.Claim;
import me.breakblog.util.JwtUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author: tw.huang
 * @DateTime: 2020-08-01 22:28
 * @Description: 登录拦截器
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token == null) {
            throw new RuntimeException("无Token，请登录");
        }
        Map<String, Claim> stringClaimMap = JwtUtil.verifyToken(token);
        if (stringClaimMap == null) {
            throw new RuntimeException("Token不正确");
        }
        return true;
    }
}
