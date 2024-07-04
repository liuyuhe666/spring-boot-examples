package org.example.hello.controller;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam(required = false, value = "name") String name) {
        if (StrUtil.isBlank(name)) {
            name = "World";
        }
        return StrUtil.format("Hello, {}!", name);
    }
}
