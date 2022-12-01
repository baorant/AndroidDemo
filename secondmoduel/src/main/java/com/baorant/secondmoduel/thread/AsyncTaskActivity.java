package com.baorant.secondmoduel.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baorant.frameworkmodule.activity.AbstractSubActivity;
import com.baorant.secondmoduel.R;

public class AsyncTaskActivity extends AbstractSubActivity {
    private TextView textView;
    private ProgressBar progressBar;
    private MyAsyncTask myAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        textView= findViewById(R.id.textView);
        progressBar= findViewById(R.id.progressBar);
    }

    public void doTaskClick(View view){
        myAsyncTask = new MyAsyncTask(textView, progressBar,this);
        // 执行异步任务
        myAsyncTask.execute(1);
    }

    public void cancelTaskClick(View view){
        // 取消异步任务
        myAsyncTask.cancel(true);
    }
}