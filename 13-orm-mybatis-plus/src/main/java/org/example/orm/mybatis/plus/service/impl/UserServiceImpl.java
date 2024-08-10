package org.example.orm.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.orm.mybatis.plus.entity.User;
import org.example.orm.mybatis.plus.mapper.UserMapper;
import org.example.orm.mybatis.plus.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
