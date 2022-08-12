package com.baorant.secondmoduel.eventBus;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baorant.frameworkmodule.activity.AbstractSubActivity;
import com.baorant.frameworkmodule.msg.EventMessage;
import com.baorant.secondmoduel.R;

import org.greenrobot.eventbus.EventBus;

@Route(path = "/second/EventBusActivity")
public class EventBusActivity extends AbstractSubActivity {
    private static final String TAG = "EventBusActivity";
    private Button sendEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

        sendEvent = findViewById(R.id.sendEvent);
        sendEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "sendEvent onClick" );
                EventMessage msg = new EventMessage(1,"Hello SecondMainActivity");
                EventBus.getDefault().post(msg);
            }
        });
    }
}