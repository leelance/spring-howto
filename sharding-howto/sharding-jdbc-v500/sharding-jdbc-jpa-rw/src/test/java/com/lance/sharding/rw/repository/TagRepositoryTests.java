package com.lance.sharding.rw.repository;

import com.lance.sharding.rw.entity.TagEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * TagRepository
 *
 * @author lance
 * @date 2022/2/19 11:56
 */
@Slf4j
@SpringBootTest
class TagRepositoryTests {
  @Autowired
  private TagRepository tagRepository;

  @Test
  void findAll() {
    List<TagEntity> list = tagRepository.findAll();
    log.info("===>{}", list);
  }
}
