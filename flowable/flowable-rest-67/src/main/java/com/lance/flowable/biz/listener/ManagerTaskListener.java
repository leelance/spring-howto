package com.lance.flowable.biz.listener;

import lombok.extern.slf4j.Slf4j;
import org.flowable.task.service.delegate.DelegateTask;
import org.flowable.task.service.delegate.TaskListener;

/**
 * Manager task
 *
 * @author lance
 * @date 2022/3/27 15:03
 */
@Slf4j
public class ManagerTaskListener implements TaskListener {

  @Override
  public void notify(DelegateTask delegateTask) {
    log.info("===>listener Manager");
    delegateTask.setAssignee("Manager");
  }
}
