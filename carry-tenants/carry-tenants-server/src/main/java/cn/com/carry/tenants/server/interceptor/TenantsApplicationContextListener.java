package cn.com.carry.tenants.server.interceptor;


import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * spring容器初始化完成事件
 * Created by 严汉羽 on 2017/1/7.
 */
public class TenantsApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {

    private static Logger logger = Logger.getLogger(TenantsApplicationContextListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // root application context
        logger.info("Spring容器加载完成了.");
        ApplicationContext context = contextRefreshedEvent.getApplicationContext();
    }

}
