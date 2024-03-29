package com.baorant.layoutdemo.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baorant.frameworkmodule.activity.AbstractSubActivity;
import com.baorant.layoutdemo.R;

@Route(path = "/base/CrashHandlerActivity")
public class CrashHandlerActivity extends AbstractSubActivity {
    private static final String TAG = "CrashHandlerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash_handler);

        Button crashBtn = findViewById(R.id.crashButton);
        crashBtn.setOnClickListener(v -> {
            Log.d(TAG, "crashBtn onclick");
            throw new RuntimeException("手动抛出运行时异常");
        });
    }
}