package com.laoz.zertty.register.exceptions;

/**
 * 注册中心操作异常
 */
public class RegistryException extends Exception {

    public RegistryException(String message) {
        super(message);
    }

    public RegistryException(String message, Throwable cause) {
        super(message, cause);
    }

}