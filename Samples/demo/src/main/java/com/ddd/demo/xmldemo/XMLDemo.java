package com.ddd.demo.xmldemo;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ddd.demo.R;
import com.ddd.demo.utils.NetUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XMLDemo extends AppCompatActivity implements View.OnClickListener {

    String xmlStr;
    String path = "https://restapi.amap.com/v3/weather/weatherInfo?city=110101&key=5b37cca580393de9edc5a5226162141f&output=xml&extensions=all";
    private InputStream in;
    private List<WeatherData> dataList;
    Button sax,pull,dom;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xml_parse_activity);

        dataList = new ArrayList<WeatherData>();

        sax = (Button)findViewById(R.id.sax);
        sax.setOnClickListener(this);
        pull = (Button)findViewById(R.id.pull);
        pull.setOnClickListener(this);
        dom = (Button)findViewById(R.id.dom);
        dom.setOnClickListener(this);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new MyAdapter(this, dataList));

    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
        Context mContext;
        List<WeatherData> list;
        public MyAdapter(Context c, List<WeatherData> list) {
            mContext =c ;
            this.list = list;
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.xml_weahter_item, viewGroup, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyAdapter.MyViewHolder viewHolder, int i) {
            viewHolder.filldata(list.get(i));
        }

        @Override
        public int getItemCount() {
            return this.list.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView date,week,dayweather,daywind,daytemp,daypower,nightweather,nightwind,nightpower,nighttemp;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                date = (TextView)itemView.findViewById(R.id.date);
                week = (TextView)itemView.findViewById(R.id.week);
                dayweather = (TextView)itemView.findViewById(R.id.dayweather);
                daywind = (TextView)itemView.findViewById(R.id.daywind);
                daytemp = (TextView)itemView.findViewById(R.id.daytemp);
                daypower = (TextView)itemView.findViewById(R.id.daypower);
                nightweather = (TextView)itemView.findViewById(R.id.nightweather);
                nightwind = (TextView)itemView.findViewById(R.id.nightwind);
                nightpower = (TextView)itemView.findViewById(R.id.nightpower);
                nighttemp = (TextView)itemView.findViewById(R.id.nighttemp);
            }

            public void filldata(WeatherData weatherData) {
                date.setText(weatherData.getDate());
                week.setText("周"+weatherData.getWeek());
                dayweather.setText("白天："+weatherData.getDayweather());
                daywind.setText(weatherData.getDaywind());
                daytemp.setText(weatherData.getDaytemp());
                daypower.setText(weatherData.getDaypower());
                nightweather.setText("夜晚："+weatherData.getNightweather());
                nightwind.setText(weatherData.getNightwind());
                nightpower.setText(weatherData.getNightpower());
                nighttemp.setText(weatherData.getNighttemp());
            }
        }
    }

    class getXmlDataTask extends AsyncTask<String, Void, InputStream>{
        String type;
        @Override
        protected InputStream doInBackground(String... strings) {
            type = strings[1];
            return NetUtil.parseData(strings[0]);
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            super.onPostExecute(inputStream);
            parse(inputStream, type);
        }
    }

    private void parse(InputStream inputStream, String type) {
        ParseFactory factory=null;
        switch (type){
            case TYPE_SAX:
                factory = new SaxParseFactory();
                break;
            case TYPE_DOM:
                factory = new DomParseFactory();
                break;
            case TYPE_PULL:
                factory = new PullParseFactory();
                break;
        }
        factory.parse(inputStream, null,dataList);
        recyclerView.setAdapter(new MyAdapter(this, dataList));
    }

    private static final String TYPE_SAX = "sax";
    private static final String TYPE_PULL = "pull";
    private static final String TYPE_DOM = "dom";

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sax:
                new getXmlDataTask().execute(path,TYPE_SAX);
                break;
            case R.id.pull:
                new getXmlDataTask().execute(path,TYPE_PULL);
                break;
            case R.id.dom:
                new getXmlDataTask().execute(path,TYPE_DOM);
                break;
        }
    }
}
