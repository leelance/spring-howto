package com.lance.sharding.volume;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mybatis table sharding volume range
 *
 * @author lance
 * @date 2022/3/4 13:39
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lance.sharding.volume.mapper")
public class MybatisTableVolumeRangeApplication {

  public static void main(String[] args) {
    SpringApplication.run(MybatisTableVolumeRangeApplication.class, args);
  }

}
