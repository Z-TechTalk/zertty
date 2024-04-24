package com.laoz.zertty.common.constant;

public class ZerttyConstant {
    /**
     * 服务元信息常量
     */
    public static class ServiceMetaInfoConstant {
        /**
         * 默认分组
         */
        public static final String DEFAULT_GROUP = "default";
        /**
         * 默认版本
         */
        public static final String DEFAULT_VERSION = "1.0";
        /**
         * 默认配置前缀
         */
        public static final String DEFAULT_CONFIG_PREFIX = "zertty";
    }

    /**
     * zertty的注册中心
     */
    public static class RegisterCenter{
        /**
         * 注册中心类型
         */
        public static final String TYPE = "zertty.register.center.type";
        /**
         * 注册中心地址
         */
        public static final String ADDRESS = "zertty.register.center.address";
        /**
         * 注册中心用户名
         */
        public static final String USERNAME = "zertty.register.center.username";
        /**
         * 注册中心密码
         */
        public static final String PASSWORD = "zertty.register.center.password";
        /**
         * 注册中心超时时长（单位毫秒）
         */
        public static final String TIMEOUT = "zertty.register.center.timeout";
    }

}
