package org.example.cache.redis;

import lombok.extern.slf4j.Slf4j;
import org.example.cache.redis.entity.UserEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Slf4j
public class RedisTest extends SpringBootCacheRedisApplicationTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Test
    public void get() {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(i -> executorService.execute(() -> stringRedisTemplate.opsForValue().increment("cnt", 1)));

        stringRedisTemplate.opsForValue().set("k1", "v1");
        String k1 = stringRedisTemplate.opsForValue().get("k1");
        log.debug("k1={}", k1);

        String key = "test:user:1";
        redisTemplate.opsForValue().set(key, new UserEntity(1L, "user1"));
        UserEntity user = (UserEntity) redisTemplate.opsForValue().get(key);
        log.debug("user={}", user);
    }
}
