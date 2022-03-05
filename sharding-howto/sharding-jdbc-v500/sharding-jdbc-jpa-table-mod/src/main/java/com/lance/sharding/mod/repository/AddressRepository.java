package com.lance.sharding.mod.repository;

import com.lance.sharding.mod.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * address
 *
 * @author lance
 * @date 2022/2/19 11:50
 */
@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
