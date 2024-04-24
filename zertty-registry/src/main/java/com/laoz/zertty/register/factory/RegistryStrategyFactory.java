package com.laoz.zertty.register.factory;

import com.laoz.zertty.common.constant.ZerttyConstant;
import com.laoz.zertty.common.enums.RegistryCenterEnum;
import com.laoz.zertty.register.config.RegistryCondition;
import com.laoz.zertty.register.config.RegistryConfig;
import com.laoz.zertty.register.strategy.RegistryStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Component
@Conditional(value = RegistryCondition.class)
public class RegistryStrategyFactory implements ApplicationContextAware, InitializingBean {

    private RegistryStrategy registryStrategy;

    private RegistryConfig registryConfig;

    private final Environment environment;

    private static final Map<RegistryCenterEnum, String> BEAN_NAME_MAP = Map.of(
            RegistryCenterEnum.REGISTER_CENTER_LOCAL, "localRegisterStrategy",
            RegistryCenterEnum.REGISTER_CENTER_REDIS, "redisRegisterStrategy",
            RegistryCenterEnum.REGISTER_CENTER_ZOOKEEPER, "zookeeperRegistryStrategy"
    );

    public RegistryStrategyFactory(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        String registerCenterType = environment.getProperty(ZerttyConstant.RegisterCenter.TYPE);
        if (StringUtils.isBlank(registerCenterType)) {
            throw new IllegalArgumentException("The required configuration value is missing from nacos.");
        }
        Optional<RegistryCenterEnum> registerCenterEnumOpt = Optional.ofNullable(RegistryCenterEnum.getRegisterCenterEnum(registerCenterType));
        if (registerCenterEnumOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid or unsupported register center type: " + registerCenterType);
        }
        String beanName = BEAN_NAME_MAP.get(registerCenterEnumOpt.get());
        if (StringUtils.isBlank(beanName)) {
            throw new IllegalArgumentException("Invalid or unsupported register center type: " + registerCenterType);
        }
        //初始化配置
        registryConfig = new RegistryConfig.RegistryConfigBuilder()
                .type(registerCenterType)
                .address(environment.getProperty(ZerttyConstant.RegisterCenter.ADDRESS,""))
                .username(environment.getProperty(ZerttyConstant.RegisterCenter.USERNAME,""))
                .password(environment.getProperty(ZerttyConstant.RegisterCenter.PASSWORD,""))
                .timeout(Long.parseLong(Objects.requireNonNull(environment.getProperty(ZerttyConstant.RegisterCenter.TIMEOUT))))
                .build();
        registryStrategy = applicationContext.getBean(beanName, RegistryStrategy.class);
    }

    @Override
    public void afterPropertiesSet() {
        registryStrategy.initialize(registryConfig);
    }
}
