package com.lance.common.core.id;

import lombok.experimental.UtilityClass;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Id helper
 *
 * @author lance
 * @date 2021/9/26 10:50
 */
@UtilityClass
public class IdHelper {
  public final static ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
  private final static Snowflake SNOW_FLAKE = new Snowflake(RANDOM.nextInt(0, 1023));

  /**
   * 产生Snowflake.id
   *
   * @return id
   */
  public static long id() {
    return SNOW_FLAKE.nextId();
  }

  /**
   * 产生ID
   *
   * @param prefix 前缀
   * @return id
   */
  public static String id(String prefix) {
    return prefix + SNOW_FLAKE.nextId();
  }
}
