package cn.com.youplus.thirdparty.server;

import cn.com.youplus.common.util.base.BaseWebMvcConfigurerAdapter;
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
public class ThirdpartyServerApplication extends BaseWebMvcConfigurerAdapter {

    private static Logger logger = Logger.getLogger(ThirdpartyServerApplication.class);

    static public void main(String [] args) {
        logger.info(">>>>> Youplus THIRDPARTY Server 正在启动 <<<<<");
        SpringApplication.run(ThirdpartyServerApplication.class,args);
        logger.info(">>>>> Youplus THIRDPARTY Server 启动完成 <<<<<");
    }

    /**
     * 配置拦截器
     * @author yanhy
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
    }




}
