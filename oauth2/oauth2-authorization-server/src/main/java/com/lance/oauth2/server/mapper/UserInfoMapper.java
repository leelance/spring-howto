package com.lance.oauth2.server.mapper;

import com.lance.oauth2.server.domain.UserInfo;

/**
 * user info mapper
 *
 * @author lance
 * @date 2022/3/25 17:33
 */
public interface UserInfoMapper {

  /**
   * save user
   *
   * @param info info
   */
  void save(UserInfo info);

  /**
   * fine one
   *
   * @param userId userId
   * @return UserInfo
   */
  UserInfo findOne(long userId);

  /**
   * find by username
   *
   * @param username username
   * @return userInfo
   */
  UserInfo findByUsername(String username);

  /**
   * 根据id删除
   *
   * @param userId 用户id
   * @return row line
   */
  int deleteOne(long userId);
}
