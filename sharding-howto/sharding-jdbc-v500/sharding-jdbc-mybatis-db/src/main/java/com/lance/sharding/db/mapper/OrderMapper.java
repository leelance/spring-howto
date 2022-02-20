package com.lance.sharding.db.mapper;

import com.lance.sharding.db.domain.Order;

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
}
