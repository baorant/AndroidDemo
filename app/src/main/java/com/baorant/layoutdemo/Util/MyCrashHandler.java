package com.baorant.layoutdemo.Util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.baorant.layoutdemo.MainActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyCrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "MyCrashHandler";
    private static final boolean DEBUG = true;
    // 文件路径
    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + File.separator+ "crash";
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFEIX = ".trace";
    private static Thread.UncaughtExceptionHandler mDefaultCrashHandler;
    private static MyCrashHandler mMyCrashHandler = new MyCrashHandler();
    private Context mContext;

    private MyCrashHandler() {
    }

    public static MyCrashHandler getInstance() {
        return mMyCrashHandler;
    }

    public void init(Context context) {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Log.d(TAG, "do uncaughtException begin: " + ex.toString());
        try {
            //将文件写入sd卡
            writeToSdcard(ex);
            //写入后在这里可以进行上传操作
        } catch (IOException | PackageManager.NameNotFoundException e) {
            Log.d(TAG, "catch exception: " + e.toString());
        }
        restartMyApp();
//        //如果系统提供了默认异常处理就交给系统进行处理，否则自己进行处理。
//        if (mDefaultCrashHandler != null) {
//            Log.d(TAG, "mDefaultCrashHandler != null");
//            mDefaultCrashHandler.uncaughtException(thread, ex);
//        } else {
//            Log.d(TAG, "mDefaultCrashHandler == null");
//            android.os.Process.killProcess(android.os.Process.myPid());
//        }
    }

    private void restartMyApp() {
        Intent intent = new Intent();
        intent.setClass(mContext, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);

        PendingIntent pendingIntent = PendingIntent.getActivity(mContext,0,
                intent,PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC,System.currentTimeMillis() + 100, pendingIntent);

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    //将异常写入文件
    private void writeToSdcard(Throwable ex) throws IOException, PackageManager.NameNotFoundException {
        Log.d(TAG, "do writeToSdcard begin");
        //如果没有SD卡，直接返回
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        File filedir = new File(PATH);
        if (!filedir.exists()) {
            filedir.mkdirs();
        }
        long currenttime = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(currenttime));

        File exfile = new File(PATH +File.separator+FILE_NAME+time + FILE_NAME_SUFEIX);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(exfile)));
        Log.e("错误日志文件路径",""+exfile.getAbsolutePath());
        pw.println(time);
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        //当前版本号
        pw.println("App Version:" + pi.versionName + "_" + pi.versionCode);
        //当前系统
        pw.println("OS version:" + Build.VERSION.RELEASE + "_" + Build.VERSION.SDK_INT);
        //制造商
        pw.println("Vendor:" + Build.MANUFACTURER);
        //手机型号
        pw.println("Model:" + Build.MODEL);
        //CPU架构
        pw.println("CPU ABI:" + Build.CPU_ABI);

        ex.printStackTrace(pw);
        pw.close();
        Log.d(TAG, "do writeToSdcard end");
    }
}

