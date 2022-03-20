package com.lance.oauth2.server.common.result;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Optional;

/**
 * 统一API响应结果封装
 *
 * @author lance
 * @date 8/25/2021 10:27
 */
@Data
@NoArgsConstructor
public class R<T> implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * 默认为空消息
   */
  public static final String DEFAULT_NULL_MESSAGE = "暂无承载数据";
  /**
   * 默认成功消息
   */
  public static final String DEFAULT_SUCCESS_MESSAGE = "操作成功";
  /**
   * 默认失败消息
   */
  public static final String DEFAULT_FAILURE_MESSAGE = "操作失败";
  /**
   * 默认未授权消息
   */
  public static final String DEFAULT_UNAUTHORIZED_MESSAGE = "签名认证失败";
  /**
   * 错误码
   */
  private String code;
  /**
   * 是否成功, true/false
   */
  private boolean success;
  /**
   * 返回的业务数据
   */
  private T data;
  /**
   * 返回提示信息
   */
  private String msg;

  private R(IResultCode resultCode) {
    this(resultCode, null, resultCode.getMessage());
  }

  private R(IResultCode resultCode, String msg) {
    this(resultCode, null, msg);
  }

  private R(IResultCode resultCode, T data) {
    this(resultCode, data, resultCode.getMessage());
  }

  private R(IResultCode resultCode, T data, String msg) {
    this(resultCode.getCode(), data, msg);
  }

  private R(String code, T data, String msg) {
    this.code = code;
    this.data = data;
    this.msg = msg;

    this.success = ResultCode.SUCCESS.code.equals(code);
  }

  /**
   * 判断返回是否为成功
   *
   * @param result Result
   * @return 是否成功
   */
  public static boolean isSuccess(@Nullable R<?> result) {
    return Optional.ofNullable(result)
        .map(x -> ObjectUtils.nullSafeEquals(ResultCode.SUCCESS.code, x.code))
        .orElse(Boolean.FALSE);
  }

  /**
   * 判断返回是否为成功
   *
   * @param result Result
   * @return 是否成功
   */
  public static boolean isNotSuccess(@Nullable R<?> result) {
    return !R.isSuccess(result);
  }

  /**
   * 返回R
   *
   * @param data 数据
   * @param <T>  T 泛型标记
   * @return R
   */
  public static <T> R<T> data(T data) {
    return data(data, DEFAULT_SUCCESS_MESSAGE);
  }

  /**
   * 返回R
   *
   * @param data 数据
   * @param msg  消息
   * @param <T>  T 泛型标记
   * @return R
   */
  public static <T> R<T> data(T data, String msg) {
    return data(HttpServletResponse.SC_OK + "", data, msg);
  }

  /**
   * 返回R
   *
   * @param code 状态码
   * @param data 数据
   * @param msg  消息
   * @param <T>  T 泛型标记
   * @return R
   */
  public static <T> R<T> data(String code, T data, String msg) {
    return new R<>(code, data, data == null ? DEFAULT_NULL_MESSAGE : msg);
  }

  /**
   * 返回R
   *
   * @param msg 消息
   * @param <T> T 泛型标记
   * @return R
   */
  public static <T> R<T> success(String msg) {
    return new R<>(ResultCode.SUCCESS, msg);
  }

  /**
   * 返回R
   *
   * @param resultCode 业务代码
   * @param <T>        T 泛型标记
   * @return R
   */
  public static <T> R<T> success(IResultCode resultCode) {
    return new R<>(resultCode);
  }

  /**
   * 返回R
   *
   * @param resultCode 业务代码
   * @param msg        消息
   * @param <T>        T 泛型标记
   * @return R
   */
  public static <T> R<T> success(IResultCode resultCode, String msg) {
    return new R<>(resultCode, msg);
  }

  /**
   * 返回R
   *
   * @param msg 消息
   * @param <T> T 泛型标记
   * @return R
   */
  public static <T> R<T> fail(String msg) {
    return new R<>(ResultCode.FAILURE, msg);
  }


  /**
   * 返回R
   *
   * @param code 状态码
   * @param msg  消息
   * @param <T>  T 泛型标记
   * @return R
   */
  public static <T> R<T> fail(String code, String msg) {
    return new R<>(code, null, msg);
  }

  /**
   * 返回R
   *
   * @param resultCode 业务代码
   * @param <T>        T 泛型标记
   * @return R
   */
  public static <T> R<T> fail(IResultCode resultCode) {
    return new R<>(resultCode);
  }

  /**
   * 返回R
   *
   * @param resultCode 业务代码
   * @param msg        消息
   * @param <T>        T 泛型标记
   * @return R
   */
  public static <T> R<T> fail(IResultCode resultCode, String msg) {
    return new R<>(resultCode, msg);
  }

  /**
   * 返回R
   *
   * @param flag 成功状态
   * @return R
   */
  public static <T> R<T> status(boolean flag) {
    return flag ? success(DEFAULT_SUCCESS_MESSAGE) : fail(DEFAULT_FAILURE_MESSAGE);
  }
}
