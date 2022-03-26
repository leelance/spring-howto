package com.lance.flowable.service.process;

import org.flowable.common.engine.api.FlowableException;
import org.flowable.common.engine.api.FlowableObjectNotFoundException;
import org.flowable.engine.runtime.ProcessInstance;

import java.util.Map;

/**
 * 流程相关service
 *
 * @author lance
 * @date 2022/3/26 16:24
 * @see org.flowable.engine.RuntimeService
 */
public interface ProcessInstanceService {
  /**
   * 开始流程根据processDefinitionKey和参数
   *
   * @param processDefinitionKey 流程定义key
   * @param variables            变量集合
   * @return ProcessInstance
   */
  ProcessInstance startProcessInstanceByKey(String processDefinitionKey, Map<String, Object> variables);

  /**
   * 开启流程根据流程定义可以,租户Id,参数
   *
   * @param processDefinitionKey 流程定义Key
   * @param tenantId             租户Id
   * @param variables            变量集合
   * @return ProcessInstance
   */
  ProcessInstance startProcessInstanceByKeyAndTenantId(String processDefinitionKey, String tenantId, Map<String, Object> variables);

  /**
   * 开启流程,根据流程定义key, 业务key, 参数
   *
   * @param processDefinitionKey 流程定义Key
   * @param businessKey          业务key
   * @param variables            变量集合
   * @return ProcessInstance
   */
  ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey, Map<String, Object> variables);

  /**
   * 开启流程,根据流程定义key、业务key、租户Id,参数
   *
   * @param processDefinitionKey 流程定义Key
   * @param businessKey          业务key
   * @param tenantId             租户Id
   * @param variables            变量集合
   * @return ProcessInstance
   */
  ProcessInstance startProcessInstanceByKeyAndTenantId(String processDefinitionKey, String businessKey, String tenantId, Map<String, Object> variables);

  /**
   * 根据流程Id, 中止流程
   * <p>
   * If a process instance is in state suspended, flowable will not execute jobs (timers, messages) associated with this instance.
   * <p>
   * If you have a process instance hierarchy, suspending one process instance form the hierarchy will not suspend other process instances form that hierarchy.
   *
   * @param processInstanceId 流程Id
   * @throws FlowableObjectNotFoundException if no such processInstance can be found.
   * @throws FlowableException               the process instance is already in state suspended.
   */
  void suspendProcessInstanceById(String processInstanceId);

  /**
   * 根据流程Id, 激活流程
   * <p>
   * If you have a process instance hierarchy, suspending one process instance form the hierarchy will not suspend other process instances form that hierarchy.
   *
   * @param processInstanceId 流程Id
   * @throws FlowableObjectNotFoundException if no such processInstance can be found.
   * @throws FlowableException               if the process instance is already in state active.
   */
  void activateProcessInstanceById(String processInstanceId);

  /**
   * 根据流程id删除流程
   *
   * @param processInstanceId id of process instance to delete, cannot be null.
   * @param deleteReason      reason for deleting, can be null.
   * @throws FlowableObjectNotFoundException when no process instance is found with the given id.
   */
  void deleteProcessInstance(String processInstanceId, String deleteReason);
}
