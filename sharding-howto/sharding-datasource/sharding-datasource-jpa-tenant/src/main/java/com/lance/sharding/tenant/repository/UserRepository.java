package com.lance.sharding.tenant.repository;

import com.lance.sharding.tenant.entity.UserEntity;
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
