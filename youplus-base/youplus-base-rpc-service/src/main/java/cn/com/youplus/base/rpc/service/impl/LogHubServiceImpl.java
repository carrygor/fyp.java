package cn.com.youplus.base.rpc.service.impl;

import cn.com.youplus.base.api.LogHubService;
import cn.com.youplus.common.model.log.annotation.UpLogItem;
import cn.com.youplus.common.model.log.annotation.UpLogStore;
import cn.com.youplus.common.model.resources.AliyunProperties;
import cn.com.youplus.model.auto.entity.apps.UpAppsAnswerSheetItem;
import com.aliyun.openservices.log.Client;
import com.aliyun.openservices.log.common.LogItem;
import com.aliyun.openservices.log.request.PutLogsRequest;
import com.aliyun.openservices.log.response.PutLogsResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * Created by 严汉羽 on 2018/1/24.
 */
@Service("logHubService")
public class LogHubServiceImpl implements LogHubService {

    private static Logger logger = Logger.getLogger(LogHubServiceImpl.class);

    @Autowired
    private AliyunProperties aliyunProperties;

    @Resource(name = "logClient")
    private Client logClient;

    /*@Override
    public void putLog(ApiAccessLog log) throws LogException {
        // 列出当前 project 下的所有日志库名称
        int offset = 0;
        int size = 1;
        String logStoreSubName = "";
        ListLogStoresRequest req1 = new ListLogStoresRequest(aliyunProperties.getAliyunLogProjectName(), offset, size, logStoreSubName);
        ArrayList<String> logStores = logClient.ListLogStores(req1).GetLogStores();
        // 写入日志
        Vector<LogItem> logGroup = new Vector<LogItem>();
        LogItem logItem = new LogItem((int) (new Date().getTime() / 1000));

        if (log.getBroswer() != null) { logItem.PushBack("broswer", log.getBroswer());}
        if (log.getCookies() != null) { logItem.PushBack("cookies", log.getCookies());}
        if (log.getDevice() != null) { logItem.PushBack("device", log.getDevice());}
        if (log.getErrorMsg() != null) { logItem.PushBack("errorMsg", log.getErrorMsg());}
        logItem.PushBack("timeConsume", log.getTimeConsume() + "");
        if (log.getIp() != null) { logItem.PushBack("ip", log.getIp());}
        if (log.getModuleName() != null) { logItem.PushBack("moduleName", log.getModuleName());}
        if (log.getRequestParameter() != null) { logItem.PushBack("requestParameter", log.getRequestParameter());}
        if (log.getRequestUri() != null) { logItem.PushBack("requestUri", log.getRequestUri());}
        if (log.getSystem() != null) { logItem.PushBack("system", log.getSystem());}
        if (log.getAddTime() != null) { logItem.PushBack("addTime", log.getAddTime().getTime() + "");}
        logItem.PushBack("returnCode", log.getReturnCode() + "");
        logGroup.add(logItem);

        PutLogsRequest logsRequest = new PutLogsRequest(aliyunProperties.getAliyunLogProjectName(),
                LogHubService.API_LOG_STORE_NAME,
                "",
                "",
                logGroup);
        logClient.PutLogs(logsRequest);
    }*/

    @Override
    public <T> void putLog(T t) throws Exception {
        putLog(t, new Date());
    }

    @Override
    public <T> void putLog(T t, Date time) throws Exception {
        UpLogStore upLogStore = t.getClass().getAnnotation(UpLogStore.class);
        if (upLogStore == null) {
            //  该对象没有UpLogStore注解不能putLog
            return;
        }
        Vector<LogItem> logGroup = new Vector<LogItem>();
        LogItem logItem = new LogItem((int) (time.getTime() / 1000));
        Class clazz = t.getClass();
        while (clazz != null && !clazz.getSimpleName().equals("Object")) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                UpLogItem upLogItem = field.getAnnotation(UpLogItem.class);
                if (upLogItem == null) {
                    continue;
                }
                String name = upLogItem.name();
                field.setAccessible(true);
                Object value = field.get(t);
                if (value != null) {
                    logItem.PushBack(name, value.toString());
                }
            }
            clazz = clazz.getSuperclass();
        }
        logGroup.add(logItem);
        PutLogsRequest logsRequest = new PutLogsRequest(aliyunProperties.getAliyunLogProjectName(),
                upLogStore.name(),
                "",
                "",
                logGroup);

        try {
            PutLogsResponse response = logClient.PutLogs(logsRequest);
        } catch (Exception e) {
            logger.error("", e);
        }


    }

    @Override
    public void putAnswerItemsLog(List<UpAppsAnswerSheetItem> items) throws Exception {

        Vector<LogItem> logGroup = new Vector<>();
        for (UpAppsAnswerSheetItem answerSheetItem : items) {
            LogItem logItem = new LogItem((int) (new Date().getTime() / 1000));
            logItem.PushBack("addTime", answerSheetItem.getAddTime() + "");
            logItem.PushBack("answerSheetId", answerSheetItem.getAnswerSheetId() + "");
            logItem.PushBack("questionId", answerSheetItem.getQuestionId() + "");
            logItem.PushBack("questionType", answerSheetItem.getQuestionType());
            logItem.PushBack("questionItemId", answerSheetItem.getQuestionItemId() + "");
            logItem.PushBack("quickTag", answerSheetItem.getQucikTag());
            logItem.PushBack("score", answerSheetItem.getScore() + "");
            logItem.PushBack("value", answerSheetItem.getValue());
            logItem.PushBack("inputContent", answerSheetItem.getInputContent());
            logGroup.add(logItem);
        }
        PutLogsRequest logsRequest = new PutLogsRequest(aliyunProperties.getAliyunLogProjectName(),
                "question-stat-log",
                "",
                "",
                logGroup);
        logClient.PutLogs(logsRequest);

    }

}
