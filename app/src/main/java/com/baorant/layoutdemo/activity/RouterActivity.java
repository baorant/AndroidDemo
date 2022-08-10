package com.baorant.layoutdemo.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.baorant.frameworkmodule.activity.AbstractSubActivity;
import com.baorant.layoutdemo.R;

@Route(path = "/base/RouterActivity")
public class RouterActivity extends AbstractSubActivity {
    Button jumpToCountDown;
    Button jumpToSecondModule;
    EditText edit_url;
    Button beginRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_router);

        jumpToCountDown = findViewById(R.id.jumpToCountDown);
        jumpToSecondModule = findViewById(R.id.jumpToExoplayer);
        edit_url = findViewById(R.id.edit_url);
        beginRouter = findViewById(R.id.beginRouter);

        jumpToCountDown.setOnClickListener(v -> ARouter.getInstance().build("/base/CountDownLatchActivity")
                .withString("actionBarName", "CountDownLatchActivity页面").navigation());
        jumpToSecondModule.setOnClickListener(v ->
        {
            ARouter.getInstance().build("/second/SecondMainActivity")
                    .withString("actionBarName", "SecondMainActivity页面").navigation();
        });

        beginRouter.setOnClickListener(v -> {
            String url = edit_url.getText().toString();
            if (url.trim().equals("")) {
                return;
            }
            ARouter.getInstance().build(url).withString("actionBarName", "自定义跳转URL页面").navigation();
        });
    }
}