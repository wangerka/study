package com.ddd.demo.jsondemo;

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
import com.ddd.demo.jsondemo.bean.WeatherDa;
import com.ddd.demo.utils.NetUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 不同的方式解析，工厂模式？
 * 1,JSONObject
 * 2,GSON
 * 3,fastJson
 */

/**
 * {
 * 	"time": "2018-12-30 13:11:57",
 * 	"cityInfo": {
 * 		"city": "天津市",
 * 		"cityId": "101030100",
 * 		"parent": "天津",
 * 		"updateTime": "13:00"
 *        },
 * 	"date": "20181230",
 * 	"message": "Success !",
 * 	"status": 200,
 * 	"data": {
 * 		"shidu": "19%",
 * 		"pm25": 41.0,
 * 		"pm10": 68.0,
 * 		"quality": "良",
 * 		"wendu": "-2",
 * 		"ganmao": "极少数敏感人群应减少户外活动",
 * 		"yesterday": {
 * 			"date": "29",
 * 			"sunrise": "07:30",
 * 			"high": "高温 -2.0℃",
 * 			"low": "低温 -8.0℃",
 * 			"sunset": "16:56",
 * 			"aqi": 52.0,
 * 			"ymd": "2018-12-29",
 * 			"week": "星期六",
 * 			"fx": "西北风",
 * 			"fl": "4-5级",
 * 			"type": "晴",
 * 			"notice": "愿你拥有比阳光明媚的心情"
 *        },
 * 		"forecast": [{
 * 			"date": "30",
 * 			"sunrise": "07:30",
 * 			"high": "高温 0.0℃",
 * 			"low": "低温 -6.0℃",
 * 			"sunset": "16:57",
 * 			"aqi": 59.0,
 * 			"ymd": "2018-12-30",
 * 			"week": "星期日",
 * 			"fx": "北风",
 * 			"fl": "<3级",
 * 			"type": "晴",
 * 			"notice": "愿你拥有比阳光明媚的心情"
 *        }, {
 * 			"date": "31",
 */
public class JSONDmeo extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView weatherInfo;
    String path = "http://t.weather.sojson.com/api/weather/city/101030100";

    private Button jsonobject,gson,fastjson;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
        weatherInfo = (RecyclerView)findViewById(R.id.weather_recyclerview);
        weatherInfo.setLayoutManager(new LinearLayoutManager(this));
//        new WeatherTask().execute(path);
        
        jsonobject = (Button)findViewById(R.id.jsonobject);
        gson = (Button)findViewById(R.id.gson);
        fastjson = (Button)findViewById(R.id.fastjson);
        jsonobject.setOnClickListener(this);
        gson.setOnClickListener(this);
        fastjson.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jsonobject:
                new WeatherTask().execute(path,SimpleFactory.TYPE_JSONOBJECT);
                break;
            case R.id.gson:
                new WeatherTask().execute(path,SimpleFactory.TYPE_GSON);
                break;
            case R.id.fastjson:
                new WeatherTask().execute(path,SimpleFactory.TYPE_FASTJSON);
                break;
        }
    }

    class WeatherAdapter extends RecyclerView.Adapter{

        class WeatherViewHolder extends RecyclerView.ViewHolder{
            TextView sunrise,sunset,high,low,fx,fl,type,notice;

            public WeatherViewHolder(@NonNull View itemView) {
                super(itemView);
                sunrise =(TextView)itemView.findViewById(R.id.sunrise);
                sunset =(TextView)itemView.findViewById(R.id.sunset);
                high =(TextView)itemView.findViewById(R.id.high);
                low =(TextView)itemView.findViewById(R.id.low);
                fx =(TextView)itemView.findViewById(R.id.fx);
                fl =(TextView)itemView.findViewById(R.id.fl);
                type =(TextView)itemView.findViewById(R.id.type);
                notice =(TextView)itemView.findViewById(R.id.notice);
            }

            public void fillData(WeatherDa data){
                sunrise.setText(data.getSunrise());
                sunset.setText(data.getSunset());
                high.setText(data.getHigh());
                low.setText(data.getLow());
                fx.setText(data.getFx());
                fl.setText(data.getFl());
                type.setText(data.getType());
                notice.setText(data.getNotice());
            }
        }

        private List<WeatherDa> weatherDatas;

        public WeatherAdapter(List<WeatherDa> data) {
            weatherDatas  = data;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(JSONDmeo.this).inflate(R.layout.day_weather, viewGroup,false);
            return new WeatherViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            WeatherViewHolder view = (WeatherViewHolder)viewHolder;
            view.fillData(weatherDatas.get(i));
        }

        @Override
        public int getItemCount() {
            return weatherDatas.size();
        }
    }

    class WeatherTask extends AsyncTask<String, Void,String>{
        
        private String parseType;
        
        @Override
        protected String doInBackground(String... strings) {
            String weatherUrl = strings[0];
            parseType = strings[1];
            return NetUtil.readStream(NetUtil.parseData(weatherUrl));
        }

        @Override
        protected void onPostExecute(String s) {
            updateWeather(s,parseType);
        }
    }

    private List<WeatherDa> weatherList = new ArrayList<>();

    public void updateWeather(String weatherJson, String type){
        parseJsonString(weatherJson,type);
        weatherInfo.setAdapter(new WeatherAdapter(weatherList));
    }

    public void parseJsonString(String json, String type){
        weatherList.clear();
        ParseFactory factory = SimpleFactory.create(type);
        factory.parse(weatherList, json);
    }

    /*private void jsonobjectParse(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String time = jsonObject.getString("time");
            JSONObject cityInfoObject = jsonObject.getJSONObject("cityInfo");
            String privince = cityInfoObject.getString("parent");
            String city = cityInfoObject.getString("city");

            JSONObject dataObject = jsonObject.getJSONObject("data");
            JSONObject yesterdayObject = dataObject.getJSONObject("yesterday");
            weatherList.add(parseWeatherDa(yesterdayObject));
            JSONArray forcastObjects = dataObject.getJSONArray("forecast");
            for(int i =0;i<forcastObjects.length();i++){
                weatherList.add(parseWeatherDa(forcastObjects.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

    private void gsonParse(String json) {

    }

    private void fastjsonParse(String json) {

    }


    /*public WeatherDa parseWeatherDa(JSONObject weather){
        try {
            return new WeatherDa(weather.getString("sunrise"),
                    weather.getString("sunset"),
                    weather.getString("low"),
                    weather.getString("high"),
                    weather.getString("fx"),
                    weather.getString("fl"),
                    weather.getString("type"),
                    weather.getString("notice"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }*/
}
