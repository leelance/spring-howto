package com.lance.sharding.tenant.config.prop;

import com.zaxxer.hikari.HikariConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * CustomDbProperties
 *
 * @author lance
 * @date 2022/3/9 21:57
 */
@Data
@ConfigurationProperties(prefix = CustomDbProperties.PREFIX)
public class CustomDbProperties {
  public static final String PREFIX = "com.multi";
  private Map<String, HikariConfig> db;
}
