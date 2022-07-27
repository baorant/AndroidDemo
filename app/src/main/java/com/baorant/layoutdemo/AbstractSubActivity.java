package com.baorant.layoutdemo;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;

public abstract class AbstractSubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.action_bar_more_comment);
        Resources resources = AbstractSubActivity.this.getResources();

        // 设置状态栏文字内容
        TextView actionBarText = findViewById(R.id.actionBarTile);
        actionBarText.setText(getActionBarTitle());

        Drawable drawable = resources.getDrawable(R.drawable.action_bar_radius);
        actionBar.setBackgroundDrawable(drawable);
        //给actionBar设置上圆角长方形背景是关键
        //里面的那些其实可以只设置textView和imageButton
        ImageButton imageButton=actionBar.getCustomView()
                .findViewById(R.id.imageButtonActionBarMoreCommentBack);
        imageButton.setOnClickListener(view -> finish());

        // 注入路由
        ARouter.getInstance().inject(this);
    }

    /**
     * 获取传入的字串作为actionBar的名称
     *
     * @return actionBar的名称
     */
    private String getActionBarTitle() {
        Intent intent = getIntent();
        return intent.getStringExtra("actionBarName");
    }
}
