package com.ceaa.examples.operationlog;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.EntityTypes;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.history.UserOperationLogEntry;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class CustomOperationLogDemo {
    @Autowired
    ProcessEngine processEngine;
    private final String processName = "customOperationLogProcess";;

    @Test
    void demoWithAuthenticatedUser() {
        String loggedUser = "user@email.com";
        String businessKey = "businessKey";
        processEngine.getIdentityService().setAuthenticatedUserId(loggedUser);

        ProcessInstance process = processEngine.getRuntimeService()
                .createProcessInstanceByKey(processName)
                .businessKey(businessKey)
                .execute();
        Task task = processEngine.getTaskService().createTaskQuery()
                .processInstanceId(process.getId())
                .singleResult();
        processEngine.getTaskService().claim(task.getId(), loggedUser);
        processEngine.getTaskService().complete(task.getId());

        List<UserOperationLogEntry> logEntries = processEngine.getHistoryService()
                .createUserOperationLogQuery()
                .userId(loggedUser)
                .list();
        assertThat(logEntries).isNotNull().isNotEmpty().hasSize(3);

        UserOperationLogEntry createEntry = processEngine.getHistoryService().createUserOperationLogQuery()
                .userId(loggedUser)
                .operationType(UserOperationLogEntry.OPERATION_TYPE_CREATE)
                .entityType(EntityTypes.PROCESS_INSTANCE)
                .singleResult();
        assertThat(createEntry).isNotNull();
    }
}
