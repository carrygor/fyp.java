package cn.com.youplus.tenants.server.task;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by 严汉羽 on 2017/6/27.
 */

@Component
public class TentantsTask {
    private static Logger log = Logger.getLogger(TentantsTask.class);

    @Scheduled(cron="0/10 * *  * * ? ")
    public void alive() {
        log.info("优加+ TENANTS Server服务器---每10秒一次的log，证明服务器还活着。。。");
    }

}

