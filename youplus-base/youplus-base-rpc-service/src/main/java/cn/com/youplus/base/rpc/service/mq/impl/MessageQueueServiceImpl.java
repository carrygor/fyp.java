package cn.com.youplus.base.rpc.service.mq.impl;

import cn.com.youplus.base.api.mq.MessageQueueService;
import cn.com.youplus.common.model.base.EmailParams;
import cn.com.youplus.common.model.base.MessageQueueParams;
import cn.com.youplus.common.model.base.SmsParams;
import cn.com.youplus.common.model.enums.MessageQueueTagEnum;
import cn.com.youplus.common.model.resources.AliyunProperties;
import cn.com.youplus.common.util.ValueHelper;
import cn.com.youplus.model.auto.entity.notification.UpNotificationMessageQueueRecord;
import cn.com.youplus.notification.api.auto.UpNotificationMessageQueueRecordService;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * Created by 严汉羽 on 2017/6/24.
 */

@Service("messageQueueService")
public class MessageQueueServiceImpl implements MessageQueueService {

    private final static Logger logger = Logger.getLogger(MessageQueueServiceImpl.class);

    @Resource
    private AliyunProperties aliyunProperties;

    @Autowired
    private UpNotificationMessageQueueRecordService upNotificationMessageQueueRecordService;

    @Autowired
    private ProducerBean producerBean;
    /**
     * 发送消息到阿里云
     * @param messageQueueParams
     * @throws Exception
     */

    @Override
    public void sendMq(MessageQueueParams messageQueueParams) throws Exception {
        String body;
        if (messageQueueParams.getBody() instanceof String) {
            body = (String) messageQueueParams.getBody();
        } else {
            body = JSONObject.toJSONString(messageQueueParams.getBody());
        }

        Date deliverTime = messageQueueParams.getDeliverTime();
        Message msg = new Message( //
                deliverTime == null ? aliyunProperties.getAliyunMqBlockOrderTopicName() : aliyunProperties.getAliyunMqNoneOrderTopicName(),
                messageQueueParams.getTag(),
                body.getBytes());
        // 设置代表消息的业务关键属性，请尽可能全局唯一
        // 以方便您在无法正常收到消息情况下，可通过MQ 控制台查询消息并补发
        // 注意：不设置也不会影响消息正常收发
        msg.setKey(UUID.randomUUID().toString());
        if (deliverTime != null) {
            msg.setStartDeliverTime(deliverTime.getTime());
        }
        // 发送消息，只要不抛异常就是成功
        UpNotificationMessageQueueRecord record = new UpNotificationMessageQueueRecord();
        record.setAddTime(new Date())
                .setUpdateTime(new Date())
                .setBody(body)
                .setTag(messageQueueParams.getTag())
                .setMessageKey(msg.getKey())
                .setTopic(msg.getTopic())
                .setConsumeResult("")
                .setDeliverTime(deliverTime == null ? new Date() : deliverTime);
        try {
            SendResult sendResult = producerBean.send(msg);
            if (sendResult != null) {
                record.setMessageId(sendResult.getMessageId());
                record.setSendResult("成功");
                record.setSendResultJson(JSONObject.toJSONString(sendResult));
            } else {
                record.setSendResult("失败");
                record.setSendResultJson("");
            }
            logger.debug(JSONObject.toJSONString(sendResult));
        }catch (Exception e) {
            record.setSendResult("发送异常").setSendResultJson(e.toString());
            logger.info("发送异常",e);
        }
        upNotificationMessageQueueRecordService.insert(record);
    }

    /**
     * 异步发送，异步通过Dubbo实现
     * @param messageQueueParams
     * @throws Exception
     */
    @Override
    public void sendMqAsync(MessageQueueParams messageQueueParams) throws Exception {
        sendMq(messageQueueParams);
    }

    @Override
    public void consumeMessageQueue(String msgKey, String result) {
        try {
            if (ValueHelper.isNone(msgKey)) {
                return;
            }
            UpNotificationMessageQueueRecord record = upNotificationMessageQueueRecordService.selectOne(
                    new EntityWrapper<UpNotificationMessageQueueRecord>()
                            .eq(UpNotificationMessageQueueRecord.MESSAGE_KEY, msgKey));
            if (record != null) {
                record.setConsumeResult(result);
                record.setUpdateTime(new Date());
                record.setConsumeTimes(record.getConsumeTimes() + 1);

                upNotificationMessageQueueRecordService.updateById(record);
            } else {
                logger.info("找不到msgKey为[" + msgKey + "], 无法更新记录结果为:[" + result + "]");
            }
        } catch (Exception e) {
            logger.info("找不到msgKey为[" + msgKey + "], 无法更新记录结果为:[" + result + "]失败原因：[" + e.getMessage() +"]");
        }
    }

    /**
     * 可以重复消费的
     * @param msgKey
     * @param result
     * @return
     */
    @Override
    public boolean consumeMessageQueueWithRetry(String msgKey, String result) {
        if (ValueHelper.isNone(msgKey)) {
            logger.info("msgKey 为 null,不需要更新");
            return false;
        }
        UpNotificationMessageQueueRecord record = upNotificationMessageQueueRecordService.selectOne(
                new EntityWrapper<UpNotificationMessageQueueRecord>()
                        .eq(UpNotificationMessageQueueRecord.MESSAGE_KEY, msgKey));
        try {
            if (record == null) {
                return true;
            }

            if (record.getConsumeTimes() > MessageQueueService.MAX_CONSOME_TIMES) { //超过10 消费不了，就不用消费了
                record.setConsumeResult("消费失败: 消费次数超过最大次数[" + MessageQueueService.MAX_CONSOME_TIMES + "]");
                record.setUpdateTime(new Date());
                upNotificationMessageQueueRecordService.updateById(record);
                return true;
            } else {
                record.setConsumeTimes(record.getConsumeTimes() + 1);
                record.setUpdateTime(new Date());
                upNotificationMessageQueueRecordService.updateById(record);
                return false;
            }
        } catch (Exception e1) {

            logger.info("", e1);
            return true;
        }
    }

    @Override
    public void sendSms(SmsParams smsParams, Date deliverTime) throws Exception {
        MessageQueueParams messageQueueParams = new MessageQueueParams();
        if (deliverTime != null) {
            messageQueueParams.setBody(JSONObject.toJSONString(smsParams))
                    .setTag(MessageQueueTagEnum.TIMER_SMS.getType())
                    .setDeliverTime(deliverTime);
        } else {
            messageQueueParams.setBody(JSONObject.toJSONString(smsParams))
                    .setTag(MessageQueueTagEnum.NORMAL_SMS.getType())
                    .setDeliverTime(null);
        }

        sendMq(messageQueueParams);
    }

    @Override
    public void sendEmail(EmailParams emailParams, Date deliverTime) throws Exception {
        MessageQueueParams messageQueueParams = new MessageQueueParams();
        if (deliverTime != null) {
            messageQueueParams.setBody(JSONObject.toJSONString(emailParams))
                    .setTag(MessageQueueTagEnum.TIMER_EMAIL.getType())
                    .setDeliverTime(deliverTime);
        } else {
            messageQueueParams.setBody(JSONObject.toJSONString(emailParams))
                    .setTag(MessageQueueTagEnum.NORMAL_EMAIL.getType())
                    .setDeliverTime(null);
        }

        sendMq(messageQueueParams);
    }
}
