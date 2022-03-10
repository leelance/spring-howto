package com.lance.sharding.tenant.config.datasource;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 自定义多租户provider
 *
 * @author lance
 * @date 2022/3/10 18:52
 */
@Slf4j
@AllArgsConstructor
public class DbMultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
  private final Map<String, DataSource> dataSourceMap;

  @Override
  protected DataSource selectAnyDataSource() {
    return this.dataSourceMap.values().iterator().next();
  }

  @Override
  protected DataSource selectDataSource(String tenantIdentifier) {
    return this.dataSourceMap.get(tenantIdentifier);
  }
}
