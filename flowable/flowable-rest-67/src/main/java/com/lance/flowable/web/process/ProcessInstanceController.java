package com.lance.flowable.web.process;

import com.lance.common.core.result.R;
import com.lance.flowable.service.process.ProcessInstanceService;
import com.lance.flowable.web.vo.ProcessDelVo;
import com.lance.flowable.web.vo.ProcessVo;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 流程操作
 *
 * @author lance
 * @date 2022/3/26 17:08
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/process")
public class ProcessInstanceController {
  private final ProcessInstanceService processInstanceService;

  /**
   * 根据流程定义key开启流程
   *
   * @param processDefinitionKey 流程定义key
   * @param variables            参数
   * @return R<ProcessInstance>
   */
  @PostMapping("/start/key/{processDefinitionKey}")
  public R<ProcessVo> start(@PathVariable String processDefinitionKey, @RequestBody Map<String, Object> variables) {
    ProcessInstance instance = processInstanceService.startProcessInstanceByKey(processDefinitionKey, variables);
    return R.data(ProcessVo.of(instance));
  }

  /**
   * 根据流程定义key, 租户Id开启流程
   *
   * @param processDefinitionKey 流程定义key
   * @param tenantId             租户Id
   * @param variables            参数
   * @return R<ProcessInstance>
   */
  @PostMapping("/start/key/{processDefinitionKey}-{tenantId}")
  public R<ProcessVo> start(@PathVariable String processDefinitionKey, @PathVariable String tenantId, @RequestBody Map<String, Object> variables) {
    ProcessInstance instance = processInstanceService.startProcessInstanceByKeyAndTenantId(processDefinitionKey, tenantId, variables);
    return R.data(ProcessVo.of(instance));
  }

  /**
   * 根据流程定义key, bizId开启流程
   *
   * @param processDefinitionKey 流程定义key
   * @param bizId                业务Id
   * @param variables            参数
   * @return R<ProcessInstance>
   */
  @PostMapping("/start/key/biz/{processDefinitionKey}-{bizId}")
  public R<ProcessVo> startByBizId(@PathVariable String processDefinitionKey, @PathVariable String bizId, @RequestBody Map<String, Object> variables) {
    ProcessInstance instance = processInstanceService.startProcessInstanceByKey(processDefinitionKey, bizId, variables);
    return R.data(ProcessVo.of(instance));
  }

  /**
   * @param processDefinitionKey 流程定义key
   * @param tenantId             租户Id
   * @param bizId                业务Id
   * @param variables            参数
   * @return R<ProcessInstance>
   */
  @PostMapping("/start/key/{processDefinitionKey}-{tenantId}-{bizId}")
  public R<ProcessVo> start(@PathVariable String processDefinitionKey, @PathVariable String tenantId, @PathVariable String bizId, @RequestBody Map<String, Object> variables) {
    ProcessInstance instance = processInstanceService.startProcessInstanceByKeyAndTenantId(processDefinitionKey, bizId, tenantId, variables);
    return R.data(ProcessVo.of(instance));
  }

  /**
   * 根据流程Id, 中止流程
   *
   * @param processInstanceId 流程实例Id
   * @return 是否成功
   */
  @PostMapping("/suspend/{processInstanceId}")
  public R<Boolean> suspend(@PathVariable String processInstanceId) {
    processInstanceService.suspendProcessInstanceById(processInstanceId);
    return R.data(Boolean.TRUE);
  }

  /**
   * 根据流程Id, 激活流程
   *
   * @param processInstanceId 流程实例Id
   * @return 是否成功
   */
  @PostMapping("/activate/{processInstanceId}")
  public R<Boolean> activate(@PathVariable String processInstanceId) {
    processInstanceService.activateProcessInstanceById(processInstanceId);
    return R.data(Boolean.TRUE);
  }

  /**
   * 根据流程定义Id, 删除流程实例
   *
   * @param processInstanceId 流程实例Id
   * @param vo                删除原因
   * @return 是否成功
   */
  @PostMapping("/delete/{processInstanceId}")
  public R<Boolean> delete(@PathVariable String processInstanceId, @RequestBody ProcessDelVo vo) {
    processInstanceService.deleteProcessInstance(processInstanceId, vo.getReason());
    return R.data(Boolean.TRUE);
  }
}
