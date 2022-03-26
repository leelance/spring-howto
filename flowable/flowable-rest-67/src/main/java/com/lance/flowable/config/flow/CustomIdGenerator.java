package com.lance.flowable.config.flow;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.flowable.common.engine.impl.cfg.IdGenerator;
import org.flowable.common.engine.impl.persistence.StrongUuidGenerator;

/**
 * custom id generator
 *
 * @author lance
 * @date 2022/3/26 11:43
 */
public class CustomIdGenerator implements IdGenerator {
  /**
   * different ProcessEngines on the same classloader share one generator.
   */
  protected static volatile TimeBasedGenerator timeBasedGenerator;

  public CustomIdGenerator() {
    ensureGeneratorInitialized();
  }

  protected void ensureGeneratorInitialized() {
    if (timeBasedGenerator == null) {
      synchronized (StrongUuidGenerator.class) {
        if (timeBasedGenerator == null) {
          timeBasedGenerator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
        }
      }
    }
  }

  @Override
  public String getNextId() {
    return timeBasedGenerator.generate().toString().replaceAll("-", "");
  }
}
