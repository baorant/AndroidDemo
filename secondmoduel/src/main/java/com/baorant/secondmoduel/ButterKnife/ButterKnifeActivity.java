package com.baorant.secondmoduel.ButterKnife;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baorant.frameworkmodule.activity.AbstractSubActivity;
import com.baorant.secondmoduel.R;
import com.baorant.secondmoduel.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ButterKnifeActivity extends AbstractSubActivity {
    @BindView(R2.id.ButterKnifeTxt)
    TextView butterKnifeTxt;

    @BindView(R2.id.ButterKnifeBtn1)
    Button butterKnifeBtn1;

    @BindView(R2.id.ButterKnifeBtn2)
    Button butterKnifeBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);

        ButterKnife.bind(ButterKnifeActivity.this);
    }

    @OnClick({R2.id.ButterKnifeBtn1, R2.id.ButterKnifeBtn2})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.ButterKnifeBtn1) {
            butterKnifeTxt.setText("butterKnifeBtn1 onclick");
        }
        if (view.getId() == R.id.ButterKnifeBtn2) {
            butterKnifeTxt.setText("butterKnifeBtn2 onclick");
        }
    }
}