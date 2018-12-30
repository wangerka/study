package com.ddd.demo.jsondemo;

import com.alibaba.fastjson.JSON;
import com.ddd.demo.jsondemo.bean.Data;
import com.ddd.demo.jsondemo.bean.WeatherDa;
import com.ddd.demo.jsondemo.bean.WeatherResult;

import java.util.List;

public class FastJsonFactory extends ParseFactory {
    @Override
    void parse(List<WeatherDa> weater, String json) {
        WeatherResult result = JSON.parseObject(json, WeatherResult.class);
        Data data = result.getData();
        WeatherDa yesterday = data.getYesterday();
        WeatherDa[] forecast = data.getForecast();
        weater.add(yesterday);
        for(int i=0;i<forecast.length;i++){
            weater.add(forecast[i]);
        }
    }
}
