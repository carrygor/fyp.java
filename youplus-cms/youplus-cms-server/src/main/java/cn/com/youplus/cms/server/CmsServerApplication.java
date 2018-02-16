package cn.com.youplus.cms.server;

import cn.com.youplus.cms.server.interceptor.CmsInterceptor;
import cn.com.youplus.cms.server.interceptor.CmsInterceptor4User;
import cn.com.youplus.common.util.base.BaseWebMvcConfigurerAdapter;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.List;

/**
 * Created by 严汉羽 on 2017/6/24.
 */

@SpringBootApplication
@ImportResource("classpath:META-INF/spring/applicationContext.xml") //不要用 *号 保证清晰的引用
public class CmsServerApplication extends BaseWebMvcConfigurerAdapter {

    private static Logger logger = Logger.getLogger(CmsServerApplication.class);

    static public void main(String [] args) {
        logger.info(">>>>> Youplus  CMS Server 正在启动 <<<<<");
        SpringApplication.run(CmsServerApplication.class,args);
        logger.info(">>>>> Youplus CMS Server 启动完成 <<<<<");
    }

    /**
     * 配置拦截器
     * @author yanhy
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CmsInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new CmsInterceptor4User()).addPathPatterns("/**");

    }

}
