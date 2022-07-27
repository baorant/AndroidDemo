package com.baorant.frameworkmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;

public class FrameWorkActivity extends AppCompatActivity {
    Button jumpToSecondModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_work);

        jumpToSecondModule = findViewById(R.id.jumpToSecondModule);

        jumpToSecondModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build("/second/SecondMainActivity").navigation();
            }
        });
    }
}