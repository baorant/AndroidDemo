package com.baorant.layoutdemo.util;

import android.content.SharedPreferences;

import com.baorant.layoutdemo.MyApplication;

public class SharePreferenceUtil {
    public static void write(String key, String value) {
        SharedPreferences pref = MyApplication.getApplication().getSharedPreferences("androidDemo",
                MyApplication.getApplication().MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String read(String key) {
        SharedPreferences pre = MyApplication.getApplication().getSharedPreferences("androidDemo",
                MyApplication.getApplication().MODE_PRIVATE);
        return pre.getString(key,"");
    }
}
