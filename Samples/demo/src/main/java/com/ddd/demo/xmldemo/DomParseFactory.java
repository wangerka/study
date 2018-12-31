package com.ddd.demo.xmldemo;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DomParseFactory extends ParseFactory {
    @Override
    void parse(InputStream in, String encode, List<WeatherData> datas) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(in);
            NodeList list = doc.getElementsByTagName("cast");
            for(int i=0;i<list.getLength();i++){
                WeatherData weatherData = new WeatherData();
                Node node = list.item(i);
                NodeList childList = node.getChildNodes();
                for(int j=0;j<childList.getLength();j++){
                    Node childNode = childList.item(j);
                    String tagName = childNode.getNodeName();
                    if(tagName.equals("date")){
                        weatherData.setDate(childNode.getTextContent());
                    } else if(tagName.equals("week")){
                        weatherData.setWeek(childNode.getTextContent());
                    } else if(tagName.equals("dayweather")){
                        weatherData.setDayweather(childNode.getTextContent());
                    } else if(tagName.equals("nightweather")){
                        weatherData.setNightweather(childNode.getTextContent());
                    } else if(tagName.equals("daytemp")){
                        weatherData.setDaytemp(childNode.getTextContent());
                    } else if(tagName.equals("nighttemp")){
                        weatherData.setNighttemp(childNode.getTextContent());
                    } else if(tagName.equals("daywind")){
                        weatherData.setDaywind(childNode.getTextContent());
                    } else if(tagName.equals("nightwind")){
                        weatherData.setNightwind(childNode.getTextContent());
                    } else if(tagName.equals("daypower")){
                        weatherData.setDaypower(childNode.getTextContent());
                    } else if(tagName.equals("nightpower")){
                        weatherData.setNightpower(childNode.getTextContent());
                    }
                }
                datas.add(weatherData);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
