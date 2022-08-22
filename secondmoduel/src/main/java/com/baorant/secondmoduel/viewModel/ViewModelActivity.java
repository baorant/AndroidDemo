package com.baorant.secondmoduel.viewModel;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.baorant.frameworkmodule.activity.AbstractSubActivity;
import com.baorant.secondmoduel.R;

public class ViewModelActivity extends AbstractSubActivity {
    private MyViewModel myViewModel;

    private Button btn1,btn2;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model);

        // 获取ViewModel实例
        myViewModel = new ViewModelProvider(ViewModelActivity.this).get(MyViewModel.class);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        tv = findViewById(R.id.tv);

        // 屏幕切换等生命周期重置需重新手动设置一下最新值
        tv.setText(String.valueOf(myViewModel.getNumber()));

        btn1.setOnClickListener(view -> {
            myViewModel.doSet(1);
            tv.setText(String.valueOf(myViewModel.getNumber()));
        });

        btn2.setOnClickListener(view -> {
            myViewModel.doSet(-1);
            tv.setText(String.valueOf(myViewModel.getNumber()));
        });
    }
}
