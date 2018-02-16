package cn.com.youplus.apps.rpc.service;

import com.alibaba.dubbo.container.Main;
import org.apache.log4j.Logger;

/**
 * Created by 严汉羽 on 2017/6/24.
 */
public class AppsRpcServiceStartup {
    private static Logger logger = Logger.getLogger(AppsRpcServiceStartup.class);

    public static void main(String[] args) {
        logger.info(">>>>> 优加 APPS RPC 正在启动 <<<<<");
        Main.main(args);
        logger.info(">>>>> 优加 APPS RPC 启动完成 <<<<<");
    }
}


