package org.example.cache.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.example.cache.redis.SpringBootCacheRedisApplicationTests;
import org.example.cache.redis.entity.UserEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UserServiceTest extends SpringBootCacheRedisApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void get() {
        // 模拟查询 id = 1 的用户
        UserEntity user1 = userService.findById(1L);
        log.debug("user1: {}", user1);

        // 再次查询
        UserEntity user2 = userService.findById(1L);
        log.debug("user2: {}", user2);
    }

    @Test
    public void getAfterSave() {
        userService.saveOrUpdate(new UserEntity(10L, "user10"));

        UserEntity user = userService.findById(10L);
        log.debug("user: {}", user);
    }

    @Test
    public void deleteUser() {
        // 查询
        userService.findById(1L);
        // 删除
        userService.deleteById(1L);
    }
}
