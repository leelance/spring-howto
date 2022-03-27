package com.lance.flowable.web.vo.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * deployment vo
 *
 * @author lance
 * @date 2022/3/27 15:37
 */
@Data
public class DeploymentRes {
  /**
   * id
   */
  private String id;
  /**
   * name
   */
  private String name;
  /**
   * category
   */
  private String category;
  /**
   * tenantId
   */
  private String tenantId;
  /**
   * url
   */
  private String url;
  /**
   * deploymentTime
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date deploymentTime;
}
