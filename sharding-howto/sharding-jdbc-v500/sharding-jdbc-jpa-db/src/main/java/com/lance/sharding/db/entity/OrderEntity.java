package com.lance.sharding.db.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * order table
 *
 * @author lance
 * @date 2022/2/20 20:48
 */
@Data
@Entity
@Table(name = "t_order")
public class OrderEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long orderId;
  private int userId;
  private long addressId;
  private int status;
  private String creator;
  private Date createTime;
  private String updater;
  private Date updateTime;
}
