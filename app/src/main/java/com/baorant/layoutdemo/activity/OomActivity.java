package com.baorant.layoutdemo.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.baorant.layoutdemo.AbstractSubActivity;
import com.baorant.layoutdemo.MyApplication;
import com.baorant.layoutdemo.R;
import com.baorant.layoutdemo.Util.Singleton;

public class OomActivity extends AbstractSubActivity {
    private static final String TAG = "OomActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oom);

        MyApplication.getRefWatcher().watch(this);
        Singleton singleton = Singleton.newInstance(this);
    }
}