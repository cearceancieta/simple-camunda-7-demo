package com.ceaa.examples.httpconnector;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

@Slf4j
public class ResponseListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution execution) {
        log.info(execution.getVariableNames().toString());
    }
}
