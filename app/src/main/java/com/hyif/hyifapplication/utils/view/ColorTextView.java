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
public class ColorTextView extends TextView {
  public ColorTextView(Context context) {
    this(context, null);
  }

  public ColorTextView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ColorTextView(Context context, AttributeSet attrs,
      int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    getAttr(attrs);
  }

  private void getAttr(AttributeSet attrs) {

  }
}
