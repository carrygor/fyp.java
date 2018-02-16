package cn.com.youplus.base.rpc.test;

import cn.com.youplus.base.api.mq.MessageQueueService;
import cn.com.youplus.common.model.base.MessageQueueParams;
import cn.com.youplus.common.model.enums.MessageQueueTagEnum;
import cn.com.youplus.common.model.enums.SystemConfigTypeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by 严汉羽 on 2017/10/12.
 */
@ActiveProfiles("slave")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/spring/*.xml"})
public class MqParse {

    private static Logger logger = Logger.getLogger(MqParse.class);

    @Autowired
    private MessageQueueService messageQueueService;

    @Test
    public void testSend() throws Exception {
        messageQueueService.sendMq(new MessageQueueParams().setTag(MessageQueueTagEnum.BROADCAST_SYSTEM_CONFIG.getType()).setBody(SystemConfigTypeEnum.APPS_SYSTEM_CONFIG.getType()));
    }

    @Test
    public void testSendNo() throws Exception {
        messageQueueService.sendMq(new MessageQueueParams()
                .setTag(MessageQueueTagEnum.NORMAL_SMS.getType())
                .setBody(SystemConfigTypeEnum.APPS_SYSTEM_CONFIG.getType()));
    }

}
