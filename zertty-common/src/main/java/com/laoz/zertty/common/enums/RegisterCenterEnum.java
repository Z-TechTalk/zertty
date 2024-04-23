package com.laoz.zertty.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RegisterCenterEnum {

    /**
     * 本地注册中心
     */
    REGISTER_CENTER_LOCAL("local", "本地注册中心"),
    /**
     * redis注册中心
     */
    REGISTER_CENTER_REDIS("redis", "redis注册中心"),
    /**
     * zookeeper注册中心
     */
    REGISTER_CENTER_ZOOKEEPER("zookeeper", "zookeeper注册中心");

    private final String code;

    private final String desc;

}
