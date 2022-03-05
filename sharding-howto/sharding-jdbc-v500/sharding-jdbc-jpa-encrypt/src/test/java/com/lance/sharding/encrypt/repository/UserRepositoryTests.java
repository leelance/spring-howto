package com.lance.sharding.encrypt.repository;

import com.lance.sharding.encrypt.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

/**
 * UserRepository
 *
 * @author lance
 * @date 2022/3/5 23:27
 */
@Slf4j
@SpringBootTest
class UserRepositoryTests {
  @Autowired
  private UserRepository userRepository;

  @Test
  @Disabled
  void save() {
    IntStream.range(0, 20).forEach(i -> {
      UserEntity user = new UserEntity();
      user.setUsername("user00" + i);
      user.setPwd("pwd@" + i);
      user.setCreator(user.getUsername());
      user.setUpdater(user.getUpdater());
      userRepository.save(user);
    });
  }

  @Test
  @Disabled
  void findByUsername() {
    String username = "user001";
    UserEntity user = userRepository.findByUsername(username);
    log.info("===>{}", user);
  }
}
