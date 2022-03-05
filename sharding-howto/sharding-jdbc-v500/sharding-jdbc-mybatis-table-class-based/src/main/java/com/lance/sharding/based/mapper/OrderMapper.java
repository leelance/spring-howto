package com.lance.sharding.based.mapper;

import com.lance.sharding.based.domain.Order;

import java.util.Date;
import java.util.List;

/**
 * user mapper
 *
 * @author lance
 * @date 2022/2/20 20:57
 */
public interface OrderMapper {
  /**
   * save info
   *
   * @param info info
   */
  void save(Order info);

  /**
   * delete by id
   *
   * @param userId userId
   */
  void delete(int userId);

  /**
   * list user
   *
   * @return list
   */
  List<Order> findAll();

  /**
   * createTime
   *
   * @param createTime createTime
   * @return List
   */
  List<Order> findAllAtCreate(Date createTime);
}
