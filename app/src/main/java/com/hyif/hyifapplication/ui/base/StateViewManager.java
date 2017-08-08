package com.hyif.hyifapplication.ui.base;

import android.content.Context;
import android.view.ViewStub;

/**
 * 各种状态视图管理
 *
 * @author: 黄一凡
 * @date: 2017-08-08
 */
public class StateViewManager {
  public static final class Build {
    private Context context;
    private int loadingLayouResId;
    private int contentLayouResId;
    private ViewStub netWorkErrorVs;
    private int netWorkErrorRetryViewId;



  }
}
