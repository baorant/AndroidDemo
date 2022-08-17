package com.baorant.secondmoduel.dataBinding;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.baorant.frameworkmodule.activity.AbstractSubActivity;
import com.baorant.secondmoduel.R;
import com.baorant.secondmoduel.dataBinding.model.User;
import com.baorant.secondmoduel.databinding.SimpleBinding;

public class DatabindingActivity extends AbstractSubActivity {
    private SimpleBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);
        mBinding.setUser(new User("展示用户名", "展示用户密码"));
    }
}