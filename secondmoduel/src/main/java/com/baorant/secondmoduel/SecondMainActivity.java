package com.baorant.secondmoduel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/second/SecondMainActivity")
public class SecondMainActivity extends AppCompatActivity {
    private static final String TAG = "secondMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "secondMainActivity onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);
    }
}