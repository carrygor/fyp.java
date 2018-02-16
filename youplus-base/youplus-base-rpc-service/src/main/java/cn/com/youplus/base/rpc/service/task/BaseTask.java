package cn.com.youplus.base.rpc.service.task;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by 严汉羽 on 2017/6/27.
 */

@Component
public class BaseTask {
    private static Logger log = Logger.getLogger(BaseTask.class);

    @Scheduled(cron="0/10 * *  * * ? ")
    public void alive() {
        log.info("优加+ BASE RPC Server服务器---每10秒一次的log，证明服务器还活着。。。");
    }

}

