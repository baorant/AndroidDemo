package com.baorant.secondmoduel.navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.baorant.secondmoduel.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationActivity extends AppCompatActivity {
    private static final String TAG = "NavigationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        initView();
    }

    private void initView() {
        Log.d(TAG, "initView");
        BottomNavigationView navBtn=findViewById(R.id.bottomNavigation);
        NavController navController = Navigation.findNavController(this, R.id.mainFragment);

        // 这里如果我们需要对导航栏跳转时做判断，比如判断用户是否登录，未登录，就不跳转或者处理其他事件；
        // 这里返回true，则表示拦截不跳转，false则正常跳转；
        navBtn.setOnNavigationItemSelectedListener(item -> {
            Log.d(TAG, "onNavigationItemSelected");
            int itemId = item.getItemId();
            if (itemId == R.id.homefragment) {
                navController.navigate(R.id.action_thirdFragment_to_firstFragment);
            } else if (itemId == R.id.discoverfragment) {
                navController.navigate(R.id.action_firstFragment_to_secondFragment);
            } else if (itemId == R.id.mefragment) {
                navController.navigate(R.id.action_secondFragment_to_thirdFragment);
            }
            // return true表示拦截，不让跳转，
            return false;
        });
        // todo 绑定navBtn和navControl，不注释会报错，需要晚点研究
//        NavigationUI.setupWithNavController(navBtn, navController);

    }
}