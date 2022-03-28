package com.lance.flowable.web.vo.process;

import lombok.Data;

/**
 * process definition result
 *
 * @author lance
 * @date 2022/3/28 22:13
 */
@Data
public class ProcessDefinitionRes {
  private String id;
  private String url;
  private int version;
  private String key;
  private String category;
  private boolean suspended;
  private String name;
  private String description;
  private String deploymentId;
  private String deploymentUrl;
  private boolean graphicalNotationDefined;
  private String resource;
  private String diagramResource;
  private boolean startFormDefined;
}
