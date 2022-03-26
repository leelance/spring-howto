package com.lance.common.core.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.HttpServletResponse;

/**
 * 定义基本的错误码, 参考HttpServlet
 *
 * @author lance
 * @date 2021/10/25 11:26
 */
@Getter
@AllArgsConstructor
public enum ResultCode implements IResultCode {

  /**
   * 操作成功
   */
  SUCCESS(HttpServletResponse.SC_OK + "", "操作成功", "Success"),

  /**
   * 业务异常
   */
  FAILURE(HttpServletResponse.SC_BAD_REQUEST + "", "业务异常", "Failure"),

  /**
   * 请求未授权
   */
  UN_AUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED + "", "请求未授权", "unauthorized"),

  /**
   * 404 没找到请求
   */
  NOT_FOUND(HttpServletResponse.SC_NOT_FOUND + "", "404 没找到请求", "Not found"),

  /**
   * 消息不能读取
   */
  MSG_NOT_READABLE(HttpServletResponse.SC_BAD_REQUEST + "", "消息不能读取", "Not readable"),

  /**
   * 不支持当前请求方法
   */
  METHOD_NOT_SUPPORTED(HttpServletResponse.SC_METHOD_NOT_ALLOWED + "", "不支持当前请求方法", "Not supported"),

  /**
   * 不支持当前媒体类型
   */
  MEDIA_TYPE_NOT_SUPPORTED(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE + "", "不支持当前媒体类型", "Media type not supported"),

  /**
   * 请求被拒绝
   */
  REQ_REJECT(HttpServletResponse.SC_FORBIDDEN + "", "请求被拒绝", "Request forbidden"),

  /**
   * 服务器异常
   */
  INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR + "", "数据操作错误, 请联系管理员", "Operation fail, please contact the admin"),

  /**
   * 缺少必要的请求参数
   */
  PARAM_MISS(HttpServletResponse.SC_BAD_REQUEST + "", "缺少必要的请求参数", "Params bad request"),

  /**
   * 请求参数类型错误
   */
  PARAM_TYPE_ERROR(HttpServletResponse.SC_BAD_REQUEST + "", "请求参数类型错误", "Params bad request"),

  /**
   * 请求参数绑定错误
   */
  PARAM_BIND_ERROR(HttpServletResponse.SC_BAD_REQUEST + "", "请求参数绑定错误", "Params bind reuqest"),

  /**
   * 参数校验失败
   */
  PARAM_VALID_ERROR(HttpServletResponse.SC_BAD_REQUEST + "", "参数校验失败", "Params valid fail"),
  /**
   * 参数校验失败
   */
  VALID_CODE_ERROR(HttpServletResponse.SC_BAD_REQUEST + "", "验证码错误", "Params valid fail"),
  /**
   * 数据重复保存
   */
  DUPLICATE_KEY_ERROR("600", "数据重复保存", "Data duplicate"),
  /**
   * 远程请求错误
   */
  FEIGN_ERROR("601", "远程请求错误", "feign request fail"),
  ;

  /**
   * code编码
   */
  final String code;
  /**
   * 中文信息描述
   */
  final String message;
  /**
   * 英文信息
   *
   * @since 2021.10.24
   */
  final String messageEn;
}
