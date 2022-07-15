package com.baorant.layoutdemo.Util;

import android.app.Activity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AndroidInterfaceWeb {
    private static final String TAG = "AndroidInterfaceWeb";
    private Activity activity;
    private TextView textView;
    private int count = 1;

    public AndroidInterfaceWeb(Activity activity, TextView textView) {
        this.activity = activity;
        this.textView = textView;
    }

    @JavascriptInterface
    public void invoke_native() {
        Log.d(TAG, "invoke_native");

    }

    @JavascriptInterface
    public void invoke_location() {
        Log.d(TAG, "invoke_location");
        textView.setText("h5点击：" + (count++) + "次");
    }

}