package cn.com.youplus.common.model.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 严汉羽 on 2017/6/30.
 */
public class EmailParams implements Serializable {

    private String title;

    private String content;

    private List<String> receiverList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getReceiverList() {
        return receiverList;
    }

    public void setReceiverList(List<String> receiverList) {
        this.receiverList = receiverList;
    }
}
