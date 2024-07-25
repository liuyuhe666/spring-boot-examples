package org.example.properties.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "developer")
public class DeveloperConfig {
    private String name;
    private String website;
    private String qq;
    private String phoneNumber;
}
