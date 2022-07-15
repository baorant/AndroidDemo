package com.baorant.layoutdemo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.baorant.layoutdemo.R;

public class HandlerThreadActivity extends AppCompatActivity {
    Button button;
    TextView textView1;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        button = findViewById(R.id.btn1);
        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        Thread.currentThread().setName("主线程");

        textView1.setText("当前线程名为" + Thread.currentThread().getName());

        HandlerThread handlerThread = new HandlerThread("子线程");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper(), msg -> {
            if (msg.what == 4) {
                String temThreadName = Thread.currentThread().getName();
                runOnUiThread(() -> textView2.setText("当前线程名为：" + temThreadName));
            }
            return true;
        });

        button.setOnClickListener(v -> handler.sendEmptyMessage(4));
    }
}