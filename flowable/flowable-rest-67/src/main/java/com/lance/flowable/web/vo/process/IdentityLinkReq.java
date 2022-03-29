package com.lance.flowable.web.vo.process;

import lombok.Data;

/**
 * 新增候选人和分组
 *
 * @author lance
 * @date 2022/3/29 10:02
 */
@Data
public class IdentityLinkReq {
  /**
   * If the identity link involves a user, then this will be a non-null id of a user. That userId can be used to query for user information through the UserQuery API.
   */
  private String user;
  /**
   * If the identity link involves a group, then this will be a non-null id of a group. That groupId can be used to query for user information through the GroupQuery API.
   */
  private String groupId;
}
