package cn.com.carry.common.model.base;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/19.
 */
public class Location implements Serializable {

    /**
     * 序列
     */
    private static final long serialVersionUID = 1L;

    /**
     * 精度
     */
    private float longitude;

    /**
     * 纬度
     */
    private float latitude;

    private String city;

    private String district;

    private String country;

    private String province;

    private String street;

    private String detail;

    private Date lastTime;

    private int cityCode;

    public Location(){

    }

    public Location(boolean iamLatAndLng, String jsonStr) {
        JSONObject json = (JSONObject) JSONObject.parse(jsonStr);
        if (json.getIntValue("status") == 0) {
            JSONObject result = json.getJSONObject("result");

            JSONObject location = result.getJSONObject("location");
            longitude = location.getFloatValue("lng");
            latitude = location.getFloatValue("lat");

            detail = result.getString("formatted_address");

            JSONObject addressComponent = result.getJSONObject("addressComponent");
            country = addressComponent.getString("country");
            province = addressComponent.getString("province");
            city = addressComponent.getString("city");
            district = addressComponent.getString("district");
            street = addressComponent.getString("street");
        }
    }

    public Location(String jsonStr) {
        JSONObject json = (JSONObject) JSONObject.parse(jsonStr);
        if (json.getIntValue("status") == 0) {
            String total = json.getString("address");
            String [] totalArr = total.split("|");

            if (totalArr.length > 0) {
                country = totalArr[0];
                country = country.toUpperCase().equals("CN") ? "中国" : country;
            }
            JSONObject content = json.getJSONObject("content");
            detail = content.getString("address");
            JSONObject point = content.getJSONObject("point");
            longitude = point.getFloatValue("x");
            latitude = point.getFloatValue("y");

            JSONObject address_detail = content.getJSONObject("address_detail");
            province = address_detail.getString("province");
            city = address_detail.getString("city");
            district = address_detail.getString("district");
            street = address_detail.getString("street");
            cityCode = address_detail.getIntValue("city_code");
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDetail() {
        return detail;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
