package com.lance.sharding.table;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mybatis table
 *
 * @author lance
 * @date 2022/2/20 22:22
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lance.sharding.table.mapper")
public class MybatisTableApplication {

  public static void main(String[] args) {
    SpringApplication.run(MybatisTableApplication.class, args);
  }

}
