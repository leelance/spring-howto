package com.lance.flowable.web.vo.process;

import com.lance.common.core.result.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.repository.ProcessDefinitionQuery;

/**
 * 定义ProcessDefinition查询入参数
 *
 * @author lance
 * @date 2022/3/28 21:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProcessDefinitionReq extends BasePage {
  /**
   * Only return process definitions with the given version
   */
  private Integer version;
  /**
   * Only return process definitions with the given name
   */
  private String name;
  /**
   * Only return process definitions with a name like the given name.
   */
  private String nameLike;
  /**
   * Only return process definitions with the given key.
   */
  private String key;
  /**
   * Only return process definitions with a name like the given key.
   */
  private String keyLike;
  /**
   * Only return process definitions with the given resource name.
   */
  private String resourceName;
  /**
   * Only return process definitions with a name like the given resource name.
   */
  private String resourceNameLike;
  /**
   * Only return process definitions with the given category.
   */
  private String category;
  /**
   * Only return process definitions with a category like the given name.
   */
  private String categoryLike;
  /**
   * Only return process definitions which don’t have the given category.
   */
  private String categoryNotEquals;
  /**
   * Only return process definitions which are part of a deployment with the given id.
   */
  private String deploymentId;
  /**
   * Only return process definitions which can be started by the given user.
   */
  private String startByUser;
  /**
   * Only return the latest process definition versions.
   * Can only be used together with key and keyLike parameters, using any other parameter will result in a 400-response.
   */
  private Boolean latest;
  /**
   * If true, only returns process definitions which are suspended.
   * If false, only active process definitions (which are not suspended) are returned.
   */
  private Boolean suspended;

  /**
   * 组合参数
   *
   * @param query ProcessDefinitionQuery
   * @return ProcessDefinitionQuery
   */
  public ProcessDefinitionQuery combine(ProcessDefinitionQuery query) {
    if (this.version != null) {
      query.processDefinitionVersion(this.version);
    }
    if (StringUtils.isNotBlank(this.name)) {
      query.processDefinitionName(this.name);
    }
    if (StringUtils.isNotBlank(this.nameLike)) {
      query.processDefinitionNameLike("%" + this.nameLike + "%");
    }
    if (StringUtils.isNotBlank(this.key)) {
      query.processDefinitionKey(this.key);
    }
    if (StringUtils.isNotBlank(this.keyLike)) {
      query.processDefinitionKeyLike("%" + this.keyLike + "%");
    }
    if (StringUtils.isNotBlank(this.resourceName)) {
      query.processDefinitionResourceName(this.resourceName);
    }
    if (StringUtils.isNotBlank(this.resourceNameLike)) {
      query.processDefinitionResourceNameLike("%" + this.resourceNameLike + "%");
    }
    if (StringUtils.isNotBlank(this.category)) {
      query.processDefinitionCategory(this.category);
    }
    if (StringUtils.isNotBlank(this.categoryLike)) {
      query.processDefinitionCategoryLike("%" + this.categoryLike + "%");
    }
    if (StringUtils.isNotBlank(this.categoryNotEquals)) {
      query.processDefinitionCategoryNotEquals(this.categoryNotEquals);
    }
    if (StringUtils.isNotBlank(this.deploymentId)) {
      query.deploymentId(this.deploymentId);
    }
    if (StringUtils.isNotBlank(this.startByUser)) {
      query.startableByUser(this.startByUser);
    }
    if (this.latest != null) {
      query.latestVersion();
    }
    if (this.suspended != null) {
      query.suspended();
    }

    return query;
  }
}
