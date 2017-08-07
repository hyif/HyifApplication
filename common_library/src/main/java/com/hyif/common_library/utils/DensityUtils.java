package com.hyif.common_library.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * 描述: 单位转换的辅助类
 * 作者: hyif
 * 创建日期：2017/7/16 on 20:30
 */

public class DensityUtils {
    /**
     * dp转px
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

}
