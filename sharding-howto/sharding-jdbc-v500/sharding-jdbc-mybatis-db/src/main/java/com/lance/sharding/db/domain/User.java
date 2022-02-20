package com.lance.sharding.db.domain;

import lombok.Data;

import java.util.Date;

/**
 * order table
 *
 * @author lance
 * @date 2022/2/20 20:48
 */
@Data
public class User {
  private int userId;
  private String username;
  private String pwd;
  private int status;
  private String creator;
  private Date createTime;
  private String updater;
  private Date updateTime;
}
