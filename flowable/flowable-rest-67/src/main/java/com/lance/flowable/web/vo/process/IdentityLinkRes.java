package com.lance.flowable.web.vo.process;

import com.lance.flowable.config.code.Constants;
import lombok.Data;
import org.flowable.identitylink.api.IdentityLink;

/**
 * <pre>
 *      {
 *       "url":"http://localhost:8182/repository/process-definitions/oneTaskProcess%3A1%3A4/identitylinks/groups/admin",
 *       "user":null,
 *       "group":"admin",
 *       "type":"candidate"
 *    }
 * </pre>
 * 候选人记录
 *
 * @author lance
 * @date 2022/3/29 10:06
 */
@Data
public class IdentityLinkRes {
  private String url;
  private String user;
  private String group;
  private String type;

  /**
   * 类型转换 IdentityLink -> IdentityLinkRes
   *
   * @param link IdentityLink
   * @return IdentityLinkRes
   */
  public static IdentityLinkRes convert(IdentityLink link) {
    if (link == null) {
      return null;
    }

    IdentityLinkRes res = new IdentityLinkRes();
    res.setUrl(Constants.DEFAULT_URL);
    res.setUser(link.getUserId());
    res.setGroup(link.getGroupId());
    res.setType(link.getType());
    return res;
  }
}