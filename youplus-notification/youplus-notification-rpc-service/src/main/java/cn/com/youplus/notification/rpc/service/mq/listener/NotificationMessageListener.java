package cn.com.youplus.notification.rpc.service.mq.listener;

import cn.com.youplus.base.api.mq.MessageQueueService;
import cn.com.youplus.common.model.base.EmailParams;
import cn.com.youplus.common.model.base.SmsParams;
import cn.com.youplus.common.model.enums.MessageQueueTagEnum;
import cn.com.youplus.notification.api.EmailService;
import cn.com.youplus.notification.api.SmsService;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 严汉羽 on 2017/7/1.
 */
public class NotificationMessageListener implements MessageListener {
    private static Logger logger = Logger.getLogger(NotificationMessageListener.class);

    @Autowired
    private SmsService smsService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private MessageQueueService messageQueueService;

    @Override
    public Action consume(Message message, ConsumeContext consumeContext) {
        logger.info("Receive: " + message.getMsgID());
        logger.debug(message.toString());
        try {
            //message.getTag()
            //do something..
            MessageQueueTagEnum tagEnum = MessageQueueTagEnum.valueOf(message.getTag());

            switch (tagEnum) {
                case TIMER_SMS:
                case NORMAL_SMS:
                    SmsParams smsParams = JSONObject.parseObject(message.getBody(), SmsParams.class);
                    String result = smsService.sendSms(smsParams);
                    messageQueueService.consumeMessageQueue(message.getKey(), result);
                    break;
                case NORMAL_EMAIL:
                case TIMER_EMAIL:
                    EmailParams emailParams = JSONObject.parseObject(message.getBody(), EmailParams.class);
                    String emailResult = emailService.sendEmail(emailParams);
                    messageQueueService.consumeMessageQueue(message.getKey(), emailResult);
                    break;
                /**
                 * 注意，如果添加tag类型，需要在MQconsumer里面添加订阅的tag，否则无法接收
                 */
                default:
                    logger.info("unknown message queue tag type");
                    break;
            }

            return Action.CommitMessage;
        }catch (Exception e) {
            //消费失败
            return Action.ReconsumeLater;
        }
    }

}
