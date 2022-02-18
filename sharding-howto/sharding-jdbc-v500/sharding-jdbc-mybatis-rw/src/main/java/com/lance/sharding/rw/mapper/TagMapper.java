package com.lance.sharding.rw.mapper;

import com.lance.sharding.rw.domain.Tag;

import java.util.List;

/**
 * tag mapper
 *
 * @author lance
 * @date 2022/2/18 13:46
 */
public interface TagMapper {

  /**
   * find all
   *
   * @return list
   */
  List<Tag> findAll();
}
