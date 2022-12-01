package com.baorant.layoutdemo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baorant.frameworkmodule.activity.AbstractSubActivity;
import com.baorant.layoutdemo.R;
import com.baorant.frameworkmodule.Util.ThreadPoolUtil;

@Route(path = "/base/HandlerThreadActivity")
public class HandlerThreadActivity extends AbstractSubActivity {
    private static final String TAG = "HandlerThreadActivity";
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        button = findViewById(R.id.btn1);

        Thread.currentThread().setName("主线程");
        Log.d(TAG, "主线程当前线程名为" + Thread.currentThread().getId()
                + " " + Thread.currentThread().getName());

        HandlerThread handlerThread = new HandlerThread("子线程");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper(), msg -> {
            if (msg.what == 1) {
                Log.d(TAG, "handlerThread当前线程名为：" + Thread.currentThread().getId()
                        + " " + Thread.currentThread().getName());
            }
            return true;
        });

        button.setOnClickListener(v -> {
            handler.sendEmptyMessage(1);

            ThreadPoolUtil.executeRunnableByFixedPool(() -> {
                Log.d(TAG, "executeRunnableByFixedPool：" + Thread.currentThread().getId()
                        + " " + Thread.currentThread().getName());
            });
        });
    }
}