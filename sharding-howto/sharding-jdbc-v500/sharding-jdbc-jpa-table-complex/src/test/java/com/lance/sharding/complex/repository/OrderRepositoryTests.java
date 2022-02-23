package com.lance.sharding.complex.repository;

import com.lance.sharding.complex.entity.OrderEntity;
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
class OrderRepositoryTests {
	@Autowired
	private OrderRepository orderRepository;

	@Test
	@Disabled
	void save() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		String[] cities = {"beijing", "shanghai"};

		IntStream.range(0, 20).forEach(i -> {
			OrderEntity order = new OrderEntity();
			order.setOrderId(System.nanoTime() + i);
			order.setAddressId(i);
			order.setCity(cities[i % 2]);
			order.setUserId(Math.abs(random.nextInt()));
			order.setCreator("user.0" + i);
			order.setUpdater(order.getCreator());
			orderRepository.save(order);

		});
	}

	@Test
	@Disabled
	void findOne() {
		long orderId = 782568039714202L;
		String city = "beijing";
		OrderEntity orderEntity = orderRepository.findByOrderIdAndCity(orderId, city);
		log.info("===>{}", orderEntity);
	}
}
