package com.lance.flowable.service.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 流程相关service
 *
 * @author lance
 * @date 2022/3/26 16:47
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessInstanceServiceImpl implements ProcessInstanceService {
  private final RuntimeService runtimeService;

  @Override
  public ProcessInstance startProcessInstanceByKey(@NonNull String processDefinitionKey, Map<String, Object> variables) {
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
    if (log.isDebugEnabled()) {
      log.debug("start process instance key: {}, piId: {}", processDefinitionKey, processInstance.getProcessInstanceId());
    }
    return processInstance;
  }

  @Override
  public ProcessInstance startProcessInstanceByKeyAndTenantId(@NonNull String processDefinitionKey, String tenantId, Map<String, Object> variables) {
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKeyAndTenantId(processDefinitionKey, variables, tenantId);
    if (log.isDebugEnabled()) {
      log.debug("start process instance key: {}, tenantId: {}, piId: {}", processDefinitionKey, tenantId, processInstance.getProcessInstanceId());
    }
    return processInstance;
  }

  @Override
  public ProcessInstance startProcessInstanceByKey(@NonNull String processDefinitionKey, String businessKey, Map<String, Object> variables) {
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
    if (log.isDebugEnabled()) {
      log.debug("start process instance key: {}, bizId: {}, piId: {}", processDefinitionKey, businessKey, processInstance.getProcessInstanceId());
    }
    return processInstance;
  }

  @Override
  public ProcessInstance startProcessInstanceByKeyAndTenantId(@NonNull String processDefinitionKey, String businessKey, String tenantId, Map<String, Object> variables) {
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKeyAndTenantId(processDefinitionKey, businessKey, variables, tenantId);
    if (log.isDebugEnabled()) {
      log.debug("start process instance key: {}, bizId: {}, tenantId: {}, piId: {}", processDefinitionKey, businessKey, tenantId, processInstance.getProcessInstanceId());
    }
    return processInstance;
  }

  @Override
  public void suspendProcessInstanceById(@NonNull String processInstanceId) {
    if (log.isDebugEnabled()) {
      log.debug("suspend process id: {}", processInstanceId);
    }
    runtimeService.suspendProcessInstanceById(processInstanceId);
  }

  @Override
  public void activateProcessInstanceById(@NonNull String processInstanceId) {
    if (log.isDebugEnabled()) {
      log.debug("active process id: {}", processInstanceId);
    }
    runtimeService.activateProcessInstanceById(processInstanceId);
  }

  @Override
  public void deleteProcessInstance(@NonNull String processInstanceId, String deleteReason) {
    if (log.isDebugEnabled()) {
      log.debug("delete process id: {}", processInstanceId);
    }

    runtimeService.deleteProcessInstance(processInstanceId, deleteReason);
  }
}
