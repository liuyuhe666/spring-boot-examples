package org.example.ratelimit.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootRateLimitRedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRateLimitRedisApplication.class, args);
    }
}
