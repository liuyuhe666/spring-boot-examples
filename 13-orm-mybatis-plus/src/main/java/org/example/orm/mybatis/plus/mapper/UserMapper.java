package org.example.orm.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.orm.mybatis.plus.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends BaseMapper<User> {
}
