package com.example.recyclerview_app;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author（作者）：jtl
 * Date（日期）：2023/3/29 01:38
 * Detail（详情）：
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Data> list = new ArrayList<>();

    public MyAdapter(List<Data> list) {
        this.list = list;
    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType==0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_text,null);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_image,null);
        }

        MyViewHolder myViewHolder = new MyViewHolder(view,viewType);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type){
            case 0:
                holder.textView.setText(list.get(position).data);
                break;
            case 1:
                holder.imageView.setImageResource(list.get(position).imageId);
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).type;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView, int type) {
            super(itemView);
            if (type == 0){
                textView = itemView.findViewById(R.id.rv_text_content);
            }else{
                imageView = itemView.findViewById(R.id.rv_image_content);
            }
        }
    }
}
