package com.hyif.hyifapplication.ui.base;

/**
 * 在此写用途
 *
 * @author: 黄一凡
 * @date: 2017-08-08
 */
public abstract class BaseLazyMVPFragment<P extends BasePresenter> extends BaseLazyFragment {
  protected P mPresenter;

  protected abstract P createPresenter();

  @Override
  public void onDestroy() {
    if (mPresenter.isViewAttached()) {
      mPresenter.detachView();
    }
    super.onDestroy();
  }
}
