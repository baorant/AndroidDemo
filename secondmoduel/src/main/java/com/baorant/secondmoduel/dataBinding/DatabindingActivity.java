package com.baorant.secondmoduel.dataBinding;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.baorant.frameworkmodule.activity.AbstractSubActivity;
import com.baorant.secondmoduel.R;
import com.baorant.secondmoduel.dataBinding.model.User;
import com.baorant.secondmoduel.databinding.SimpleBinding;

public class DatabindingActivity extends AbstractSubActivity {
    private static final String TAG = "DatabindingActivity";
    private SimpleBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate begin");
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);

        User user = new User("展示用户名", "展示用户密码");
        mBinding.setUser(user);
        mBinding.singBindBtn.setOnClickListener(view -> {
            Log.d(TAG, "onClick");
            user.setFirstName("bao");
            user.setLastName("li");
        });
    }
}