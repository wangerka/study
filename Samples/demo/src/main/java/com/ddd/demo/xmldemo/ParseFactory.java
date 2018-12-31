package com.ddd.demo.xmldemo;

import java.io.InputStream;
import java.util.List;

public abstract class ParseFactory {

    abstract void parse(InputStream in, String encode, List<WeatherData> datas);
}
