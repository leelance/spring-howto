package com.lance.sharding.volume.entity;

import lombok.Data;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
public class OrderEntity implements Persistable<Long> {
  @Id
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


  @Override
  public Long getId() {
    return orderId;
  }

  @Override
  public boolean isNew() {
    return true;
  }
}
