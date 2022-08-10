package com.baorant.secondmoduel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baorant.frameworkmodule.Util.JumpUtil;
import com.baorant.frameworkmodule.activity.AbstractSubActivity;
import com.baorant.frameworkmodule.msg.EventMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@Route(path = "/second/SecondMainActivity")
public class SecondMainActivity extends AbstractSubActivity {
    private static final String TAG = "secondMainActivity";
    private Button btnToEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "secondMainActivity onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);

        btnToEvent = findViewById(R.id.btnToEvent);
        btnToEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtil.jumpNextActivity(SecondMainActivity.this, EventBusActivity.class, "EventBusActivity页面");
            }
        });
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg(EventMessage message) {
        Log.d(TAG, "onReceiveMsg: " + message.toString());
        Toast.makeText(SecondMainActivity.this, "this is SecondMainActivity show", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}