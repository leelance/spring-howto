package com.lance.sharding.volume.repository;

import com.lance.sharding.volume.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * order
 *
 * @author lance
 * @date 2022/2/19 11:50
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

  /**
   * find orderId and city
   *
   * @param orderId orderId
   * @param price   price
   * @return OrderEntity
   */
  OrderEntity findByOrderIdAndPrice(long orderId, long price);

  /**
   * 根据价格查询
   *
   * @param price price
   * @return list
   */
  List<OrderEntity> findByPrice(long price);
}
