package com.ddd.demo.jsondemo;

import com.ddd.demo.jsondemo.bean.WeatherDa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JSONObjectFactory extends ParseFactory {
    @Override
    void parse(List<WeatherDa> weatherList, String json) {
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
    }

    public WeatherDa parseWeatherDa(JSONObject weather){
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
    }
}
