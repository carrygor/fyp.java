import cn.com.youplus.apps.api.auto.*;
import cn.com.youplus.base.api.mq.MessageQueueService;
import cn.com.youplus.common.model.base.MessageQueueParams;
import cn.com.youplus.common.model.enums.*;
import cn.com.youplus.tenants.api.auto.UpTenantsCompanyService;
import cn.com.youplus.tenants.api.auto.UpTenantsRegionService;
import cn.com.youplus.tenants.api.auto.UpTenantsUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by 严汉羽 on 2017/10/12.
 */
@ActiveProfiles("slave")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/spring/applicationContext.xml"})
public class questionTest {

    private static Logger logger = Logger.getLogger(questionTest.class);

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
    private MessageQueueService messageQueueService;

    @Autowired
    private UpTenantsUserService UpTenantsUserService;


    @Test
    public void testQuestionItem() throws Exception {

    }

    @Test
    public void testSendNo() throws Exception {
        messageQueueService.sendMq(new MessageQueueParams()
                .setTag(MessageQueueTagEnum.NORMAL_SMS.getType())
                .setBody(SystemConfigTypeEnum.APPS_SYSTEM_CONFIG.getType()));
    }

}
