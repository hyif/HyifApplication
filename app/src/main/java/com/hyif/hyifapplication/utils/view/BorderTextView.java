package com.hyif.hyifapplication.utils.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 在此写用途
 *
 * @author: 黄一凡
 * @date: 2017-07-17
 */
public class BorderTextView extends TextView {
  private int mBgColor;
  public BorderTextView(Context context) {
    this(context, null);
  }

  public BorderTextView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public BorderTextView(Context context, AttributeSet attrs,
      int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    getAttr(attrs);
  }

  private void getAttr(AttributeSet attrs) {

  }
}
