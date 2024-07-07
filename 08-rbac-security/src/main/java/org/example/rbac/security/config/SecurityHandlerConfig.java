package org.example.rbac.security.config;

import org.example.rbac.security.common.Status;
import org.example.rbac.security.util.ResponseUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class SecurityHandlerConfig {
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> ResponseUtil.renderJson(response, Status.ACCESS_DENIED, null);
    }
}
