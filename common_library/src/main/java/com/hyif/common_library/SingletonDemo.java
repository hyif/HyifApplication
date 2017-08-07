package com.hyif.common_library;

/**
 * 描述: 单例模式模版
 * 作者: hyif
 * 创建日期：2017/7/16 on 17:06
 */

public class SingletonDemo {
    private static class SingletonHolder {
        private static final SingletonDemo INSTANCE = new SingletonDemo();
    }

    private SingletonDemo() {};

    public static SingletonDemo getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
