package com.baorant.layoutdemo.activity;

import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baorant.layoutdemo.AbstractSubActivity;
import com.baorant.layoutdemo.R;

@Route(path = "/base/VideoViewActivity")
public class VideoViewActivity extends AbstractSubActivity {
    private static final String TAG = "VideoViewActivity";
    private VideoView videoView;
    private MediaController mediaController;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        videoView = findViewById(R.id.videoView);
        mediaController = new MediaController(this);

        uri = Uri.parse("http://vjs.zencdn.net/v/oceans.mp4");
        getWindow().setFormat(PixelFormat.TRANSLUCENT);

        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);

        videoView.setOnPreparedListener(mp -> {
            Log.d(TAG, "MediaPlayer onPrepared");
            videoView.start();
        });

        videoView.setOnErrorListener((mp, what, extra) -> {
            Log.d(TAG, "MediaPlayer onError");
            return false;
        });
    }
}