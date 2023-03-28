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
    List<String> list = new ArrayList<>();

    public MyAdapter(List<String> list) {
        this.list = list;
    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType==1){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_image,null);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_text,null);
        }

        MyViewHolder myViewHolder = new MyViewHolder(view,viewType);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        int type = 0;
        TextView textView;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView, int type) {
            super(itemView);
            this.type = type;
            if (type == 1){
                imageView = itemView.findViewById(R.id.rv_image_content);
            }else{
                textView = itemView.findViewById(R.id.rv_text_content);
            }

        }
    }
}
