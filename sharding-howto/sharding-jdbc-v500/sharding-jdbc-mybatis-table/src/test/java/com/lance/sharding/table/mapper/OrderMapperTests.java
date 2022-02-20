package com.lance.sharding.table.mapper;

import com.lance.sharding.table.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
  @Autowired
  private OrderMapper orderMapper;

  @Test
  @Disabled
  void save() {
    ThreadLocalRandom random = ThreadLocalRandom.current();

    IntStream.range(0, 20).forEach(i -> {
      Order order = new Order();
      order.setOrderId(System.currentTimeMillis());
      order.setAddressId(i);
      order.setUserId(Math.abs(random.nextInt()));
      order.setCreator("user.0" + i);
      order.setUpdater(order.getCreator());
      orderMapper.save(order);
    });
  }
}
