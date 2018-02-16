package cn.com.youplus.common.util.listener;


import cn.com.youplus.common.util.base.BaseInterface;
import cn.com.youplus.common.util.base.BaseService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * spring容器初始化完成事件
 * Created by 严汉羽 on 2017/1/7.
 */
public class ApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {

    private static Logger logger = Logger.getLogger(ApplicationContextListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // root application context
        logger.info("Spring容器加载完成了.");
        if(null == contextRefreshedEvent.getApplicationContext().getParent()) {
        }
    }

}
