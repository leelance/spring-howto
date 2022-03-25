package com.lance.oauth2.server.mapper;

import com.lance.oauth2.server.common.helper.SnowflakeHelper;
import com.lance.oauth2.server.common.json.JsonUtils;
import com.lance.oauth2.server.domain.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * UserInfoMapper
 *
 * @author lance
 * @date 2022/3/25 17:46
 */
@Slf4j
@SpringBootTest
class UserInfoMapperTests {
  @Autowired
  private SnowflakeHelper snowflakeHelper;
  @Autowired
  private UserInfoMapper userInfoMapper;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  @Disabled
  void save() {
    String password = "abc@123456";
    UserInfo info = new UserInfo();
    info.setUserId(snowflakeHelper.nextId());
    info.setUsername("lance");
    info.setPassword(passwordEncoder.encode(password));
    info.setStatus(1);
    info.setCreateId(0);
    info.setUpdateId(0);

    userInfoMapper.save(info);
  }

  @Test
  @Disabled
  void findOne() {
    long userId = 956858485126447104L;
    UserInfo info = userInfoMapper.findOne(userId);
    log.info("===>{}", JsonUtils.toJsonString(info));
  }

  @Test
  void findByUsername() {
    String username = "lance";
    UserInfo info = userInfoMapper.findByUsername(username);
    log.info("===>{}", JsonUtils.toJsonString(info));
  }

  @Test
  @Disabled
  void deleteOne() {
    long userId = 0L;
    int result = userInfoMapper.deleteOne(userId);
    log.info("===>{}", result);
  }
}
