package com.baorant.secondmoduel.thread;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.baorant.frameworkmodule.activity.AbstractSubActivity;
import com.baorant.secondmoduel.R;

public class IntentServiceActivity extends AbstractSubActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
    }

    public void startIntentService(View view) {
        //启动service
        Intent intent = new Intent(IntentServiceActivity.this, MyIntentService.class);
        startService(intent);
    }
}