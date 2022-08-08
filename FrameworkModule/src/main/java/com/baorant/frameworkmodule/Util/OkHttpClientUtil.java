package com.baorant.frameworkmodule.Util;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpClientUtil {
    private static final String TAG = "OkHttpClientUtil";
    private static final boolean IS_RETRY = false;//失败是否重连
    private static final int CONNECT_TIME = 10;//设置连接超时时间 单位:秒
    private static final int READ_TIME = 10;//设置读取超时时间
    private static final int WRITE_TIME = 10;//设置写入超时间
    private static OkHttpClient mOkHttpClient;

    public static OkHttpClient createClient(){
        if (mOkHttpClient == null){
            return mOkHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(IS_RETRY)
                    .connectTimeout(CONNECT_TIME, TimeUnit.SECONDS)//连接超时
                    .readTimeout(READ_TIME,TimeUnit.SECONDS)//读取超时
                    .writeTimeout(WRITE_TIME,TimeUnit.SECONDS)//写入超时
//                    .callTimeout()//呼叫超时,设置此参数为整体流程请求的超时时间
//                    .addInterceptor() //设置拦截器
//                    .authenticator() //设置认证器
//                    .proxy()//设置代理
                    .build();
        }
        return mOkHttpClient;
    }

    /**
     * 同步请求
     */
    public static Response syncGetRequest(String url){
        final Response[] response = new Response[1];
        try {
            // 创建请求
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            // 执行请求
            response[0] = OkHttpClientUtil.createClient().newCall(request).execute();
            // 得到返回响应，注意response.body().string() 只能调用一次！
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
        return response[0];
    }

    /**
     * get异步请求
     */
    public static void asyncGetRequest(String url, Callback callback){
        Request request = new Request.Builder()
                .url(url)
                .build();
        OkHttpClientUtil.createClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(call, e);
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                callback.onResponse(call, response);
            }
        });
    }

    /**
     * post请求
     */
    public static void asyncPostRequest(String url, Map<String, String> params, Callback callback){
        ThreadPoolUtil.executeRunnableByFixedPool(() -> {
            // 创建post请求数据表单
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
            RequestBody requestBody = builder.build();

            // 创建请求
            final Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();

            // 发送请求得到响应
            OkHttpClientUtil.createClient().newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    callback.onFailure(call, e);
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    callback.onResponse(call, response);
                }
            });
        });
    }

    public static void destroy(){
        mOkHttpClient = null;
    }
}
