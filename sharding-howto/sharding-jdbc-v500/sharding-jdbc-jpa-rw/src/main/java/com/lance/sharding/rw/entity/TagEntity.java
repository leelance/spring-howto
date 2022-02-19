package com.lance.sharding.rw.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Tag
 *
 * @author lance
 * @date 2022/2/19 11:47
 */
@Data
@Entity
@Table(name = "t_tag")
public class TagEntity {
  @Id
  private long id;
  private String name;
  private int sort;
  private String creator;
  private Date createTime;
  private String updater;
  private Date updateTime;
}
