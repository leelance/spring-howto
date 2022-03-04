package com.lance.sharding.volume.mapper;

import com.lance.sharding.volume.domain.Order;

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
   * price
   *
   * @param price price
   * @return List
   */
  List<Order> findAllAtPrice(long price);
}
