package com.ddd.demo.jsondemo.bean;
/**
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
 */
public class WeatherDa {
    String date;
    float aqi;
    String ymd;
    String week;
    String sunrise;
    String sunset;
    String low;
    String high;
    String fx;
    String fl;
    String type;
    String notice;

    public WeatherDa() {
    }

    public WeatherDa(String sunraise, String sunset, String low, String high, String fx, String fl, String type, String notice) {
        this.sunrise = sunraise;
        this.sunset = sunset;
        this.low = low;
        this.high = high;
        this.fx = fx;
        this.fl = fl;
        this.type = type;
        this.notice = notice;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

//    public String getSunraise() {
//        return sunrise;
//    }
//
//    public void setSunraise(String sunraise) {
//        this.sunrise = sunraise;
//    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
