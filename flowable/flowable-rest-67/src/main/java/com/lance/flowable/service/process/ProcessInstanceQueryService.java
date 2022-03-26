package com.lance.flowable.service.process;

import org.flowable.engine.runtime.ProcessInstance;

/**
 * 流程实例相关查询
 *
 * @author lance
 * @date 2022/3/26 18:49
 */
public interface ProcessInstanceQueryService {
  /**
   * 根据流程实例id查询流程实例
   *
   * @param processInstanceId 流程实例id
   * @return ProcessInstance
   */
  ProcessInstance findOne(String processInstanceId);
}
