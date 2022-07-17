package com.baorant.layoutdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baorant.layoutdemo.R;
import com.baorant.layoutdemo.Util.DexFixTest;

public class HotFixActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_fix);

        Button hotTest = findViewById(R.id.hotTest);
        hotTest.setOnClickListener(v -> {
            DexFixTest dexFixTest = new DexFixTest();
            dexFixTest.testFix(HotFixActivity.this);
        });
    }
}