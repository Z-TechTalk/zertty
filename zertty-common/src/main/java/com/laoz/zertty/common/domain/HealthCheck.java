package com.laoz.zertty.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 健康检查结果类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthCheck {
    /**
     * 是都健康
     */
    private boolean healthy;
    /**
     * 消息
     */
    private String message;
}