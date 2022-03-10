package com.lance.sharding.tenant.config;

import com.lance.sharding.tenant.config.datasource.CustomCurrentTenantIdentifierResolver;
import com.lance.sharding.tenant.config.datasource.DbMultiTenantConnectionProviderImpl;
import com.lance.sharding.tenant.config.prop.CustomDbProperties;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lance
 * @date 2022/3/9 21:56
 */
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(CustomDbProperties.class)
@EnableJpaRepositories(basePackages = {"com.lance.sharding.tenant.repository"}, transactionManagerRef = "txManager")
public class MultiDbConfig {
  private final CustomDbProperties customDbProperties;
  private final JpaProperties jpaProperties;

  @Bean
  public MultiTenantConnectionProvider multiTenantConnectionProvider() {
    Map<String, DataSource> map = new HashMap<>(8);
    customDbProperties.getDb().forEach((k, cfg) -> map.put(k, new HikariDataSource(cfg)));
    return new DbMultiTenantConnectionProviderImpl(map);
  }

  @Bean
  public CurrentTenantIdentifierResolver currentTenantIdentifierResolver() {
    return new CustomCurrentTenantIdentifierResolver();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
      MultiTenantConnectionProvider multiTenantConnectionProvider, CurrentTenantIdentifierResolver currentTenantIdentifierResolver) {

    Map<String, Object> hibernateProps = new LinkedHashMap<>(jpaProperties.getProperties());
    hibernateProps.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
    hibernateProps.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
    hibernateProps.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);
    hibernateProps.put(Environment.PHYSICAL_NAMING_STRATEGY, CamelCaseToUnderscoresNamingStrategy.class.getName());
    hibernateProps.put(Environment.IMPLICIT_NAMING_STRATEGY, SpringImplicitNamingStrategy.class.getName());

    // No dataSource is set to resulting entityManagerFactoryBean
    LocalContainerEntityManagerFactoryBean result = new LocalContainerEntityManagerFactoryBean();
    result.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    result.setJpaPropertyMap(hibernateProps);
    result.setPackagesToScan("com.lance.sharding.tenant.entity");

    return result;
  }

  @Bean
  public EntityManagerFactory entityManagerFactory(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
    return entityManagerFactoryBean.getObject();
  }

  @Bean
  public PlatformTransactionManager txManager(EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory);
    return transactionManager;
  }
}
