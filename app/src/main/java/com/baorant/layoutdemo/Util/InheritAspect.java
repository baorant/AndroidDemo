package com.baorant.layoutdemo.Util;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class InheritAspect {
    private static final String TAG = "InheritAspect";
    private static final String ON_CREATE_EXECUTION = "execution(void *..*Activity.onCreate(..))";

    @Pointcut(ON_CREATE_EXECUTION)
    public void onCreateExecution() {
    }

    @Before("onCreateExecution()")
    public void beforeOnCreateExecution(JoinPoint joinPoint) {
        Log.i(TAG,joinPoint.getTarget().toString() + "onCreate start");
    }
}
