package com.baorant.frameworkmodule.Util;

import android.content.Context;
import android.content.pm.ApplicationInfo;

public class CommonUtil {
    private static int sIsDebugMode = -1;
    public static boolean isDebugMode(Context context) {
        if (sIsDebugMode == -1) {
            boolean isDebug = context.getApplicationInfo() != null
                    && (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
            sIsDebugMode = isDebug ? 1 : 0;
        }
        return sIsDebugMode == 1;
    }
}
