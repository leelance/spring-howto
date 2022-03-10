package com.lance.sharding.tenant.repository;

import com.lance.sharding.tenant.config.context.DbContextHolder;
import com.lance.sharding.tenant.entity.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * OrderRepository
 *
 * @author lance
 * @date 2022/3/10 19:12
 */
@Slf4j
@SpringBootTest
public class OrderRepositoryTests {
  @Autowired
  private OrderRepository orderRepository;

  @Test
    //@Disabled
  void save() {
    try {
      DbContextHolder.set("bbs_1");
      //DbContextHolder.set("bbs_2");
      //DbContextHolder.set("bbs_3");
      ThreadLocalRandom random = ThreadLocalRandom.current();

      IntStream.range(0, 20).forEach(i -> {
        OrderEntity order = new OrderEntity();
        order.setOrderId(System.nanoTime() + i);
        order.setAddressId(i);
        order.setUserId(Math.abs(random.nextInt()));
        order.setCreator("user.0" + i);
        order.setCreateTime(new Date());
        order.setUpdater(order.getCreator());
        order.setUpdateTime(order.getCreateTime());
        orderRepository.save(order);
      });
    } finally {
      DbContextHolder.clear();
    }
  }

  @Test
  @Disabled
  void findAll() {
    try {
      //DbContextHolder.set("bbs_1");
      DbContextHolder.set("bbs_2");
      //DbContextHolder.set("bbs_1");
      List<OrderEntity> list = orderRepository.findAll();
      log.info("===>{}", list);
    } finally {
      DbContextHolder.clear();
    }
  }
}
