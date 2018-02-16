package cn.com.youplus.common.model.enums;

import java.io.Serializable;

/**
 * Created by pan on 2016/8/29.
 */
public enum WeixinApiTypeEnum implements Serializable {
    /**
     * 1 文本消息
     */
    text("text"),
    /**
     * 2 图片消息
     */
    image("image"),
    /**
     * 3 语音消息
     */
    voice("voice"),
    /**
     * 4 视频消息
     */
    video("video"),
    /**
     * 5 小视频消息
     */
    shortvideo("shortvideo"),
    /**
     * 6 地理位置消息
     */
    location("location"),
    /**
     * 7 链接消息
     */
    link("link"),
    /**
     * 事件消息
     */
    event("event"),
    /**
     * 音乐消息
     */
    music("music"),
    /**
     * 图文消息
     */
    news("news")
    ;
    private String type;

    WeixinApiTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
