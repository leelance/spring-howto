package com.lance.flowable.service.repository;

import com.lance.common.core.result.PageInfo;
import com.lance.flowable.web.vo.repository.DeploymentReq;
import com.lance.flowable.web.vo.repository.DeploymentRes;
import com.lance.flowable.web.vo.repository.DeploymentResourceRes;

import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * deployment service
 *
 * @author lance
 * @date 2022/3/27 15:09
 */
public interface DeploymentService {
  /**
   * 查询列表
   *
   * @param request 请求入参数
   * @return PageInfo
   */
  PageInfo<DeploymentRes> list(DeploymentReq request);

  /**
   * 根据id查询 DeploymentRes
   *
   * @param deploymentId deploymentId
   * @return DeploymentRes
   */
  DeploymentRes findOne(String deploymentId);

  /**
   * 删除deployment
   *
   * @param deploymentId deploymentId
   */
  void deleteOne(String deploymentId);

  /**
   * 发布流程, 通过上传zip bar文件(bpmn20文件)
   *
   * @param name     deploymentName
   * @param tenantId 租户Id
   * @param category category
   * @param zis      zip文件流
   * @return DeploymentRes
   */
  DeploymentRes deploy(String name, String tenantId, String category, ZipInputStream zis);

  /**
   * 根据id查询deployment资源信息
   *
   * @param deploymentId deploymentId
   * @return List
   */
  List<DeploymentResourceRes> resources(String deploymentId);
}
