package com.ceaa.examples.errorhandling;

import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorEventHandlingConfig {
    @Bean
    public JavaDelegate throwErrorEventDelegate() {
        return new ThrowErrorEventDelegate();
    }
}
