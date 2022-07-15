package com.baorant.layoutdemo.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baorant.layoutdemo.R;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchActivity extends AppCompatActivity {
    CountDownLatch countDownLatch = new CountDownLatch(3);
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_down_latch);

        Button button = findViewById(R.id.threadBtn);
        Button button2 = findViewById(R.id.threadBtn2);
        textView1 = findViewById(R.id.textThread1);
        textView2 = findViewById(R.id.textThread2);
        textView3 = findViewById(R.id.textThread3);
        textView4 = findViewById(R.id.textThread4);

        button.setOnClickListener(v -> new Thread(() -> {
            startThreeWorks();
            try {
                countDownLatch.await();
//                        Toast.makeText(CountDownLatchActivity.this, "事件都执行完毕", Toast.LENGTH_SHORT);
                runOnUiThread(() -> {
                    textView4.setText(textView4.getText() + " 三个事件都已经完成 ");
                    Toast.makeText(CountDownLatchActivity.this, "事件都执行完毕", Toast.LENGTH_SHORT).show();
                    button.setClickable(false);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());
        button2.setOnClickListener(v -> {
            textView1.setBackgroundColor(Color.rgb(255, 255, 255));
            textView2.setBackgroundColor(Color.rgb(255, 255, 255));
            textView3.setBackgroundColor(Color.rgb(255, 255, 255));
            textView4.setText("事件4");
            countDownLatch = new CountDownLatch(3);
            button.setClickable(true);
        });
    }

    private void startThreeWorks() {
        new Thread() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(() -> {
                    textView1.setBackgroundColor(Color.rgb(0, 0, 255));
                    countDownLatch.countDown();
                });
            }
        }.start();

        new Thread() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(() -> {
                    textView2.setBackgroundColor(Color.rgb(255, 0, 0));
                    countDownLatch.countDown();
                });
            }
        }.start();

        new Thread() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(() -> {
                    textView3.setBackgroundColor(Color.rgb(0, 255, 0));
//                    textView3.setBackgroundColor(Color.green(Color.GREEN));
                    countDownLatch.countDown();
                });
            }
        }.start();
    }
}