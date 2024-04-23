package com.laoz.zertty.register.strategy;

import com.laoz.zertty.common.domain.InstanceMetaInfo;
import com.laoz.zertty.common.enums.RegisterCenterEnum;
import com.laoz.zertty.register.config.RegisterCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Conditional(value = RegisterCondition.class)
public class RedisRegisterStrategy implements RegisterStrategy {
    @Override
    public String getStrategyCode() {
        return RegisterCenterEnum.REGISTER_CENTER_REDIS.getCode();
    }

    @Override
    public void initialize(Map<String, String> config) {

    }

    @Override
    public void registerService(InstanceMetaInfo instanceMetaInfo) {

    }

    @Override
    public void deregisterService(InstanceMetaInfo instanceMetaInfo) {

    }

    @Override
    public List<InstanceMetaInfo> serviceDiscovery(String serviceKey) {
        return null;
    }

}
