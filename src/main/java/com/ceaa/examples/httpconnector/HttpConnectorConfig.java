package com.ceaa.examples.httpconnector;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpConnectorConfig {

    @Value("${application.demos.http-connector.url}")
    private String url;

    @Bean
    ConfigureApiRequestDelegate configureApiRequestDelegate() {
        return new ConfigureApiRequestDelegate(url);
    }

    @Bean
    PrintResponseDelegate printResponseDelegate() {
        return new PrintResponseDelegate();
    }

    @Bean
    ResponseListener responseListener() {
        return new ResponseListener();
    }
}
