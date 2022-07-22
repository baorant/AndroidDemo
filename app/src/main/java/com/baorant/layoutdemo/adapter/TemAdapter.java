package com.baorant.layoutdemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baorant.layoutdemo.listener.OnItemClickListener;
import com.baorant.layoutdemo.R;

import java.util.List;
import java.util.Random;

public class TemAdapter extends RecyclerView.Adapter<TemAdapter.MyViewHolder>{
    private static final String TAG = "TemAdapter";
    Context context;
    List<String> stringList;

    private OnItemClickListener mClickListener;

    public TemAdapter(Context context, List<String> stringList) {
        Log.d(TAG, "new TemAdapter");
        this.context = context;
        this.stringList = stringList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = View.inflate(context, R.layout.oneitem, null);
        return new MyViewHolder(view, this.mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d(TAG, "onBindViewHolder");
        holder.getTextView().setText(stringList.get(position));
        holder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount");
        return stringList != null ? stringList.size() : 0;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private static final String TAG = "MyViewHolder";
        private final TextView textView;
        private final ImageView imageView;
        private int position;
        // 声明自定义的接口
        private final OnItemClickListener mListener;
        private final int[] drawables = {R.drawable.flower1, R.drawable.flower2,
                R.drawable.flower3};

        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            textView = itemView.findViewById(R.id.oneItem);
            imageView = itemView.findViewById(R.id.itemImage);

            imageView.setBackgroundResource(drawables[new Random().nextInt(3)]);
            textView.setOnClickListener(this);
            this.mListener = listener;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public TextView getTextView() {
            return textView;
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "MyViewHolder onClick");
            mListener.onItemClick(v, this.position);
        }
    }
}
