package com.ddd.demo.xmldemo;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SaxParseFactory extends ParseFactory {
    @Override
    void parse(InputStream in, String encode, List<WeatherData> datas) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(in, new MyDefaultHandler(datas));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class MyDefaultHandler extends DefaultHandler{

        List<WeatherData> datas;
        String tagName;
        WeatherData weatherData=null;

        public MyDefaultHandler(List<WeatherData> datas) {
            this.datas = datas;
        }
        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            tagName = localName;
            if(tagName.equals("cast")){
                weatherData = new WeatherData();
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if(localName.equals("cast")){
                datas.add(weatherData);
                weatherData=null;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            String text = new String(ch, start, length);
            if(tagName.equals("date")){
                weatherData.setDate(text);
            } else if(tagName.equals("week")){
                weatherData.setWeek(text);
            } else if(tagName.equals("dayweather")){
                weatherData.setDayweather(text);
            } else if(tagName.equals("nightweather")){
                weatherData.setNightweather(text);
            } else if(tagName.equals("daytemp")){
                weatherData.setDaytemp(text);
            } else if(tagName.equals("nighttemp")){
                weatherData.setNighttemp(text);
            } else if(tagName.equals("daywind")){
                weatherData.setDaywind(text);
            } else if(tagName.equals("nightwind")){
                weatherData.setNightwind(text);
            } else if(tagName.equals("daypower")){
                weatherData.setDaypower(text);
            } else if(tagName.equals("nightpower")){
                weatherData.setNightpower(text);
            }
        }
    }
}
