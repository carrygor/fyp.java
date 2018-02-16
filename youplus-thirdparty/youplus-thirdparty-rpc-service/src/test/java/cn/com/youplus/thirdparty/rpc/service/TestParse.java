package cn.com.youplus.thirdparty.rpc.service;

import cn.com.youplus.thirdparty.api.shenmiren.ShenmirenExcelService;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by 严汉羽 on 2017/10/12.
 */
@ActiveProfiles({"master","dev"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/spring/*.xml"})
public class TestParse {

    private static Logger logger = LoggerFactory.getLogger(TestParse.class);

    @Autowired
    private ShenmirenExcelService shenmirenExcelService;

    @Test
    public void testParse() throws Exception {
        shenmirenExcelService.parseExcel("D:/fjzh_report/201801_all.xlsx", "福建", "201801");

        logger.info(JSONObject.toJSONString(shenmirenExcelService.getParsedExcelData("福建", "2018-01")));
    }

    @Test
    public void testGet() throws Exception {
        logger.info(JSONObject.toJSONString(shenmirenExcelService.getParsedExcelData("福建", "2018-01")));
    }
}
