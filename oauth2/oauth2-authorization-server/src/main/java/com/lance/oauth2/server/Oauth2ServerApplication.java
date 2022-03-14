package com.lance.oauth2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 基于spring-authorization-server服务进行开发
 *
 * @author lance
 * @date 2022/3/14 22:26
 */
@SpringBootApplication
public class Oauth2ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2ServerApplication.class, args);
	}

}
