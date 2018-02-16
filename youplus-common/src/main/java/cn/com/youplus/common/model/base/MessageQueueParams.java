package cn.com.youplus.common.model.base;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 严汉羽 on 2017/10/19.
 */
public class MessageQueueParams implements Serializable {

    /**
     * 标签
     */
    private String tag;

    private Object body;

    private Date deliverTime;

    public String getTag() {
        return tag;
    }

    public MessageQueueParams setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public Object getBody() {
        return body;
    }

    public MessageQueueParams setBody(Object body) {
        this.body = body;
        return this;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public MessageQueueParams setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
        return this;
    }
}
