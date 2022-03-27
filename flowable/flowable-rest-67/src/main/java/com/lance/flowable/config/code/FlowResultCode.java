package com.lance.flowable.config.code;

import com.lance.common.core.result.IResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Flowable定义错误码
 *
 * @author lance
 * @date 2022/3/26 19:14
 */
@Getter
@AllArgsConstructor
public enum FlowResultCode implements IResultCode {
  /**
   * 流程不存在
   */
  PROCESS_NOT_FOUND("800001", "操作对象不存在"),
  ;
  /**
   * code编码
   */
  final String code;
  /**
   * 中文信息描述
   */
  final String message;
}
