package com.baorant.frameworkmodule.Util;

import android.content.Context;
import android.content.Intent;

public class JumpUtil {
    public static void jumpNextActivity(Context context, Class temActivity, String str) {
        Intent intent = new Intent(context, temActivity);
        intent.putExtra("actionBarName", str);
        context.startActivity(intent);
    }
}
