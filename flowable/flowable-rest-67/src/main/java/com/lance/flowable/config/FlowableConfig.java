package com.lance.flowable.config;

import com.lance.flowable.config.flow.CustomIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * config flowable
 *
 * @author lance
 * @date 2022/3/26 09:35
 */
@Slf4j
@Configuration
public class FlowableConfig {

  @Bean
  public EngineConfigurationConfigurer<SpringProcessEngineConfiguration> engineConfigurationConfigurer() {
    return engineConfiguration -> {
      engineConfiguration.setIdGenerator(new CustomIdGenerator());
    };
  }
}
