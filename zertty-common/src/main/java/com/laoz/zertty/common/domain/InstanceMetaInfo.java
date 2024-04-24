package com.laoz.zertty.common.domain;

import com.laoz.zertty.common.constant.ZerttyConstant;
import lombok.Data;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@Data
public class InstanceMetaInfo {
    /**
     * 服务名称
     */
    private String name;
    /**
     * 服务域名
     */
    private String host;
    /**
     * 服务端口
     */
    private Integer port;
    /**
     * 服务地址
     */
    private URI address;
    /**
     * 服务版本
     */
    private String version;
    /**
     * 服务组
     */
    private String group;

    private InstanceMetaInfo(String name, URI address, String version, String group) {
        this.name = Objects.requireNonNull(name, "Name must not be null");
        this.address = Objects.requireNonNull(address, "Address must not be null");
        this.version = Objects.requireNonNull(version, "Version must not be null");
        this.group = Objects.requireNonNull(group, "Group must not be null");
    }

    @Data
    public static class ServiceMetaINFBuilder {
        private String name;
        private String host;
        private int port;
        private boolean useHttps = false;
        private String version = ZerttyConstant.ServiceMetaInfoConstant.DEFAULT_VERSION;
        private String group = ZerttyConstant.ServiceMetaInfoConstant.DEFAULT_GROUP;

        public ServiceMetaINFBuilder host(String host) {
            this.host = host;
            return this;
        }

        public ServiceMetaINFBuilder port(int port) {
            this.port = port;
            return this;
        }

        public ServiceMetaINFBuilder useHttps(boolean useHttps) {
            this.useHttps = useHttps;
            return this;
        }

        public ServiceMetaINFBuilder version(String version) {
            this.version = version;
            return this;
        }

        public ServiceMetaINFBuilder group(String group) {
            this.group = group;
            return this;
        }

        public InstanceMetaInfo build() {
            String protocol = useHttps ? "https" : "http";
            URI address;
            try {
                address = new URI(protocol, null, host, port, null, null, null);
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException("Invalid host or port", e);
            }
            return new InstanceMetaInfo(name, address, version, group);
        }
    }
}
