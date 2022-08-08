package com.baorant.layoutdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baorant.frameworkmodule.activity.AbstractSubActivity;
import com.baorant.layoutdemo.R;

@Route(path = "/base/ViewStubActivity")
public class ViewStubActivity extends AbstractSubActivity {
    Button show;
    Button increaseHeight;
    boolean visible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        show = findViewById(R.id.showEditView);
        increaseHeight = findViewById(R.id.increaseHeight);

        show.setOnClickListener(v -> {
//            show1();
            show2();
            visible = true;
        });

        increaseHeight.setOnClickListener(v -> {
//            if (!visible) {
//                return;
//            }
            increaseHeight();
        });

    }

    private void increaseHeight() {
        ViewGroup.LayoutParams layoutParams = show.getLayoutParams();
        layoutParams.height += 10;
        show.setLayoutParams(layoutParams);
    }

    public void show1(){
        ViewStub stub = findViewById(R.id.viewstub);
        if(stub!=null){
            View stubView = stub.inflate();		//inflate方法只能调用一次，第二次会抛出异常
            EditText address = stubView.findViewById(R.id.address_id);
            EditText wechatId = stubView.findViewById(R.id.weichat_id);
        }
    }

    public void show2(){
        ViewStub stub = findViewById(R.id.viewstub);
        if(stub != null){
            stub.setVisibility(View.VISIBLE);
        }
    }

}