package com.lance.sharding.mod;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mybatis table sharding hash mod
 *
 * @author lance
 * @date 2022/3/5 16:19
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lance.sharding.mod.mapper")
public class MybatisTableModApplication {

  public static void main(String[] args) {
    SpringApplication.run(MybatisTableModApplication.class, args);
  }

}
