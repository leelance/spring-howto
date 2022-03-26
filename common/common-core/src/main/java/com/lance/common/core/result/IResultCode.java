package com.lance.common.core.result;

import lombok.AllArgsConstructor;

import java.io.Serializable;

/**
 * 错误码定义接口
 *
 * @author lance
 * @date 2021/9/18 17:31
 */
public interface IResultCode extends Serializable {

  /**
   * 消息
   *
   * @return String
   */
  String getMessage();

  /**
   * 状态码
   *
   * @return int
   */
  String getCode();

  /**
   * 服务错误码前缀
   */
  @AllArgsConstructor
  enum P {
    /**
     * Rms系统
     */
    RM("101", "Rms系统"),
    MA_ADMIN("102", "mall-admin系统"),
    MA_PORT("103", "mall-portal系统"),
    MA_DATA("104", "mall-data系统"),
    MA_SEARCH("105", "mall-search系统"),
    JZ_OPS("106", "zcs-jzsy-ops-new系统"),
    JZ_TRACE("107", "zcs-jzsy-trace系统"),
    JZ_APPS("108", "zcs-jzsy-apps系统"),
    JZ_CODER("109", "zcs-jzsy-coder系统"),
    ZIM("110", "zim系统"),
    USER_CENTER("1011", "user-center系统"),
    PAY("112", "lgm-pay系统"),
    TSM("113", "lgm-tsm系统"),
    SDZW("114", "lgm-sdzw系统"),
    CARD_SERVER("115", "lgm-card-server系统"),
    HSM("116", "HSM系统"),
    MESSAGE("117", "lgm-message系统"),
    WALLET_APPS("118", "zcs-wallet-apps系统"),
    COM_CONFIG("119", "zcs-common-config系统"),
    OPEN_SE("120", "zcs-open-biz-se系统"),
    OPEN_SE_GW("121", "zcs-open-gateway-se系统"),
    GLOBAL_PERM("122", "zcs-global-permission针对B端用户信息"),
    BILL_CENTER("123", "zcs-bill-center账单中心"),
    APPS("124", "lgm-apps系统"),
    ADHOC_SNMP("125", "zcs-adhoc-snmp自主网系统"),
    AMS("126", "zcs-ams系统"),
    APP_COMMON("127", "lgm_app_common系统"),
    APP_MANAGE("128", "lgm-app-manage系统"),
    COMMUNITY("129", "lgm-community智慧社区系统"),
    MONITOR("130", "lgm-monitor监控系统"),
    PLATFORM("131", "lgm-platform卡证管理系统"),
    SMART_LOCK("132", "lgm-smart-lock自能锁系统"),
    TASK_DIRECTOR("133", "lgm-task-director系统"),
    TJ_POS("134", "lgm-tjposp天津通系统"),
    HIK("135", "lgm-zcs-hik硬件对接系统"),
    LGM_AGENT("136", "legion-agent系统"),
    CERT_AUTH("137", "lgm-cert-auth交换系统"),
    FILE("138", "lgm-file公用文件系统"),
    MAKE_KEY("139", "lgm-make-key交换系统"),
    MAKE_VCARD("140", "lgm-make-vcard交换系统"),
    GW("141", "lgm-spring-gateway最外层网关"),
    BBS("142", "zcs-server-bbs社区论坛"),
    ;

    private final String prefix;
    private final String desc;

    public String prefix() {
      return this.prefix;
    }
  }
}
