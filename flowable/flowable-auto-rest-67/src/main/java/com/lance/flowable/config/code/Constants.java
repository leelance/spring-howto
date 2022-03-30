package com.lance.flowable.config.code;

/**
 * 常量定义
 *
 * @author lance
 * @date 2022/3/29 00:39
 */
public interface Constants {
  /**
   * 暂停
   */
  String SUSPEND = "suspend";
  /**
   * 激活
   */
  String ACTIVATE = "activate";
  /**
   * default url
   */
  String DEFAULT_URL = "default";

  interface Family {
    /**
     * 暂停
     */
    String USER_ID = "user";
    /**
     * 激活
     */
    String GROUP_ID = "group";
  }
}
