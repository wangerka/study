package com.ddd.demo.listview_pic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ddd.demo.R;
import com.ddd.demo.listview_pic.bean.Data;

import java.util.List;

public class ListviewAdapter extends RecyclerView.Adapter {

    public List<Data> getDatas() {
        return datas;
    }

    List<Data> datas;
    Context mContext;
    RecyclerView recyclerView;
    ImageLoader loader;

    int start;
    int end;

    boolean fitsrEnter = true;

    public ListviewAdapter(Context c, List<Data> datas, RecyclerView view) {
        this.datas = datas;
        mContext = c;
        recyclerView = view;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    loader.loadImages(start, end);
                } else {
                    loader.cancelAllTask();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if(layoutManager instanceof LinearLayoutManager){
                    start = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                    end = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();

                    if(fitsrEnter){
                        loader.loadImages(start, end);
                        fitsrEnter = false;
                    }
                }
            }
        });
        loader = new ImageLoader(view);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_pic_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Data d = datas.get(i);
        String title = d.getName();
        String des = d.getDescription();
        String pic = d.getPicSmall();

        MyViewHolder view = (MyViewHolder)viewHolder;
        view.title.setText(title);
        view.des.setText(des);
        view.pic.setTag(pic);
        if(loader.getFromcache(pic) != null){
            view.pic.setImageBitmap(loader.getFromcache(pic));
        } else {
            view.pic.setImageResource(R.mipmap.ic_launcher);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView des;
        ImageView pic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            des = (TextView)itemView.findViewById(R.id.descri);
            pic = (ImageView)itemView.findViewById(R.id.iamge);
        }
    }

}
