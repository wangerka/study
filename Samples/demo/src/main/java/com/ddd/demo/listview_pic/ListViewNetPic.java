package com.ddd.demo.listview_pic;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ddd.demo.R;
import com.ddd.demo.listview_pic.bean.Data;
import com.ddd.demo.listview_pic.bean.Result;
import com.ddd.demo.utils.NetUtil;
import com.google.gson.Gson;

import java.util.Arrays;

public class ListViewNetPic extends AppCompatActivity {
    private String path = "http://www.imooc.com/api/teacher?type=4&num=30";
    String jsonResult;
    private RecyclerView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pic);

        listView = (RecyclerView)findViewById(R.id.list);
        listView.setLayoutManager(new LinearLayoutManager(this));

        new JsonTask().execute(path);
    }

    class JsonTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            return NetUtil.parseData(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            jsonResult = s;
            Gson gson = new Gson();
            Result result = gson.fromJson(jsonResult,Result.class);
            Data[] datas = result.getData();
            listView.setAdapter(new ListviewAdapter(ListViewNetPic.this, Arrays.asList(datas),listView));
        }
    }
}
