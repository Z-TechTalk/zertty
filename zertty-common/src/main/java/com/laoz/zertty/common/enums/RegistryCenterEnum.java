package com.laoz.zertty.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum RegistryCenterEnum {

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

    public static RegistryCenterEnum getRegisterCenterEnum(String code){
        if (Objects.isNull(code)){
            return null;
        }
        for (RegistryCenterEnum value : RegistryCenterEnum.values()) {
            if (code.equals(value.getCode())){
                return value;
            }
        }
        return null;
    }


}
