package com.lance.flowable.web.vo.repository;

import lombok.Data;

/**
 * deployment对应的资源信息
 *
 * @author lance
 * @date 2022/3/27 17:44
 */
@Data
public class DeploymentResourceRes {
  /**
   *
   */
  private String id;
  private String url;
  private String dataUrl;
  /**
   * Contains the media-type the resource has.
   * This is resolved using a (pluggable) MediaTypeResolver and contains, by default, a limited number of mime-type mappings.
   */
  private String mediaType;
  /**
   * Type of resource, possible values:
   * <ul>
   *   <li>resource: Plain old resource</li>
   *   <li>processDefinition: Resource that contains one or more process-definitions. This resource is picked up by the deployer</li>
   *   <li>processImage: Resource that represents a deployed process definition’s graphical layout</li>
   * </ul>
   */
  private String type;
}
