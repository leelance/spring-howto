package com.lance.sharding.interval.mapper;

import com.lance.sharding.interval.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
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
class OrderMapperTests {
  private final static String[] CITIES = {"shanghai", "beijing"};
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
      order.setAddressId(i);
      order.setCity(CITIES[i % 2]);
      order.setUserId(Math.abs(random.nextInt()));
      order.setCreator("user.0" + i);
      order.setIntervalTime(dates[i % 4]);
      order.setUpdater(order.getCreator());
      log.info("====>{}", order);
      orderMapper.save(order);
    });
  }

  @Test
  @Disabled
  void findAll() {
    Date[] dates = create();
    List<Order> list = orderMapper.findAllAtCreate(dates[1]);
    log.info("===>{}", list);
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
