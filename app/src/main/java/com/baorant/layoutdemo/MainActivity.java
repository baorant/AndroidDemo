package com.baorant.layoutdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.baorant.layoutdemo.activity.CountDownLatchActivity;
import com.baorant.layoutdemo.activity.HandlerThreadActivity;
import com.baorant.layoutdemo.activity.ViewStubActivity;
import com.baorant.layoutdemo.activity.WebViewActivity;
import com.baorant.layoutdemo.adapter.TemAdapter;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "WelcomeActivity";
    String[] strings = {"webview和h5交互", "多线程通信", "countDownLatch并发控制", "viewStub组件"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.listView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        TemAdapter temAdapter = new TemAdapter(MainActivity.this, Arrays.asList(strings));
        temAdapter.setOnItemClickListener((view, position) -> {
            switch (position) {
                case 0:
                    Log.d(TAG, "click index 0");
                    jumpWebViewActivity();
                case 1:
                    Log.d(TAG, "click index 1");
                    jumpThreadActivity();
                case 2:
                    Log.d(TAG, "click index 2");
                    jumpCountDownLatchActivity();
                case 3:
                    Log.d(TAG, "click index 3");
                    jumpViewStubActivity();
                default:
                    break;
            }
        });
        recyclerView.setAdapter(temAdapter);
    }

    private void jumpWebViewActivity() {
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        startActivity(intent);
    }

    private void jumpThreadActivity() {
        Intent intent = new Intent(MainActivity.this, HandlerThreadActivity.class);
        startActivity(intent);
    }

    private void jumpCountDownLatchActivity() {
        Intent intent = new Intent(MainActivity.this, CountDownLatchActivity.class);
        startActivity(intent);
    }

    private void jumpViewStubActivity() {
        Intent intent = new Intent(MainActivity.this, ViewStubActivity.class);
        startActivity(intent);
    }
}