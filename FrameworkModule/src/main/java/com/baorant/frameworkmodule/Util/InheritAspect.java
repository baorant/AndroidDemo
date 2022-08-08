package com.baorant.frameworkmodule.Util;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class InheritAspect {
    private static final String TAG = "InheritAspect";
    private static final String ON_ACTIVITY_METHOD_EXECUTION = "execution(void *..*Activity.*(..))";

    @Pointcut(ON_ACTIVITY_METHOD_EXECUTION)
    public void onMethodExecution() {
    }

    @Before("onMethodExecution()")
    public void beforeMethodExecution(JoinPoint joinPoint) {
        String className = joinPoint.getStaticPart().getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getStaticPart().getSignature().getName();
        Log.d(TAG,"beforeMethodExecution: " + className + " " + methodName);
    }
}
