package com.lance.sharding.volume.domain;

import lombok.Data;

import java.util.Date;

/**
 * order table
 *
 * @author lance
 * @date 2022/2/20 20:48
 */
@Data
public class Order {
  private long orderId;
  private long price;
  private int userId;
  private long addressId;
  private String city;
  private int status;
  private Date intervalTime;
  private String creator;
  private Date createTime;
  private String updater;
  private Date updateTime;
}
