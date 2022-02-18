package com.lance.sharding.rw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * sharding jdbc mybatis read write
 *
 * @author lance
 * @date 2022/2/18 11:28
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lance.sharding.rw.mapper")
public class MybatisRwApplication {

  public static void main(String[] args) {
    SpringApplication.run(MybatisRwApplication.class, args);
  }

}
