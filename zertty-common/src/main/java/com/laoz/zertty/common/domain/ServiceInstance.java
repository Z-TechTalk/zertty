package com.laoz.zertty.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceInstance {
    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 服务地址
     */
    private String serviceAddress;
    /**
     * 其他元数据
     */
    private Map<String, String> metadata;
    /**
     * 最后一次心跳
     */
    private long lastHeartbeat;

    public ServiceInstance(String serviceName, String serviceAddress, Map<String, String> metadata) {
        this.serviceName = serviceName;
        this.serviceAddress = serviceAddress;
        this.metadata = metadata;
        this.lastHeartbeat = Instant.now().toEpochMilli();
    }
}
