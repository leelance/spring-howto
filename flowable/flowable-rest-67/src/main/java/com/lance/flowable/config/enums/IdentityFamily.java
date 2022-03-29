package com.lance.flowable.config.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * identity family type[1:user, 2:group]
 *
 * @author lance
 * @date 2022/3/29 11:15
 */
@Getter
@AllArgsConstructor
public enum IdentityFamily {
  /**
   * 用户id
   */
  USER,
  /**
   * group id
   */
  GROUP;
}
