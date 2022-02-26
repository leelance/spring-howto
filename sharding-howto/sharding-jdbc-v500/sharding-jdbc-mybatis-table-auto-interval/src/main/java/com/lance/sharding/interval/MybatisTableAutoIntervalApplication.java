package com.lance.sharding.interval;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mybatis table sharding auto interval
 *
 * @author lance
 * @date 2022/2/27 00:04
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lance.sharding.interval.mapper")
public class MybatisTableAutoIntervalApplication {

  public static void main(String[] args) {
    SpringApplication.run(MybatisTableAutoIntervalApplication.class, args);
  }

}
