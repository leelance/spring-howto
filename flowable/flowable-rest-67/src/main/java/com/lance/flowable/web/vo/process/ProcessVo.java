package com.lance.flowable.web.vo.process;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.flowable.engine.runtime.ProcessInstance;

import java.util.Date;
import java.util.Map;

/**
 * 流程实例Vo
 *
 * @author lance
 * @date 2022/3/26 18:32
 */
@Data
public class ProcessVo {

  /**
   * Id of the root of the execution tree representing the process instance. It is the same as {link #getId() if this execution is the process instance.
   */
  private String processInstanceId;
  /**
   * The id of the process definition of the process instance.
   */
  private String processDefinitionId;

  /**
   * Persisted reference to the process definition name.
   */
  private String processDefinitionName;

  /**
   * Persisted reference to the process definition key.
   */
  private String processDefinitionKey;
  /**
   * persisted reference to the process definition version.
   */
  private Integer processDefinitionVersion;

  /**
   * Persisted reference to the deployment id.
   */
  private String deploymentId;
  /**
   * Persisted reference to the business key.
   */
  private String businessKey;
  /**
   * Persisted reference to the business status.
   */
  private String businessStatus;
  /**
   * returns true if the process instance is suspended
   */
  private boolean isSuspended;
  /**
   * Returns the process variables if requested in the process instance query
   */
  private Map<String, Object> processVariables;
  /**
   * The tenant identifier of this process instance
   */
  private String tenantId;
  /**
   * Returns the name of this process instance.
   */
  private String name;
  /**
   * Returns the description of this process instance.
   */
  private String description;

  /**
   * Returns the localized name of this process instance.
   */
  private String localizedName;

  /**
   * Returns the localized description of this process instance.
   */
  private String localizedDescription;

  /**
   * Returns the start time of this process instance.
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date startTime;

  /**
   * Returns the user id of this process instance.
   */
  private String startUserId;

  /**
   * Returns the callback id of this process instance.
   */
  private String callbackId;

  /**
   * Returns the callback type of this process instance.
   */
  private String callbackType;

  public static ProcessVo of(ProcessInstance instance) {
    if (instance == null) {
      return null;
    }

    ProcessVo p = new ProcessVo();
    p.setProcessInstanceId(instance.getProcessInstanceId());
    p.setProcessDefinitionId(instance.getProcessDefinitionId());
    p.setProcessDefinitionName(instance.getProcessDefinitionName());
    p.setProcessDefinitionKey(instance.getProcessDefinitionKey());
    p.setProcessDefinitionVersion(instance.getProcessDefinitionVersion());
    p.setDeploymentId(instance.getDeploymentId());
    p.setBusinessKey(instance.getBusinessKey());
    p.setBusinessStatus(instance.getBusinessStatus());
    p.setSuspended(instance.isSuspended());
    p.setProcessVariables(instance.getProcessVariables());
    p.setTenantId(instance.getTenantId());
    p.setName(instance.getName());
    p.setDescription(instance.getDescription());
    p.setLocalizedName(instance.getLocalizedName());
    p.setLocalizedDescription(instance.getLocalizedDescription());
    p.setStartTime(instance.getStartTime());
    p.setStartUserId(instance.getStartUserId());
    p.setCallbackId(instance.getCallbackId());
    p.setCallbackType(instance.getCallbackType());
    return p;
  }
}
