package com.ddd.demo.timeline;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ddd.demo.R;

import java.util.List;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter {

    List<Map<String, String>> listData;
    Context mContext;

    public MyAdapter(List<Map<String, String>> listData, Context mContext) {
        this.listData = listData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.timeline_recycler_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyViewHolder view = (MyViewHolder)viewHolder;
        Map<String, String> map = listData.get(i);
        view.setValue(map.get(TimeLineActivity.KEY1), map.get(TimeLineActivity.KEY2));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView text1,text2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = (TextView)itemView.findViewById(R.id.text1);
            text2 = (TextView)itemView.findViewById(R.id.text2);
        }

        public void setValue(String a, String b){
            text1.setText(a);
            text2.setText(b);
        }
    }
}
