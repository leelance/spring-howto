package com.lance.sharding.rw.repository;

import com.lance.sharding.rw.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * tag
 *
 * @author lance
 * @date 2022/2/19 11:50
 */
@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {
}
