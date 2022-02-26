package com.lance.sharding.interval.repository;

import com.lance.sharding.interval.entity.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * order auto interval repository
 *
 * @author lance
 * @date 2022/2/27 00:40
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
      order.setIntervalTime(dates[i % 6]);
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
    long orderId = 407273259105427L;
    Date[] dates = create();
    OrderEntity orderEntity = orderRepository.findByOrderIdAndIntervalTime(orderId, dates[0]);
    log.info("===>{}", orderEntity);
  }

  @Test
  @Disabled
  void findByInterval() {
    Date[] dates = create();
    List<OrderEntity> list = orderRepository.findByIntervalTime(dates[5]);

    log.info("===>{}", !list.isEmpty() ? list.size() : 0);
    log.info("===>{}", list);
  }

  private Date[] create() {
    Date[] dates = new Date[6];

    try {
      Date date = DateUtils.parseDate("2022-02-01 00:00:00", Locale.CHINA, "yyyy-MM-dd HH:mm:ss");
      IntStream.range(0, 6).forEach(i -> dates[i] = DateUtils.addDays(date, i));
    } catch (ParseException e) {
      log.error("date parse fail: ", e);
    }
    return dates;
  }
}
