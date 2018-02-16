package cn.com.youplus.base.api;


import cn.com.youplus.model.auto.entity.apps.UpAppsAnswerSheetItem;

import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/8/9.
 */
public interface LogHubService {

    /**
     * 往logHub推送一条log
     */
    <T> void putLog(T t) throws Exception;

    <T> void putLog(T t, Date time) throws Exception;

    void putAnswerItemsLog(List<UpAppsAnswerSheetItem> items) throws Exception;
}
