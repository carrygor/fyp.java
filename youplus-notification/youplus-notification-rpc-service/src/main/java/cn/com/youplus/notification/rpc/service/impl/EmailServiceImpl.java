package cn.com.youplus.notification.rpc.service.impl;

import cn.com.youplus.common.model.base.EmailParams;
import cn.com.youplus.model.auto.entity.notification.UpNotificationEmailRecord;
import cn.com.youplus.notification.api.EmailService;
import cn.com.youplus.notification.api.auto.UpNotificationEmailRecordService;
import cn.com.youplus.notification.common.email.SendmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;

/**
 * Created by 严汉羽 on 2017/11/7.
 */

@Service("emailService")
public class EmailServiceImpl implements EmailService {

    private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private UpNotificationEmailRecordService upNotificationEmailRecordService;

    @Override
    public String sendEmail(EmailParams emailParams) {

        SendmailUtil sendmailUtil = new SendmailUtil();
        List<String> receiverList = emailParams.getReceiverList();
        UpNotificationEmailRecord record = new UpNotificationEmailRecord();
        record.setTitle(emailParams.getTitle())
                .setMailType("通知")
                .setToUser(emailParams.getReceiverList().toString())
                .setAddTime(new Date())
                .setUpdateTime(new Date());
        try {
            if (receiverList.size() == 1) {
                sendmailUtil.doSendHtmlEmail(emailParams.getTitle(), emailParams.getContent(), receiverList.get(0));
            } else {
                sendmailUtil.doSendHtmlEmail(emailParams.getTitle(), emailParams.getContent(), receiverList);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.info("邮件发送失败:");
            logger.info(e.toString());
            record.setResult("发送失败");
            upNotificationEmailRecordService.insert(record);
            return "发送失败" + e.toString();
        }
        record.setResult("发送成功");
        upNotificationEmailRecordService.insert(record);
        return "发送成功";
    }

}
