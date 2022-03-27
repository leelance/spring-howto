package com.lance.flowable.web.repository;

import com.lance.common.core.result.PageInfo;
import com.lance.common.core.result.R;
import com.lance.flowable.service.repository.DeploymentService;
import com.lance.flowable.web.vo.repository.DeploymentReq;
import com.lance.flowable.web.vo.repository.DeploymentRes;
import com.lance.flowable.web.vo.repository.DeploymentResourceRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * deployment 相关操作
 *
 * @author lance
 * @date 2022/3/27 16:55
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/deployment")
public class DeploymentController {
  private final DeploymentService deploymentService;

  /**
   * 分页查询Deployment列表
   *
   * @param request 请求入参数
   * @return R
   */
  @PostMapping("/list")
  public R<PageInfo<DeploymentRes>> list(@RequestBody DeploymentReq request) {
    return R.data(deploymentService.list(request));
  }

  /**
   * 查询Deployment详情
   *
   * @param deploymentId deploymentId
   * @return R
   */
  @GetMapping("/detail/{deploymentId}")
  public R<DeploymentRes> detail(@PathVariable String deploymentId) {
    return R.data(deploymentService.findOne(deploymentId));
  }

  /**
   * 根据Id删除deployment对象
   *
   * @param deploymentId deploymentId
   * @return R
   */
  @GetMapping("/delete/{deploymentId}")
  public R<Boolean> delete(@PathVariable String deploymentId) {
    deploymentService.deleteOne(deploymentId);
    return R.data(Boolean.TRUE);
  }

  /**
   * 根据id查询deployment资源信息
   *
   * @param deploymentId deploymentId
   * @return R
   */
  @PostMapping("/{deploymentId}/resources")
  public R<List<DeploymentResourceRes>> resources(@PathVariable String deploymentId) {
    // 待完成根据deployId查询资源集合
    return R.data(null);
  }
}