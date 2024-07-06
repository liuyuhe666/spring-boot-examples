package org.example.ratelimit.guava.controller;

import cn.hutool.core.lang.Dict;
import lombok.extern.slf4j.Slf4j;
import org.example.ratelimit.guava.annotation.MyRateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @MyRateLimiter(value = 1.0, timeout = 300)
    @GetMapping("/test1")
    public Dict test1() {
        log.info("test1 被执行了");
        return Dict.create().set("code", 0).set("msg", "test1").set("desc", "别想一直看到我，不信你快速刷新看看~");
    }

    @GetMapping("/test2")
    public Dict test2() {
        log.info("test2 被执行了");
        return Dict.create().set("code", 0).set("msg", "test2").set("desc", "我一直都在，不信你快速刷新看看~");
    }

    @MyRateLimiter(value = 2.0, timeout = 300)
    @GetMapping("/test3")
    public Dict test3() {
        log.info("test3 被执行了");
        return Dict.create().set("code", 0).set("msg", "test3").set("desc", "别想一直看到我，不信你快速刷新看看~");
    }
}
