package com.lance.sharding.file.domain;

import lombok.Data;

import java.util.Date;

/**
 * order table
 *
 * @author lance
 * @date 2022/2/20 20:48
 */
@Data
public class OrderItem {
  private long orderItemId;
  private long orderId;
  private int userId;
  private int status;
  private String creator;
  private Date createTime;
  private String updater;
  private Date updateTime;
}
