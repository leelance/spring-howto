package com.lance.sharding.complex;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mybatis table complex
 *
 * @author lance
 * @date 2022/2/23 20:32
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lance.sharding.complex.mapper")
public class MybatisTableComplexApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisTableComplexApplication.class, args);
	}

}
