package com.lance.sharding.hint.mapper;

import com.lance.sharding.hint.domain.Order;
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
class OrderMapperTests {
  private final static String[] CITIES = {"shanghai", "beijing"};
  @Autowired
  private OrderMapper orderMapper;

  @Test
  @Disabled
  void save() {
    ThreadLocalRandom random = ThreadLocalRandom.current();

    IntStream.range(0, 20).forEach(i -> {
      try (HintManager hintManager = HintManager.getInstance()) {
        hintManager.addTableShardingValue("t_order", CITIES[i % 2]);

        Order order = new Order();
        order.setOrderId(System.nanoTime() + i);
        order.setAddressId(i);
        order.setCity(CITIES[i % 2]);
        order.setUserId(Math.abs(random.nextInt()));
        order.setCreator("user.0" + i);
        order.setUpdater(order.getCreator());
        orderMapper.save(order);
      }
    });
  }

  @Test
  void findAll() {
    try (HintManager hintManager = HintManager.getInstance()) {
      // value[0..1]
      hintManager.addTableShardingValue("t_order", CITIES[1]);
      List<Order> list = orderMapper.findAll();

      log.info("===>{}", list);
    }
  }
}
