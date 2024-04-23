package com.laoz.zertty.register.strategy;

import com.laoz.zertty.common.domain.InstanceMetaInfo;

import java.util.List;
import java.util.Map;

public interface RegisterStrategy {
    /**
     * 注册中心策略码
     * @return 策略码
     */
    String getStrategyCode();
    /**
     * 初始化注册中心。
     * @param config 注册中心相关的配置信息
     */
    void initialize(Map<String, String> config);
    /**
     * 注册服务
     * @param instanceMetaInfo 服务实力元数据
     */
    void registerService(InstanceMetaInfo instanceMetaInfo);
    /**
     * 取消服务注册
     * @param instanceMetaInfo 服务实力元数据
     */
    void deregisterService(InstanceMetaInfo instanceMetaInfo);

    /**
     * 服务发现
     * @param serviceKey 服务实例keu
     * @return
     */
    List<InstanceMetaInfo> serviceDiscovery(String serviceKey);
}
