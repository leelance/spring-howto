package com.lance.oauth2.server.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户对象
 *
 * @author lance
 * @date 2022/3/25 17:30
 */
@Data
public class UserInfo implements Serializable {
  /**
   * user id
   */
  private long userId;
  private String username;
  private String password;
  private int status;
  private long createId;
  private LocalDateTime createTime;
  private long updateId;
  private LocalDateTime updateTime;
}
