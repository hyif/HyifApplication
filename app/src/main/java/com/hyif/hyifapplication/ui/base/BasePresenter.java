package com.hyif.hyifapplication.ui.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Presenter的基类
 *
 * @author: 黄一凡
 * @date: 2017-08-08
 */
public abstract class BasePresenter<T> {
  protected Reference<T> mViewRef;//View接口类型的弱引用

  /**
   * 建立关联
   *
   * @param view 这边的view指的是activity或者是fragment
   */
  public void attachView(T view) {
    this.mViewRef = new WeakReference<T>(view);
  }

  public void detachView() {
    if (mViewRef != null) {
      mViewRef.clear();
      mViewRef = null;
    }
  }

  /**
   * 获取View
   *
   * @return 返回View
   */
  protected T getView() {
    return mViewRef.get();
  }

  /**
   * 判断是否与view建立了关联
   *
   * @return 与view建立了关联
   */
  public boolean isViewAttached() {
    return mViewRef != null && mViewRef.get() != null;
  }
}
