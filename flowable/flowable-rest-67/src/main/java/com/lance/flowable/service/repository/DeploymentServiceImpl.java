package com.lance.flowable.service.repository;

import com.lance.common.core.result.PageInfo;
import com.lance.flowable.web.vo.repository.DeploymentReq;
import com.lance.flowable.web.vo.repository.DeploymentRes;
import com.lance.flowable.web.vo.repository.DeploymentResourceRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentQuery;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * deployment service
 *
 * @author lance
 * @date 2022/3/27 15:09
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DeploymentServiceImpl implements DeploymentService {
  private final RepositoryService repositoryService;

  /**
   * 查询列表
   *
   * @param request 请求入参数
   * @return PageInfo
   */
  @Override
  public PageInfo<DeploymentRes> list(DeploymentReq request) {
    DeploymentQuery query = query();

    if (StringUtils.isNotBlank(request.getName())) {
      query.deploymentName(request.getName());
    }

    if (StringUtils.isNotBlank(request.getNameLike())) {
      query.deploymentNameLike("%" + request.getNameLike() + "%");
    }

    if (StringUtils.isNotBlank(request.getCategory())) {
      query.deploymentCategory(request.getCategory());
    }

    if (StringUtils.isNotBlank(request.getCategoryNotEquals())) {
      query.deploymentCategoryNotEquals(request.getCategoryNotEquals());
    }

    if (StringUtils.isNotBlank(request.getTenantId())) {
      query.deploymentTenantId(request.getTenantId());
    }

    if (StringUtils.isNotBlank(request.getTenantIdLike())) {
      query.deploymentTenantIdLike("%" + request.getTenantIdLike() + "%");
    }

    List<Deployment> deployments = query.listPage(request.getPageStart(), request.getPageSize());
    List<DeploymentRes> list = deployments.stream().map(this::convert).collect(Collectors.toList());
    int count = (int) query.count();
    return PageInfo.of(request.getCurrent(), request.getPageSize(), list, count);
  }

  /**
   * 根据id查询 DeploymentRes
   *
   * @param deploymentId deploymentId
   * @return DeploymentRes
   */
  @Override
  public DeploymentRes findOne(@NonNull String deploymentId) {
    Deployment deployment = query().deploymentId(deploymentId).singleResult();
    return convert(deployment);
  }

  /**
   * 删除deployment
   *
   * @param deploymentId deploymentId
   */
  @Override
  public void deleteOne(@NonNull String deploymentId) {
    repositoryService.deleteDeployment(deploymentId, false);
  }

  /**
   * 根据id查询deployment资源信息
   *
   * @param deploymentId deploymentId
   * @return List
   */
  @Override
  public List<DeploymentResourceRes> resources(String deploymentId) {
    Object object = repositoryService.getDeploymentResourceNames(deploymentId);
    log.info("===>{}", object);
    return null;
  }

  /**
   * create deployment query
   *
   * @return DeploymentQuery;
   */
  private DeploymentQuery query() {
    return repositoryService.createDeploymentQuery();
  }

  /**
   * Deployment -> DeploymentVo
   *
   * @param d Deployment
   * @return DeploymentVo
   */
  private DeploymentRes convert(Deployment d) {
    if (Objects.isNull(d)) {
      return null;
    }

    DeploymentRes o = new DeploymentRes();
    o.setId(d.getId());
    o.setName(d.getName());
    o.setCategory(d.getCategory());
    o.setTenantId(d.getTenantId());
    o.setUrl("default");
    o.setDeploymentTime(d.getDeploymentTime());
    return o;
  }
}
