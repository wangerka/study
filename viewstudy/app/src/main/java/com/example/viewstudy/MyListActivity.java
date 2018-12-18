package com.example.viewstudy;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gejun on 18-12-18.
 */

public class MyListActivity extends ListActivity implements DragListView.DragListener{

    List<String> data;
    ListAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.first_fragment);
        data = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            data.add("test" + i);
        }
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, data);
        setListAdapter(adapter);
        ((DragListView)getListView()).setListener(this);

    }

    @Override
    public void changeItem(int origin, int dest) {
        String ori = (String) adapter.getItem(origin);
        String des = (String) adapter.getItem(dest);
        Log.i("gejun","ori = " +ori+", des = " +des);
        Log.i("gejun","origin = " +origin+", dest = " +dest);
        data.remove(ori);
        data.remove(des);
        if(origin < dest){
            data.add(origin, des);
            data.add(dest, ori);
        } else {
            data.add(dest, ori);
            data.add(origin, des);
        }
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, data);
        setListAdapter(adapter);
    }
}
