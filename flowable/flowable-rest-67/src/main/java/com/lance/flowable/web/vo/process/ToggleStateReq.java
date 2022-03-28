package com.lance.flowable.web.vo.process;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * process definition toggle state
 *
 * @author lance
 * @date 2022/3/29 00:31
 */
@Data
public class ToggleStateReq {
  /**
   * Action to perform. Either activate or suspend.
   */
  private String action;
  /**
   * Whether or not to suspend/activate running process-instances for this process-definition.
   * If omitted, the process-instances are left in the state they are.
   */
  private boolean includeProcessInstances;
  /**
   * Date (ISO-8601) when the suspension/activation should be executed.
   * If omitted, the suspend/activation is effective immediately.
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date date;
}
