package com.lance.flowable.web.vo.process;

import lombok.Data;

/**
 * update process definition category
 *
 * @author lance
 * @date 2022/3/28 23:52
 */
@Data
public class ProcessDefinitionCategoryReq {
  private String category;

  /**
   * ProcessDefinitionCategoryReq
   *
   * @param category category
   * @return ProcessDefinitionCategoryReq
   */
  public static ProcessDefinitionCategoryReq of(String category) {
    ProcessDefinitionCategoryReq req = new ProcessDefinitionCategoryReq();
    req.setCategory(category);
    return req;
  }
}
