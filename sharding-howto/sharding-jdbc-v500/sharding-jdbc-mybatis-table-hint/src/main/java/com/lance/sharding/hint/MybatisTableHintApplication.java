package com.lance.sharding.hint;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * sharding jdbc hint
 *
 * @author lance
 * @date 2022/2/24 15:34
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lance.sharding.hint.mapper")
public class MybatisTableHintApplication {

  public static void main(String[] args) {
    SpringApplication.run(MybatisTableHintApplication.class, args);
  }

}
