package cn.com.carry.tenants.server;

import cn.com.carry.common.util.base.BaseWebMvcConfigurerAdapter;
import cn.com.carry.tenants.server.interceptor.TenantsInterceptor4User;
import cn.com.carry.tenants.server.interceptor.TentantsInterceptor;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * Created by 严汉羽 on 2017/6/24.
 */

@SpringBootApplication
@ImportResource("classpath:META-INF/spring/applicationContext.xml") //不要用 *号 保证清晰的引用
public class TenantsServerApplication extends BaseWebMvcConfigurerAdapter {

    private static Logger logger = Logger.getLogger(TenantsServerApplication.class);

    static public void main(String [] args) {
        logger.info(">>>>> Carry TENANTS Server 正在启动 <<<<<");
        SpringApplication.run(TenantsServerApplication.class,args);
        logger.info(">>>>> Carry TENANTS Server 启动完成 <<<<<");
    }

    /**
     * 配置拦截器
     * @author yanhy
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TentantsInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new TenantsInterceptor4User()).addPathPatterns("/**");
    }




}
