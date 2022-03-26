package com.lance.flowable.common;

import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.impl.cfg.IdGenerator;
import org.flowable.common.engine.impl.persistence.StrongUuidGenerator;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

/**
 * IdGenerator
 *
 * @author lance
 * @date 2022/3/26 11:32
 * @see org.flowable.common.engine.impl.persistence.StrongUuidGenerator
 */
@Slf4j
class IdGeneratorTests {
  private final IdGenerator idGenerator = new StrongUuidGenerator();


  @Test
  void getNextId() {
    IntStream.range(0, 20).forEach(i -> log.info("===>id: {}", idGenerator.getNextId()));
  }
}
