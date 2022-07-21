package com.baorant.layoutdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;

import com.baorant.layoutdemo.AbstractSubActivity;
import com.baorant.layoutdemo.R;

public class ViewStubActivity extends AbstractSubActivity {
    Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        Handler handler = new Handler();
        handler.post(() -> {

        });

        show = findViewById(R.id.showEditView);
        show.setOnClickListener(v -> {
//            show1();
            show2();
        });
    }

    public void show1(){
        ViewStub stub = ((ViewStub) findViewById(R.id.viewstub));
        if(stub!=null){
            View stubView = stub.inflate();		//inflate方法只能调用一次，第二次会抛出异常
            EditText address = (EditText) stubView.findViewById(R.id.address_id);
            EditText wechatId = (EditText) stubView.findViewById(R.id.weichat_id);
        }
    }

    public void show2(){
        ViewStub stub = ((ViewStub) findViewById(R.id.viewstub));
        if(stub!=null){
            stub.setVisibility(View.VISIBLE);
        }
    }

}