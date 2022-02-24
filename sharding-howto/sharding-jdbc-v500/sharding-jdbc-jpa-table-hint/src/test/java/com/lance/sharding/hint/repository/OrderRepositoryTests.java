package com.lance.sharding.hint.repository;

import com.lance.sharding.hint.entity.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * order mapper
 *
 * @author lance
 * @date 2022/2/20 21:16
 */
@Slf4j
@SpringBootTest
class OrderRepositoryTests {
  private final static String[] CITIES = {"shanghai", "beijing"};
  @Autowired
  private OrderRepository orderRepository;

  @Test
  @Disabled
  void save() {
    ThreadLocalRandom random = ThreadLocalRandom.current();

    IntStream.range(0, 20).forEach(i -> {
      try (HintManager hintManager = HintManager.getInstance()) {
        hintManager.addTableShardingValue("t_order", CITIES[i % 2]);

        OrderEntity order = new OrderEntity();
        order.setOrderId(System.nanoTime() + i);
        order.setAddressId(i);
        order.setCity(CITIES[i % 2]);
        order.setUserId(Math.abs(random.nextInt()));
        order.setCreator("user.0" + i);
        order.setUpdater(order.getCreator());
        orderRepository.save(order);
      }
    });
  }

  @Test
  @Disabled
  void findOne() {
    try (HintManager hintManager = HintManager.getInstance()) {
      hintManager.addTableShardingValue("t_order", CITIES[0]);

      long orderId = 782568039714202L;
      String city = CITIES[0];
      OrderEntity orderEntity = orderRepository.findByOrderIdAndCity(orderId, city);
      log.info("===>{}", orderEntity);
    }
  }

  @Test
  @Disabled
  void findAll() {
    try (HintManager hintManager = HintManager.getInstance()) {
      hintManager.addTableShardingValue("t_order", CITIES[1]);

      List<OrderEntity> list = orderRepository.findAll();
      log.info("===>{}", list);
    }
  }
}
