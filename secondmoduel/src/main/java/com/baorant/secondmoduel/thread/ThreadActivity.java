package com.baorant.secondmoduel.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.baorant.secondmoduel.R;

public class ThreadActivity extends AppCompatActivity {
    private static final String TAG = "ThreadActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread1);

        Log.d(TAG, "TAG onCreate " + Thread.currentThread().getId() + " "
                + Thread.currentThread().getName());

        runInNewThread();
    }

    private void runInNewThread() {
        new Thread() {
            @Override
            public void run() {
                try {
                    // 模拟执行耗时任务
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "runInNewThread" + Thread.currentThread().getId() + " " + Thread.currentThread().getName());
            }
        }.start();
    }
}