package cn.com.youplus.base.rpc.service.impl;

import cn.com.youplus.base.api.LhAnswerSheetService;
import cn.com.youplus.common.model.resources.AliyunProperties;
import cn.com.youplus.common.util.ValueHelper;
import com.aliyun.openservices.log.Client;
import com.aliyun.openservices.log.common.LogContent;
import com.aliyun.openservices.log.common.LogItem;
import com.aliyun.openservices.log.common.QueriedLog;
import com.aliyun.openservices.log.request.GetLogsRequest;
import com.aliyun.openservices.log.response.GetLogsResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 严汉羽 on 2018/2/6.
 */
@Service("lhAnswerSheetService")
public class LhAnswerSheetServiceImpl implements LhAnswerSheetService{

    private static Logger logger = Logger.getLogger(LogHubServiceImpl.class);

    @Autowired
    private AliyunProperties aliyunProperties;

    @Resource(name = "logClient")
    private Client logClient;

    @Override
    public int getAnswerSheetTotalCount(Long questionnaireId) throws Exception {
        int to = (int) (new Date().getTime() / 1000);
        int from = to - LhAnswerSheetService.MAX_TIME_DELTA; //一年
        return getAnswerSheetCount(questionnaireId, from, to);
    }

    @Override
    public int getAnswerSheetCurrentDayCount(Long questionnaireId) throws Exception {
        int to = (int) (new Date().getTime() / 1000);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        int from = (int)(calendar.getTime().getTime() / 1000);
        return getAnswerSheetCount(questionnaireId, from, to);
    }

    @Override
    public int getAnswerSheetLastHourCount(Long questionnaireId) throws Exception {
        int to = (int) (new Date().getTime() / 1000);
        int from = to - 3600;
        return getAnswerSheetCount(questionnaireId, from, to);
    }

    public int getAnswerSheetCount(Long questionnaireId, int from, int to) throws Exception {
        GetLogsRequest req4 = new GetLogsRequest(aliyunProperties.getAliyunLogProjectName(),
                LhAnswerSheetService.ANWSER_SHEET_LOG_STORE_NAME,
                from,
                to, "", " questionnaireId=" + questionnaireId + " | select count(1) as c");
        GetLogsResponse res4 = logClient.GetLogs(req4);
        if(res4 != null && res4.IsCompleted()){
            for (QueriedLog log : res4.GetLogs()){
                LogItem item = log.GetLogItem();
                for(LogContent content : item.GetLogContents()){
                    return ValueHelper.tryParse(content.GetValue(), 0);
                }
            }
        }
        return 0;
    }
}
