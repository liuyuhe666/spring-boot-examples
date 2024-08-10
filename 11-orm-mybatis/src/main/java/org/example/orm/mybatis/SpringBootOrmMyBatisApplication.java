package org.example.orm.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"org.example.orm.mybatis.mapper"})
@SpringBootApplication
public class SpringBootOrmMyBatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootOrmMyBatisApplication.class, args);
    }
}
