package com.hyif.hyifapplication.ui.base;

import android.os.Bundle;

/**
 * 在此写用途
 *
 * @author: 黄一凡
 * @date: 2017-08-08
 */
public abstract class BaseLazyFragment extends BaseFragment {
  private boolean isFirstResume = true;
  private boolean isFirstVisible = true;
  private boolean isFirstInvisible = true;
  private boolean isPrepared;

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    initPrepare();
  }

  private synchronized void initPrepare() {
    if (isPrepared) {
      onFirstUserVisible();
    } else {
      isPrepared = true;
    }
  }

  @Override
  public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (isVisibleToUser) {
      if (isFirstVisible) {
        isFirstVisible = false;
        initPrepare();
      } else {
        onUserVisible();
      }
    } else {
      if (isFirstInvisible) {
        isFirstInvisible = false;
        onFirstUserInvisible();
      } else {
        onUserInvisible();
      }
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    if (isFirstResume) {
      isFirstResume = false;
      return;
    }
    if (getUserVisibleHint()) {
      onUserVisible();
    }
  }

  @Override
  public void onPause() {
    super.onPause();
    if (getUserVisibleHint()) {
      onUserInvisible();
    }
  }

  /**
   * 当Fragment第一次显示的时候，调用该方法进行初始化的操作。
   */
  protected void onFirstUserVisible() {
    onLazyLoadData();
  }

  protected abstract void onLazyLoadData();

  protected void onUserVisible() {}

  private void onFirstUserInvisible() { }

  protected void onUserInvisible() {}

}
