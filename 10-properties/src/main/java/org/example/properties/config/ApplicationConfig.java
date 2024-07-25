package org.example.properties.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ApplicationConfig {
    @Value("${application.name}")
    private String name;
    @Value("${application.version}")
    private String version;
}
