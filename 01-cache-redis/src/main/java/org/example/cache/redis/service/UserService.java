package org.example.cache.redis.service;

import org.example.cache.redis.entity.UserEntity;

public interface UserService {
    /**
     * 保存或修改用户
     *
     * @param user 用户对象
     * @return 用户对象
     */
    UserEntity saveOrUpdate(UserEntity user);

    /**
     * 查找用户
     *
     * @param id 主键 id
     * @return 用户对象
     */
    UserEntity findById(Long id);

    /**
     * 删除用户
     *
     * @param id 主键 id
     */
    void deleteById(Long id);
}
