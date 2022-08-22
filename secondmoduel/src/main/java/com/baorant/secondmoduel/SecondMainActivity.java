package com.baorant.secondmoduel;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baorant.frameworkmodule.Util.JumpUtil;
import com.baorant.frameworkmodule.activity.AbstractSubActivity;
import com.baorant.frameworkmodule.msg.EventMessage;
import com.baorant.secondmoduel.ButterKnife.ButterKnifeActivity;
import com.baorant.secondmoduel.dataBinding.DatabindingActivity;
import com.baorant.secondmoduel.eventBus.EventBusActivity;
import com.baorant.secondmoduel.navigation.NavigationActivity;
import com.baorant.secondmoduel.viewModel.ViewModelActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = "/second/SecondMainActivity")
public class SecondMainActivity extends AbstractSubActivity {
    private static final String TAG = "secondMainActivity";
    @BindView(R2.id.btnToEvent)
    Button btnToEvent;
    @BindView(R2.id.btnToButterKnife)
    Button btnToButterKnife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "secondMainActivity onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        ButterKnife.bind(SecondMainActivity.this);
    }

    @OnClick({R2.id.btnToEvent, R2.id.btnToButterKnife, R2.id.btnToNavigation, R2.id.btnToDataBinding,
    R2.id.btnToViewModel})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.btnToEvent) {
            JumpUtil.jumpNextActivity(SecondMainActivity.this, EventBusActivity.class, "EventBusActivity页面");
        }
        if (view.getId() == R.id.btnToButterKnife) {
            JumpUtil.jumpNextActivity(SecondMainActivity.this, ButterKnifeActivity.class, "ButterKnifeActivity页面");
        }
        if (view.getId() == R.id.btnToNavigation) {
            JumpUtil.jumpNextActivity(SecondMainActivity.this, NavigationActivity.class, "NavigationActivity页面");
        }
        if (view.getId() == R.id.btnToDataBinding) {
            JumpUtil.jumpNextActivity(SecondMainActivity.this, DatabindingActivity.class, "DatabindingActivity页面");
        }
        if (view.getId() == R.id.btnToViewModel) {
            JumpUtil.jumpNextActivity(SecondMainActivity.this, ViewModelActivity.class, "ViewModelActivity页面");
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