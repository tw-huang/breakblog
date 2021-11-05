package top.twhuang;

import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.Set;

@SpringBootTest
public class RedisTest {

    public final static String POST_ID_ZSET = "post_id_zset::";

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    void redis() {
        Set<String> range = redisTemplate.opsForZSet().reverseRange(POST_ID_ZSET + DateUtil.format(new Date(), "yyyy-MM"), 0, -1);
        assert range != null;
        range.forEach(x->{
            System.out.println(x);
        });
    }
}
