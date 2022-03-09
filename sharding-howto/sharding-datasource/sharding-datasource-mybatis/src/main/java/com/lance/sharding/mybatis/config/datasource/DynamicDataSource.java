package com.lance.sharding.mybatis.config.datasource;

import com.lance.sharding.mybatis.config.context.DbContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

/**
 * Dynamic DataSource
 *
 * @author lance
 * @date 2022/3/9 22:12
 */
public class DynamicDataSource extends AbstractRoutingDataSource {


	public DynamicDataSource(Map<Object, Object> targetDataSources) {
		super.setTargetDataSources(targetDataSources);
	}


	/**
	 * 从 DbContextHolder 中获取所需的数据源
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		return DbContextHolder.get();
	}
}
