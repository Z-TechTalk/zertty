package com.laoz.zertty.register.factory;

import com.alibaba.fastjson.JSON;
import com.laoz.zertty.register.config.RegistryConfig;
import com.laoz.zertty.register.config.RegistryProperties;
import com.laoz.zertty.register.service.RegistryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
@ConditionalOnProperty(
        prefix = "zertty.register",
        name = "type"
)
@EnableConfigurationProperties(RegistryProperties.class)
public class RegistryServiceFactory implements InitializingBean {

    private final RegistryProperties registryProperties;

    private final List<RegistryService> registryServiceList;

    @Autowired
    public RegistryServiceFactory(RegistryProperties registryProperties, List<RegistryService> registryServiceList) {
        this.registryProperties = registryProperties;
        this.registryServiceList = registryServiceList;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //配置了注册中心配置了允许通过
        Objects.requireNonNull(registryProperties, "RegistryProperties must not be null");
        //加载所有的注册中心
        Map<String, RegistryService> registryServiceMap = Optional.ofNullable(registryServiceList)
                .orElse(new ArrayList<>()).stream()
                .collect(Collectors.toMap(RegistryService::registryKey, Function.identity(), (oldVal, newVal) -> oldVal));
        //匹配要使用的注册中心
        String registryCenterType = registryProperties.getType();
        RegistryService registryService = registryServiceMap.get(registryCenterType);
        if (Objects.isNull(registryService)) {
            log.error("No RegistryService found for type: {}", registryCenterType);
            throw new IllegalStateException("No matching RegistryService implementation found.");
        }
        //构建注册中心配置
        RegistryConfig registryConfig = new RegistryConfig.builder()
                .type(registryCenterType).address(registryProperties.getAddress())
                .username(registryProperties.getUsername()).password(registryProperties.getPassword())
                .timeout(registryProperties.getTimeout()).build();
        //初始化配置中心
        registryService.initialize(registryConfig);
        log.info("Successfully initialized registry! Registry Center Type: {}, Config: {}", registryCenterType, JSON.toJSONString(registryConfig));
    }

}
