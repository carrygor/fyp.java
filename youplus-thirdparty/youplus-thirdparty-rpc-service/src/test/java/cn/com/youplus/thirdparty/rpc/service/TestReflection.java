package cn.com.youplus.thirdparty.rpc.service;

import cn.com.xiaohanyu.report4j.datasource.MyBatisDataSource;
import cn.com.youplus.common.form.ReportFilterForm;
import cn.com.youplus.common.mybatis.FilterWarpper;
import cn.com.youplus.common.util.Constants;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.IService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * Created by 严汉羽 on 2017/10/12.
 */
@ActiveProfiles({"master","dev"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/spring/*.xml"})
public class TestReflection implements ApplicationContextAware {
    private static Logger logger = LoggerFactory.getLogger(TestReflection.class);

    @Test
    public void testParse() throws Exception {
        ReportFilterForm filterForm = new ReportFilterForm();
        filterForm.setStartDate(new Date(new Date().getTime() - Constants.ONE_MONTH).getTime());
        filterForm.setEndDate(new Date().getTime());

        URL schemaUrl = TestReflection.class.getResource("/dataSource.json");
        if (schemaUrl == null) {
            throw new Exception("文件不存在:");
        }
        StringBuilder stringBuilder = new StringBuilder(1*1024*1024);
        BufferedReader br=new BufferedReader(new FileReader( schemaUrl.getPath()));
        int i=br.read();
        while (i!=-1){
            stringBuilder.append((char)i);
            i=br.read();
        }
        br.close();

        MyBatisDataSource dataSource = JSONObject.parseObject(stringBuilder.toString(), MyBatisDataSource.class);

        Class clazz = Class.forName(dataSource.getBeanClassName());
        IService service = (IService)applicationContext.getBean(dataSource.getServiceName());

        logger.debug(JSONObject.toJSONString(dataSource));

        List list = service.selectMaps( dataSource.asumbleWrapper(new FilterWarpper()
                .filter(filterForm, clazz)));
        logger.debug(JSONObject.toJSONString(list));
    }

    ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

