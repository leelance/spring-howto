package com.lance.flowable.web.process;

import com.lance.common.core.result.PageInfo;
import com.lance.common.core.result.R;
import com.lance.flowable.service.process.ProcessDefinitionService;
import com.lance.flowable.web.vo.process.*;
import lombok.RequiredArgsConstructor;
import org.flowable.bpmn.model.BpmnModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lance
 * @date 2022/3/28 22:29
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/process/define")
public class ProcessDefinitionController {
  private final ProcessDefinitionService processDefinitionService;

  /**
   * 1.分页查询ProcessDefinition列表
   *
   * @param request 请求入参数
   * @return R
   */
  @PostMapping("/list")
  public R<PageInfo<ProcessDefinitionRes>> list(@RequestBody ProcessDefinitionReq request) {
    return R.data(processDefinitionService.list(request));
  }

  /**
   * 2.查询processDefinition详情
   *
   * @param processDefinitionId processDefinitionId
   * @return R
   */
  @GetMapping("/detail/{processDefinitionId}")
  public R<ProcessDefinitionRes> detail(@PathVariable String processDefinitionId) {
    return R.data(processDefinitionService.findOne(processDefinitionId));
  }

  /**
   * 3.更新流程定义的类别
   *
   * @param processDefinitionId processDefinitionId
   * @param req                 ProcessDefinitionCategoryReq
   * @return R
   */
  @PutMapping("/update/category/{processDefinitionId}")
  public R<Boolean> updateCategory(@PathVariable String processDefinitionId, @RequestBody ProcessDefinitionCategoryReq req) {
    processDefinitionService.updateCategory(processDefinitionId, req);
    return R.data(Boolean.TRUE);
  }

  /**
   * 5.获取流程定义 BPMN model模型
   *
   * @param processDefinitionId processDefinitionId
   * @return R
   */
  @GetMapping("/bpmn/model/{processDefinitionId}")
  public R<BpmnModel> bpmnModel(@PathVariable String processDefinitionId) {
    return R.data(processDefinitionService.bpmnModel(processDefinitionId));
  }

  /**
   * 6/7.暂停/激活流程定义
   *
   * @param processDefinitionId processDefinitionId
   * @param req                 ToggleStateReq
   * @return R
   */
  @PutMapping("/toggle/state/{processDefinitionId}")
  public R<Boolean> toggleState(@PathVariable String processDefinitionId, @RequestBody ToggleStateReq req) {
    processDefinitionService.toggleState(processDefinitionId, req);
    return R.data(Boolean.TRUE);
  }

  /**
   * 8.获取流程定义中的候选人列表
   *
   * @param processDefinitionId processDefinitionId
   * @return R
   */
  @GetMapping("/candidate/list/{processDefinitionId}")
  public R<List<IdentityLinkRes>> getAllCandidate(@PathVariable String processDefinitionId) {
    return R.data(processDefinitionService.getAllCandidate(processDefinitionId));
  }

  /**
   * 9.候选启动器添加到流程定义
   *
   * @param processDefinitionId processDefinitionId
   * @param req                 req
   * @return R
   */
  @PostMapping("/candidate/{processDefinitionId}")
  public R<Boolean> addCandidate(@PathVariable String processDefinitionId, @RequestBody IdentityLinkReq req) {
    processDefinitionService.addCandidate(processDefinitionId, req);
    return R.data(Boolean.TRUE);
  }

  /**
   * 10.从流程定义中删除候选起始者
   *
   * @param processDefinitionId The id of the process definition
   * @param family              Either users or groups, depending on the type of identity link
   * @param identityId          Either the userId or groupId of the identity to get as candidate starter
   */
  @DeleteMapping("/candidate/delete/{processDefinitionId}/{family}/{identityId}")
  public R<Boolean> deleteCandidate(@PathVariable String processDefinitionId, @PathVariable String family, @PathVariable String identityId) {
    processDefinitionService.deleteCandidate(processDefinitionId, family, identityId);
    return R.data(Boolean.TRUE);
  }

  /**
   * 11.从流程定义中获取候选启动器
   *
   * @param processDefinitionId The id of the process definition
   * @param family              Either users or groups, depending on the type of identity link
   * @param identityId          Either the userId or groupId of the identity to get as candidate starter
   * @return IdentityLinkRes
   */
  @GetMapping("/candidate/{processDefinitionId}/{family}/{identityId}")
  public R<IdentityLinkRes> getCandidate(@PathVariable String processDefinitionId, @PathVariable String family, @PathVariable String identityId) {
    return R.data(processDefinitionService.getCandidate(processDefinitionId, family, identityId));
  }
}
