package com.baorant.frameworkmodule.Util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtil {
    private static int fixNum = 6;
    private static int maxNum = 8;

    private static ExecutorService fixedThreadPool = new ThreadPoolExecutor(fixNum, maxNum,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());;

    public static void executeRunnableByFixedPool(Runnable runnable) {
        fixedThreadPool.execute(runnable);
    }

}
