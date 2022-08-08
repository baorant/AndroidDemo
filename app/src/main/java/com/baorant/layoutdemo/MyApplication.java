package com.baorant.layoutdemo;

import android.app.Application;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baorant.frameworkmodule.Util.DexFixUtils;
import com.baorant.frameworkmodule.Util.MyCrashHandler;
import com.baorant.layoutdemo.util.SharePreferenceUtil;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    private static MyApplication myApplication = null;
    private static RefWatcher sRefWatcher;

    public static MyApplication getApplication() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        myApplication = this;

        MyCrashHandler crashHandler= MyCrashHandler.getInstance();
        crashHandler.init(this);

//        if (CommonUtil.isDebugMode(this)) {
//            Log.d(TAG, "isApkInDebug");
//            ARouter.openLog();
//            ARouter.openDebug();
//        }

        ARouter.openLog();
        ARouter.openDebug();

        // 初始化路由框架
        ARouter.init(MyApplication.this);

        // 热修复
        if (SharePreferenceUtil.read("hotfixOpen").equals("1")) {
            Log.d(TAG, "hotfixOpen");
            DexFixUtils.loadFixedDex(this);
        }

        // 判断当前进程是否为LeakCanary进程，该进程运行一个HeapAnalyzerService服务
        // 如果不是，则初始化LeakCanary进程
        if (! LeakCanary.isInAnalyzerProcess(this)) {
            Log.d(TAG, "LeakCanary install");
            sRefWatcher = LeakCanary.install(this);
        }
    }

    public static RefWatcher getRefWatcher() {
        return sRefWatcher;
    }
}
