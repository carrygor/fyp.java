package cn.com.youplus.notification.api;


import cn.com.youplus.common.model.base.Location;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 严汉羽 on 2017/6/30.
 */
public interface LocationBaseService {

    String LOCATION_IP_URL = "https://api.map.baidu.com/location/ip";
    String LOCATION_LOCATION_URL = "http://api.map.baidu.com/geocoder/v2/";
    String BAIDU_AK = "xmGgzdM1ZsLBqkAV2LE9buQDbluybjaG";

    Location getLocationByIp(String ip) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    Location getLocationByLocation(Long weixinUserId, float lat, float lng) throws Exception;
}
