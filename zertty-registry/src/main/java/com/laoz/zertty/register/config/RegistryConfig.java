package com.laoz.zertty.register.config;

import lombok.Data;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Data
public class RegistryConfig {

    private String type;

    private String address;

    private String username;

    private String password;

    private long timeout;

    private TimeUnit timeUnit;

    private RegistryConfig(String type, String address, String username, String password) {
        this.type = Objects.requireNonNull(type, "Type must not be null");
        this.address = Objects.requireNonNull(address, "Address must not be null");
        this.username = Objects.requireNonNull(username, "Username must not be null");
        this.password = Objects.requireNonNull(password, "Password must not be null");
    }

    @Data
    public static class RegistryConfigBuilder {
        private String type;
        private String address;
        private String username;
        private String password;
        private long timeout = 3000;
        private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

        public RegistryConfigBuilder type(String type) {
            this.type = type;
            return this;
        }

        public RegistryConfigBuilder address(String address) {
            this.address = address;
            return this;
        }

        public RegistryConfigBuilder username(String username) {
            this.username = username;
            return this;
        }

        public RegistryConfigBuilder password(String password) {
            this.password = password;
            return this;
        }

        public RegistryConfigBuilder timeout(long timeout) {
            this.timeout = timeout;
            return this;
        }

        public RegistryConfigBuilder timeUnit(TimeUnit timeUnit) {
            this.timeUnit = timeUnit;
            return this;
        }

        public RegistryConfig build() {
            return new RegistryConfig(type, address, username, password);
        }
    }

}
