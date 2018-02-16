package cn.com.youplus.thirdparty.api.weixin;

import cn.com.youplus.apps.common.model.enums.EntranceEnum;
import cn.com.youplus.model.auto.entity.thirdparty.UpWeixinUser;

import java.util.Map;

/**
 * Created by 严汉羽 on 2017/6/30.
 */
public interface WeixinService {
    UpWeixinUser getWeixinUserByCode(String code,String cookie, EntranceEnum entranceEnum) throws Exception;
    UpWeixinUser getWeixinUserByRefreshToken( String cookie, EntranceEnum entranceEnum) throws Exception;
}
