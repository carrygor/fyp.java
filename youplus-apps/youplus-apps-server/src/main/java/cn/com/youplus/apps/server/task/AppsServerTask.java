package cn.com.youplus.apps.server.task;

import cn.com.youplus.apps.server.AppsServerApplication;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by 严汉羽 on 2017/6/27.
 */

@Component
public class AppsServerTask {
    private static Logger logger = Logger.getLogger(AppsServerTask.class);

    @Scheduled(cron="0/10 * *  * * ? ")
    public void alive() {
        logger.info("优加+ APPS Server服务器---每10秒一次的log，证明服务器还活着。。。");
    }

}

