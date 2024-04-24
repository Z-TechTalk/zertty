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
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
@Conditional(value = RegistryCondition.class)
public class RegistryStrategyFactory implements ApplicationContextAware, InitializingBean {

    private RegistryStrategy registryStrategy;



    private static final Map<RegistryCenterEnum, String> BEAN_NAME_MAP = Map.of(
            RegistryCenterEnum.REGISTER_CENTER_LOCAL, "localRegisterStrategy",
            RegistryCenterEnum.REGISTER_CENTER_REDIS, "redisRegisterStrategy",
            RegistryCenterEnum.REGISTER_CENTER_ZOOKEEPER, "zookeeperRegistryStrategy"
    );

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        String registerCenterType = applicationContext.getEnvironment().getProperty(ZerttyConstant.RegisterCenter.TYPE);
        if (StringUtils.isBlank(registerCenterType)) {
            throw new IllegalArgumentException("The required configuration value is missing from nacos.");
        }
        Optional<RegistryCenterEnum> registerCenterEnumOpt = Optional.ofNullable(RegistryCenterEnum.getRegisterCenterEnum(registerCenterType));
        if (registerCenterEnumOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid or unsupported register center type: " + registerCenterType);
        }
        RegistryCenterEnum registryCenterEnum = registerCenterEnumOpt.get();
        String beanName = BEAN_NAME_MAP.get(registryCenterEnum);
        if (StringUtils.isBlank(beanName)) {
            throw new IllegalArgumentException("Invalid or unsupported register center type: " + registerCenterType);
        }
        //初始化配置



        registryStrategy = applicationContext.getBean(beanName, RegistryStrategy.class);
    }

    @Override
    public void afterPropertiesSet() {
        registryStrategy.initialize();
    }

}
