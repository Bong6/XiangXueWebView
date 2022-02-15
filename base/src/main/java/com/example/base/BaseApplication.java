package com.example.base;

import android.app.Application;


/*
*           防止内存泄漏
*                   ->所有不需要使用UI的context可以使用这个context
*                   ->这个BaseApplication的生命周期是跟随整个应用的
*
* */
public class BaseApplication extends Application {
    public static Application sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
