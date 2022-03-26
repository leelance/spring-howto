package com.lance.common.core.result;

import lombok.Setter;

/**
 * 分页基础类
 *
 * @author lance
 * @date 2021/11/10 16:42
 */
public abstract class BasePage {
  /**
   * 当前页面
   */
  @Setter
  protected int current;
  /**
   * 每页多少条
   */
  @Setter
  protected int pageSize;
  /**
   * 分页起始
   * select *
   * from t_user
   * limit pageStart pageSize
   */
  protected int pageStart;

  public int getCurrent() {
    return Math.max(1, current);
  }

  public int getPageSize() {
    return Math.max(1, pageSize);
  }

  public int getPageStart() {
    return (getCurrent() - 1) * getPageSize();
  }
}
