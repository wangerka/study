package com.ddd.demo.timeline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ddd.demo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeLineActivity extends AppCompatActivity {

    List<Map<String,String>> dataList;
    RecyclerView recyclerView;

    public static final String KEY1 = "ItemTitle";
    public static final String KEY2 = "ItemText";
    public static final String KEY_TIME = "time";
    public static final String KEY_DATE = "date";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline_activity);

        initData();

        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView)findViewById(R.id.timeline_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addItemDecoration(new TimeLineDecorator(dataList));

        recyclerView.setAdapter(new MyAdapter(dataList, this));
    }

    private void initData() {
        dataList = new ArrayList<Map<String, String>>();

        HashMap<String, String> map1 = new HashMap<String, String>();
        HashMap<String, String> map2 = new HashMap<String, String>();
        HashMap<String, String> map3 = new HashMap<String, String>();
        HashMap<String, String> map4 = new HashMap<String, String>();
        HashMap<String, String> map5 = new HashMap<String, String>();
        HashMap<String, String> map6 = new HashMap<String, String>();

        map1.put(KEY1, "美国谷歌公司已发出");
        map1.put(KEY2, "发件人:谷歌 CEO Sundar Pichai");
        map1.put(KEY_TIME, "13:02");
        map1.put(KEY_DATE, "2019.01.02");
        dataList.add(map1);

        map2.put(KEY1, "国际顺丰已收入");
        map2.put(KEY2, "等待中转");
        map2.put(KEY_TIME, "13:02");
        map2.put(KEY_DATE, "2019.01.02");
        dataList.add(map2);

        map3.put(KEY1, "国际顺丰转件中");
        map3.put(KEY2, "下一站中国");
        map3.put(KEY_TIME, "13:02");
        map3.put(KEY_DATE, "2019.01.02");
        dataList.add(map3);

        map4.put(KEY1, "中国顺丰已收入");
        map4.put(KEY2, "下一站广州华南理工大学");
        map4.put(KEY_TIME, "13:02");
        map4.put(KEY_DATE, "2019.01.02");
        dataList.add(map4);

        map5.put(KEY1, "中国顺丰派件中");
        map5.put(KEY2, "等待派件");
        map5.put(KEY_TIME, "13:02");
        map5.put(KEY_DATE, "2019.01.02");
        dataList.add(map5);

        map6.put(KEY1, "华南理工大学已签收");
        map6.put(KEY2, "收件人:Carson");
        map6.put(KEY_TIME, "13:02");
        map6.put(KEY_DATE, "2019.01.02");
        dataList.add(map6);
    }
}
