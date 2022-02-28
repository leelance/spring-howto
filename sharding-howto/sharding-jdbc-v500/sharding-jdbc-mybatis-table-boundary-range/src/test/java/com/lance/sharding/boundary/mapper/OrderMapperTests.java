package com.lance.sharding.boundary.mapper;

import com.lance.sharding.boundary.domain.Order;
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
 * order[0..5], auto-interval
 *
 * @author lance
 * @date 2022/2/27 00:13
 */
@Slf4j
@SpringBootTest
class OrderMapperTests {
  private final static String[] CITIES = {"shanghai", "beijing"};
  /**
   * 0, 100, 500, 1000, 5000, 10000
   */
  private final static long[] PRICES = {1, 18, 200, 880, 600, 1200, 3200, 6000, 9900};
  @Autowired
  private OrderMapper orderMapper;

  @Test
  @Disabled
  void save() {
    ThreadLocalRandom random = ThreadLocalRandom.current();

    Date[] dates = create();
    IntStream.range(0, 20).forEach(i -> {
      Order order = new Order();
      order.setOrderId(System.nanoTime() + i);
      order.setPrice(PRICES[i % PRICES.length]);
      order.setAddressId(i);
      order.setCity(CITIES[i % 2]);
      order.setUserId(Math.abs(random.nextInt()));
      order.setCreator("user.0" + i);
      order.setIntervalTime(dates[i % 5]);
      order.setUpdater(order.getCreator());
      log.info("====>{}", order);
      orderMapper.save(order);
    });
  }

  @Test
  @Disabled
  void findAll() {
    List<Order> list = orderMapper.findAllAtPrice(9900);
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
