package com.lance.sharding.rw.domain;

import lombok.Data;

import java.util.Date;

/**
 * Tag
 *
 * @author lance
 * @date 2022/2/18 13:43
 */
@Data
public class Tag {
  private long id;
  private String name;
  private int sort;
  private String creator;
  private Date createTime;
  private String updater;
  private Date updateTime;
}
