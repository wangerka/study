package com.ddd.demo.listview_pic.bean;

/**
 * 		"id": 1,
 * 		"name": "Tony老师聊shell——环境变量配置文件",
 * 		"picSmall": "http:\/\/img.mukewang.com\/55237dcc0001128c06000338-300-170.jpg",
 * 		"picBig": "http:\/\/img.mukewang.com\/55237dcc0001128c06000338.jpg",
 * 		"description": "为你带来shell中的环境变量配置文件",
 * 		"learner": 12312
 */
public class Data {
    int id;
    String name;
    String picSmall;
    String picBig;
    String description;
    String learner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicSmall() {
        return picSmall;
    }

    public void setPicSmall(String picSmall) {
        this.picSmall = picSmall;
    }

    public String getPicBig() {
        return picBig;
    }

    public void setPicBig(String picBig) {
        this.picBig = picBig;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLearner() {
        return learner;
    }

    public void setLearner(String learner) {
        this.learner = learner;
    }
}
