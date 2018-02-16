package cn.com.youplus.base.rpc.service.mq.listener;

import cn.com.youplus.common.model.base.SystemConfigVersionReference;
import cn.com.youplus.common.model.enums.SystemConfigTypeEnum;
import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import org.apache.log4j.Logger;

/**
 * Created by 严汉羽 on 2017/7/1.
 */
public class BroadcastMessageListener implements MessageListener {

    private static Logger logger = Logger.getLogger(BroadcastMessageListener.class);

    @Override
    public Action consume(Message message, ConsumeContext consumeContext) {
        logger.info("Receive: " + message.getMsgID());
        logger.debug(message.toString());
        try {
            SystemConfigTypeEnum configTypeEnum = SystemConfigTypeEnum.valueOf( new String(message.getBody(),"utf-8"));
            setSysConfigRefByType(configTypeEnum);
            return Action.CommitMessage;
        }catch (EnumConstantNotPresentException ecnpe) {
            logger.error("不存在这种配置类型。");
            logger.error("",ecnpe);
            return Action.CommitMessage;
        }
        catch (Exception e) {
            //消费失败
            return Action.ReconsumeLater;
        }
    }

    public void setSysConfigRefByType(SystemConfigTypeEnum configTypeEnum) {
        SystemConfigVersionReference ref = SystemConfigVersionReference.getInstance();
        switch (configTypeEnum) {
            case APPS_SYSTEM_CONFIG:
                ref.setAppsSystemConfigLocalRef(ref.getAppsSystemConfigSystemRef() + 1);
                break;
            case TENANTS_SYSTEM_CONFIG:
                ref.setTenantsSystemConfigLocalRef(ref.getTenantsSystemConfigSystemRef() + 1);
                break;
            case THIRDPARTY_SYSTEM_CONFIG:
                ref.setThirdpartySystemConfigLocalRef(ref.getThirdpartySystemConfigSystemRef() + 1);
                break;
            case CMS_SYSTEM_CONFIG:
                ref.setCmsSystemConfigLocalRef(ref.getCmsSystemConfigSystemRef() + 1);
                break;
            default:
                logger.info("unknown SystemConfigTypeEnum");
                break;
        }
    }

}
