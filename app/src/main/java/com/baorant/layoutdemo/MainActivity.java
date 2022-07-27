package com.baorant.layoutdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.baorant.layoutdemo.Util.AssetsUtil;
import com.baorant.layoutdemo.Util.SharePreferenceUtil;
import com.baorant.layoutdemo.activity.CountDownLatchActivity;
import com.baorant.layoutdemo.activity.CrashHandlerActivity;
import com.baorant.layoutdemo.activity.ExoplayerActivity;
import com.baorant.layoutdemo.activity.HandlerThreadActivity;
import com.baorant.layoutdemo.activity.HotFixActivity;
import com.baorant.layoutdemo.activity.OkhttpActivity;
import com.baorant.layoutdemo.activity.OomActivity;
import com.baorant.layoutdemo.activity.RouterActivity;
import com.baorant.layoutdemo.activity.VideoViewActivity;
import com.baorant.layoutdemo.activity.ViewStubActivity;
import com.baorant.layoutdemo.activity.WebViewActivity;
import com.baorant.layoutdemo.adapter.TemAdapter;

import java.util.Arrays;

@Route(path = "/base/MainActivity")
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "WelcomeActivity";
    String[] strings = {"webview和h5交互", "多线程通信", "countDownLatch并发控制", "viewStub组件",
    "crashHandler兜底", "videoview播放视频", "exoplayer播放视频", "热修复测试",
    "oom内存泄漏测试", "okhttp工具类测试", "路由测试页面"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 注入路由
        ARouter.getInstance().inject(this);

        requestPermission();

        RecyclerView recyclerView = findViewById(R.id.listView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        TemAdapter temAdapter = new TemAdapter(MainActivity.this, Arrays.asList(strings));
        temAdapter.setOnItemClickListener((view, position) -> {
            switch (position) {
                case 0:
                    Log.d(TAG, "click index 0");
                    jumpNextActivity(WebViewActivity.class, position);
                    break;
                case 1:
                    Log.d(TAG, "click index 1");
                    jumpNextActivity(HandlerThreadActivity.class, position);
                    break;
                case 2:
                    Log.d(TAG, "click index 2");
                    jumpNextActivity(CountDownLatchActivity.class, position);
                    break;
                case 3:
                    Log.d(TAG, "click index 3");
                    jumpNextActivity(ViewStubActivity.class, position);
                    break;
                case 4:
                    Log.d(TAG, "click index 4");
                    jumpNextActivity(CrashHandlerActivity.class, position);
                    break;
                case 5:
                    Log.d(TAG, "click index 5");
                    jumpNextActivity(VideoViewActivity.class, position);
                    break;
                case 6:
                    Log.d(TAG, "click index 6");
                    jumpNextActivity(ExoplayerActivity.class, position);
                    break;
                case 7:
                    Log.d(TAG, "click index 7");
                    jumpNextActivity(HotFixActivity.class, position);
                    break;
                case 8:
                    Log.d(TAG, "click index 8");
                    jumpNextActivity(OomActivity.class, position);
                    break;
                case 9:
                    Log.d(TAG, "click index 9");
                    jumpNextActivity(OkhttpActivity.class, position);
                    break;
                case 10:
                    Log.d(TAG, "click index 10");
                    jumpNextActivity(RouterActivity.class, position);
                    break;
                default:
                    break;
            }
        });
        recyclerView.setAdapter(temAdapter);

        copyAssetsDex();
    }

    private void copyAssetsDex() {
        // 复制asset下的dex资源到应用对应文件目录下
        new Thread(() -> AssetsUtil.loadAssetsToCache(MainActivity.this, "classes2.dex")).start();
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT>=23&&checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }

    private void jumpNextActivity(Class temActivity, int position) {
        Intent intent = new Intent(MainActivity.this, temActivity);
        intent.putExtra("actionBarName", strings[position]);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.region_right_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.open_close_hot_fix:
                openOrCloseHotFix();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openOrCloseHotFix() {
        if (SharePreferenceUtil.read("hotfixOpen").equals("1")) {
            Toast.makeText(MainActivity.this, "关闭热修复", Toast.LENGTH_SHORT).show();
            SharePreferenceUtil.write("hotfixOpen", "0");
            return;
        }
        Toast.makeText(MainActivity.this, "打开热修复", Toast.LENGTH_SHORT).show();
        SharePreferenceUtil.write("hotfixOpen", "1");
    }
}