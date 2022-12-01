package com.baorant.secondmoduel.thread;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyAsyncTask extends AsyncTask<Integer,Integer,Integer> {
    private final String TAG = "AsyncTask";
    private TextView textView;
    private ProgressBar progressBar;
    private Context context;
    public MyAsyncTask(TextView textView, ProgressBar progressBar, Context context) {
        this.textView = textView;
        this.progressBar = progressBar;
        this.context = context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG,"onPreExecute(): " + Thread.currentThread().getName());
    }
    @Override
    protected Integer doInBackground(Integer... ints) {
        Integer count = ints[0];
        while (count < 10 && !isCancelled()){
            // isCancelled()表示判断当前任务是否被取消，防止在取消异步任务的时候循环不能及时停下
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            Log.d(TAG,"doInBackground(): "+ Thread.currentThread().getName() +" "+count);
            publishProgress(count);
        }
        return count;
    }
    @Override
    protected void onPostExecute(Integer i) {
        Log.d(TAG,"onPostExecute(): "+ Thread.currentThread().getName());
        textView.setText(i + "");
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
        Log.d(TAG,"onProgressUpdate(): " + Thread.currentThread().getName());
        textView.setText(values[0]+"");
        progressBar.setProgress(values[0]);
    }
    @Override
    protected void onCancelled() {
        Log.d(TAG,"nCancelled(): "+Thread.currentThread().getName());
        super.onCancelled();
        Toast.makeText(context,"任务取消成功", Toast.LENGTH_LONG).show();
    }
}
