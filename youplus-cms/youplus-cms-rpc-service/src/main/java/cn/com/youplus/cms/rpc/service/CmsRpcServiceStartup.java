package cn.com.youplus.cms.rpc.service;

import com.alibaba.dubbo.container.Main;
import org.apache.log4j.Logger;

/**
 * Created by 严汉羽 on 2017/6/24.
 */
public class CmsRpcServiceStartup {
    private static Logger logger = Logger.getLogger(CmsRpcServiceStartup.class);

    public static void main(String[] args) {
        logger.info(">>>>> 优加 CMS RPC 正在启动 <<<<<");
        Main.main(args);
        logger.info(">>>>> 优加 CMS RPC 启动完成 <<<<<");
    }
}


