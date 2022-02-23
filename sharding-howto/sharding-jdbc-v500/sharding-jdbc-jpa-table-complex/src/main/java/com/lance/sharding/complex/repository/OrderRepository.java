package com.lance.sharding.complex.repository;

import com.lance.sharding.complex.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
	 * @param city    city
	 * @return OrderEntity
	 */
	OrderEntity findByOrderIdAndCity(long orderId, String city);
}
