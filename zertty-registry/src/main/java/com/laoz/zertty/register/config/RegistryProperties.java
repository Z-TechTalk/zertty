package com.laoz.zertty.register.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "zertty.registry")
public class RegistryProperties {

    private String type;

    private String address;

    private String username;

    private String password;

    private long timeout;

}
