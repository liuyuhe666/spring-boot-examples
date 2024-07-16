package org.example.dubbo.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.example.dubbo.common.service.HelloService;
import org.springframework.stereotype.Component;

@Service
@Component
@Slf4j
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        log.info("HelloServiceImpl sayHello");
        return "say hello to: " + name;
    }
}
