package com.lance.flowable.serivce.process;

import com.lance.common.core.json.JsonUtils;
import com.lance.common.core.result.PageInfo;
import com.lance.flowable.config.code.Constants;
import com.lance.flowable.service.process.ProcessDefinitionService;
import com.lance.flowable.web.vo.process.*;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.BpmnModel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

/**
 * ProcessDefinitionService
 *
 * @author lance
 * @date 2022/3/28 22:34
 */
@Slf4j
@SpringBootTest
class ProcessDefinitionServiceTests {
  @Autowired
  private ProcessDefinitionService processDefinitionService;

  @Test
  @Disabled
  void list() {
    ProcessDefinitionReq req = new ProcessDefinitionReq();
    log.info("===>{}", JsonUtils.toJsonString(req));

    PageInfo<ProcessDefinitionRes> page = processDefinitionService.list(req);
    log.info("===>{}", JsonUtils.toJsonString(page));
  }

  @Test
  @Disabled
  void findOne() {
    String processDefinitionId = "Expense:1:b79bdd5cae7311ec985e42e7ef8243f3";
    ProcessDefinitionRes res = processDefinitionService.findOne(processDefinitionId);
    log.info("===>{}", JsonUtils.toJsonString(res));
  }

  @Test
  @Disabled
  void updateCategory() {
    String processDefinitionId = "Expense:1:b79bdd5cae7311ec985e42e7ef8243f3";
    ProcessDefinitionCategoryReq req = ProcessDefinitionCategoryReq.of("rename_category");
    processDefinitionService.updateCategory(processDefinitionId, req);
  }

  @Test
  @Disabled
  void resourceData() {
    String processDefinitionId = "Expense:1:b79bdd5cae7311ec985e42e7ef8243f3";
    processDefinitionService.resourceData(processDefinitionId);
  }

  @Test
  @Disabled
  void bpmnModel() {
    String processDefinitionId = "Expense:1:b79bdd5cae7311ec985e42e7ef8243f3";
    BpmnModel model = processDefinitionService.bpmnModel(processDefinitionId);
    log.info("===>{}", JsonUtils.toJsonString(model));
  }

  @Test
  @Disabled
  void toggleState() {
    String processDefinitionId = "Expense:1:b79bdd5cae7311ec985e42e7ef8243f3";
    ToggleStateReq req = new ToggleStateReq();
    req.setAction(Constants.ACTIVATE);
    req.setIncludeProcessInstances(true);
    req.setDate(new Date());
    processDefinitionService.toggleState(processDefinitionId, req);
  }

  @Test
  @Disabled
  void getAllCandidate() {
    String processDefinitionId = "Expense:1:b79bdd5cae7311ec985e42e7ef8243f3";
    List<IdentityLinkRes> list = processDefinitionService.getAllCandidate(processDefinitionId);
    log.info("===>{}", JsonUtils.toJsonString(list));
  }

  @Test
  @Disabled
  void addCandidate() {
    String processDefinitionId = "Expense:1:b79bdd5cae7311ec985e42e7ef8243f3";
    IdentityLinkReq req = new IdentityLinkReq();
    req.setUser("kermit");
    req.setGroupId("sales");

    processDefinitionService.addCandidate(processDefinitionId, req);
  }

  @Test
  @Disabled
  void deleteCandidate() {
    String processDefinitionId = "Expense:1:b79bdd5cae7311ec985e42e7ef8243f3";
    String family = Constants.Family.USER_ID;
    String identityId = "kermit";

    processDefinitionService.deleteCandidate(processDefinitionId, family, identityId);
  }

  @Test
  @Disabled
  void getCandidate() {
    String processDefinitionId = "Expense:1:b79bdd5cae7311ec985e42e7ef8243f3";
    String family = Constants.Family.USER_ID;
    String identityId = "kermit";

    IdentityLinkRes res = processDefinitionService.getCandidate(processDefinitionId, family, identityId);
    log.info("===>{}", JsonUtils.toJsonString(res));
  }
}
