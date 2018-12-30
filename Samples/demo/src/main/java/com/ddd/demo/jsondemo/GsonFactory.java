package com.ddd.demo.jsondemo;

import com.ddd.demo.jsondemo.bean.WeatherDa;
import com.ddd.demo.jsondemo.bean.WeatherResult;
import com.ddd.demo.jsondemo.bean.Data;
import com.google.gson.Gson;

import java.util.List;

public class GsonFactory extends ParseFactory {
    @Override
    void parse(List<WeatherDa> weater, String json) {
        Gson gson = new Gson();
        WeatherResult result = gson.fromJson(json, WeatherResult.class);
        Data data = result.getData();
        WeatherDa yesterday = data.getYesterday();
        WeatherDa[] forecast = data.getForecast();
        weater.add(yesterday);
        for(int i=0;i<forecast.length;i++){
            weater.add(forecast[i]);
        }
    }
}
