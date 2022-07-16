package com.baorant.layoutdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.baorant.layoutdemo.activity.CountDownLatchActivity;
import com.baorant.layoutdemo.activity.CrashHandlerActivity;
import com.baorant.layoutdemo.activity.HandlerThreadActivity;
import com.baorant.layoutdemo.activity.ViewStubActivity;
import com.baorant.layoutdemo.activity.WebViewActivity;
import com.baorant.layoutdemo.adapter.TemAdapter;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "WelcomeActivity";
    String[] strings = {"webview和h5交互", "多线程通信", "countDownLatch并发控制", "viewStub组件",
    "crashHandler 兜底"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();

        RecyclerView recyclerView = findViewById(R.id.listView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        TemAdapter temAdapter = new TemAdapter(MainActivity.this, Arrays.asList(strings));
        temAdapter.setOnItemClickListener((view, position) -> {
            switch (position) {
                case 0:
                    Log.d(TAG, "click index 0");
                    jumpNextActivity(WebViewActivity.class);
                    break;
                case 1:
                    Log.d(TAG, "click index 1");
                    jumpNextActivity(HandlerThreadActivity.class);
                    break;
                case 2:
                    Log.d(TAG, "click index 2");
                    jumpNextActivity(CountDownLatchActivity.class);
                    break;
                case 3:
                    Log.d(TAG, "click index 3");
                    jumpNextActivity(ViewStubActivity.class);
                    break;
                case 4:
                    Log.d(TAG, "click index 4");
                    jumpNextActivity(CrashHandlerActivity.class);
                    break;
                default:
                    break;
            }
        });
        recyclerView.setAdapter(temAdapter);
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT>=23&&checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }

    private void jumpNextActivity(Class temActivity) {
        Intent intent = new Intent(MainActivity.this, temActivity);
        startActivity(intent);
    }
}