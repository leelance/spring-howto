package com.lance.sharding.encrypt.repository;

import com.lance.sharding.encrypt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * user
 *
 * @author lance
 * @date 2022/2/19 11:50
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  /**
   * find by username
   *
   * @param username username
   * @return user
   */
  UserEntity findByUsername(String username);
}
