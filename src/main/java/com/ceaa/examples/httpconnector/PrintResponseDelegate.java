package com.ceaa.examples.httpconnector;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Slf4j
public class PrintResponseDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        log.info("Api response [{}]", execution.getVariable("response"));
    }
}
