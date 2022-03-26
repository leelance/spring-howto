package com.lance.flowable.service.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.springframework.stereotype.Service;

/**
 * 流程实例相关查询
 *
 * @author lance
 * @date 2022/3/26 18:49
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessInstanceQueryServiceImpl implements ProcessInstanceQueryService {
  private final RuntimeService runtimeService;

  /**
   * 根据流程实例id查询流程实例
   *
   * @param processInstanceId 流程实例id
   * @return ProcessInstance
   */
  @Override
  public ProcessInstance findOne(String processInstanceId) {
    return createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
  }

  private ProcessInstanceQuery createProcessInstanceQuery() {
    return runtimeService.createProcessInstanceQuery();
  }
}
