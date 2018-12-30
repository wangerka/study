package com.ddd.demo.jsondemo;

import com.ddd.demo.jsondemo.bean.WeatherDa;

import java.util.List;

public abstract class ParseFactory {
    abstract void parse(List<WeatherDa> weater, String json);
}
