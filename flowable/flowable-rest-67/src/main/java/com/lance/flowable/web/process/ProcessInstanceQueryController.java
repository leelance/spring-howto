package com.lance.flowable.web.process;

import com.lance.common.core.result.R;
import com.lance.flowable.service.process.ProcessInstanceQueryService;
import com.lance.flowable.web.vo.ProcessVo;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 流程查询
 *
 * @author lance
 * @date 2022/3/26 17:08
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/process")
public class ProcessInstanceQueryController {
  private final ProcessInstanceQueryService processInstanceQueryService;

  /**
   * 根据流程定义key开启流程
   *
   * @param processInstanceId 流程实例Id
   * @return R<ProcessInstance>
   */
  @PostMapping("/detail/{processInstanceId}")
  public R<ProcessVo> findOne(@PathVariable String processInstanceId) {
    ProcessInstance instance = processInstanceQueryService.findOne(processInstanceId);
    return R.data(ProcessVo.of(instance));
  }
}
