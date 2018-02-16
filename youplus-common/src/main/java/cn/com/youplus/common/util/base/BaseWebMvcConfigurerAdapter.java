package cn.com.youplus.common.util.base;

import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.serializer.SerializerFeature;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.support.config.FastJsonConfig;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by 严汉羽 on 2017/10/20.
 */
public class BaseWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fjc = new FastJsonConfig();
        //1、序列化重点
        fjc.setSerializerFeatures(SerializerFeature.BrowserCompatible);
        fastJsonConverter.setFastJsonConfig(fjc);
        converters.add(fastJsonConverter);
    }
}
