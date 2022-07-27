package com.baorant.layoutdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.FragmentActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.baorant.frameworkmodule.FrameWorkActivity;
import com.baorant.layoutdemo.AbstractSubActivity;
import com.baorant.layoutdemo.R;

@Route(path = "/base/RouterActivity")
public class RouterActivity extends AbstractSubActivity {
    Button jumpToCountDown;
    Button jumpToExoplayer;
    EditText edit_url;
    Button beginRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_router);

        jumpToCountDown = findViewById(R.id.jumpToCountDown);
        jumpToExoplayer = findViewById(R.id.jumpToExoplayer);
        edit_url = findViewById(R.id.edit_url);
        beginRouter = findViewById(R.id.beginRouter);

        jumpToCountDown.setOnClickListener(v -> ARouter.getInstance().build("/base/CountDownLatchActivity").navigation());
        jumpToExoplayer.setOnClickListener(v ->
        {
//            ARouter.getInstance().build("/second/MainActivity").navigation();
            Intent intent = new Intent(RouterActivity.this, FrameWorkActivity.class);
            startActivity(intent);
        });

        beginRouter.setOnClickListener(v -> {
            String url = edit_url.getText().toString();
            if (url.trim().equals("")) {
                return;
            }
            ARouter.getInstance().build(url).navigation();
        });
    }
}