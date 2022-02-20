package com.lance.sharding.db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mybatis db application
 *
 * @author lance
 * @date 2022/2/20 21:06
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lance.sharding.db.mapper")
public class MybatisDbApplication {

  public static void main(String[] args) {
    SpringApplication.run(MybatisDbApplication.class, args);
  }

}
