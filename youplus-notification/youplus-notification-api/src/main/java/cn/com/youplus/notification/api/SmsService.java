package cn.com.youplus.notification.api;

import cn.com.youplus.common.model.base.SmsParams;
import cn.com.youplus.model.auto.entity.notification.UpNotificationSmsRecord;

/**
 * Created by 严汉羽 on 2017/11/7.
 */
public interface SmsService {

    //产品名称:云通信短信API产品,开发者无需替换
    String PRODUCT = "Dysmsapi";
    //产品域名,开发者无需替换
    String DOMAIN = "dysmsapi.aliyuncs.com";
    //产品端点,开发者无需替换
    String ENDPOINT = "cn-shenzhen";

    int VERFY_CODE_LENGTH = 4;

    /**
     * 验证码${code}，您正在进行身份验证，打死不要告诉别人哦！
     */
    String 身份验证验证码 = "SMS_109735336";
    String 问卷验证码 = "SMS_125021754";

    String sendSms(SmsParams smsParams);

    UpNotificationSmsRecord checkVerifyCode(String phone, String code);

    boolean checkVerifyCodeOneMinute(String phone);

    void consumeVerifyCode(UpNotificationSmsRecord record);
}
