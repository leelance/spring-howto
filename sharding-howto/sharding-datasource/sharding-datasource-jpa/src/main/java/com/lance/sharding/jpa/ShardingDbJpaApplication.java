package com.lance.sharding.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * jpa sharding database
 *
 * @author lance
 * @date 2022/3/9 21:46
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ShardingDbJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingDbJpaApplication.class, args);
	}

}
