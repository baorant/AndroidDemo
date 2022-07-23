package com.baorant.layoutdemo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baorant.layoutdemo.AbstractSubActivity;
import com.baorant.layoutdemo.R;
import com.baorant.layoutdemo.Util.OkHttpClientUtil;
import com.baorant.layoutdemo.Util.ThreadPoolUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class OkhttpActivity extends AbstractSubActivity {
    private static final String TAG = "OkhttpActivity";

    private Button syncGet;
    private Button asyncGet;
    private Button asyncPost;

    private TextView syncGetResult;
    private TextView asyncGetResult;
    private TextView asyncPostResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        syncGet = findViewById(R.id.syncGet);
        asyncGet = findViewById(R.id.asyncGet);
        asyncPost = findViewById(R.id.asyncPost);

        syncGetResult = findViewById(R.id.syncGetResponse);
        asyncGetResult = findViewById(R.id.asyncGetResponse);
        asyncPostResult = findViewById(R.id.asyncPostResponse);

        syncGet.setOnClickListener(v -> {
            Log.d(TAG, "syncGet onclick begin");
            ThreadPoolUtil.executeRunnableByFixedPool(() -> {
                Response response = OkHttpClientUtil.syncGetRequest(
                        "http://192.168.11.1:3030/path/get");
                runOnUiThread(() ->
                {
                    Log.d(TAG, "runOnUiThread onResponse");
                    if (response != null && response.isSuccessful()) {
                        Log.d(TAG, "runOnUiThread onResponse not null");
                        syncGetResult.setText(syncGetResult.getText() + response.body().toString());
                    }
                });
            });
        });

        asyncGet.setOnClickListener(v -> {
            Log.d(TAG, "asyncGet onclick begin");
            String url = "http://192.168.11.1:3030/path/get";
            OkHttpClientUtil.asyncGetRequest(url, new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response == null) {
                        return;
                    }
                    Log.d(TAG, "asyncGet onResponse begin");
                    asyncGetResult.setText(asyncGetResult.getText() + response.body().toString());
                }
            });
        });

        asyncPost.setOnClickListener(v -> {
            String url = "";
            Map<String, String> params = new HashMap<>();
            OkHttpClientUtil.asyncPostRequest(url, params, new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                }
            });
        });
    }
}