package com.ceaa.examples.httpconnector;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class HttpConnectorProcessDemo {
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    HistoryService historyService;

    @Test
    void demo() {
        ProcessInstance process = runtimeService.startProcessInstanceByKey("connectToApiProcess");
        log.info(process.getId());
        HistoricVariableInstance response = historyService.createHistoricVariableInstanceQuery()
                .processInstanceId(process.getId())
                .variableName("apiResponse")
                .singleResult();
        HistoricVariableInstance statusCode = historyService.createHistoricVariableInstanceQuery()
                .processInstanceId(process.getId())
                .variableName("apiStatusCode")
                .singleResult();
        log.info(statusCode.getValue() + " - " + response.getValue().toString());
    }
}
