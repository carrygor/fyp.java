package cn.com.youplus.base.rpc.service;

import com.alibaba.dubbo.container.Main;
import org.apache.log4j.Logger;

/**
 * Created by 严汉羽 on 2017/6/24.
 */
public class BaseRpcServiceStartup {
    private static Logger logger = Logger.getLogger(BaseRpcServiceStartup.class);

    public static void main(String[] args) {
        logger.info(">>>>> 优加 BASE RPC 正在启动 <<<<<");
        Main.main(args);
        logger.info(">>>>> 优加 BASE RPC 启动完成 <<<<<");
    }
}


