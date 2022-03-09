package com.lance.sharding.mybatis.config;

import com.lance.sharding.mybatis.config.datasource.DynamicDataSource;
import com.lance.sharding.mybatis.config.prop.CustomDbProperties;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lance
 * @date 2022/3/9 21:56
 */
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(CustomDbProperties.class)
public class MultiDbConfig {
	private final CustomDbProperties customDbProperties;

	@Bean
	public DynamicDataSource dataSource() {
		Map<Object, Object> map = new HashMap<>(8);
		customDbProperties.getDb().forEach((k, cfg) -> map.put(k, new HikariDataSource(cfg)));
		return new DynamicDataSource(map);
	}
}
