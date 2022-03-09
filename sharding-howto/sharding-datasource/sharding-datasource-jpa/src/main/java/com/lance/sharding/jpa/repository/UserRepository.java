package com.lance.sharding.jpa.repository;

import com.lance.sharding.jpa.entity.UserEntity;
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
}
