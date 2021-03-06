package com.lance.flowable.service.process;

import com.lance.common.core.result.PageInfo;
import com.lance.flowable.config.code.Constants;
import com.lance.flowable.web.vo.process.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.flowable.identitylink.api.IdentityLink;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * process definition service
 *
 * @author lance
 * @date 2022/3/28 21:19
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {
  private final RepositoryService repositoryService;

  /**
   * 1.查询process define list
   *
   * @param params 查询参数
   * @return pageInfo
   */
  @Override
  public PageInfo<ProcessDefinitionRes> list(ProcessDefinitionReq params) {
    ProcessDefinitionQuery query = params.combine(query());

    List<ProcessDefinition> deployments = query.listPage(params.getPageStart(), params.getPageSize());
    List<ProcessDefinitionRes> list = deployments.stream().map(this::convert).collect(Collectors.toList());
    int count = (int) query.count();
    return PageInfo.of(params.getCurrent(), params.getPageSize(), list, count);
  }

  /**
   * 2.根据id查询 ProcessDefinition
   *
   * @param processDefinitionId processDefinitionId
   * @return ProcessDefinitionRes
   */
  @Override
  public ProcessDefinitionRes findOne(String processDefinitionId) {
    return convert(query().processDefinitionId(processDefinitionId).singleResult());
  }

  /**
   * 3.更新流程定义的类别
   *
   * @param processDefinitionId processDefinitionId
   * @param req                 ProcessDefinitionCategoryReq
   */
  @Override
  public void updateCategory(String processDefinitionId, ProcessDefinitionCategoryReq req) {
    repositoryService.setProcessDefinitionCategory(processDefinitionId, req.getCategory());
  }

  /**
   * 4.获取流程定义资源内容
   *
   * @param processDefinitionId processDefinitionId
   */
  @Override
  public void resourceData(String processDefinitionId) {
    //here your code
  }

  /**
   * 5.获取流程定义 BPMN model模型
   *
   * @param processDefinitionId processDefinitionId
   * @return BpmnModel
   */
  @Override
  public BpmnModel bpmnModel(String processDefinitionId) {
    return repositoryService.getBpmnModel(processDefinitionId);
  }

  /**
   * 6/7.暂停/激活流程定义
   *
   * @param processDefinitionId processDefinitionId
   * @param req                 ToggleStateReq
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public void toggleState(String processDefinitionId, ToggleStateReq req) {
    if (Objects.isNull(req)) {
      return;
    }

    if (StringUtils.equals(req.getAction(), Constants.SUSPEND)) {
      repositoryService.suspendProcessDefinitionById(processDefinitionId, req.isIncludeProcessInstances(), req.getDate());
    }

    if (StringUtils.equals(req.getAction(), Constants.ACTIVATE)) {
      repositoryService.activateProcessDefinitionById(processDefinitionId, req.isIncludeProcessInstances(), req.getDate());
    }
  }

  /**
   * 8.获取流程定义中的候选人列表
   *
   * @param processDefinitionId processDefinitionId
   * @return list
   */
  @Override
  public List<IdentityLinkRes> getAllCandidate(String processDefinitionId) {
    List<IdentityLink> list = repositoryService.getIdentityLinksForProcessDefinition(processDefinitionId);
    return list.stream().map(IdentityLinkRes::convert).collect(Collectors.toList());
  }

  /**
   * 9.候选启动器添加到流程定义
   *
   * @param processDefinitionId processDefinitionId
   * @param req                 req
   */
  @Override
  public void addCandidate(String processDefinitionId, IdentityLinkReq req) {
    if (StringUtils.isNotBlank(req.getUser())) {
      repositoryService.addCandidateStarterUser(processDefinitionId, req.getUser());
    }

    if (StringUtils.isNotBlank(req.getGroupId())) {
      repositoryService.addCandidateStarterGroup(processDefinitionId, req.getGroupId());
    }
  }

  /**
   * 10.从流程定义中删除候选起始者
   *
   * @param processDefinitionId The id of the process definition
   * @param family              Either users or groups, depending on the type of identity link
   * @param identityId          Either the userId or groupId of the identity to get as candidate starter
   */
  @Override
  public void deleteCandidate(String processDefinitionId, String family, String identityId) {
    if (family.equals(Constants.Family.GROUP_ID)) {
      repositoryService.deleteCandidateStarterGroup(processDefinitionId, identityId);
    }

    if (family.equals(Constants.Family.USER_ID)) {
      repositoryService.deleteCandidateStarterUser(processDefinitionId, identityId);
    }
  }

  /**
   * 11.从流程定义中获取候选启动器
   *
   * @param processDefinitionId The id of the process definition
   * @param family              Either users or groups, depending on the type of identity link
   * @param identityId          Either the userId or groupId of the identity to get as candidate starter
   * @return IdentityLinkRes
   */
  @Override
  public IdentityLinkRes getCandidate(@NonNull String processDefinitionId, String family, @NonNull String identityId) {
    List<IdentityLink> list = repositoryService.getIdentityLinksForProcessDefinition(processDefinitionId);
    if (list == null || list.isEmpty()) {
      return null;
    }

    if (family.equals(Constants.Family.GROUP_ID)) {
      IdentityLink identityLink = list.stream().filter(l -> identityId.equals(l.getGroupId())).findFirst().orElse(null);
      return IdentityLinkRes.convert(identityLink);
    }

    if (family.equals(Constants.Family.USER_ID)) {
      IdentityLink identityLink = list.stream().filter(l -> identityId.equals(l.getUserId())).findFirst().orElse(null);
      return IdentityLinkRes.convert(identityLink);
    }
    return null;
  }

  /**
   * 定义 ProcessDefinitionQuery
   *
   * @return ProcessDefinitionQuery
   */
  private ProcessDefinitionQuery query() {
    return repositoryService.createProcessDefinitionQuery();
  }

  /**
   * ProcessDefinition -> ProcessDefinitionRes
   *
   * @param d ProcessDefinition
   * @return ProcessDefinitionRes
   */
  private ProcessDefinitionRes convert(ProcessDefinition d) {
    if (Objects.isNull(d)) {
      return null;
    }

    ProcessDefinitionRes o = new ProcessDefinitionRes();
    o.setId(d.getId());
    o.setUrl("default");
    o.setVersion(d.getVersion());
    o.setKey(d.getKey());
    o.setCategory(d.getCategory());
    o.setSuspended(d.isSuspended());
    o.setName(d.getName());
    o.setDescription(d.getDescription());
    o.setDeploymentId(d.getDeploymentId());
    o.setDeploymentUrl("default");
    o.setGraphicalNotationDefined(d.hasGraphicalNotation());
    o.setResource(d.getResourceName());
    o.setDiagramResource("default");
    o.setStartFormDefined(d.hasStartFormKey());
    return o;
  }
}