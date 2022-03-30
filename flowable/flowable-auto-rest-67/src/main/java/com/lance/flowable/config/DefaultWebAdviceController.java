package com.lance.flowable.config;

import com.lance.common.core.result.R;
import com.lance.flowable.config.code.FlowResultCode;
import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.api.FlowableObjectNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 自定义异常处理类
 *
 * @author lance
 * @date 2022/3/26 19:13
 */
@Slf4j
@RestControllerAdvice
public class DefaultWebAdviceController {

  /**
   * 流程不存在
   *
   * @param ex FlowableObjectNotFoundException
   * @return R<String>
   */
  @ExceptionHandler(FlowableObjectNotFoundException.class)
  public R<String> handlerFlowableObjectNotFoundException(FlowableObjectNotFoundException ex) {
    if (log.isInfoEnabled()) {
      log.info("process not found exception: {}", ex.getMessage());
    }

    return R.fail(FlowResultCode.PROCESS_NOT_FOUND);
  }
}
