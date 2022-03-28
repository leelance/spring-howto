package com.lance.flowable.web.repository;

import com.lance.common.core.result.PageInfo;
import com.lance.common.core.result.R;
import com.lance.flowable.config.code.FlowResultCode;
import com.lance.flowable.service.repository.DeploymentService;
import com.lance.flowable.web.vo.repository.DeploymentReq;
import com.lance.flowable.web.vo.repository.DeploymentRes;
import com.lance.flowable.web.vo.repository.DeploymentResourceRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * deployment 相关操作
 *
 * @author lance
 * @date 2022/3/27 16:55
 */
@Slf4j
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
   * 发布流程, 通过上传zip bar文件(bpmn20文件)
   *
   * @param name     deploymentName
   * @param tenantId 租户Id
   * @param category category
   * @param file     .zip, .bar文件
   * @return DeploymentRes
   */
  @PostMapping(value = "/upload/zip", headers = "content-type=multipart/form-data")
  public R<DeploymentRes> uploadZip(String name, String category, String tenantId, MultipartFile file) {
    try (ZipInputStream zipIn = new ZipInputStream(file.getInputStream(), StandardCharsets.UTF_8)) {
      DeploymentRes deployment = deploymentService.deploy(name, category, tenantId, zipIn);
      return R.data(deployment);
    } catch (Exception e) {
      String fileName = (file == null || file.isEmpty()) ? "" : file.getOriginalFilename();
      log.warn("upload deployment file[fileName: {}, name: {}, category: {}, tenantId: {}] fail: ", fileName, name, category, tenantId, e);
    }

    return R.fail(FlowResultCode.UPLOAD_DEPLOY_FAIL);
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
