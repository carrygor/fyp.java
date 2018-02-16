import cn.com.youplus.apps.api.auto.*;
import cn.com.youplus.apps.api.common.AnswerSheetService;
import cn.com.youplus.base.api.mq.MessageQueueService;
import cn.com.youplus.common.model.enums.*;
import cn.com.youplus.common.util.Constants;
import cn.com.youplus.common.util.MD5Util;
import cn.com.youplus.common.util.StringHelper;
import cn.com.youplus.model.auto.entity.apps.*;
import cn.com.youplus.model.auto.entity.tenants.*;
import cn.com.youplus.tenants.api.auto.*;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by 严汉羽 on 2017/10/12.
 */
@ActiveProfiles("slave")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/spring/*.xml"})
public class exportTest {
    private static Logger logger = Logger.getLogger(exportTest.class);
    @Autowired
    private UpAppsAnswerSheetService appsAnswerSheetService;

    @Autowired
    private UpAppsAnswerSheetItemService appsAnswerSheetItemService;

    @Autowired
    private UpAppsQuestionItemService appsQuestionItemService;

    @Autowired
    private UpAppsQuestionService appsQuestionService;

    @Autowired
    private UpAppsQuestionnaireService appsQuestionnaireService;

    @Autowired
    private UpAppsQuestionnaireThemeService appsQuestionnaireThemeService;

    @Autowired
    private UpTenantsCompanyService UpTenantsCompanyService;

    @Autowired
    private UpTenantsRegionService UpTenantsRegionService;

    @Autowired
    private UpTenantsUserRoleService upTenantsUserRoleService;


    @Autowired
    private UpTenantsLevelService upTenantsLevelService;

    @Autowired
    private MessageQueueService messageQueueService;

    @Autowired
    private UpTenantsUserService UpTenantsUserService;

    @Autowired
    private UpTenantsRegionService upTenantsRegionService;

    @Autowired
    private AnswerSheetService answerSheetService;


    @Test
    public void testClear() throws Exception {

        //940130732104327169 友谊
        //940760726052569090L 中华
        answerSheetService.exportDataToExcel(940760726052569090L,
                new Date(new Date().getTime() - Constants.ONE_DAY * 20),
                new Date()
        );
    }

}
