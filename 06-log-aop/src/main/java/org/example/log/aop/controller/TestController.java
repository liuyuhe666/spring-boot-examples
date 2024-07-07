package org.example.log.aop.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class TestController {
    /**
     * 测试方法
     *
     * @param who 测试参数
     * @return {@link Dict}
     */
    @GetMapping("/test")
    public Dict test(String who) {
        return Dict.create()
            .set("code", 0)
            .set("msg", "0")
            .set("data", StrUtil.isBlank(who) ? "me" : who);
    }

    /**
     * 测试 post json方法
     *
     * @param map 请求的json参数
     * @return {@link Dict}
     */
    @PostMapping("/testJson")
    public Dict testJson(@RequestBody Map<String, Object> map) {
        final String jsonStr = JSONUtil.toJsonStr(map);
        log.info(jsonStr);
        return Dict.create()
            .set("code", 0)
            .set("msg", "0")
            .set("data", map);
    }
}
