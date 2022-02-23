package com.lance.sharding.complex.entity;

import lombok.Data;

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
@Table(name = "t_order_item")
public class OrderItemEntity {
	@Id
	private long orderItemId;
	private long orderId;
	private int userId;
	private int status;
	private String creator;
	private Date createTime;
	private String updater;
	private Date updateTime;
}
