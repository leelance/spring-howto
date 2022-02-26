package com.lance.sharding.interval.domain;

import lombok.Data;

import java.util.Date;

/**
 * order table
 *
 * @author lance
 * @date 2022/2/20 20:48
 */
@Data
public class Address {
  private long addressId;
  private String addressName;
  private int status;
  private String creator;
  private Date createTime;
  private String updater;
  private Date updateTime;
}
