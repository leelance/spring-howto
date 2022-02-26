package com.lance.sharding.interval.repository;

import com.lance.sharding.interval.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
   * @param orderId  orderId
   * @param interval interval
   * @return OrderEntity
   */
  OrderEntity findByOrderIdAndIntervalTime(long orderId, Date interval);

  /**
   * 根据时间查询
   *
   * @param interval interval
   * @return list
   */
  List<OrderEntity> findByIntervalTime(Date interval);
}
