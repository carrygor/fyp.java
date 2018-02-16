package cn.com.youplus.notification.rpc.service.impl;

import cn.com.youplus.common.model.base.SmsParams;
import cn.com.youplus.common.model.enums.SmsTypeEnum;
import cn.com.youplus.common.model.resources.AliyunProperties;
import cn.com.youplus.common.util.Constants;
import cn.com.youplus.common.util.MD5Util;
import cn.com.youplus.model.auto.entity.notification.UpNotificationSmsRecord;
import cn.com.youplus.notification.api.SmsService;
import cn.com.youplus.notification.api.auto.UpNotificationSmsRecordService;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by 严汉羽 on 2017/11/7.
 */

@Service("smsService")
public class SmsServiceImpl implements SmsService {

    private static Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Autowired
    private AliyunProperties aliyunProperties;

    @Autowired
    private UpNotificationSmsRecordService upNotificationSmsRecordService;

    /**
     * 发送短信，不保证发送成功
     * @param smsParams
     */
    @Override
    public String sendSms(SmsParams smsParams) {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile(SmsService.ENDPOINT,
                aliyunProperties.getAliyunMqAccessKeyId(),
                aliyunProperties.getAliyunMqAccessKeySecret());
        try {
            DefaultProfile.addEndpoint(SmsService.ENDPOINT, SmsService.ENDPOINT, SmsService.PRODUCT, SmsService.DOMAIN);
        } catch (ClientException e) {
            logger.error("", e);
            return "发送失败: " + e.getErrMsg();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(smsParams.getPhone());
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(smsParams.getSign());
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(smsParams.getTemplateId());
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(com.alibaba.fastjson.JSONObject.toJSONString(smsParams.getData()));
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        String jobId = MD5Util.MD5(smsParams.getPhone() + new Date().getTime());
        request.setOutId(jobId);

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = null;
        UpNotificationSmsRecord record = new UpNotificationSmsRecord();
        record.setAddTime(new Date())
                .setUpdateTime(new Date())
                .setUserId(smsParams.getUserId())
                .setUserType(smsParams.getUserType().getType())
                .setCode(smsParams.getData().getOrDefault("code", ""))
                .setJobId(jobId)
                .setPhone(smsParams.getPhone())
                .setSendJson(JSONObject.toJSONString(smsParams))
                .setCookie(smsParams.getCookie())
                .setOpenid(smsParams.getOpenid())
                .setIp(smsParams.getIp())
                .setCompanyId(smsParams.getCompanyId())
                .setQuestionnaireId(smsParams.getQuestionnaireId())
                .setDay(smsParams.getDay());

        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
            logger.error("",sendSmsResponse);
            record.setResult(sendSmsResponse.getMessage());
            upNotificationSmsRecordService.insert(record);
            return sendSmsResponse.getMessage();
        } catch (ClientException e) {
            logger.error("", e);
            record.setResult(e.getErrMsg());
            upNotificationSmsRecordService.insert(record);
            return "发送失败: " + sendSmsResponse.getMessage();
        }
    }

    /**
     * 查询是否有一小时内的短信记录
     * @param phone
     * @return
     */
    @Override
    public UpNotificationSmsRecord checkVerifyCode(String phone, String code) {
        UpNotificationSmsRecord record = upNotificationSmsRecordService.selectOne(
                new EntityWrapper<UpNotificationSmsRecord>()
                        .eq(UpNotificationSmsRecord.PHONE, phone)
                        .eq(UpNotificationSmsRecord.SMS_TYPE, SmsTypeEnum.VERIFY_CODE.getType())
                        .eq(UpNotificationSmsRecord.IS_CONSUME, 0)
                        .eq(UpNotificationSmsRecord.CODE, code)
                        .ge(UpNotificationSmsRecord.ADD_TIME, new Date(new Date().getTime() - Constants.ONE_HOUR)));

        return record;
    }

    /**
     * 查询是否有一分钟内的短信记录
     * @param phone
     * @return 返回秒数
     */
    @Override
    public boolean checkVerifyCodeOneMinute(String phone) {
        UpNotificationSmsRecord record = upNotificationSmsRecordService.selectOne(
                new EntityWrapper<UpNotificationSmsRecord>()
                        .eq(UpNotificationSmsRecord.PHONE, phone)
                        .eq(UpNotificationSmsRecord.SMS_TYPE, SmsTypeEnum.VERIFY_CODE.getType())
                        .eq(UpNotificationSmsRecord.IS_CONSUME, 0)
                        .ge(UpNotificationSmsRecord.ADD_TIME, new Date(new Date().getTime() - Constants.ONE_MINUTE)));
        if (record == null) {
            return true;
        }
        return false;
    }

    /**
     * 消费验证码
     * @param record
     * @return
     */
    @Override
    public void consumeVerifyCode(UpNotificationSmsRecord record) {
        record.setUpdateTime(new Date())
                .setIsConsume(1);
        upNotificationSmsRecordService.updateById(record);
    }
}
