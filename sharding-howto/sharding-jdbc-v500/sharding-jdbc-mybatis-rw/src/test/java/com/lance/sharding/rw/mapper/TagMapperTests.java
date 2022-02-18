package com.lance.sharding.rw.mapper;

import com.lance.sharding.rw.domain.Tag;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * TagMapper
 *
 * @author lance
 * @date 2022/2/18 13:47
 */
@Slf4j
@SpringBootTest
class TagMapperTests {
  @Autowired
  private TagMapper tagMapper;

  @Test
  void findAll() {
    List<Tag> list = tagMapper.findAll();
    log.info("===>{}", list);
  }
}
