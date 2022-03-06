package com.lance.sharding.file;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mybatis table column encrypt
 *
 * @author lance
 * @date 2022/3/5 16:19
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lance.sharding.file.mapper")
public class MybatisModeFileApplication {

  public static void main(String[] args) {
    SpringApplication.run(MybatisModeFileApplication.class, args);
  }

}
