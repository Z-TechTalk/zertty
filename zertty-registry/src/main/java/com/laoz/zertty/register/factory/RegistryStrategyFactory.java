package com.laoz.zertty.register.factory;

import com.laoz.zertty.common.constant.ZerttyConstant;
import com.laoz.zertty.common.enums.RegistryCenterEnum;
import com.laoz.zertty.register.config.RegistryConfig;
import com.laoz.zertty.register.strategy.RegistryStrategy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
@ConditionalOnProperty(
        prefix = "zertty.register-center",
        name = "type"
)
public class RegistryStrategyFactory implements ApplicationContextAware, InitializingBean {

    private RegistryStrategy registryStrategy;

    private RegistryConfig registryConfig;

    private final Environment environment;

    private static final Map<RegistryCenterEnum, String> REGISTRY_STRATEGY_MAP = Map.of(
            RegistryCenterEnum.REGISTRY_CENTER_LOCAL, "localRegisterStrategy",
            RegistryCenterEnum.REGISTRY_CENTER_REDIS, "redisRegisterStrategy",
            RegistryCenterEnum.REGISTRY_CENTER_ZOOKEEPER, "zookeeperRegistryStrategy"
    );

    public RegistryStrategyFactory(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        String registryCenterType = environment.getProperty(ZerttyConstant.RegistryCenter.TYPE);
        Optional<RegistryCenterEnum> registryCenterEnumOpt = Optional.ofNullable(RegistryCenterEnum.getRegistryCenterEnum(registryCenterType));
        if (registryCenterEnumOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid or unsupported register center type: " + registryCenterType);
        }
        String registryBeanName = REGISTRY_STRATEGY_MAP.get(registryCenterEnumOpt.get());
        if (registryBeanName == null) {
            throw new IllegalArgumentException("Invalid or unsupported register center type: " + registryCenterType);
        }
        //初始化配置
        registryConfig = new RegistryConfig.RegistryConfigBuilder()
                .type(registryCenterType)
                .address(environment.getProperty(ZerttyConstant.RegistryCenter.ADDRESS, ""))
                .username(environment.getProperty(ZerttyConstant.RegistryCenter.USERNAME, ""))
                .password(environment.getProperty(ZerttyConstant.RegistryCenter.PASSWORD, ""))
                .timeout(environment.getProperty(ZerttyConstant.RegistryCenter.TIMEOUT, Long.class, 3000L))
                .build();
        registryStrategy = applicationContext.getBean(registryBeanName, RegistryStrategy.class);
    }

    @Override
    public void afterPropertiesSet() {
        registryStrategy.initialize(registryConfig);
    }
}
