package com.ddd.demo.xmldemo;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <date>2019-01-03</date>
 * <week>4</week>
 * <dayweather>多云</dayweather>
 * <nightweather>晴</nightweather>
 * <daytemp>3</daytemp>
 * <nighttemp>-8</nighttemp>
 * <daywind>西</daywind>
 * <nightwind>西</nightwind>
 * <daypower>≤3</daypower>
 * <nightpower>≤3</nightpower>
 */
public class PullParseFactory extends ParseFactory {
    @Override
    void parse(InputStream in, String encode, List<WeatherData> data) {
        try {
//            XmlPullParser parse = Xml.newPullParser();
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parse = factory.newPullParser();
            parse.setInput(in, encode);
            int eventType = parse.getEventType();
            WeatherData d=null;
            while(eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.END_TAG:
                        if(parse.getName().equals("cast")){
                            data.add(d);
                            d=null;
                        }
                        break;
                    case XmlPullParser.START_TAG:
                        String tagName = parse.getName();
//                        String value = parse.nextText();
                        if(tagName.equals("cast")){
                            d=new WeatherData();
                        } else if(tagName.equals("date")){
                            d.setDate(parse.nextText());
                        } else if(tagName.equals("week")){
                            d.setWeek(parse.nextText());
                        } else if(tagName.equals("dayweather")){
                            d.setDayweather(parse.nextText());
                        } else if(tagName.equals("nightweather")){
                            d.setNightweather(parse.nextText());
                        } else if(tagName.equals("daytemp")){
                            d.setDaytemp(parse.nextText());
                        } else if(tagName.equals("nighttemp")){
                            d.setNighttemp(parse.nextText());
                        } else if(tagName.equals("daywind")){
                            d.setDaywind(parse.nextText());
                        } else if(tagName.equals("nightwind")){
                            d.setNightwind(parse.nextText());
                        } else if(tagName.equals("daypower")){
                            d.setDaypower(parse.nextText());
                        } else if(tagName.equals("nightpower")){
                            d.setNightpower(parse.nextText());
                        }
                        break;
                    case XmlPullParser.TEXT:
                        break;
                }
                eventType = parse.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * String tag_name = parse.getName();
 *                         String text = parse.nextText();
 *                         if(tag_name.equals("date")){
 *                             d.setDate(text);
 *                         } else if(tag_name.equals("week")){
 *                             d.setWeek(text);
 *                         } else if(tag_name.equals("dayweather")){
 *                             d.setDayweather(text);
 *                         } else if(tag_name.equals("nightweather")){
 *                             d.setNightweather(text);
 *                         } else if(tag_name.equals("daytemp")){
 *                             d.setDaytemp(text);
 *                         } else if(tag_name.equals("nighttemp")){
 *                             d.setNighttemp(text);
 *                         } else if(tag_name.equals("daywind")){
 *                             d.setDaywind(text);
 *                         } else if(tag_name.equals("nightwind")){
 *                             d.setNightwind(text);
 *                         } else if(tag_name.equals("daypower")){
 *                             d.setDaypower(text);
 *                         } else if(tag_name.equals("nightpower")){
 *                             d.setNightpower(text);
 *                         } else if(tag_name.equals("cast")){
 *                             d = new WeatherData();
 *                         }
 *                         break;
 */
