package com.ddd.demo.jsondemo;


public class SimpleFactory {

    public static final String TYPE_JSONOBJECT = "jsonobject";
    public static final String TYPE_GSON = "gson";
    public static final String TYPE_FASTJSON = "fastjson";

    public static ParseFactory create(String type){
        ParseFactory factory=null;
        switch(type){
            case TYPE_FASTJSON:
                factory = new FastJsonFactory();
                break;
            case TYPE_GSON:
                factory = new GsonFactory();
                break;
            case TYPE_JSONOBJECT:
                factory = new JSONObjectFactory();
                break;
        }
        return factory;
    }
}
