package com.laoz.zertty.register.strategy;

import com.laoz.zertty.common.domain.InstanceMetaInfo;

import java.util.List;
import java.util.Map;


public class ZookeeperRegistryStrategy implements RegistryStrategy {

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
