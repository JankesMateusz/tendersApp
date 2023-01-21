package com.jankes.tendersApp.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("jwt")
public class JwtConfigurationProperties {

    private String secret;
    private long validity;

    String getSecret() {
        return secret;
    }

    void setSecret(String secret) {
        this.secret = secret;
    }

    long getValidity() {
        return validity;
    }

    void setValidity(long validity) {
        this.validity = validity;
    }
}
