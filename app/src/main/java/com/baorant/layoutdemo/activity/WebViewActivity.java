package com.baorant.layoutdemo.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baorant.frameworkmodule.activity.AbstractSubActivity;
import com.baorant.layoutdemo.R;
import com.baorant.frameworkmodule.Util.AndroidInterfaceWeb;

/**
 * webView和h5交互页面
 */
@Route(path = "/base/WebViewActivity")
public class WebViewActivity extends AbstractSubActivity {
    int count = 0;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webView = findViewById(R.id.webView);
        Button button = findViewById(R.id.dianji);

        TextView textView = findViewById(R.id.textView2);

        // 开启javascript 渲染
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        // 载入内容
        webView.loadUrl("file:///android_asset/webh5.html");
        webView.addJavascriptInterface(new AndroidInterfaceWeb(this, textView),
                "android");// AndroidtoJS类对象映射到js的test对象
        webView.setBackgroundColor(Color.GREEN); // 设置背景色

        button.setOnClickListener(v -> {
            // 无参数调用
            //            webView.loadUrl("javascript:javacalljs()");
            // 传递参数调用
            webView.loadUrl("javascript:javacalljswithargs('" + "android端按钮点击次数: "
                    + (count++) + "')");
        });

    }
}