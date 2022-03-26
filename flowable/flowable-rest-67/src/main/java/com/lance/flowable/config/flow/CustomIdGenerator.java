package com.lance.flowable.config.flow;

import com.lance.common.core.id.IdHelper;
import org.flowable.common.engine.impl.cfg.IdGenerator;

/**
 * custom id generator
 *
 * @author lance
 * @date 2022/3/26 11:43
 */
public class CustomIdGenerator implements IdGenerator {
  @Override
  public String getNextId() {
    return IdHelper.id("F");
  }
}
