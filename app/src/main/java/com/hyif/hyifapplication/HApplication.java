package com.hyif.hyifapplication;

import android.app.Application;
import android.content.Context;

/**
 * 描述:
 * 作者: hyif
 * 创建日期：2017/7/16 on 17:02
 */

public class HApplication extends Application {
    private static HApplication sHApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sHApplication = this;
    }

    public static HApplication getInstance() {
        return sHApplication;
    }

    public static Context getContext() {
        return sHApplication.getApplicationContext();
    }
}
