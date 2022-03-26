package com.lance.common.core.result;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * PageInfo
 *
 * @author lance
 * @date 12/8/2020 11:05
 */
public class PageInfo<T> {
  /**
   * 数据集合
   */
  @Setter
  @Getter
  private List<T> data;
  /**
   * 当前页码
   */
  @Setter
  @Getter
  private int current;
  /**
   * 每页显示条数
   */
  @Setter
  @Getter
  private int pageSize;
  /**
   * 总记录数据量
   */
  @Setter
  @Getter
  private int total;

  /**
   * Returns the number of total pages.
   */
  private int totalPages;

  public static <T> PageInfo<T> of(int current, int pageSize, List<T> data, int total) {
    PageInfo<T> info = new PageInfo<>();
    info.setCurrent(current);
    info.setPageSize(pageSize);
    info.setData(data);
    info.setTotal(total);
    return info;
  }

  public static <T> PageInfo<T> of(int current, int pageSize) {
    PageInfo<T> info = new PageInfo<>();
    info.setCurrent(current);
    info.setPageSize(pageSize);
    return info;
  }

  public int getTotalPages() {
    return getPageSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getPageSize());
  }
}
