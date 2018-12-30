package com.ddd.demo.jsondemo.bean;

/**
 * 	"cityInfo": {
 * 		"city": "天津市",
 * 		"cityId": "101030100",
 * 		"parent": "天津",
 * 		"updateTime": "13:00"
 *        },
 */
class CityInfo {
    String city;
    String cityId;
    String parent;
    String updateTime;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
