package cn.com.carry.tenants.rpc.service;

import com.alibaba.dubbo.container.Main;
import org.apache.log4j.Logger;

/**
 * Created by 严汉羽 on 2017/6/24.
 */
public class TentantsRpcServiceStartup {
    private static Logger logger = Logger.getLogger(TentantsRpcServiceStartup.class);

    public static void main(String[] args) {
        logger.info(">>>>> Carry TENANTS RPC 正在启动 <<<<<");
        Main.main(args);
        logger.info(">>>>> Carry TENANTS RPC 启动完成 <<<<<");
    }
}


