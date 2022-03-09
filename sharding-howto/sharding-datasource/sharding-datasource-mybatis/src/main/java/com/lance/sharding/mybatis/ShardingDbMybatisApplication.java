package com.lance.sharding.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * mybatis sharding database
 *
 * @author lance
 * @date 2022/3/9 21:46
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = "com.lance.sharding.mybatis.mapper")
public class ShardingDbMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingDbMybatisApplication.class, args);
	}

}
