package org.example.ratelimit.redis.controller;

import cn.hutool.core.lang.Dict;
import lombok.extern.slf4j.Slf4j;
import org.example.ratelimit.redis.annotation.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {
    @RateLimiter(value = 5)
    @GetMapping("/test1")
    public Dict test1() {
        log.info("test1被执行了");
        return Dict.create()
            .set("code", 0)
            .set("msg", "test1被执行了")
            .set("desc", "别想一直看到我，不信你快速刷新看看~");
    }

    @GetMapping("/test2")
    public Dict test2() {
        log.info("test2被执行了");
        return Dict.create()
            .set("code", 0)
            .set("msg", "test2被执行了")
            .set("desc", "我一直都在，不信你快速刷新看看~");
    }

    @RateLimiter(value = 2, key = "testKey")
    @GetMapping("/test3")
    public Dict test3() {
        log.info("test3被执行了");
        return Dict.create()
            .set("code", 0)
            .set("msg", "test3被执行了")
            .set("desc", "别想一直看到我，不信你快速刷新看看~");
    }
}
