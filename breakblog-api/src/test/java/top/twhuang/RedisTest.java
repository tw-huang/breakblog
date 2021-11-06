package top.twhuang;

import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
public class RedisTest {

    public final static String POST_ID_ZSET = "post_id_zset::";

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    void redis() {
        Set<String> postHotSet = redisTemplate.opsForZSet().reverseRange(POST_ID_ZSET + DateUtil.format(new Date(), "yyyy-MM"), 0, 4);
        if (!Objects.isNull(postHotSet) && postHotSet.size() >= 5) {
            List<Integer> collect = postHotSet.stream().map(Integer::valueOf).collect(Collectors.toList());
            collect.forEach(x->{
                System.out.println(x);
            });

        }
    }
}
