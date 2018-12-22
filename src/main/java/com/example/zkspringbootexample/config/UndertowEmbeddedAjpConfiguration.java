package com.example.zkspringbootexample.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yann39
 * @since nov 2018
 */
@Configuration
@EnableConfigurationProperties(UndertowEmbeddedAjpProperties.class)
public class UndertowEmbeddedAjpConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(UndertowEmbeddedAjpConfiguration.class);

    private UndertowEmbeddedAjpProperties config;

    public UndertowEmbeddedAjpConfiguration(UndertowEmbeddedAjpProperties config) {
        this.config = config;
    }

    @Bean
    public UndertowServletWebServerFactory undertowServletWebServerFactory() {

        final UndertowServletWebServerFactory undertow = new UndertowServletWebServerFactory();

        undertow.addBuilderCustomizers((UndertowBuilderCustomizer) builder -> {
            builder.addAjpListener(this.config.getPort(), this.config.getHost());
            logger.info("[Undertow][AJP] Configured AJP listener for {}:{}", this.config.getHost(),
                    this.config.getPort());
        });

        return undertow;
    }
}
