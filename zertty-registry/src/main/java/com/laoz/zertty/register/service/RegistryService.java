package com.laoz.zertty.register.service;

import com.laoz.zertty.common.domain.HealthCheck;
import com.laoz.zertty.common.domain.ServiceInstance;
import com.laoz.zertty.register.config.RegistryConfig;
import com.laoz.zertty.register.exceptions.RegistryException;

import java.util.List;
import java.util.Map;


public interface RegistryService {

    /**
     * 注册中心key
     *
     * @return 注册中心key
     */
    String registryKey();

    /**
     * 初始化配置中心
     *
     * @param registryConfig 注册中心配置
     * @throws RegistryException 注册失败异常
     */
    default void initialize(RegistryConfig registryConfig) throws RegistryException {

    }

    /**
     * 服务注册
     *
     * @param serviceName    服务名称
     * @param serviceAddress 服务地址（IP:Port）
     * @param metadata       附加元数据，如版本、权重等
     * @throws RegistryException 注册失败异常
     */
    void register(String serviceName, String serviceAddress, Map<String, String> metadata) throws RegistryException;

    /**
     * 服务注销
     *
     * @param serviceName    服务名称
     * @param serviceAddress 服务地址（IP:Port）
     * @throws RegistryException 注销失败异常
     */
    void deregister(String serviceName, String serviceAddress) throws RegistryException;

    /**
     * 发现服务实例
     *
     * @param serviceName 服务名称
     * @return 服务实例列表，每个实例包含地址和服务元数据
     * @throws RegistryException 查询失败异常
     */
    List<ServiceInstance> discover(String serviceName) throws RegistryException;

    /**
     * 心跳维持
     *
     * @param serviceName    服务名称
     * @param serviceAddress 服务地址（IP:Port）
     * @throws RegistryException 心跳失败异常
     */
    void heartbeat(String serviceName, String serviceAddress) throws RegistryException;

    /**
     * 获取服务健康状态
     *
     * @param serviceName    服务名称
     * @param serviceAddress 服务地址（IP:Port）
     * @return 健康状态信息
     * @throws RegistryException 查询健康状态失败异常
     */
    HealthCheck healthCheck(String serviceName, String serviceAddress) throws RegistryException;

}
