package com.laoz.zertty.register.service;

import com.laoz.zertty.common.domain.HealthCheck;
import com.laoz.zertty.common.domain.ServiceInstance;
import com.laoz.zertty.common.enums.RegistryCenterEnum;
import com.laoz.zertty.register.exceptions.RegistryException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class LocalRegistryService extends AbstractRegistryService {

    private final ConcurrentHashMap<String, ConcurrentHashMap<String, ServiceInstance>> registry = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<String, ConcurrentHashMap<String, Long>> heartbeatTimes = new ConcurrentHashMap<>();

    @Override
    public String registryKey() {
        return RegistryCenterEnum.REGISTRY_CENTER_LOCAL.getCode();
    }

    @Override
    public void register(String serviceName, String serviceAddress, Map<String, String> metadata) throws RegistryException {
        // 初始化注册表中服务名对应的实例列表
        if (!registry.containsKey(serviceName)) {
            registry.putIfAbsent(serviceName, new ConcurrentHashMap<>());
        }
        // 创建服务实例并添加到注册表中
        ServiceInstance instance = new ServiceInstance(serviceName, serviceAddress, metadata);
        registry.get(serviceName).put(serviceAddress, instance);
        // 刷新心跳时间
        heartbeat(serviceName, serviceAddress);
    }

    @Override
    public void deregister(String serviceName, String serviceAddress) throws RegistryException {
        // 从注册表中移除指定服务实例
        if (registry.containsKey(serviceName)) {
            registry.get(serviceName).remove(serviceAddress);
            heartbeatTimes.get(serviceName).remove(serviceAddress);
        }
    }

    @Override
    public List<ServiceInstance> discover(String serviceName) throws RegistryException {
        // 从注册表中发现指定服务名的服务实例列表，并过滤掉过期的实例
        ConcurrentHashMap<String, ServiceInstance> instances = registry.get(serviceName);
        if (instances != null) {
            return instances.values().stream()
                    .filter(instance -> System.currentTimeMillis() - instance.getLastHeartbeat() <= TimeUnit.SECONDS.toMillis(5)) // 假设心跳超时为5秒
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public void heartbeat(String serviceName, String serviceAddress) throws RegistryException {
        // 刷新服务实例的心跳时间
        if (registry.containsKey(serviceName) && registry.get(serviceName).containsKey(serviceAddress)) {
            ServiceInstance instance = registry.get(serviceName).get(serviceAddress);
            instance.setLastHeartbeat(System.currentTimeMillis());
            // 更新心跳时间记录
            heartbeatTimes.computeIfAbsent(serviceName, k -> new ConcurrentHashMap<>()).put(serviceAddress, instance.getLastHeartbeat());
        }
    }

    @Override
    public HealthCheck healthCheck(String serviceName, String serviceAddress) throws RegistryException {
        if (registry.containsKey(serviceName) && registry.get(serviceName).containsKey(serviceAddress)) {
            ServiceInstance instance = registry.get(serviceName).get(serviceAddress);
            long now = System.currentTimeMillis();
            long heartbeatTimeout = TimeUnit.SECONDS.toMillis(5); // 假设心跳超时时间为5秒
            return (now - instance.getLastHeartbeat()) <= heartbeatTimeout ?
                    new HealthCheck(true, "Service is healthy.") :
                    new HealthCheck(false, "Service heartbeat timeout.");
        }
        return new HealthCheck(false, "Service not found.");
    }

}
