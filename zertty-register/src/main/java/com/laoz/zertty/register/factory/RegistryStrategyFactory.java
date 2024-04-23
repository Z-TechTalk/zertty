package com.laoz.zertty.register.factory;

import com.laoz.zertty.register.strategy.RegisterStrategy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RegistryStrategyFactory implements InitializingBean {

    @Autowired
    private List<RegisterStrategy> registerStrategyList;

    private static final Map<String, Class<? extends RegisterStrategy>> REGISTRY_STRATEGY_MAP = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        //初始化注册中心

    }

}
