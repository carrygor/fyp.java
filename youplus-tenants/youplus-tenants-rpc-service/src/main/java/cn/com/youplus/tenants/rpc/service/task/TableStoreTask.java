package cn.com.youplus.tenants.rpc.service.task;

import cn.com.youplus.tenants.api.common.TsAnswerSheetStatService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by 严汉羽 on 2017/6/27.
 */

@Profile("master")
@Component
public class TableStoreTask {
    private static Logger log = Logger.getLogger(TableStoreTask.class);

    @Autowired
    private TsAnswerSheetStatService tsAnswerSheetStatService;

    @Scheduled(cron="0/1 * *  * * ? ")
    public void test() {
        log.info("STAT Table Store任务");
    }

    @Scheduled(cron="0 10 0/1  * * ? ")
    public void alive() {
        log.info("优加+ TENANTS RPC Server服务器---每小时执行一次Table Store任务");
        tsAnswerSheetStatService.lastHourStatTask();
    }

}

