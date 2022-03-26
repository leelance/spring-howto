package com.lance.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态[1:正常, 0:无效]
 *
 * @author lance
 * @date 2/28/2021 09:53
 */
@Getter
@AllArgsConstructor
public enum Status {
  /**
   * 状态有效无效
   */
  NO(0, "无效"),
  YES(1, "正常");

  private final int code;
  private final String name;
}
