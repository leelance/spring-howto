package com.lance.sharding.interval;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mysql table interval
 *
 * @author lance
 * @date 2022/2/26 12:29
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lance.sharding.interval.mapper")
public class MybatisTableIntervalApplication {

  public static void main(String[] args) {
    SpringApplication.run(MybatisTableIntervalApplication.class, args);
  }

}
