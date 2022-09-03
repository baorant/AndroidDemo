package com.baorant.layoutdemo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;
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
    TextView textView1;
    TextView textView2;
    TextView textView3;
    Button handlerRunnableBtn;
    int count;

    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        public void run() {
            Log.e(TAG, Thread.currentThread().getName() + " " + count);
            count++;
            setTitle("" + count);
            // 每3秒执行一次
//            mHandler.postDelayed(mRunnable, 3000);  //给自己发送消息，自运行
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        button = findViewById(R.id.btn1);
        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        textView3 = findViewById(R.id.text3);
        handlerRunnableBtn = findViewById(R.id.handlerRunnableBtn);

        Thread.currentThread().setName("主线程");

        textView1.setText("主线程当前线程名为" + Thread.currentThread().getName());

        HandlerThread handlerThread = new HandlerThread("子线程");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper(), msg -> {
            if (msg.what == 4) {
                String temThreadName = Thread.currentThread().getName();
                runOnUiThread(() -> textView2.setText("handlerThread当前线程名为：" + temThreadName));
            }
            return true;
        });

        button.setOnClickListener(v ->
        {
            handler.sendEmptyMessage(4);

            ThreadPoolUtil.executeRunnableByFixedPool(() -> {
                String temThreadName = Thread.currentThread().getName();
                runOnUiThread(() -> textView3.setText("线程池当前子线程名为：" + temThreadName));
            });
        });

        handlerRunnableBtn.setOnClickListener(v -> mHandler.post(mRunnable));
    }
}