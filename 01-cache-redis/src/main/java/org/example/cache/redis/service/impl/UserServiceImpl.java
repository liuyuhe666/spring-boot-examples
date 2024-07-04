package org.example.cache.redis.service.impl;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.example.cache.redis.entity.UserEntity;
import org.example.cache.redis.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    /**
     * 模拟数据库
     */
    private static final Map<Long, UserEntity> userMap = Maps.newConcurrentMap();

    /*
      初始化数据
     */
    static {
        userMap.put(1L, new UserEntity(1L, "user1"));
        userMap.put(2L, new UserEntity(2L, "user2"));
        userMap.put(3L, new UserEntity(3L, "user3"));
        userMap.put(4L, new UserEntity(4L, "user4"));
    }

    @CachePut(value = "user", key = "#user.id")
    @Override
    public UserEntity saveOrUpdate(UserEntity user) {
        userMap.put(user.getId(), user);
        log.info("保存用户 user: {}", user);
        return user;
    }

    @Cacheable(value = "user", key = "#id")
    @Override
    public UserEntity findById(Long id) {
        log.info("查询用户 id: {}", id);
        return userMap.get(id);
    }

    @CacheEvict(value = "user", key = "#id")
    @Override
    public void deleteById(Long id) {
        userMap.remove(id);
        log.info("删除用户 id: {}", id);
    }
}
