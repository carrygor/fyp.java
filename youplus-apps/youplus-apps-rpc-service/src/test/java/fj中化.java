import cn.com.youplus.apps.api.auto.*;
import cn.com.youplus.base.api.mq.MessageQueueService;
import cn.com.youplus.common.model.enums.*;
import cn.com.youplus.common.util.Constants;
import cn.com.youplus.common.util.MD5Util;
import cn.com.youplus.common.util.StringHelper;
import cn.com.youplus.model.auto.entity.apps.*;
import cn.com.youplus.model.auto.entity.tenants.*;
import cn.com.youplus.tenants.api.auto.*;
import com.alibaba.fastjson.JSONArray;
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

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 严汉羽 on 2017/10/12.
 */
@ActiveProfiles("slave")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/spring/*.xml"})
public class fj中化 {

    private static Logger logger = Logger.getLogger(fj中化.class);

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
    private UpAppsQuestionnaireService upAppsQuestionnaireService;


    /**
     *
     DELETE from up_apps_answer_sheet;
     DELETE from up_apps_answer_sheet_item;
     DELETE from up_apps_questionnaire;
     DELETE from up_apps_question;
     DELETE from up_apps_question_item;
     DELETE from up_apps_questionnaire_theme;

     DELETE from up_tenants_company;
     DELETE from up_tenants_region;
     DELETE from up_tenants_user;
     */


    private static  String [] 门店列表 ={
            "微信公众号"
    };

    @Test
    public void testClear() throws Exception {
        while(true) {
            UpTenantsCompany upTenantsCompany = getCompany();
            if(upTenantsCompany == null) {
                break;
            }
            appsAnswerSheetItemService.delete(
                    new EntityWrapper<UpAppsAnswerSheetItem>()
                            .eq(UpAppsAnswerSheetItem.TENANTS_COMPANY_ID, upTenantsCompany.getId()));

            appsAnswerSheetService.delete(
                    new EntityWrapper<UpAppsAnswerSheet>()
                            .eq(UpAppsAnswerSheet.TENANTS_COMPANY_ID, upTenantsCompany.getId()));

            List<UpAppsQuestionnaire> questionnaires = appsQuestionnaireService.selectList(
                    new EntityWrapper<UpAppsQuestionnaire>()
                            .eq(UpAppsQuestionnaire.TENANTS_COMPANY_ID, upTenantsCompany.getId())
            ) ;
            if (questionnaires != null && questionnaires.size() > 0) {
                for (UpAppsQuestionnaire questionnaire : questionnaires) {
                    appsQuestionItemService.delete(
                            new EntityWrapper<UpAppsQuestionItem>()
                                    .eq(UpAppsQuestionItem.QUESTIONNAIRE_ID, questionnaire.getId()));
                    appsQuestionService.delete(
                            new EntityWrapper<UpAppsQuestion>()
                                    .eq(UpAppsQuestion.QUESTIONNAIRE_ID, questionnaire.getId()));
                    appsQuestionnaireThemeService.deleteById(questionnaire.getThemeId());
                    appsQuestionnaireService.deleteById(questionnaire.getId());
                }
            }

            UpTenantsRegionService.delete(
                    new EntityWrapper<UpTenantsRegion>().eq(UpTenantsRegion.TENANTS_COMPANY_ID, upTenantsCompany.getId())
            );

            UpTenantsUserService.delete(
                    new EntityWrapper<UpTenantsUser>().eq(UpTenantsUser.TENANTS_COMPANY_ID, upTenantsCompany.getId())
            );

            upTenantsLevelService.delete(
                    new EntityWrapper<UpTenantsLevel>().eq(UpTenantsLevel.TENANTS_COMPANY_ID, upTenantsCompany.getId())
            );

            UpTenantsCompanyService.deleteById(upTenantsCompany.getId());
        }
    }

    @Test
    public void testALl() throws Exception {
        testClear();
        testInsertCompany();
        testAddQuestionnaire( );
        testGenExcel();
    }

    private static String companyName = "福建中化";

    private List<UpTenantsUserRole> roles;

    Long getRoles(int roleCode) {
        Long r = 0L;
        if (roles == null) {
            roles = upTenantsUserRoleService.selectList(new EntityWrapper<UpTenantsUserRole>());
        }
        for (UpTenantsUserRole role : roles){
            if (role.getRoleCode().equals(roleCode)) {
                r = role.getId();
            }
        }
        return r;
    }

    @Test
    public void testInsertCompany() throws Exception {
        //
        String salt = StringHelper.getRandomLetterNo(16);
        String pwd = MD5Util.getPassword(MD5Util.MD5("123456"), salt);
        //添加公司
        UpTenantsCompany tenantsCompany = new UpTenantsCompany();
        tenantsCompany.setAddress("东直门北大街9号")
                .setUpdateTime(new Date())
                .setAddTime(new Date())
                .setProvince("广东省")
                .setCity("广州市")
                .setDistrict("天河区")
                .setHangye("零售")
                .setName(companyName)
                .setCompanyName(companyName)
                .setPhone("13925093101")
                .setMaxSiteNum(100)
                .setMaxReportNum(100000)
                .setMaxUserNum(100)
                .setAuthStartTime(new Date(new Date().getTime() - Constants.ONE_MONTH * 12))
                .setAuthExpiredTime(new Date(new Date().getTime() + Constants.ONE_MONTH * 12))
                .setFunctionStr("")
                .setTenantsType(TenantsCompanyTypeEnum.企业.getType());

        tenantsCompany = UpTenantsCompanyService.mInsert(tenantsCompany);

        //添加一个区域架构
        UpTenantsLevel upTenantsLevel = new UpTenantsLevel();
        upTenantsLevel.setDescription("总部")
                .setLevel(0)
                .setTenantsCompanyId(tenantsCompany.getId())
                .setName("总部");
        upTenantsLevelService.mInsert(upTenantsLevel);

        logger.info("插入一个企业记录:");
        logger.info(tenantsCompany.toString());

        //弄一个总部进来
        UpTenantsRegion 总部region = new UpTenantsRegion();
        总部region.setAddTime(new Date())
                .setDescription("这是友谊商店总部")
                .setLevel(0)
                .setName("总公司")
                .setParentId(0L)
                .setQuickTag("")
                .setIsStore(0)
                .setUpdateTime(new Date())
                .setTenantsCompanyId(tenantsCompany.getId());
        总部region = UpTenantsRegionService.mInsert(总部region);
        总部region.setQuickTag("," + 总部region.getId() + ",");
        UpTenantsRegionService.updateById(总部region);

        UpTenantsUser 总部User = new UpTenantsUser();
        总部User.setAddTime(new Date())
                .setUpdateTime(new Date())
                .setEmail("yanhy@youplus.com.cn")
                .setHeadImg("/upload/head_img.jpg")
                .setJobTitle("广州友谊")
                .setSalt(salt)
                .setPassword(pwd)
                .setTenantsCompanyId(tenantsCompany.getId())
                .setRegionId(总部region.getId())
                .setPhoneNum("13925093101")
                .setRealName("广州友谊")
                .setRoleId(getRoles(100)) //总部用户的roleId
                .setSex(1)
                .setUserName("fjzh"); //中国石油的英文缩写
        总部User = UpTenantsUserService.mInsert(总部User);

        //更新 user id
        tenantsCompany.setTenantsUserId(总部User.getId());
        UpTenantsCompanyService.updateById(tenantsCompany);

        //省级区域
        for (int l1 = 0; l1 < 门店列表.length; l1++) {
            String 门店 = 门店列表[l1];

            UpTenantsRegion 门店region = new UpTenantsRegion();
            门店region.setAddTime(new Date())
                    .setDescription(门店)
                    .setLevel(1)
                    .setName(门店)
                    .setParentId(总部region.getId())
                    .setQuickTag("," + 总部region.getId() + ",")
                    .setUpdateTime(new Date())
                    .setIsStore(1)
                    .setTenantsCompanyId(tenantsCompany.getId());

            门店region = UpTenantsRegionService.mInsert(门店region);
            门店region.setQuickTag(门店region.getQuickTag() + 门店region.getId() + ",");
            UpTenantsRegionService.updateById(门店region);
        }
    }

    public UpTenantsCompany getCompany() {
        return UpTenantsCompanyService.selectOne(
                new EntityWrapper<UpTenantsCompany>()
                        .eq(UpTenantsCompany.NAME, companyName)
                        .orderBy(UpTenantsCompany.ADD_TIME, false)
        ) ;
    }

    @Test
    public void testAddQuestionnaire() throws Exception {
        URL schemaUrl = fj中化.class.getResource("/fjzh.json");
        if (schemaUrl == null) {
            throw new Exception("文件不存在:");
        }
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader( schemaUrl.getPath()));
        int i=br.read();
        while (i != -1){
            stringBuilder.append((char)i);
            i = br.read();
        }
        br.close();

        JSONObject jsonObject = JSONObject.parseObject(stringBuilder.toString());

        UpTenantsCompany company = getCompany();

        //尝试去读取问卷配置：
        UpAppsQuestionnaire questionnaire = appsQuestionnaireService.selectOne(
                new EntityWrapper<UpAppsQuestionnaire>()
                        .eq(UpAppsQuestionnaire.TENANTS_COMPANY_ID, company.getId())
                        .orderBy(UpAppsQuestionnaire.ADD_TIME, false)
        );



        //添加问卷
        UpAppsQuestionnaireTheme questionnaireTheme = null;
        boolean already = false;
        if (questionnaire == null) {
            questionnaireTheme = new UpAppsQuestionnaireTheme();
        } else {
            already = true;
            questionnaireTheme = appsQuestionnaireThemeService.selectById(questionnaire.getThemeId());
        }

        questionnaireTheme
                .setAddTime(new Date())
                .setSelectIcon(jsonObject.getString("selectImg"))
                .setUnselectIcon(jsonObject.getString("unselectImg"))
                .setBannerImg(jsonObject.getString("banner"))
                .setStartImg(jsonObject.getString("welcome"))
                .setEndImg(jsonObject.getString("end"))
                .setAlreadySubmitImg(jsonObject.getString("alreadySubmit"))
                .setThemeColor(jsonObject.getString("color"))
                .setUpdateTime(new Date());

        if (questionnaire == null) {
            questionnaireTheme = appsQuestionnaireThemeService.mInsert(questionnaireTheme);
        } else {
            appsQuestionItemService.delete(
                    new EntityWrapper<UpAppsQuestionItem>()
                            .eq(UpAppsQuestionItem.QUESTIONNAIRE_ID, questionnaire.getId()));
            appsQuestionService.delete(
                    new EntityWrapper<UpAppsQuestion>()
                            .eq(UpAppsQuestion.QUESTIONNAIRE_ID, questionnaire.getId()));


            appsQuestionnaireThemeService.updateById(questionnaireTheme);
        }


        int questionNum = 0;
        int requiredNum = 0;

        if (!already) {
            questionnaire = new UpAppsQuestionnaire();
        }
        questionnaire.setAddTime(new Date())
                .setUpdateTime(new Date())
                .setDescription(jsonObject.getString("title"))
                .setQuestionnaireType(QuestionnaireTypeEnum.普通.getType())
                .setRequiredOptionNum(questionNum)
                .setSort(1)
                .setTitle(jsonObject.getString("title"))
                .setSubTitle("")
                .setQuestionNum(jsonObject.getJSONArray("questions").size())
                .setTenantsCompanyId(company.getId())
                .setStartTime(new Date())
                .setExpiredTime(new Date(new Date().getTime() + 12 * Constants.ONE_MONTH))
                .setStatus(QuestionnaireStatusEnum.收集中.getType())
                .setPeriodJson("{}")
                .setSiteName("分店")
                .setServiceTypeJson("{}")
                .setNpsJson("")
                .setThemeId(questionnaireTheme.getId());

        if (!already) {
            questionnaire = appsQuestionnaireService.mInsert(questionnaire);
        } else {
            appsQuestionnaireService.updateById(questionnaire);
        }

        int sort = 1;
        for (Object object : jsonObject.getJSONArray("questions")) {
            addOneQuestion(sort++, questionnaire.getId(), (JSONObject)object);
        }
    }

    void addOneQuestion(int sort, Long qid, JSONObject jsonObject) {
        UpAppsQuestion question = new UpAppsQuestion();
        question.setTitle(jsonObject.getString("title"));
        question.setSubTitle(jsonObject.getString("order"));
        question.setIsVisible(jsonObject.get("isVisible") == null ? 1 : jsonObject.getIntValue("isVisible") );
        question.setQuestionOrder(sort);
        QuestionTypeEnum typeEnum = QuestionTypeEnum.valueOf(jsonObject.getString("type"));
        question.setQuestionType(typeEnum.getType());
        question.setDisplayRule("")
                .setQuestionnaireId(qid);
        question.setIsRequired(jsonObject.get("isRequired") == null ? 1 : jsonObject.getIntValue("isRequired") );
        question.setQuestionJson("");
        question = appsQuestionService.mInsert(question);

        List<UpAppsQuestionItem> list = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("options");
        int itemSort = 1;
        for (Object o : jsonArray) {
            JSONObject itemJson = (JSONObject)o;
            QuestionItemTypeEnum itemType = QuestionItemTypeEnum.选项;
            switch (typeEnum) {
                case 分项满意度:
                case 总体满意度:
                case NPS:
                case 评分:
                    itemType = QuestionItemTypeEnum.分数选择;
                    break;

                case 排序:
                case 单选:
                case 多选:
                case 是非:
                    itemType = QuestionItemTypeEnum.选项;
                    break;
                default:
                    itemType = QuestionItemTypeEnum.填空;
                    break;

            }

            UpAppsQuestionItem questionItem = new UpAppsQuestionItem();
            questionItem.setEditorType(itemJson.getString("type"));
            questionItem.setKey(itemJson.getString("key"));
            questionItem.setValue(itemJson.getString("value"));
            questionItem.setDisplay(itemJson.getString("display"));
            questionItem.setQuestionItemType(itemType.getType());
            questionItem.setIsShowEditor(itemJson.getIntValue("isShowEditor"));
            questionItem.setSort(itemSort++);
            questionItem.setIconSelect(itemJson.getString("iconSelect"));
            questionItem.setIconUnselect(itemJson.getString("iconUnselect"));
            questionItem.setPlaceholder(itemJson.getString("placeholder"));
            questionItem.setEditorPlaceholder(itemJson.getString("editorPlaceholder"))
                    .setQuestionnaireId(qid)
                    .setQuestionId(question.getId());

            questionItem = appsQuestionItemService.mInsert(questionItem);
            list.add(questionItem);
        }


        //处理逻辑
        JSONObject displayRuleObject = jsonObject.getJSONObject("displayRule");
        if (displayRuleObject!= null) {
            JSONObject j = handleQuestionDisplayRule(qid, question, displayRuleObject);
            question.setDisplayRule(JSONObject.toJSONString(j));
        }

        question.setQuestionJson(JSONObject.toJSONString(list));
        appsQuestionService.updateById(question);

    }

    JSONObject handleQuestionDisplayRule(Long questionnaireId, UpAppsQuestion question, JSONObject jsonObject) {
        JSONObject newJson = (JSONObject) jsonObject.clone();
        JSONArray array = newJson.getJSONArray("rules");
        int i = 0;
        for (Object r : jsonObject.getJSONArray("rules")) {
            JSONObject rule = (JSONObject) r;
            UpAppsQuestion q = getQuestionBySubTitle(questionnaireId, rule.getString("questionId"));
            UpAppsQuestionItem item = getQuestionItemByKey(q.getId(), rule.getString("questionItemId"));

            JSONObject newItem = array.getJSONObject(i);
            newItem.put("questionId", q.getId() + "");
            newItem.put("questionItemId", item.getId() + "");
            i++;
        }
        return newJson;

    }

    UpAppsQuestion getQuestionBySubTitle(Long questionId, String subTitle){
        return appsQuestionService.selectOne(
                new EntityWrapper<UpAppsQuestion>()
                        .eq(UpAppsQuestion.QUESTIONNAIRE_ID, questionId)
                        .eq(UpAppsQuestion.SUB_TITLE, subTitle)
                        .orderBy(UpAppsQuestion.ADD_TIME, false)
        );
    }

    UpAppsQuestionItem getQuestionItemByKey(Long questionId, String key){
        return appsQuestionItemService.selectOne(
                new EntityWrapper<UpAppsQuestionItem>()
                        .eq(UpAppsQuestionItem.QUESTION_ID, questionId)
                        .eq("`key`", key)
                        .orderBy(UpAppsQuestionItem.ADD_TIME, false)
        );
    }

    @Test
    public void testGenExcel() throws IOException {
        UpTenantsCompany company = getCompany();
        UpAppsQuestionnaire questionnaire = upAppsQuestionnaireService.selectOne(
                new EntityWrapper<UpAppsQuestionnaire>()
                        .eq(UpAppsQuestionnaire.TENANTS_COMPANY_ID, company.getId())
                        .orderBy(UpAppsQuestionnaire.ADD_TIME, false)
        );
        Long questionnaireId = questionnaire.getId();
        String urlPrefix = "http://api.shenmiren.com.cn/questionnaire/#/welcome/";
        //String urlPrefix = "http://thirdparty.shenmiren.com.cn/users/#/welcome/";
        //String urlPrefix = "http://localhost:8080/users/#/welcome/";
        Long companyId = company.getId();
        JSONObject data = new JSONObject();
        data.put("q", questionnaireId + "");
        data.put("c", company.getId() + "");
        List<UpTenantsRegion> regionList = upTenantsRegionService.selectList(
                new EntityWrapper<UpTenantsRegion>()
                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, company.getId())
                        .eq(UpTenantsRegion.IS_STORE, 1)
        );
        //省份: 网点： 链接:
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("省份url");
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("网点");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("链接");
        cell.setCellStyle(style);

        for (int i = 0; i < regionList.size(); i++) {
            UpTenantsRegion store = regionList.get(i);
            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(store.getName());
            Long regionId = store.getId();
            data.put("r", regionId + "");
            String suffix = URLEncoder.encode(data.toJSONString(), "utf-8");
            row.createCell(1).setCellValue(urlPrefix + suffix);
        }

        FileOutputStream fout = new FileOutputStream("D:/福建中化.xls");
        workbook.write(fout);
        fout.close();

    }

}
