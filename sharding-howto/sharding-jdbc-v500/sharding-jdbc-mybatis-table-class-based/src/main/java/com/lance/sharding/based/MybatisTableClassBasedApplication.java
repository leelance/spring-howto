package com.lance.sharding.based;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mysql table custom class based
 *
 * @author lance
 * @date 2022/3/5 21:24
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lance.sharding.based.mapper")
public class MybatisTableClassBasedApplication {

  public static void main(String[] args) {
    SpringApplication.run(MybatisTableClassBasedApplication.class, args);
  }

}
