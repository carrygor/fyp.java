package cn.com.youplus.thirdparty.rpc.service;

import com.alibaba.dubbo.container.Main;
import org.apache.log4j.Logger;

/**
 * Created by 严汉羽 on 2017/6/24.
 */
public class ThirdpartyRpcServiceStartup {
    private static Logger logger = Logger.getLogger(ThirdpartyRpcServiceStartup.class);

    public static void main(String[] args) {
        logger.info(">>>>> 优加第三方服务器接口RPC 正在启动 <<<<<");
        Main.main(args);
        logger.info(">>>>> 优加第三方服务器接口RPC 启动完成 <<<<<");
    }
}


