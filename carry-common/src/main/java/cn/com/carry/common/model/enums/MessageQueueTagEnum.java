package cn.com.carry.common.model.enums;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2016/8/29.
 */
public enum MessageQueueTagEnum implements Serializable {

    /**
     * 系统配置
     */
    BROADCAST_SYSTEM_CONFIG("BROADCAST_SYSTEM_CONFIG"),

    /**
     * 消息相关
     */
    NORMAL_SMS("NORMAL_SMS"),
    NORMAL_EMAIL("NORMAL_EMAIL"),
    NORMAL_WEIXIN_MSG("NORMAL_WEIXIN_MSG"),
    NORMAL_WEIXIN_TPL("NORMAL_WEIXIN_TPL"),
    NORMAL_ASSISTANT_SYS_MSG("NORMAL_EMAIL_MSG"),

    TIMER_SMS("TIMER_SMS"),
    TIMER_EMAIL("TIMER_EMAIL"),
    TIMER_WEIXIN_MSG("TIMER_WEIXIN_MSG"),
    TIMER_WEIXIN_TPL("TIMER_WEIXIN_TPL"),
    TIMER_ASSISTANT_SYS_MSG("TIMER_EMAIL_MSG")
    ;

    //


    private String type;

    MessageQueueTagEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
