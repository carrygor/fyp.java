package cn.com.youplus.base.api.mq;


import cn.com.youplus.common.model.base.EmailParams;
import cn.com.youplus.common.model.base.MessageQueueParams;
import cn.com.youplus.common.model.base.SmsParams;

import java.util.Date;

/**
 * Created by admin on 2017/8/9.
 */
public interface MessageQueueService {

    int MAX_CONSOME_TIMES = 10;

    void sendMq(MessageQueueParams messageQueueParams) throws Exception;

    void sendMqAsync(MessageQueueParams messageQueueParams) throws Exception;

    void consumeMessageQueue(String msgKey, String result);

    boolean consumeMessageQueueWithRetry(String msgKey, String result);

    void sendSms(SmsParams smsParams, Date deliverTime) throws Exception;

    void sendEmail(EmailParams emailParams, Date deliverTime) throws Exception;
}
