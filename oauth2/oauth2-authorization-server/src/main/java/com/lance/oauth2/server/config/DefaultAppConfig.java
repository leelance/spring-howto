package com.lance.oauth2.server.config;

import com.lance.oauth2.server.common.helper.SnowflakeHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadLocalRandom;

/**
 * default app config
 *
 * @author lance
 * @date 2022/3/15 21:26
 */
@Configuration
public class DefaultAppConfig {
  private final static ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

  @Bean
  public SnowflakeHelper snowflakeHelper() {
    return new SnowflakeHelper(RANDOM.nextInt(0, 1023));
  }
}
