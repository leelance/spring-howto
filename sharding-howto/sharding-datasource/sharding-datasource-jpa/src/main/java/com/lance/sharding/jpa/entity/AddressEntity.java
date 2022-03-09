package com.lance.sharding.jpa.entity;

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
@Table(name = "t_address")
public class AddressEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long addressId;
	private String addressName;
	private int status;
	private String creator;
	private Date createTime;
	private String updater;
	private Date updateTime;
}
