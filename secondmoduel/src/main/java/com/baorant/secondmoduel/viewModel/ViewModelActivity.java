package com.baorant.secondmoduel.viewModel;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.baorant.frameworkmodule.activity.AbstractSubActivity;
import com.baorant.secondmoduel.R;

public class ViewModelActivity extends AbstractSubActivity {
    private MyViewModel myViewModel;

    private TextView tv;
    private TextView liveTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model);

        // 获取ViewModel实例
        myViewModel = new ViewModelProvider(ViewModelActivity.this).get(MyViewModel.class);

        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        tv = findViewById(R.id.tv);
        liveTv = findViewById(R.id.liveTv);

        // 屏幕切换等生命周期重置需重新手动设置一下最新值
        tv.setText(String.valueOf(myViewModel.getNumber()));

        // Create the observer which updates the UI.
        final Observer<String> nameObserver = newName -> liveTv.setText(newName);

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        myViewModel.getCurrentName().observe(this, nameObserver);

        btn1.setOnClickListener(view -> {
            myViewModel.doSet(1);
            tv.setText(String.valueOf(myViewModel.getNumber()));
            // 修改liveData的内容,对比上面修改方案更简洁
            myViewModel.getCurrentName().setValue("执行+1操作done");
        });

        btn2.setOnClickListener(view -> {
            myViewModel.doSet(-1);
            tv.setText(String.valueOf(myViewModel.getNumber()));
            // 修改liveData的内容
            myViewModel.getCurrentName().setValue("执行-1操作done");
        });
    }
}
