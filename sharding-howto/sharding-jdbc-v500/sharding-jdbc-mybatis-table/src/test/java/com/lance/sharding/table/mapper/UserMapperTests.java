package com.lance.sharding.table.mapper;

import com.lance.sharding.table.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;
import java.util.stream.IntStream;

/**
 * user mapper
 *
 * @author lance
 * @date 2022/2/20 21:08
 */
@Slf4j
@SpringBootTest
class UserMapperTests {
  @Autowired
  private UserMapper userMapper;

  @Test
  @Disabled
  void save() {
    IntStream.range(0, 20).forEach(i -> {
      User user = new User();
      user.setUsername("user0" + i);
      user.setPwd(UUID.randomUUID().toString().replaceAll("-", ""));
      user.setCreator(user.getUsername());
      user.setUpdater(user.getUpdater());
      userMapper.save(user);
    });
  }
}
