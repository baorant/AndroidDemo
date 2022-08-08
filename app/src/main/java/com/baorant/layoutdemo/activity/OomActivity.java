package com.baorant.layoutdemo.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baorant.frameworkmodule.activity.AbstractSubActivity;
import com.baorant.layoutdemo.MyApplication;
import com.baorant.layoutdemo.R;
import com.baorant.frameworkmodule.Util.Singleton;

@Route(path = "/base/OomActivity")
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