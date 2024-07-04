package org.example.cache.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 6809816592224543638L;
    /**
     * 主键 ID
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
}
