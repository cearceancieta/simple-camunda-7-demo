package com.ceaa.examples.errorhandling;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;

@Slf4j
@SpringBootTest
public class ErrorEventHandlingTest {

    @Autowired
    RuntimeService runtimeService;
    @Autowired
    HistoryService historyService;

    @BeforeEach
    void setAllUp() {
    }

    @Test
    void test() {
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("errorEventHandlingProcess");
        log.info(processInstance.getId());

        assertThat(processInstance).isStarted();
        assertThat(processInstance).isEnded();
        HistoricActivityInstance endActivity = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processInstance.getId())
                .activityType("errorEndEvent")
                .singleResult();
        log.info("End activity [{}]", endActivity.getActivityId());
    }
}
