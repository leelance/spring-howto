package com.lance.sharding.interval.repository;

import com.lance.sharding.interval.entity.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.Date;
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
    Date[] dates = create();

    IntStream.range(0, 20).forEach(i -> {
      OrderEntity order = new OrderEntity();
      order.setOrderId(System.nanoTime() + i);
      order.setAddressId(i);
      order.setCity(CITIES[i % 2]);
      order.setUserId(Math.abs(random.nextInt()));
      order.setIntervalTime(dates[i % 4]);
      order.setCreator("user.0" + i);
      order.setCreateTime(new Date());
      order.setUpdater(order.getCreator());
      order.setUpdateTime(order.getCreateTime());
      orderRepository.save(order);
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

  private Date[] create() {
    Date[] dates = new Date[4];

    try {
      Date date = DateUtils.parseDate("2022-01-01", "yyyy-MM-dd");
      IntStream.range(0, 4).forEach(i -> {
        dates[i] = DateUtils.addMonths(date, i);
      });
    } catch (ParseException e) {
      log.error("date parse fail: ", e);
    }
    return dates;
  }
}
