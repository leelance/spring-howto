package com.lance.flowable.service;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.util.Map;

/**
 * PublishArticleService
 *
 * @author lance
 * @date 2022/3/26 09:50
 */
@Slf4j
public class PublishArticleService implements JavaDelegate {
  @Override
  public void execute(DelegateExecution execution) {
    Map<String, Object> params = execution.getVariables();

    log.info("===>Publishing[{}] the approved article.", params);
  }
}
