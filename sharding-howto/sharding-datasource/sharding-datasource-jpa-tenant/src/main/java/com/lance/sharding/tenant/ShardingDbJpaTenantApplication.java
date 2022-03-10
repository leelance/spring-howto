package com.lance.sharding.tenant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * jpa sharding tenant database
 *
 * @author lance
 * @date 2022/3/10 18:46
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ShardingDbJpaTenantApplication {

  public static void main(String[] args) {
    SpringApplication.run(ShardingDbJpaTenantApplication.class, args);
  }

}
