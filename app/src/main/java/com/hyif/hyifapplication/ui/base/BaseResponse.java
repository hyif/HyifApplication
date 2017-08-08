package com.hyif.hyifapplication.ui.base;

/**
 * 数据返回基类
 *
 * @author: 黄一凡
 * @date: 2017-08-08
 */
public class BaseResponse<T> {
  private int code;
  private String message;
  private T data;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
