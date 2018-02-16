package cn.com.youplus.thirdparty.api.weixin;

import java.util.Map;

/**
 * Created by 严汉羽 on 2017/6/30.
 */
public interface WeixinApiService {
    String handleInputMessage(Map<String, String> dataMap) throws Exception;

    void updateWeixinAccountAuthorization(String auth_code);
}
