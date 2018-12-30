package com.ddd.demo.jsondemo.bean;

/**
 * "shidu": "19%",
 * 		"pm25": 41.0,
 * 		"pm10": 68.0,
 * 		"quality": "良",
 * 		"wendu": "-2",
 * 		"ganmao": "极少数敏感人群应减少户外活动",
 * 		"yesterday": {
 * 			……
 *                },
 * 		"forecast": [{
 * 			……
 *        }, {
 */
public class Data {
    String shidu;
    float pm25;
    float pm10;
    String quality;
    String wendu;
    String ganmao;
    WeatherDa yesterday;
    WeatherDa[] forecast;

    public String getShidu() {
        return shidu;
    }

    public void setShidu(String shidu) {
        this.shidu = shidu;
    }

    public float getPm25() {
        return pm25;
    }

    public void setPm25(float pm25) {
        this.pm25 = pm25;
    }

    public float getPm10() {
        return pm10;
    }

    public void setPm10(float pm10) {
        this.pm10 = pm10;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public WeatherDa getYesterday() {
        return yesterday;
    }

    public void setYesterday(WeatherDa yesterday) {
        this.yesterday = yesterday;
    }

    public WeatherDa[] getForecast() {
        return forecast;
    }

    public void setForecast(WeatherDa[] forecast) {
        this.forecast = forecast;
    }
}
