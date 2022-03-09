package com.lance.sharding.jpa.repository;

import com.lance.sharding.jpa.entity.OrderEntity;
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


}
