package com.lance.sharding.boundary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mybatis table sharding boundary range
 *
 * @author lance
 * @date 2022/2/28 16:32
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lance.sharding.boundary.mapper")
public class MybatisTableBoundaryRangeApplication {

  public static void main(String[] args) {
    SpringApplication.run(MybatisTableBoundaryRangeApplication.class, args);
  }

}
