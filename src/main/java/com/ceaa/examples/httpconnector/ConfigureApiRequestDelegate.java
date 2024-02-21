package com.ceaa.examples.httpconnector;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Slf4j
public class ConfigureApiRequestDelegate implements JavaDelegate {
    private final String url;

    public ConfigureApiRequestDelegate(String url) {
        this.url = url;
    }

    @Override
    public void execute(DelegateExecution execution) {
        execution.setVariable("apiUrl", url);
        execution.setVariable("apiMethod", "GET");
    }
}
