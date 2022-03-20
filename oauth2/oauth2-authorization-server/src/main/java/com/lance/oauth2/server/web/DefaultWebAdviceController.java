package com.lance.oauth2.server.web;

import com.lance.oauth2.server.common.result.R;
import com.lance.oauth2.server.common.result.ResultCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * custom define exception
 *
 * @author lance
 * @date 2022/3/19 15:04
 */
@Slf4j
@AllArgsConstructor
@RestControllerAdvice
public class DefaultWebAdviceController {

  /**
   * 自定义业务错误, 类型
   *
   * @param ex ServiceException
   * @return 返回异常
   */
  @ExceptionHandler(Exception.class)
  public R<String> handleException(Exception ex) {
    if (log.isInfoEnabled()) {
      log.info("biz exception[{}], fail: ", ex.getMessage(), ex);
    }
    return R.fail(ResultCode.INTERNAL_SERVER_ERROR);
  }
}
