package cn.com.youplus.notification.api;

import cn.com.youplus.common.model.base.EmailParams;

/**
 * Created by 严汉羽 on 2017/11/7.
 */
public interface EmailService {


    String sendEmail(EmailParams emailParams);
}
