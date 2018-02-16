package cn.com.youplus.apps.server;

import cn.com.youplus.apps.server.interceptor.AppsInterceptor;
import cn.com.youplus.apps.server.interceptor.AppsInterceptor4User;
import cn.com.youplus.common.util.PropertiesFileUtil;
import cn.com.youplus.common.util.base.BaseWebMvcConfigurerAdapter;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * Created by 严汉羽 on 2017/6/24.
 */

@SpringBootApplication
@ImportResource("classpath:META-INF/spring/applicationContext.xml") //不要用 *号 保证清晰的引用
public class AppsServerApplication extends BaseWebMvcConfigurerAdapter {

    private static Logger logger = Logger.getLogger(AppsServerApplication.class);

    static public void main(String [] args) {
        logger.info(">>>>> Youplus Apps Server 正在启动 <<<<<");
        SpringApplication.run(AppsServerApplication.class,args);
        logger.info(">>>>> Youplus Apps Server 启动完成 <<<<<");
    }

    /**
     * 配置拦截器
     * @author yanhy
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AppsInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new AppsInterceptor4User()).addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        String cros = PropertiesFileUtil.getInstance("/config/cros_origin").get("cros.origin");
//        int maxAge = PropertiesFileUtil.getInstance("/config/cros_origin").getInt("cros.max_age");
//        logger.info("跨域请求配置:[" + cros + "] [" + maxAge + "]");
        //registry.addMapping("/apps/v1/q/*").allowedOrigins(cros).maxAge(maxAge);
    }




}
