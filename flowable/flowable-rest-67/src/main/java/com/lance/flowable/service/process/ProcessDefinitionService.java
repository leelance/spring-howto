package com.lance.flowable.service.process;

import com.lance.common.core.result.PageInfo;
import com.lance.flowable.web.vo.process.ProcessDefinitionCategoryReq;
import com.lance.flowable.web.vo.process.ProcessDefinitionReq;
import com.lance.flowable.web.vo.process.ProcessDefinitionRes;
import com.lance.flowable.web.vo.process.ToggleStateReq;
import org.flowable.bpmn.model.BpmnModel;

/**
 * process definition service
 *
 * @author lance
 * @date 2022/3/28 21:19
 */
public interface ProcessDefinitionService {
  /**
   * 1.查询process define list
   *
   * @param params 查询参数
   * @return pageInfo
   */
  PageInfo<ProcessDefinitionRes> list(ProcessDefinitionReq params);

  /**
   * 2.根据id查询 ProcessDefinition
   *
   * @param processDefinitionId processDefinitionId
   * @return ProcessDefinitionRes
   */
  ProcessDefinitionRes findOne(String processDefinitionId);

  /**
   * 3.更新流程定义的类别
   *
   * @param processDefinitionId processDefinitionId
   * @param req                 ProcessDefinitionCategoryReq
   */
  void updateCategory(String processDefinitionId, ProcessDefinitionCategoryReq req);

  /**
   * 4.获取流程定义资源内容
   *
   * @param processDefinitionId processDefinitionId
   */
  void resourceData(String processDefinitionId);

  /**
   * 5.获取流程定义 BPMN model模型
   *
   * @param processDefinitionId processDefinitionId
   * @return BpmnModel
   */
  BpmnModel bpmnModel(String processDefinitionId);

  /**
   * 6/7.暂停/激活流程定义
   *
   * @param processDefinitionId processDefinitionId
   * @param req                 ToggleStateReq
   */
  void toggleState(String processDefinitionId, ToggleStateReq req);
}
