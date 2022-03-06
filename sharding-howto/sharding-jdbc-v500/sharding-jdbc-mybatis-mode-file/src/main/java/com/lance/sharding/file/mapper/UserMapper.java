package com.lance.sharding.file.mapper;

import com.lance.sharding.file.domain.User;

import java.util.List;

/**
 * user mapper
 *
 * @author lance
 * @date 2022/2/20 20:57
 */
public interface UserMapper {
  /**
   * save info
   *
   * @param info info
   */
  void save(User info);

  /**
   * 根据用户姓名查询
   *
   * @param username username
   * @return User
   */
  User findByUsername(String username);

  /**
   * delete by id
   *
   * @param userId userId
   */
  void delete(int userId);

  /**
   * list user
   *
   * @return list
   */
  List<User> findAll();
}
