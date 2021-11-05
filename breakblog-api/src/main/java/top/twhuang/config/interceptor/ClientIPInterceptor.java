package top.twhuang.config.interceptor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import top.twhuang.util.IPUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: tw.huang
 * @DateTime: 2021-11-005 22:38
 * @Description: 客户端IP
 */
@Configuration
@Slf4j
@AllArgsConstructor
public class ClientIPInterceptor implements HandlerInterceptor {

    private final RedisTemplate<String, String> redisTemplate;

    public final static String CLIENT_IP_SET = "client_ip_set";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String ipAddress = IPUtil.getIpAddress(request);
        log.info("Client IP address ---> " + ipAddress);
        redisTemplate.opsForSet().add(CLIENT_IP_SET, ipAddress);
        return true;
    }
}
