package com.lance.flowable.serivce.repository;

import com.lance.common.core.json.JsonUtils;
import com.lance.common.core.result.PageInfo;
import com.lance.flowable.service.repository.DeploymentService;
import com.lance.flowable.web.vo.repository.DeploymentReq;
import com.lance.flowable.web.vo.repository.DeploymentRes;
import com.lance.flowable.web.vo.repository.DeploymentResourceRes;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * DeploymentService
 *
 * @author lance
 * @date 2022/3/27 15:18
 */
@Slf4j
@SpringBootTest
class DeploymentServiceTests {
  @Autowired
  private DeploymentService deploymentService;

  @Test
  @Disabled
  void list() {
    DeploymentReq req = new DeploymentReq();
    req.setCurrent(1);
    req.setPageSize(3);
    //req.setCategoryNotEquals("book");
    //req.setNameLike("i");
    //req.setTenantIdLike("10000");

    PageInfo<DeploymentRes> list = deploymentService.list(req);
    log.info("===>{}", JsonUtils.toJsonString(list));
  }

  @Test
  @Disabled
  void findOne() {
    String deploymentId = "111";
    DeploymentRes deploymentRes = deploymentService.findOne(deploymentId);
    log.info("===>{}", JsonUtils.toJsonString(deploymentRes));
  }

  @Test
  @Disabled
  void deleteOne() {
    String deploymentId = "111";
    deploymentService.deleteOne(deploymentId);
  }

  @Test
  @Disabled
  void resources() {
    String deploymentId = "f38ff893adb411ec8d2a42e7ef8243f3";
    List<DeploymentResourceRes> resources = deploymentService.resources(deploymentId);
    log.info("===>{}", JsonUtils.toJsonString(resources));
  }
}
