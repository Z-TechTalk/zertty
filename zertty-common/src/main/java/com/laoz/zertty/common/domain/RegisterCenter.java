package com.laoz.zertty.common.domain;

import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
public class RegisterCenter {
    /**
     * 注册中心类别
     */
    private String type;
    /**
     * 注册中心地址
     */
    private String address;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 超时单位
     */
    private TimeUnit timeUnit = TimeUnit.MICROSECONDS;
    /**
     * 超时时间
     */
    private Long timeout;
}
