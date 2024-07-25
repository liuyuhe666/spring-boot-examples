package org.example.properties.controller;

import cn.hutool.core.lang.Dict;
import org.example.properties.config.ApplicationConfig;
import org.example.properties.config.DeveloperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {
    private final ApplicationConfig applicationConfig;
    private final DeveloperConfig developerConfig;

    @Autowired
    public ApplicationController(ApplicationConfig applicationConfig, DeveloperConfig developerConfig) {
        this.applicationConfig = applicationConfig;
        this.developerConfig = developerConfig;
    }

    @GetMapping("/config")
    public Dict index() {
        return Dict.create().set("applicationConfig", applicationConfig).set("developerConfig", developerConfig);
    }
}
