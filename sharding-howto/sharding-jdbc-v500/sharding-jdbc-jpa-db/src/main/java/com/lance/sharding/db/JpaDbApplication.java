package com.lance.sharding.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * jpa database application
 *
 * @author lance
 * @date 2022/2/20 22:00
 */
@SpringBootApplication
public class JpaDbApplication {

  public static void main(String[] args) {
    SpringApplication.run(JpaDbApplication.class, args);
  }

}
