package com.laoz.zertty.register.service;

import com.laoz.zertty.common.domain.HealthCheck;
import com.laoz.zertty.common.domain.ServiceInstance;
import com.laoz.zertty.common.enums.RegistryCenterEnum;
import com.laoz.zertty.register.config.RegistryConfig;
import com.laoz.zertty.register.exceptions.RegistryException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ZookeeperRegistryService extends AbstractRegistryService {

    @Override
    public String registryKey() {
        return RegistryCenterEnum.REGISTRY_CENTER_ZOOKEEPER.getCode();
    }

    @Override
    public void initialize(RegistryConfig registryConfig) throws RegistryException {

    }

    @Override
    public void register(String serviceName, String serviceAddress, Map<String, String> metadata) throws RegistryException {

    }

    @Override
    public void deregister(String serviceName, String serviceAddress) throws RegistryException {

    }

    @Override
    public List<ServiceInstance> discover(String serviceName) throws RegistryException {
        return null;
    }

    @Override
    public void heartbeat(String serviceName, String serviceAddress) throws RegistryException {

    }

    @Override
    public HealthCheck healthCheck(String serviceName, String serviceAddress) throws RegistryException {
        return null;
    }

}
