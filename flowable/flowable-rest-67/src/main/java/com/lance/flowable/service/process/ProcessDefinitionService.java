package com.lance.flowable.service.process;

import com.lance.common.core.result.PageInfo;
import com.lance.flowable.web.vo.process.*;
import org.flowable.bpmn.model.BpmnModel;

import java.util.List;

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

  /**
   * 8.获取流程定义中的候选人列表
   *
   * @param processDefinitionId processDefinitionId
   * @return List
   */
  List<IdentityLinkRes> getAllCandidate(String processDefinitionId);

  /**
   * 9.候选启动器添加到流程定义
   *
   * @param processDefinitionId processDefinitionId
   * @param req                 req
   */
  void addCandidate(String processDefinitionId, IdentityLinkReq req);

  /**
   * 10.从流程定义中删除候选起始者
   *
   * @param processDefinitionId The id of the process definition
   * @param family              Either users or groups, depending on the type of identity link
   * @param identityId          Either the userId or groupId of the identity to get as candidate starter
   */
  void deleteCandidate(String processDefinitionId, String family, String identityId);

  /**
   * 11.从流程定义中获取候选启动器
   *
   * @param processDefinitionId The id of the process definition
   * @param family              Either users or groups, depending on the type of identity link
   * @param identityId          Either the userId or groupId of the identity to get as candidate starter
   * @return IdentityLinkRes
   */
  IdentityLinkRes getCandidate(String processDefinitionId, String family, String identityId);
}
