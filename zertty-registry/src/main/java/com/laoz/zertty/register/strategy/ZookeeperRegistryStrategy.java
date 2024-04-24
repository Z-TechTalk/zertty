package com.laoz.zertty.register.strategy;

import com.laoz.zertty.common.domain.InstanceMetaInfo;
import com.laoz.zertty.register.config.RegistryConfig;

import java.util.List;


public class ZookeeperRegistryStrategy implements RegistryStrategy {

    @Override
    public void initialize(RegistryConfig registryConfig) {

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
