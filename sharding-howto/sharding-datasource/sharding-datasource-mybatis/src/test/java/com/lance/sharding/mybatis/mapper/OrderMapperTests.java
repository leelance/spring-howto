package com.lance.sharding.mybatis.mapper;

import com.lance.sharding.mybatis.config.context.DbContextHolder;
import com.lance.sharding.mybatis.domain.Order;
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
 * OrderMapper
 *
 * @author lance
 * @date 2022/3/9 22:25
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

		//DbContextHolder.set("bbs_1");
		DbContextHolder.set("bbs_2");

		IntStream.range(0, 20).forEach(i -> {
			Order order = new Order();
			order.setOrderId(System.nanoTime() + i);
			order.setAddressId(i);
			order.setCity("Beijing");
			order.setUserId(Math.abs(random.nextInt()));
			order.setCreator("user.0" + i);
			order.setIntervalTime(new Date());
			order.setUpdater(order.getCreator());
			log.info("====>{}", order);
			orderMapper.save(order);
		});
	}

	@Test
	@Disabled
	void findAll() {
		DbContextHolder.set("bbs_2");
		List<Order> list = orderMapper.findAll();
		log.info("===>{}", list);
	}
}
