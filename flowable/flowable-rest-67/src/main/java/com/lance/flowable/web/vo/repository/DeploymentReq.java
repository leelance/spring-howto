package com.lance.flowable.web.vo.repository;

import com.lance.common.core.result.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * deployment query condition
 *
 * @author lance
 * @date 2022/3/27 15:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeploymentReq extends BasePage {
  /**
   * Only return deployments with the given name
   */
  private String name;
  /**
   * Only return deployments with a name like the given name
   */
  private String nameLike;
  /**
   * Only return deployments with the given category
   */
  private String category;
  /**
   * Only return deployments which don’t have the given category
   */
  private String categoryNotEquals;
  /**
   * Only return deployments with the given tenantId
   */
  private String tenantId;
  /**
   * Only return deployments with a tenantId like the given value
   */
  private String tenantIdLike;
}


