package com.example.zkspringbootexample.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Yann39
 * @since nov 2018
 */
@ConfigurationProperties(prefix = "custom.ajp.undertow")
public class UndertowEmbeddedAjpProperties {

    private boolean enable = false;
    private int port = 8009;
    private String host = "localhost";

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
