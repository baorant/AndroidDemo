package com.baorant.layoutdemo;

import android.Manifest;
import android.app.Application;
import android.os.Build;

import com.baorant.layoutdemo.Util.MyCrashHandler;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MyCrashHandler crashHandler= MyCrashHandler.getInstance();
        crashHandler.init(this);
    }
}
