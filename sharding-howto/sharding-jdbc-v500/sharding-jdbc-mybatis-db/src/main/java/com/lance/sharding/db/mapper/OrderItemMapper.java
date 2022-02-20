package com.lance.sharding.db.mapper;

import com.lance.sharding.db.domain.OrderItem;

import java.util.List;

/**
 * user mapper
 *
 * @author lance
 * @date 2022/2/20 20:57
 */
public interface OrderItemMapper {
  /**
   * save info
   *
   * @param info info
   */
  void save(OrderItem info);

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
  List<OrderItem> findAll();
}
