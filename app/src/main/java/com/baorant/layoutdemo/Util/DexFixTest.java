package com.baorant.layoutdemo.Util;

import android.content.Context;
import android.widget.Toast;

public class DexFixTest {
    public  void testFix(Context context){
        int dividend = 10;
        // bug：除数不可为0
        int divisor = 0;
        Toast.makeText(context, "热修复OK，结果是:"+ dividend / divisor, Toast.LENGTH_SHORT).show();
    }
}