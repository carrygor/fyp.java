import cn.com.youplus.apps.api.auto.*;
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
public class zhongshiyouTest {

    private static Logger logger = Logger.getLogger(zhongshiyouTest.class);

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


    private static  String [] provinceArray ={
            "安徽省",
            "北京市",
            "福建省",
            "甘肃省",
            "广东省",
            "广西壮族自治区",
            "贵州省",
            "海南省",
            "河北省",
            "河南省",
            "黑龙江省",
            "湖北省",
            "湖南省",
            "吉林省",
            "江苏省",
            "江西省",
            "辽宁省",
            "内蒙古自治区",
            "宁夏回族自治区",
            "青海省",
            "山东省",
            "山西省",
            "陕西省",
            "上海市",
            "四川省",
            "天津市",
            "西藏自治区",
            "新疆维吾尔自治区",
            "云南省",
            "浙江省",
            "重庆市"
    };


    private  static String getCode(String proviceName) throws Exception {

        int index = 0;
        for (String pr : provinceArray) {
            index++;
            if (proviceName.equals(pr)) {
                return String.format("cnpc%02d",index);
            }
        }
        throw new Exception("找不到身份!" + proviceName);
    }

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

        UpTenantsCompany company = getCompany();
        UpAppsQuestionnaire questionnaire = upAppsQuestionnaireService.selectOne(
                new EntityWrapper<UpAppsQuestionnaire>()
                        .eq(UpAppsQuestionnaire.TENANTS_COMPANY_ID, company.getId())
                        .orderBy(UpAppsQuestionnaire.ADD_TIME, false)
        );
        Long questionnaireId = questionnaire.getId();
        String urlPrefix = "http://thirdparty.shenmiren.com.cn/users/#/welcome/";
        Long companyId = company.getId();
        JSONObject data = new JSONObject();
        data.put("q", questionnaireId);
        data.put("c", company.getId());

        List<UpTenantsRegion> regionList = upTenantsRegionService.selectList(
                new EntityWrapper<UpTenantsRegion>()
                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, companyId)
                        .eq(UpTenantsRegion.IS_STORE, 1)
        );

        Map<String, Map<String, String>> provinces = new HashMap<>();

        regionList.forEach(r -> {
            Map<String, String> reg = new HashMap<>();
            reg.put("r", r.getId()+"");
            reg.put("c", companyId + "");
            reg.put("q", questionnaireId + "");
            provinces.put(r.getRegionCode(), reg);
        });

        logger.debug(JSONObject.toJSONString(provinces));
    }

    private static String companyName = "中国石油天然气集团公司";

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
        JSONObject addressJson = getV4Data();
        //添加公司
        UpTenantsCompany tenantsCompany = new UpTenantsCompany();
        tenantsCompany.setAddress("东直门北大街9号")
                .setUpdateTime(new Date())
                .setAddTime(new Date())
                .setProvince("北京市")
                .setCity("市辖区")
                .setDistrict("东城区")
                .setHangye("企业服务")
                .setName("中国石油天然气集团公司")
                .setCompanyName("中国石油天然气集团公司")
                .setPhone("13925093101")
                .setMaxSiteNum(100)
                .setMaxReportNum(100000)
                .setMaxUserNum(100)
                .setAuthStartTime(new Date(new Date().getTime() - Constants.ONE_MONTH * 12))
                .setAuthExpiredTime(new Date(new Date().getTime() + Constants.ONE_MONTH * 12))
                .setFunctionStr("[{\"id\":10,\"name\":\"反馈收集\",\"children\":[{\"id\":11,\"name\":\"反馈收集-微信端\"},{\"id\":12,\"name\":\"反馈收集-支付宝端\"},{\"id\":13,\"name\":\"反馈收集-短信\"}]},{\"id\":20,\"name\":\"顾客反馈\",\"children\":[{\"id\":21,\"name\":\"概览分析\"},{\"id\":22,\"name\":\"提交率分析\"},{\"id\":23,\"name\":\"满意度分析\"},{\"id\":24,\"name\":\"满意度趋势\"},{\"id\":25,\"name\":\"低分选项分析\"}]},{\"id\":40,\"name\":\"警报处理\",\"children\":[{\"id\":41,\"name\":\"低分报告\"},{\"id\":42,\"name\":\"平台对比报告\"},{\"id\":43,\"name\":\"追踪报告\"},{\"id\":45,\"name\":\"原始数据报告\"}]}]")
                .setTenantsType(TenantsCompanyTypeEnum.企业.getType());

        tenantsCompany = UpTenantsCompanyService.mInsert(tenantsCompany);

        //添加一个区域架构
        UpTenantsLevel upTenantsLevel = new UpTenantsLevel();
        upTenantsLevel.setDescription("总部")
                .setLevel(0)
                .setTenantsCompanyId(tenantsCompany.getId())
                .setName("总部");
        upTenantsLevelService.mInsert(upTenantsLevel);

        UpTenantsLevel upTenantsLevel1 = new UpTenantsLevel();
        upTenantsLevel1.setDescription("省公司")
                .setLevel(1)
                .setTenantsCompanyId(tenantsCompany.getId())
                .setName("省公司");
        upTenantsLevelService.mInsert(upTenantsLevel1);

        logger.info("插入一个企业记录:");
        logger.info(tenantsCompany.toString());

        //弄一个总部进来
        UpTenantsRegion 总部region = new UpTenantsRegion();
        总部region.setAddTime(new Date())
                .setDescription("这是中国石油总部")
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
                .setJobTitle("中石油")
                .setSalt(salt)
                .setPassword(pwd)
                .setTenantsCompanyId(tenantsCompany.getId())
                .setRegionId(总部region.getId())
                .setPhoneNum("13925093101")
                .setRealName("中石油管理员")
                .setRoleId(getRoles(100)) //总部用户的roleId
                .setSex(1)
                .setUserName("cnpc"); //中国石油的英文缩写
        总部User = UpTenantsUserService.mInsert(总部User);

        //更新 user id
        tenantsCompany.setTenantsUserId(总部User.getId());
        UpTenantsCompanyService.updateById(tenantsCompany);

        UpTenantsRegion 总部网点 = new UpTenantsRegion();
        总部网点.setAddTime(new Date())
                .setDescription("这是" + "总部网点")
                .setLevel(2)
                .setName("全国中石油")
                .setParentId(总部region.getId())
                .setQuickTag("," + 总部region.getId() +",")
                .setUpdateTime(new Date())
                .setIsStore(1)
                .setRegionCode("cnpc")
                .setProvince("北京市")
                .setCity("")
                .setDistrict("")
                .setAddress("北京市")
                .setTenantsCompanyId(tenantsCompany.getId());
        总部网点 = UpTenantsRegionService.mInsert(总部网点);
        总部网点.setQuickTag(总部网点.getQuickTag() + 总部网点.getId() + ",");
        UpTenantsRegionService.updateById(总部网点);


        //插入区域
        JSONObject provinces = addressJson.getJSONObject("86");
        String[] provinceKeyList = new String[provinces.size()];
        provinceKeyList = provinces.keySet().toArray(provinceKeyList);

        //省级区域
        for (int l1 = 0; l1 < provinceKeyList.length; l1++) {
            String provinceKey = provinceKeyList[l1];
            String provice = provinces.getString(provinceKey);

            UpTenantsRegion 省级region = new UpTenantsRegion();
            省级region.setAddTime(new Date())
                    .setDescription("这是省级区域" + l1)
                    .setLevel(1)
                    .setName(provice)
                    .setParentId(总部region.getId())
                    .setQuickTag("," + 总部region.getId() + ",")
                    .setUpdateTime(new Date())
                    .setIsStore(0)
                    .setTenantsCompanyId(tenantsCompany.getId());

            省级region = UpTenantsRegionService.mInsert(省级region);
            省级region.setQuickTag(省级region.getQuickTag() + 省级region.getId() + ",");
            UpTenantsRegionService.updateById(省级region);

            UpTenantsRegion 省级网点 = new UpTenantsRegion();
            省级网点.setAddTime(new Date())
                    .setDescription("这是" + provice + "总部网点")
                    .setLevel(2)
                    .setName("中石油" + provice)
                    .setParentId(省级region.getId())
                    .setQuickTag("," + 总部region.getId() +"," + 省级region.getId() + ",")
                    .setUpdateTime(new Date())
                    .setIsStore(1)
                    .setProvince(provice)
                    .setRegionCode(getCode(provice))
                    .setCity("")
                    .setDistrict("")
                    .setAddress(provice)
                    .setTenantsCompanyId(tenantsCompany.getId());
            省级网点 = UpTenantsRegionService.mInsert(省级网点);
            省级网点.setQuickTag(省级网点.getQuickTag() + 省级网点.getId() + ",");
            UpTenantsRegionService.updateById(省级网点);
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
    public void testAddQuestionnaire() {
        UpTenantsCompany company = getCompany();
        //添加问卷
        UpAppsQuestionnaireTheme questionnaireTheme = new UpAppsQuestionnaireTheme();
        questionnaireTheme.setBkgImg("/upload/bkg.jpg")
                .setAddTime(new Date())
                .setIconImg("/upload/icon.jpg")
                .setThemeColor("#FF6536")
                .setUpdateTime(new Date());
        appsQuestionnaireThemeService.mInsert(questionnaireTheme);

        String [] 满意度选项分类 = {
                "服务态度", "服务效率", "油品质量","环境卫生" ,"促销优惠", "现场秩序"
        };

        String [] 满意度选项分类ID = {
                "A", "B", "C","D" ,"E", "F"
        };

        int questionNum = 0;
        int requiredNum = 0;
        UpAppsQuestionnaire questionnaire = new UpAppsQuestionnaire();
        questionnaire.setAddTime(new Date())
                .setUpdateTime(new Date())
                .setDescription("中国石油加油站顾客满意度调查")
                .setQuestionnaireType(QuestionnaireTypeEnum.普通.getType())
                .setRequiredOptionNum(questionNum)
                .setSort(1)
                .setTitle("中国石油加油站顾客满意度调查")
                .setSubTitle("")
                .setQuestionNum(30)
                .setTenantsCompanyId(company.getId())
                .setStartTime(new Date())
                .setExpiredTime(new Date(new Date().getTime() + 12 * Constants.ONE_MONTH))
                .setStatus(QuestionnaireStatusEnum.收集中.getType())
                .setPeriodJson("{\n" +
                        "                \"name\": \"加油时段\",\n" +
                        "                    \"periods\": [\n" +
                        "                {\n" +
                        "                    \"name\": \"全部\",\n" +
                        "                        \"timeStart\": \"00:00:00\",\n" +
                        "                        \"timeEnd\": \"23:59:59\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"早间时段\",\n" +
                        "                        \"timeStart\": \"06:00:00\",\n" +
                        "                        \"timeEnd\": \"10:00:00\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"午间时段\",\n" +
                        "                        \"timeStart\": \"10:00:00\",\n" +
                        "                        \"timeEnd\": \"15:00:00\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"晚间时段\",\n" +
                        "                        \"timeStart\": \"15:00:00\",\n" +
                        "                        \"timeEnd\": \"21:00:00\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"夜间时段\",\n" +
                        "                        \"timeStart\": \"21:00:00\",\n" +
                        "                        \"timeEnd\": \"06:00:00\"\n" +
                        "                }\n" +
                        "    ]\n" +
                        "            }")
                .setSiteName("油站")
                .setServiceTypeJson("{\n" +
                        "                \"name\": \"加油油品\",\n" +
                        "                    \"services\": [\n" +
                        "                \"全部\",\n" +
                        "                        \"-35#柴油\",\n" +
                        "                        \"-10#柴油\",\n" +
                        "                        \"-20#柴油\",\n" +
                        "                        \"0#柴油\",\n" +
                        "                        \"92#汽油\",\n" +
                        "                        \"95#汽油\",\n" +
                        "                        \"98#汽油\"\n" +
                        "    ]\n" +
                        "            }")

                .setNpsJson(JSONObject.toJSONString(满意度选项分类))
                .setThemeId(questionnaireTheme.getId());
        questionnaire = appsQuestionnaireService.mInsert(questionnaire);

        String [] 满意度选项 = {
                "非常不满意", "比较不满意", "一般","比较满意" ,"非常满意"
                 };

        String [] 满意度选项得分 = {
                "1", "2", "3","4" ,"5"
        };

        String [] 决定因素 = {
                "优惠活动多", "自助加油方便", "油品数量充足", "服务态度好", "加油站位置方便",
                "中国石油口碑好", "服务效率快速", "加油站分布多", "加油卡使用方便", "油品质量好"
        };

        String [] 改进 = {
                "业务操作不规范", "服务效率低", "服务态度差", "便利店商品单一",
                "便利店商品摆放混乱", "加油卡业务办理繁琐", "自助加油机少", "优惠活动少",
                "活动开展形式单一", "便民设施少", "整体站内环境差", "油品质量差",
                "油品数量不足", "加油站位置不方便", "加油站口碑差",
                "以上均无"
        };

        String [] 油品 = {
                "-35#柴油","-20#柴油", "-10#柴油", "0#柴油" ,"92#汽油","95#汽油", "98#汽油"
        };

        String [] 油品得分 = {
                "-35#柴油", "-20#柴油","-10#柴油", "0#柴油" ,"92#汽油","95#汽油", "98#汽油"
                //"1", "2", "3","4" ,"5","6","7"
        };

        //region jiaren
        UpAppsQuestion question = new UpAppsQuestion();
        question.setAddTime(new Date())
                .setUpdateTime(new Date())
                .setIsRequired(1)
                .setDescription("")
                .setIsVisible(1)
                .setQuestionJson("")
                .setOptionsNum(2)
                .setQuestionnaireId(questionnaire.getId())
                .setQuestionOrder(questionNum + 1)
                .setTitle("S0. 请问您本人、您的家人中，有没有在石油公司或加油站单位工作呢？（单选）")
                .setGoodScore(4)
                .setBadScore(2)
                .setIsNps(0)
                .setQuestionType(QuestionTypeEnum.单选.getType())
                .setScoreDimenssion("")
                .setIsScore(1)
                .setSubTitle("");
        question = appsQuestionService.mInsert(question);

        List<UpAppsQuestionItem> itemList = new ArrayList<>();
        //插入子选项
        for (int q2 = 0; q2 < 2; q2++) {
            UpAppsQuestionItem item = new UpAppsQuestionItem();
            item.setSort(q2)
                    .setUpdateTime(new Date())
                    .setAddTime(new Date())
                    .setQuestionId(question.getId())
                    .setQuestionnaireId(questionnaire.getId())
                    .setKey((q2 + 1) + "")
                    .setValue(q2 == 0 ? "有" : "没有")
                    .setDisplay(q2 == 0 ? "有" : "没有")
                    .setQuestionItemType(QuestionItemTypeEnum.选项.getType());
            item = appsQuestionItemService.mInsert(item);
            itemList.add(item);
        }
        question.setQuestionJson(JSONObject.toJSONString(itemList));
        appsQuestionService.updateById(question);

        requiredNum++;
        questionNum++;
        //endregion

        //region 满意度
        int index = 0;
        int order = 1;
        for (String 满意度分项 : 满意度选项分类) {
            question = new UpAppsQuestion();
            question.setAddTime(new Date())
                    .setUpdateTime(new Date())
                    .setIsRequired(1)
                    .setDescription("")
                    .setIsVisible(1)
                    .setQuestionJson("")
                    .setOptionsNum(满意度选项.length)
                    .setQuestionnaireId(questionnaire.getId())
                    .setQuestionOrder(questionNum + 1)
                    .setTitle("A1. " + 满意度选项分类ID[index] + "请问您对中石油加油站" + 满意度分项 + "方面满意度如何？(单选)")
                    .setGoodScore(4)
                    .setBadScore(2)
                    .setIsNps(0)
                    .setQuestionType(QuestionTypeEnum.分项满意度.getType())
                    .setScoreDimenssion(满意度分项)
                    .setIsScore(1)
                    .setSubTitle("");
            question = appsQuestionService.mInsert(question);

            itemList = new ArrayList<>();
            //插入子选项
            for (int q2 = 0; q2 < 满意度选项.length; q2++) {
                UpAppsQuestionItem item = new UpAppsQuestionItem();
                item.setSort(q2)
                        .setUpdateTime(new Date())
                        .setAddTime(new Date())
                        .setQuestionId(question.getId())
                        .setQuestionnaireId(questionnaire.getId())
                        .setKey(满意度选项得分[q2])
                        .setValue(满意度选项得分[q2])
                        .setDisplay(满意度选项[q2])
                        .setQuestionItemType(QuestionItemTypeEnum.分数选择.getType());
                item = appsQuestionItemService.mInsert(item);
                itemList.add(item);
            }
            question.setQuestionJson(JSONObject.toJSONString(itemList));
            appsQuestionService.updateById(question);

            index++;
            requiredNum++;
            questionNum++;
        }
        order++;
        //endregion

        //region 决定因素
        question = new UpAppsQuestion();
        question.setAddTime(new Date())
                .setUpdateTime(new Date())
                .setIsRequired(1)
                .setDescription("")
                .setIsVisible(1)
                .setQuestionJson("")
                .setOptionsNum(决定因素.length)
                .setQuestionnaireId(questionnaire.getId())
                .setQuestionOrder(questionNum + 1)
                .setTitle("A2. 以下哪些会是您选择加油站的决定因素?（可多选）")
                .setGoodScore(0)
                .setBadScore(0)
                .setIsNps(0)
                .setQuestionType(QuestionTypeEnum.多选.getType())
                .setScoreDimenssion("")
                .setIsScore(0)
                .setSubTitle("");
        question = appsQuestionService.mInsert(question);

        itemList = new ArrayList<>();
        //插入子选项
        for (int q2 = 0; q2 < 决定因素.length; q2++) {
            UpAppsQuestionItem item = new UpAppsQuestionItem();
            item.setSort(q2)
                    .setUpdateTime(new Date())
                    .setAddTime(new Date())
                    .setQuestionId(question.getId())
                    .setQuestionnaireId(questionnaire.getId())
                    .setKey(q2 + "")
                    .setValue(决定因素[q2])
                    .setDisplay(决定因素[q2])
                    .setQuestionItemType(QuestionItemTypeEnum.选项.getType());

            if (决定因素[q2].equals("其它")) {
                item.setIsShowEditor(1);
                item.setEditorPlaceholder("请填写其他因素");
            }

            item = appsQuestionItemService.mInsert(item);
            itemList.add(item);
        }
        question.setQuestionJson(JSONObject.toJSONString(itemList));
        appsQuestionService.updateById(question);

        index++;
        requiredNum++;
        questionNum++;
        order++;
        //endregion

        //region 改进
        question = new UpAppsQuestion();
        question.setAddTime(new Date())
                .setUpdateTime(new Date())
                .setIsRequired(1)
                .setDescription("")
                .setIsVisible(1)
                .setQuestionJson("")
                .setOptionsNum(改进.length)
                .setQuestionnaireId(questionnaire.getId())
                .setQuestionOrder(questionNum + 1)
                .setTitle("A3. 中国石油各项服务中，您觉得哪些服务项目需要改进？（可多选）")
                .setGoodScore(0)
                .setBadScore(0)
                .setIsNps(0)
                .setQuestionType(QuestionTypeEnum.多选.getType())
                .setScoreDimenssion("")
                .setIsScore(0)
                .setSubTitle("");
        question = appsQuestionService.mInsert(question);

        itemList = new ArrayList<>();
        //插入子选项
        for (int q2 = 0; q2 < 改进.length; q2++) {
            UpAppsQuestionItem item = new UpAppsQuestionItem();
            item.setSort(q2)
                    .setUpdateTime(new Date())
                    .setAddTime(new Date())
                    .setQuestionId(question.getId())
                    .setQuestionnaireId(questionnaire.getId())
                    .setKey(q2 + "")
                    .setValue(改进[q2])
                    .setDisplay(改进[q2])
                    .setQuestionItemType(QuestionItemTypeEnum.选项.getType());

            if (改进[q2].equals("其它")) {
                item.setIsShowEditor(1);
                item.setEditorPlaceholder("请填写其他因素");
            }

            item = appsQuestionItemService.mInsert(item);
            itemList.add(item);
        }
        question.setQuestionJson(JSONObject.toJSONString(itemList));
        appsQuestionService.updateById(question);

        index++;
        requiredNum++;
        questionNum++;
        order++;
        //endregion

        //region 油品
        question = new UpAppsQuestion();
        question.setAddTime(new Date())
                .setUpdateTime(new Date())
                .setIsRequired(1)
                .setDescription("")
                .setIsVisible(1)
                .setQuestionJson("")
                .setOptionsNum(油品.length)
                .setQuestionnaireId(questionnaire.getId())
                .setQuestionOrder(questionNum + 1)
                .setTitle("A4. 请问您到油站加油时，选择哪个标号的油品呢？ （单选）")
                .setGoodScore(0)
                .setBadScore(0)
                .setIsNps(0)
                .setQuestionType(QuestionTypeEnum.单选.getType())
                .setScoreDimenssion("")
                .setIsScore(0)
                .setSubTitle("");
        question = appsQuestionService.mInsert(question);

        itemList = new ArrayList<>();
        //插入子选项
        for (int q2 = 0; q2 < 油品.length; q2++) {
            UpAppsQuestionItem item = new UpAppsQuestionItem();
            item.setSort(q2)
                    .setUpdateTime(new Date())
                    .setAddTime(new Date())
                    .setQuestionId(question.getId())
                    .setQuestionnaireId(questionnaire.getId())
                    .setKey(油品得分[油品.length - 1 - q2])
                    .setValue(油品得分[油品.length - 1 - q2])
                    .setDisplay(油品[油品.length - 1 - q2])
                    .setQuestionItemType(QuestionItemTypeEnum.选项.getType());

            item = appsQuestionItemService.mInsert(item);
            itemList.add(item);
        }
        question.setQuestionJson(JSONObject.toJSONString(itemList));
        appsQuestionService.updateById(question);

        index++;
        requiredNum++;
        questionNum++;
        order++;
        //endregion

        //region 手机号
        question = new UpAppsQuestion();
        question.setAddTime(new Date())
                .setUpdateTime(new Date())
                .setIsRequired(1)
                .setDescription("")
                .setIsVisible(1)
                .setQuestionJson("")
                .setOptionsNum(1)
                .setQuestionnaireId(questionnaire.getId())
                .setQuestionOrder(questionNum + 1)
                .setTitle("A5. 请问您的手机号码是:")
                .setGoodScore(0)
                .setBadScore(0)
                .setIsNps(0)
                .setQuestionType(QuestionTypeEnum.填空.getType())
                .setScoreDimenssion("")
                .setIsScore(0)
                .setSubTitle("");
        question = appsQuestionService.mInsert(question);

        itemList = new ArrayList<>();
        //插入子选项
        UpAppsQuestionItem item = new UpAppsQuestionItem();
        item.setSort(1)
                .setUpdateTime(new Date())
                .setAddTime(new Date())
                .setQuestionId(question.getId())
                .setQuestionnaireId(questionnaire.getId())
                .setKey("")
                .setValue("")
                .setDisplay("")
                .setEditorType("phone")
                .setPlaceholder("请填写您的手机号码")
                .setQuestionItemType(QuestionItemTypeEnum.填空.getType());
        item = appsQuestionItemService.mInsert(item);
        itemList.add(item);
        question.setQuestionJson(JSONObject.toJSONString(itemList));
        appsQuestionService.updateById(question);

        index++;
        requiredNum++;
        questionNum++;
        order++;
        //endregion

        questionnaire.setQuestionNum(questionNum)
                .setRequiredOptionNum(requiredNum);
        appsQuestionnaireService.updateById(questionnaire);
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
        String urlPrefix = "http://thirdparty.shenmiren.com.cn/users/#/welcome/";
        Long companyId = company.getId();
        JSONObject data = new JSONObject();
        data.put("q", questionnaireId);
        data.put("c", company.getId());
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
        cell.setCellValue("省份");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("网点");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("链接");
        cell.setCellStyle(style);

        for (int i = 0; i < regionList.size(); i++) {
            UpTenantsRegion store = regionList.get(i);
            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(store.getProvince());
            row.createCell(1).setCellValue(store.getName());
            Long regionId = store.getId();
            data.put("r", regionId);
            String suffix = URLEncoder.encode(data.toJSONString(), "utf-8");
            row.createCell(2).setCellValue(urlPrefix + suffix);
        }

        FileOutputStream fout = new FileOutputStream("D:/url-prod.xls");
        workbook.write(fout);
        fout.close();

    }

    JSONObject getV4Data() {
        StringBuilder str = new StringBuilder("{\n" +
                "    \"86\": {\n" +
                "        \"110000\": \"北京市\",\n" +
                "        \"120000\": \"天津市\",\n" +
                "        \"130000\": \"河北省\",\n" +
                "        \"140000\": \"山西省\",\n" +
                "        \"150000\": \"内蒙古自治区\",\n" +
                "        \"210000\": \"辽宁省\",\n" +
                "        \"220000\": \"吉林省\",\n" +
                "        \"230000\": \"黑龙江省\",\n" +
                "        \"310000\": \"上海市\",\n" +
                "        \"320000\": \"江苏省\",\n" +
                "        \"330000\": \"浙江省\",\n" +
                "        \"340000\": \"安徽省\",\n" +
                "        \"350000\": \"福建省\",\n" +
                "        \"360000\": \"江西省\",\n" +
                "        \"370000\": \"山东省\",\n" +
                "        \"410000\": \"河南省\",\n" +
                "        \"420000\": \"湖北省\",\n" +
                "        \"430000\": \"湖南省\",\n" +
                "        \"440000\": \"广东省\",\n" +
                "        \"450000\": \"广西壮族自治区\",\n" +
                "        \"460000\": \"海南省\",\n" +
                "        \"500000\": \"重庆市\",\n" +
                "        \"510000\": \"四川省\",\n" +
                "        \"520000\": \"贵州省\",\n" +
                "        \"530000\": \"云南省\",\n" +
                "        \"540000\": \"西藏自治区\",\n" +
                "        \"610000\": \"陕西省\",\n" +
                "        \"620000\": \"甘肃省\",\n" +
                "        \"630000\": \"青海省\",\n" +
                "        \"640000\": \"宁夏回族自治区\",\n" +
                "        \"650000\": \"新疆维吾尔自治区\"\n" +
                //"        \"710000\": \"台湾省\",\n" +
                //"        \"810000\": \"香港特别行政区\",\n" +
                //"        \"820000\": \"澳门特别行政区\"\n" +
                "    },\n" +
                "    \"110000\": {\n" +
                "        \"110100\": \"市辖区\"\n" +
                "    },\n" +
                "    \"110100\": {\n" +
                "        \"110101\": \"东城区\",\n" +
                "        \"110102\": \"西城区\",\n" +
                "        \"110105\": \"朝阳区\",\n" +
                "        \"110106\": \"丰台区\",\n" +
                "        \"110107\": \"石景山区\",\n" +
                "        \"110108\": \"海淀区\",\n" +
                "        \"110109\": \"门头沟区\",\n" +
                "        \"110111\": \"房山区\",\n" +
                "        \"110112\": \"通州区\",\n" +
                "        \"110113\": \"顺义区\",\n" +
                "        \"110114\": \"昌平区\",\n" +
                "        \"110115\": \"大兴区\",\n" +
                "        \"110116\": \"怀柔区\",\n" +
                "        \"110117\": \"平谷区\",\n" +
                "        \"110118\": \"密云区\",\n" +
                "        \"110119\": \"延庆区\"\n" +
                "    },\n" +
                "    \"120000\": {\n" +
                "        \"120100\": \"市辖区\"\n" +
                "    },\n" +
                "    \"120100\": {\n" +
                "        \"120101\": \"和平区\",\n" +
                "        \"120102\": \"河东区\",\n" +
                "        \"120103\": \"河西区\",\n" +
                "        \"120104\": \"南开区\",\n" +
                "        \"120105\": \"河北区\",\n" +
                "        \"120106\": \"红桥区\",\n" +
                "        \"120110\": \"东丽区\",\n" +
                "        \"120111\": \"西青区\",\n" +
                "        \"120112\": \"津南区\",\n" +
                "        \"120113\": \"北辰区\",\n" +
                "        \"120114\": \"武清区\",\n" +
                "        \"120115\": \"宝坻区\",\n" +
                "        \"120116\": \"滨海新区\",\n" +
                "        \"120117\": \"宁河区\",\n" +
                "        \"120118\": \"静海区\",\n" +
                "        \"120119\": \"蓟州区\"\n" +
                "    },\n" +
                "    \"130000\": {\n" +
                "        \"130100\": \"石家庄市\",\n" +
                "        \"130200\": \"唐山市\",\n" +
                "        \"130300\": \"秦皇岛市\",\n" +
                "        \"130400\": \"邯郸市\",\n" +
                "        \"130500\": \"邢台市\",\n" +
                "        \"130600\": \"保定市\",\n" +
                "        \"130700\": \"张家口市\",\n" +
                "        \"130800\": \"承德市\",\n" +
                "        \"130900\": \"沧州市\",\n" +
                "        \"131000\": \"廊坊市\",\n" +
                "        \"131100\": \"衡水市\",\n" +
                "        \"139001\": \"定州市\",\n" +
                "        \"139002\": \"辛集市\"\n" +
                "    },\n" +
                "    \"130100\": {\n" +
                "        \"130102\": \"长安区\",\n" +
                "        \"130104\": \"桥西区\",\n" +
                "        \"130105\": \"新华区\",\n" +
                "        \"130107\": \"井陉矿区\",\n" +
                "        \"130108\": \"裕华区\",\n" +
                "        \"130109\": \"藁城区\",\n" +
                "        \"130110\": \"鹿泉区\",\n" +
                "        \"130111\": \"栾城区\",\n" +
                "        \"130121\": \"井陉县\",\n" +
                "        \"130123\": \"正定县\",\n" +
                "        \"130125\": \"行唐县\",\n" +
                "        \"130126\": \"灵寿县\",\n" +
                "        \"130127\": \"高邑县\",\n" +
                "        \"130128\": \"深泽县\",\n" +
                "        \"130129\": \"赞皇县\",\n" +
                "        \"130130\": \"无极县\",\n" +
                "        \"130131\": \"平山县\",\n" +
                "        \"130132\": \"元氏县\",\n" +
                "        \"130133\": \"赵县\",\n" +
                "        \"130183\": \"晋州市\",\n" +
                "        \"130184\": \"新乐市\"\n" +
                "    },\n" +
                "    \"130200\": {\n" +
                "        \"130202\": \"路南区\",\n" +
                "        \"130203\": \"路北区\",\n" +
                "        \"130204\": \"古冶区\",\n" +
                "        \"130205\": \"开平区\",\n" +
                "        \"130207\": \"丰南区\",\n" +
                "        \"130208\": \"丰润区\",\n" +
                "        \"130209\": \"曹妃甸区\",\n" +
                "        \"130223\": \"滦县\",\n" +
                "        \"130224\": \"滦南县\",\n" +
                "        \"130225\": \"乐亭县\",\n" +
                "        \"130227\": \"迁西县\",\n" +
                "        \"130229\": \"玉田县\",\n" +
                "        \"130281\": \"遵化市\",\n" +
                "        \"130283\": \"迁安市\"\n" +
                "    },\n" +
                "    \"130300\": {\n" +
                "        \"130302\": \"海港区\",\n" +
                "        \"130303\": \"山海关区\",\n" +
                "        \"130304\": \"北戴河区\",\n" +
                "        \"130306\": \"抚宁区\",\n" +
                "        \"130321\": \"青龙满族自治县\",\n" +
                "        \"130322\": \"昌黎县\",\n" +
                "        \"130324\": \"卢龙县\"\n" +
                "    },\n" +
                "    \"130400\": {\n" +
                "        \"130402\": \"邯山区\",\n" +
                "        \"130403\": \"丛台区\",\n" +
                "        \"130404\": \"复兴区\",\n" +
                "        \"130406\": \"峰峰矿区\",\n" +
                "        \"130421\": \"邯郸县\",\n" +
                "        \"130423\": \"临漳县\",\n" +
                "        \"130424\": \"成安县\",\n" +
                "        \"130425\": \"大名县\",\n" +
                "        \"130426\": \"涉县\",\n" +
                "        \"130427\": \"磁县\",\n" +
                "        \"130428\": \"肥乡县\",\n" +
                "        \"130429\": \"永年县\",\n" +
                "        \"130430\": \"邱县\",\n" +
                "        \"130431\": \"鸡泽县\",\n" +
                "        \"130432\": \"广平县\",\n" +
                "        \"130433\": \"馆陶县\",\n" +
                "        \"130434\": \"魏县\",\n" +
                "        \"130435\": \"曲周县\",\n" +
                "        \"130481\": \"武安市\"\n" +
                "    },\n" +
                "    \"130500\": {\n" +
                "        \"130502\": \"桥东区\",\n" +
                "        \"130503\": \"桥西区\",\n" +
                "        \"130521\": \"邢台县\",\n" +
                "        \"130522\": \"临城县\",\n" +
                "        \"130523\": \"内丘县\",\n" +
                "        \"130524\": \"柏乡县\",\n" +
                "        \"130525\": \"隆尧县\",\n" +
                "        \"130526\": \"任县\",\n" +
                "        \"130527\": \"南和县\",\n" +
                "        \"130528\": \"宁晋县\",\n" +
                "        \"130529\": \"巨鹿县\",\n" +
                "        \"130530\": \"新河县\",\n" +
                "        \"130531\": \"广宗县\",\n" +
                "        \"130532\": \"平乡县\",\n" +
                "        \"130533\": \"威县\",\n" +
                "        \"130534\": \"清河县\",\n" +
                "        \"130535\": \"临西县\",\n" +
                "        \"130581\": \"南宫市\",\n" +
                "        \"130582\": \"沙河市\"\n" +
                "    },\n" +
                "    \"130600\": {\n" +
                "        \"130602\": \"竞秀区\",\n" +
                "        \"130606\": \"莲池区\",\n" +
                "        \"130607\": \"满城区\",\n" +
                "        \"130608\": \"清苑区\",\n" +
                "        \"130609\": \"徐水区\",\n" +
                "        \"130623\": \"涞水县\",\n" +
                "        \"130624\": \"阜平县\",\n" +
                "        \"130626\": \"定兴县\",\n" +
                "        \"130627\": \"唐县\",\n" +
                "        \"130628\": \"高阳县\",\n" +
                "        \"130629\": \"容城县\",\n" +
                "        \"130630\": \"涞源县\",\n" +
                "        \"130631\": \"望都县\",\n" +
                "        \"130632\": \"安新县\",\n" +
                "        \"130633\": \"易县\",\n" +
                "        \"130634\": \"曲阳县\",\n" +
                "        \"130635\": \"蠡县\",\n" +
                "        \"130636\": \"顺平县\",\n" +
                "        \"130637\": \"博野县\",\n" +
                "        \"130638\": \"雄县\",\n" +
                "        \"130681\": \"涿州市\",\n" +
                "        \"130683\": \"安国市\",\n" +
                "        \"130684\": \"高碑店市\"\n" +
                "    },\n" +
                "    \"130700\": {\n" +
                "        \"130702\": \"桥东区\",\n" +
                "        \"130703\": \"桥西区\",\n" +
                "        \"130705\": \"宣化区\",\n" +
                "        \"130706\": \"下花园区\",\n" +
                "        \"130708\": \"万全区\",\n" +
                "        \"130709\": \"崇礼区\",\n" +
                "        \"130722\": \"张北县\",\n" +
                "        \"130723\": \"康保县\",\n" +
                "        \"130724\": \"沽源县\",\n" +
                "        \"130725\": \"尚义县\",\n" +
                "        \"130726\": \"蔚县\",\n" +
                "        \"130727\": \"阳原县\",\n" +
                "        \"130728\": \"怀安县\",\n" +
                "        \"130730\": \"怀来县\",\n" +
                "        \"130731\": \"涿鹿县\",\n" +
                "        \"130732\": \"赤城县\"\n" +
                "    },\n" +
                "    \"130800\": {\n" +
                "        \"130802\": \"双桥区\",\n" +
                "        \"130803\": \"双滦区\",\n" +
                "        \"130804\": \"鹰手营子矿区\",\n" +
                "        \"130821\": \"承德县\",\n" +
                "        \"130822\": \"兴隆县\",\n" +
                "        \"130823\": \"平泉县\",\n" +
                "        \"130824\": \"滦平县\",\n" +
                "        \"130825\": \"隆化县\",\n" +
                "        \"130826\": \"丰宁满族自治县\",\n" +
                "        \"130827\": \"宽城满族自治县\",\n" +
                "        \"130828\": \"围场满族蒙古族自治县\"\n" +
                "    },\n" +
                "    \"130900\": {\n" +
                "        \"130902\": \"新华区\",\n" );

        str.append(
                "        \"130903\": \"运河区\",\n" +
                "        \"130921\": \"沧县\",\n" +
                "        \"130922\": \"青县\",\n" +
                "        \"130923\": \"东光县\",\n" +
                "        \"130924\": \"海兴县\",\n" +
                "        \"130925\": \"盐山县\",\n" +
                "        \"130926\": \"肃宁县\",\n" +
                "        \"130927\": \"南皮县\",\n" +
                "        \"130928\": \"吴桥县\",\n" +
                "        \"130929\": \"献县\",\n" +
                "        \"130930\": \"孟村回族自治县\",\n" +
                "        \"130981\": \"泊头市\",\n" +
                "        \"130982\": \"任丘市\",\n" +
                "        \"130983\": \"黄骅市\",\n" +
                "        \"130984\": \"河间市\"\n" +
                "    },\n" +
                "    \"131000\": {\n" +
                "        \"131002\": \"安次区\",\n" +
                "        \"131003\": \"广阳区\",\n" +
                "        \"131022\": \"固安县\",\n" +
                "        \"131023\": \"永清县\",\n" +
                "        \"131024\": \"香河县\",\n" +
                "        \"131025\": \"大城县\",\n" +
                "        \"131026\": \"文安县\",\n" +
                "        \"131028\": \"大厂回族自治县\",\n" +
                "        \"131081\": \"霸州市\",\n" +
                "        \"131082\": \"三河市\"\n" +
                "    },\n" +
                "    \"131100\": {\n" +
                "        \"131102\": \"桃城区\",\n" +
                "        \"131103\": \"冀州区\",\n" +
                "        \"131121\": \"枣强县\",\n" +
                "        \"131122\": \"武邑县\",\n" +
                "        \"131123\": \"武强县\",\n" +
                "        \"131124\": \"饶阳县\",\n" +
                "        \"131125\": \"安平县\",\n" +
                "        \"131126\": \"故城县\",\n" +
                "        \"131127\": \"景县\",\n" +
                "        \"131128\": \"阜城县\",\n" +
                "        \"131182\": \"深州市\"\n" +
                "    },\n" +
                "    \"140000\": {\n" +
                "        \"140100\": \"太原市\",\n" +
                "        \"140200\": \"大同市\",\n" +
                "        \"140300\": \"阳泉市\",\n" +
                "        \"140400\": \"长治市\",\n" +
                "        \"140500\": \"晋城市\",\n" +
                "        \"140600\": \"朔州市\",\n" +
                "        \"140700\": \"晋中市\",\n" +
                "        \"140800\": \"运城市\",\n" +
                "        \"140900\": \"忻州市\",\n" +
                "        \"141000\": \"临汾市\",\n" +
                "        \"141100\": \"吕梁市\"\n" +
                "    },\n" +
                "    \"140100\": {\n" +
                "        \"140105\": \"小店区\",\n" +
                "        \"140106\": \"迎泽区\",\n" +
                "        \"140107\": \"杏花岭区\",\n" +
                "        \"140108\": \"尖草坪区\",\n" +
                "        \"140109\": \"万柏林区\",\n" +
                "        \"140110\": \"晋源区\",\n" +
                "        \"140121\": \"清徐县\",\n" +
                "        \"140122\": \"阳曲县\",\n" +
                "        \"140123\": \"娄烦县\",\n" +
                "        \"140181\": \"古交市\"\n" +
                "    },\n" +
                "    \"140200\": {\n" +
                "        \"140202\": \"城区\",\n" +
                "        \"140203\": \"矿区\",\n" +
                "        \"140211\": \"南郊区\",\n" +
                "        \"140212\": \"新荣区\",\n" +
                "        \"140221\": \"阳高县\",\n" +
                "        \"140222\": \"天镇县\",\n" +
                "        \"140223\": \"广灵县\",\n" +
                "        \"140224\": \"灵丘县\",\n" +
                "        \"140225\": \"浑源县\",\n" +
                "        \"140226\": \"左云县\",\n" +
                "        \"140227\": \"大同县\"\n" +
                "    },\n" +
                "    \"140300\": {\n" +
                "        \"140302\": \"城区\",\n" +
                "        \"140303\": \"矿区\",\n" +
                "        \"140311\": \"郊区\",\n" +
                "        \"140321\": \"平定县\",\n" +
                "        \"140322\": \"盂县\"\n" +
                "    },\n" +
                "    \"140400\": {\n" +
                "        \"140402\": \"城区\",\n" +
                "        \"140411\": \"郊区\",\n" +
                "        \"140421\": \"长治县\",\n" +
                "        \"140423\": \"襄垣县\",\n" +
                "        \"140424\": \"屯留县\",\n" +
                "        \"140425\": \"平顺县\",\n" +
                "        \"140426\": \"黎城县\",\n" +
                "        \"140427\": \"壶关县\",\n" +
                "        \"140428\": \"长子县\",\n" +
                "        \"140429\": \"武乡县\",\n" +
                "        \"140430\": \"沁县\",\n" +
                "        \"140431\": \"沁源县\",\n" +
                "        \"140481\": \"潞城市\"\n" +
                "    },\n" +
                "    \"140500\": {\n" +
                "        \"140502\": \"城区\",\n" +
                "        \"140521\": \"沁水县\",\n" +
                "        \"140522\": \"阳城县\",\n" +
                "        \"140524\": \"陵川县\",\n" +
                "        \"140525\": \"泽州县\",\n" +
                "        \"140581\": \"高平市\"\n" +
                "    },\n" +
                "    \"140600\": {\n" +
                "        \"140602\": \"朔城区\",\n" +
                "        \"140603\": \"平鲁区\",\n" +
                "        \"140621\": \"山阴县\",\n" +
                "        \"140622\": \"应县\",\n" +
                "        \"140623\": \"右玉县\",\n" +
                "        \"140624\": \"怀仁县\"\n" +
                "    },\n" +
                "    \"140700\": {\n" +
                "        \"140702\": \"榆次区\",\n" +
                "        \"140721\": \"榆社县\",\n" +
                "        \"140722\": \"左权县\",\n" +
                "        \"140723\": \"和顺县\",\n" +
                "        \"140724\": \"昔阳县\",\n" +
                "        \"140725\": \"寿阳县\",\n" +
                "        \"140726\": \"太谷县\",\n" +
                "        \"140727\": \"祁县\",\n" +
                "        \"140728\": \"平遥县\",\n" +
                "        \"140729\": \"灵石县\",\n" +
                "        \"140781\": \"介休市\"\n" +
                "    },\n" +
                "    \"140800\": {\n" +
                "        \"140802\": \"盐湖区\",\n" +
                "        \"140821\": \"临猗县\",\n" +
                "        \"140822\": \"万荣县\",\n" +
                "        \"140823\": \"闻喜县\",\n" +
                "        \"140824\": \"稷山县\",\n" +
                "        \"140825\": \"新绛县\",\n" +
                "        \"140826\": \"绛县\",\n" +
                "        \"140827\": \"垣曲县\",\n" +
                "        \"140828\": \"夏县\",\n" +
                "        \"140829\": \"平陆县\",\n" +
                "        \"140830\": \"芮城县\",\n" +
                "        \"140881\": \"永济市\",\n" +
                "        \"140882\": \"河津市\"\n" +
                "    },\n" +
                "    \"140900\": {\n" +
                "        \"140902\": \"忻府区\",\n" +
                "        \"140921\": \"定襄县\",\n" +
                "        \"140922\": \"五台县\",\n" +
                "        \"140923\": \"代县\",\n" +
                "        \"140924\": \"繁峙县\",\n" +
                "        \"140925\": \"宁武县\",\n" +
                "        \"140926\": \"静乐县\",\n" +
                "        \"140927\": \"神池县\",\n" +
                "        \"140928\": \"五寨县\",\n" +
                "        \"140929\": \"岢岚县\",\n" +
                "        \"140930\": \"河曲县\",\n" +
                "        \"140931\": \"保德县\",\n" +
                "        \"140932\": \"偏关县\",\n" +
                "        \"140981\": \"原平市\"\n" +
                "    },\n" +
                "    \"141000\": {\n" +
                "        \"141002\": \"尧都区\",\n" +
                "        \"141021\": \"曲沃县\",\n" +
                "        \"141022\": \"翼城县\",\n" +
                "        \"141023\": \"襄汾县\",\n" +
                "        \"141024\": \"洪洞县\",\n" +
                "        \"141025\": \"古县\",\n" +
                "        \"141026\": \"安泽县\",\n" +
                "        \"141027\": \"浮山县\",\n" +
                "        \"141028\": \"吉县\",\n" +
                "        \"141029\": \"乡宁县\",\n" +
                "        \"141030\": \"大宁县\",\n" +
                "        \"141031\": \"隰县\",\n" +
                "        \"141032\": \"永和县\",\n" +
                "        \"141033\": \"蒲县\",\n" +
                "        \"141034\": \"汾西县\",\n" +
                "        \"141081\": \"侯马市\",\n" +
                "        \"141082\": \"霍州市\"\n" +
                "    },\n" +
                "    \"141100\": {\n" +
                "        \"141102\": \"离石区\",\n" +
                "        \"141121\": \"文水县\",\n" +
                "        \"141122\": \"交城县\",\n" +
                "        \"141123\": \"兴县\",\n" +
                "        \"141124\": \"临县\",\n" +
                "        \"141125\": \"柳林县\",\n" +
                "        \"141126\": \"石楼县\",\n" +
                "        \"141127\": \"岚县\",\n" +
                "        \"141128\": \"方山县\",\n" +
                "        \"141129\": \"中阳县\",\n" +
                "        \"141130\": \"交口县\",\n" +
                "        \"141181\": \"孝义市\",\n" +
                "        \"141182\": \"汾阳市\"\n" +
                "    },\n" +
                "    \"150000\": {\n" +
                "        \"150100\": \"呼和浩特市\",\n" +
                "        \"150200\": \"包头市\",\n" +
                "        \"150300\": \"乌海市\",\n" +
                "        \"150400\": \"赤峰市\",\n" +
                "        \"150500\": \"通辽市\",\n" +
                "        \"150600\": \"鄂尔多斯市\",\n" +
                "        \"150700\": \"呼伦贝尔市\",\n" +
                "        \"150800\": \"巴彦淖尔市\",\n" +
                "        \"150900\": \"乌兰察布市\",\n" +
                "        \"152200\": \"兴安盟\",\n" +
                "        \"152500\": \"锡林郭勒盟\",\n" +
                "        \"152900\": \"阿拉善盟\"\n" +
                "    },\n" +
                "    \"150100\": {\n" +
                "        \"150102\": \"新城区\",\n" +
                "        \"150103\": \"回民区\",\n" +
                "        \"150104\": \"玉泉区\",\n" +
                "        \"150105\": \"赛罕区\",\n" +
                "        \"150121\": \"土默特左旗\",\n" +
                "        \"150122\": \"托克托县\",\n" +
                "        \"150123\": \"和林格尔县\",\n" +
                "        \"150124\": \"清水河县\",\n" +
                "        \"150125\": \"武川县\"\n" +
                "    },\n" +
                "    \"150200\": {\n" +
                "        \"150202\": \"东河区\",\n" +
                "        \"150203\": \"昆都仑区\",\n" +
                "        \"150204\": \"青山区\",\n" +
                "        \"150205\": \"石拐区\",\n" +
                "        \"150206\": \"白云鄂博矿区\",\n" +
                "        \"150207\": \"九原区\",\n" +
                "        \"150221\": \"土默特右旗\",\n" +
                "        \"150222\": \"固阳县\",\n" +
                "        \"150223\": \"达尔罕茂明安联合旗\"\n" +
                "    },\n" +
                "    \"150300\": {\n" +
                "        \"150302\": \"海勃湾区\",\n" +
                "        \"150303\": \"海南区\",\n" +
                "        \"150304\": \"乌达区\"\n" +
                "    },\n" +
                "    \"150400\": {\n" +
                "        \"150402\": \"红山区\",\n" +
                "        \"150403\": \"元宝山区\",\n" +
                "        \"150404\": \"松山区\",\n" +
                "        \"150421\": \"阿鲁科尔沁旗\",\n" +
                "        \"150422\": \"巴林左旗\",\n" +
                "        \"150423\": \"巴林右旗\",\n" +
                "        \"150424\": \"林西县\",\n" +
                "        \"150425\": \"克什克腾旗\",\n" +
                "        \"150426\": \"翁牛特旗\",\n" +
                "        \"150428\": \"喀喇沁旗\",\n" +
                "        \"150429\": \"宁城县\",\n" +
                "        \"150430\": \"敖汉旗\"\n" +
                "    },\n" +
                "    \"150500\": {\n" +
                "        \"150502\": \"科尔沁区\",\n" +
                "        \"150521\": \"科尔沁左翼中旗\",\n" +
                "        \"150522\": \"科尔沁左翼后旗\",\n" +
                "        \"150523\": \"开鲁县\",\n" +
                "        \"150524\": \"库伦旗\",\n" +
                "        \"150525\": \"奈曼旗\",\n" +
                "        \"150526\": \"扎鲁特旗\",\n" +
                "        \"150581\": \"霍林郭勒市\"\n" +
                "    },\n" +
                "    \"150600\": {\n" +
                "        \"150602\": \"东胜区\",\n" +
                "        \"150603\": \"康巴什区\",\n" +
                "        \"150621\": \"达拉特旗\",\n" +
                "        \"150622\": \"准格尔旗\",\n" +
                "        \"150623\": \"鄂托克前旗\",\n" +
                "        \"150624\": \"鄂托克旗\",\n" +
                "        \"150625\": \"杭锦旗\",\n" +
                "        \"150626\": \"乌审旗\",\n" +
                "        \"150627\": \"伊金霍洛旗\"\n" +
                "    },\n" +
                "    \"150700\": {\n" +
                "        \"150702\": \"海拉尔区\",\n" +
                "        \"150703\": \"扎赉诺尔区\",\n" +
                "        \"150721\": \"阿荣旗\",\n" +
                "        \"150722\": \"莫力达瓦达斡尔族自治旗\",\n" +
                "        \"150723\": \"鄂伦春自治旗\",\n" +
                "        \"150724\": \"鄂温克族自治旗\",\n" +
                "        \"150725\": \"陈巴尔虎旗\",\n" +
                "        \"150726\": \"新巴尔虎左旗\",\n" +
                "        \"150727\": \"新巴尔虎右旗\",\n" +
                "        \"150781\": \"满洲里市\",\n" +
                "        \"150782\": \"牙克石市\",\n" +
                "        \"150783\": \"扎兰屯市\",\n" +
                "        \"150784\": \"额尔古纳市\",\n" +
                "        \"150785\": \"根河市\"\n" +
                "    },\n" +
                "    \"150800\": {\n" +
                "        \"150802\": \"临河区\",\n" +
                "        \"150821\": \"五原县\",\n" +
                "        \"150822\": \"磴口县\",\n" +
                "        \"150823\": \"乌拉特前旗\",\n" +
                "        \"150824\": \"乌拉特中旗\",\n" +
                "        \"150825\": \"乌拉特后旗\",\n" +
                "        \"150826\": \"杭锦后旗\"\n" +
                "    },\n" +
                "    \"150900\": {\n" +
                "        \"150902\": \"集宁区\",\n" +
                "        \"150921\": \"卓资县\",\n" +
                "        \"150922\": \"化德县\",\n" +
                "        \"150923\": \"商都县\",\n" +
                "        \"150924\": \"兴和县\",\n" +
                "        \"150925\": \"凉城县\",\n" +
                "        \"150926\": \"察哈尔右翼前旗\",\n" +
                "        \"150927\": \"察哈尔右翼中旗\",\n" +
                "        \"150928\": \"察哈尔右翼后旗\",\n" +
                "        \"150929\": \"四子王旗\",\n" +
                "        \"150981\": \"丰镇市\"\n" +
                "    },\n" +
                "    \"152200\": {\n" +
                "        \"152201\": \"乌兰浩特市\",\n" +
                "        \"152202\": \"阿尔山市\",\n" +
                "        \"152221\": \"科尔沁右翼前旗\",\n" +
                "        \"152222\": \"科尔沁右翼中旗\",\n" +
                "        \"152223\": \"扎赉特旗\",\n" +
                "        \"152224\": \"突泉县\"\n" +
                "    },\n" +
                "    \"152500\": {\n" +
                "        \"152501\": \"二连浩特市\",\n" +
                "        \"152502\": \"锡林浩特市\",\n" +
                "        \"152522\": \"阿巴嘎旗\",\n" +
                "        \"152523\": \"苏尼特左旗\",\n" +
                "        \"152524\": \"苏尼特右旗\",\n" +
                "        \"152525\": \"东乌珠穆沁旗\",\n" +
                "        \"152526\": \"西乌珠穆沁旗\",\n" +
                "        \"152527\": \"太仆寺旗\",\n" +
                "        \"152528\": \"镶黄旗\",\n" +
                "        \"152529\": \"正镶白旗\",\n" +
                "        \"152530\": \"正蓝旗\",\n" +
                "        \"152531\": \"多伦县\"\n" +
                "    },\n" +
                "    \"152900\": {\n" +
                "        \"152921\": \"阿拉善左旗\",\n" +
                "        \"152922\": \"阿拉善右旗\",\n" +
                "        \"152923\": \"额济纳旗\"\n" +
                "    },\n" +
                "    \"210000\": {\n" +
                "        \"210100\": \"沈阳市\",\n" +
                "        \"210200\": \"大连市\",\n" +
                "        \"210300\": \"鞍山市\",\n" +
                "        \"210400\": \"抚顺市\",\n" +
                "        \"210500\": \"本溪市\",\n" +
                "        \"210600\": \"丹东市\",\n" +
                "        \"210700\": \"锦州市\",\n" +
                "        \"210800\": \"营口市\",\n" +
                "        \"210900\": \"阜新市\",\n" +
                "        \"211000\": \"辽阳市\",\n" +
                "        \"211100\": \"盘锦市\",\n" +
                "        \"211200\": \"铁岭市\",\n" +
                "        \"211300\": \"朝阳市\",\n" +
                "        \"211400\": \"葫芦岛市\"\n" +
                "    },\n" +
                "    \"210100\": {\n" +
                "        \"210102\": \"和平区\",\n" +
                "        \"210103\": \"沈河区\",\n" +
                "        \"210104\": \"大东区\",\n" +
                "        \"210105\": \"皇姑区\",\n" +
                "        \"210106\": \"铁西区\",\n" +
                "        \"210111\": \"苏家屯区\",\n" +
                "        \"210112\": \"浑南区\",\n" +
                "        \"210113\": \"沈北新区\",\n" +
                "        \"210114\": \"于洪区\",\n" +
                "        \"210115\": \"辽中区\",\n" +
                "        \"210123\": \"康平县\",\n" +
                "        \"210124\": \"法库县\",\n" +
                "        \"210181\": \"新民市\"\n" +
                "    },\n" +
                "    \"210200\": {\n" +
                "        \"210202\": \"中山区\",\n" +
                "        \"210203\": \"西岗区\",\n" +
                "        \"210204\": \"沙河口区\",\n" +
                "        \"210211\": \"甘井子区\",\n" +
                "        \"210212\": \"旅顺口区\",\n" +
                "        \"210213\": \"金州区\",\n" +
                "        \"210214\": \"普兰店区\",\n" +
                "        \"210224\": \"长海县\",\n" +
                "        \"210281\": \"瓦房店市\",\n" +
                "        \"210283\": \"庄河市\"\n" +
                "    },\n" +
                "    \"210300\": {\n" +
                "        \"210302\": \"铁东区\",\n" +
                "        \"210303\": \"铁西区\",\n" +
                "        \"210304\": \"立山区\",\n" +
                "        \"210311\": \"千山区\",\n" +
                "        \"210321\": \"台安县\",\n" +
                "        \"210323\": \"岫岩满族自治县\",\n" +
                "        \"210381\": \"海城市\"\n" +
                "    },\n" +
                "    \"210400\": {\n" +
                "        \"210402\": \"新抚区\",\n" +
                "        \"210403\": \"东洲区\",\n" +
                "        \"210404\": \"望花区\",\n" +
                "        \"210411\": \"顺城区\",\n" +
                "        \"210421\": \"抚顺县\",\n" +
                "        \"210422\": \"新宾满族自治县\",\n" +
                "        \"210423\": \"清原满族自治县\"\n" +
                "    },\n" +
                "    \"210500\": {\n" +
                "        \"210502\": \"平山区\",\n" +
                "        \"210503\": \"溪湖区\",\n" +
                "        \"210504\": \"明山区\",\n" +
                "        \"210505\": \"南芬区\",\n" +
                "        \"210521\": \"本溪满族自治县\",\n" +
                "        \"210522\": \"桓仁满族自治县\"\n" +
                "    },\n" +
                "    \"210600\": {\n" +
                "        \"210602\": \"元宝区\",\n" +
                "        \"210603\": \"振兴区\",\n" +
                "        \"210604\": \"振安区\",\n" +
                "        \"210624\": \"宽甸满族自治县\",\n" +
                "        \"210681\": \"东港市\",\n" +
                "        \"210682\": \"凤城市\"\n" +
                "    },\n" +
                "    \"210700\": {\n" +
                "        \"210702\": \"古塔区\",\n" +
                "        \"210703\": \"凌河区\",\n" +
                "        \"210711\": \"太和区\",\n" +
                "        \"210726\": \"黑山县\",\n" +
                "        \"210727\": \"义县\",\n" +
                "        \"210781\": \"凌海市\",\n" +
                "        \"210782\": \"北镇市\"\n" +
                "    },\n" +
                "    \"210800\": {\n" +
                "        \"210802\": \"站前区\",\n" +
                "        \"210803\": \"西市区\",\n" +
                "        \"210804\": \"鲅鱼圈区\",\n" +
                "        \"210811\": \"老边区\",\n" +
                "        \"210881\": \"盖州市\",\n" +
                "        \"210882\": \"大石桥市\"\n" +
                "    },\n" +
                "    \"210900\": {\n" +
                "        \"210902\": \"海州区\",\n" +
                "        \"210903\": \"新邱区\",\n" +
                "        \"210904\": \"太平区\",\n" +
                "        \"210905\": \"清河门区\",\n" +
                "        \"210911\": \"细河区\",\n" +
                "        \"210921\": \"阜新蒙古族自治县\",\n" +
                "        \"210922\": \"彰武县\"\n" +
                "    },\n" +
                "    \"211000\": {\n" +
                "        \"211002\": \"白塔区\",\n" +
                "        \"211003\": \"文圣区\",\n" +
                "        \"211004\": \"宏伟区\",\n" +
                "        \"211005\": \"弓长岭区\",\n" +
                "        \"211011\": \"太子河区\",\n" +
                "        \"211021\": \"辽阳县\",\n" +
                "        \"211081\": \"灯塔市\"\n" +
                "    },\n" +
                "    \"211100\": {\n" +
                "        \"211102\": \"双台子区\",\n" +
                "        \"211103\": \"兴隆台区\",\n" +
                "        \"211104\": \"大洼区\",\n" +
                "        \"211122\": \"盘山县\"\n" +
                "    },\n" +
                "    \"211200\": {\n" +
                "        \"211202\": \"银州区\",\n" +
                "        \"211204\": \"清河区\",\n" +
                "        \"211221\": \"铁岭县\",\n" +
                "        \"211223\": \"西丰县\",\n" +
                "        \"211224\": \"昌图县\",\n" +
                "        \"211281\": \"调兵山市\",\n" +
                "        \"211282\": \"开原市\"\n" +
                "    },\n" +
                "    \"211300\": {\n" +
                "        \"211302\": \"双塔区\",\n" +
                "        \"211303\": \"龙城区\",\n" +
                "        \"211321\": \"朝阳县\",\n" +
                "        \"211322\": \"建平县\",\n" +
                "        \"211324\": \"喀喇沁左翼蒙古族自治县\",\n" +
                "        \"211381\": \"北票市\",\n" +
                "        \"211382\": \"凌源市\"\n" +
                "    },\n" +
                "    \"211400\": {\n" +
                "        \"211402\": \"连山区\",\n" +
                "        \"211403\": \"龙港区\",\n" +
                "        \"211404\": \"南票区\",\n" +
                "        \"211421\": \"绥中县\",\n" +
                "        \"211422\": \"建昌县\",\n" +
                "        \"211481\": \"兴城市\"\n" +
                "    },\n" +
                "    \"220000\": {\n" +
                "        \"220100\": \"长春市\",\n" +
                "        \"220200\": \"吉林市\",\n" +
                "        \"220300\": \"四平市\",\n" +
                "        \"220400\": \"辽源市\",\n" +
                "        \"220500\": \"通化市\",\n" +
                "        \"220600\": \"白山市\",\n" +
                "        \"220700\": \"松原市\",\n" +
                "        \"220800\": \"白城市\",\n" +
                "        \"222400\": \"延边朝鲜族自治州\"\n" +
                "    },\n" +
                "    \"220100\": {\n" +
                "        \"220102\": \"南关区\",\n" +
                "        \"220103\": \"宽城区\",\n" +
                "        \"220104\": \"朝阳区\",\n" +
                "        \"220105\": \"二道区\",\n" +
                "        \"220106\": \"绿园区\",\n" +
                "        \"220112\": \"双阳区\",\n" +
                "        \"220113\": \"九台区\",\n" +
                "        \"220122\": \"农安县\",\n" +
                "        \"220182\": \"榆树市\",\n" +
                "        \"220183\": \"德惠市\"\n" +
                "    },\n" +
                "    \"220200\": {\n" +
                "        \"220202\": \"昌邑区\",\n" +
                "        \"220203\": \"龙潭区\",\n" +
                "        \"220204\": \"船营区\",\n" +
                "        \"220211\": \"丰满区\",\n" +
                "        \"220221\": \"永吉县\",\n" +
                "        \"220281\": \"蛟河市\",\n" +
                "        \"220282\": \"桦甸市\",\n" +
                "        \"220283\": \"舒兰市\",\n" +
                "        \"220284\": \"磐石市\"\n" +
                "    },\n" +
                "    \"220300\": {\n" +
                "        \"220302\": \"铁西区\",\n" +
                "        \"220303\": \"铁东区\",\n" +
                "        \"220322\": \"梨树县\",\n" +
                "        \"220323\": \"伊通满族自治县\",\n" +
                "        \"220381\": \"公主岭市\",\n" +
                "        \"220382\": \"双辽市\"\n" +
                "    },\n" +
                "    \"220400\": {\n" +
                "        \"220402\": \"龙山区\",\n" +
                "        \"220403\": \"西安区\",\n" +
                "        \"220421\": \"东丰县\",\n" +
                "        \"220422\": \"东辽县\"\n" +
                "    },\n" +
                "    \"220500\": {\n" +
                "        \"220502\": \"东昌区\",\n" +
                "        \"220503\": \"二道江区\",\n" +
                "        \"220521\": \"通化县\",\n" +
                "        \"220523\": \"辉南县\",\n" +
                "        \"220524\": \"柳河县\",\n" +
                "        \"220581\": \"梅河口市\",\n" +
                "        \"220582\": \"集安市\"\n" +
                "    },\n" +
                "    \"220600\": {\n" +
                "        \"220602\": \"浑江区\",\n" +
                "        \"220605\": \"江源区\",\n" +
                "        \"220621\": \"抚松县\",\n" +
                "        \"220622\": \"靖宇县\",\n" +
                "        \"220623\": \"长白朝鲜族自治县\",\n" +
                "        \"220681\": \"临江市\"\n" +
                "    },\n" +
                "    \"220700\": {\n" +
                "        \"220702\": \"宁江区\",\n" +
                "        \"220721\": \"前郭尔罗斯蒙古族自治县\",\n" +
                "        \"220722\": \"长岭县\",\n" +
                "        \"220723\": \"乾安县\",\n" +
                "        \"220781\": \"扶余市\"\n" +
                "    },\n" +
                "    \"220800\": {\n" +
                "        \"220802\": \"洮北区\",\n" +
                "        \"220821\": \"镇赉县\",\n" +
                "        \"220822\": \"通榆县\",\n" +
                "        \"220881\": \"洮南市\",\n" +
                "        \"220882\": \"大安市\"\n" +
                "    },\n" +
                "    \"222400\": {\n" +
                "        \"222401\": \"延吉市\",\n" +
                "        \"222402\": \"图们市\",\n" +
                "        \"222403\": \"敦化市\",\n" +
                "        \"222404\": \"珲春市\",\n" +
                "        \"222405\": \"龙井市\",\n" +
                "        \"222406\": \"和龙市\",\n" +
                "        \"222424\": \"汪清县\",\n" +
                "        \"222426\": \"安图县\"\n" +
                "    },\n" +
                "    \"230000\": {\n" +
                "        \"230100\": \"哈尔滨市\",\n" +
                "        \"230200\": \"齐齐哈尔市\",\n" +
                "        \"230300\": \"鸡西市\",\n" +
                "        \"230400\": \"鹤岗市\",\n" +
                "        \"230500\": \"双鸭山市\",\n" +
                "        \"230600\": \"大庆市\",\n" +
                "        \"230700\": \"伊春市\",\n" +
                "        \"230800\": \"佳木斯市\",\n" +
                "        \"230900\": \"七台河市\",\n" +
                "        \"231000\": \"牡丹江市\",\n" +
                "        \"231100\": \"黑河市\",\n" +
                "        \"231200\": \"绥化市\",\n" +
                "        \"232700\": \"大兴安岭地区\"\n" +
                "    },\n" +
                "    \"230100\": {\n" +
                "        \"230102\": \"道里区\",\n" +
                "        \"230103\": \"南岗区\",\n" +
                "        \"230104\": \"道外区\",\n" +
                "        \"230108\": \"平房区\",\n" +
                "        \"230109\": \"松北区\",\n" +
                "        \"230110\": \"香坊区\",\n" +
                "        \"230111\": \"呼兰区\",\n" +
                "        \"230112\": \"阿城区\",\n" +
                "        \"230113\": \"双城区\",\n" +
                "        \"230123\": \"依兰县\",\n" +
                "        \"230124\": \"方正县\",\n" +
                "        \"230125\": \"宾县\",\n" +
                "        \"230126\": \"巴彦县\",\n" +
                "        \"230127\": \"木兰县\",\n" +
                "        \"230128\": \"通河县\",\n" +
                "        \"230129\": \"延寿县\",\n" +
                "        \"230183\": \"尚志市\",\n" +
                "        \"230184\": \"五常市\"\n" +
                "    },\n" +
                "    \"230200\": {\n" +
                "        \"230202\": \"龙沙区\",\n" +
                "        \"230203\": \"建华区\",\n" +
                "        \"230204\": \"铁锋区\",\n" +
                "        \"230205\": \"昂昂溪区\",\n" +
                "        \"230206\": \"富拉尔基区\",\n" +
                "        \"230207\": \"碾子山区\",\n" +
                "        \"230208\": \"梅里斯达斡尔族区\",\n" +
                "        \"230221\": \"龙江县\",\n" +
                "        \"230223\": \"依安县\",\n" +
                "        \"230224\": \"泰来县\",\n" +
                "        \"230225\": \"甘南县\",\n" +
                "        \"230227\": \"富裕县\",\n" +
                "        \"230229\": \"克山县\",\n" +
                "        \"230230\": \"克东县\",\n" +
                "        \"230231\": \"拜泉县\",\n" +
                "        \"230281\": \"讷河市\"\n" +
                "    },\n" +
                "    \"230300\": {\n" +
                "        \"230302\": \"鸡冠区\",\n" +
                "        \"230303\": \"恒山区\",\n" +
                "        \"230304\": \"滴道区\",\n" +
                "        \"230305\": \"梨树区\",\n" +
                "        \"230306\": \"城子河区\",\n" +
                "        \"230307\": \"麻山区\",\n" +
                "        \"230321\": \"鸡东县\",\n" +
                "        \"230381\": \"虎林市\",\n" +
                "        \"230382\": \"密山市\"\n" +
                "    },\n" +
                "    \"230400\": {\n" +
                "        \"230402\": \"向阳区\",\n" +
                "        \"230403\": \"工农区\",\n" +
                "        \"230404\": \"南山区\",\n" +
                "        \"230405\": \"兴安区\",\n" +
                "        \"230406\": \"东山区\",\n" +
                "        \"230407\": \"兴山区\",\n" +
                "        \"230421\": \"萝北县\",\n" +
                "        \"230422\": \"绥滨县\"\n" +
                "    },\n" +
                "    \"230500\": {\n" +
                "        \"230502\": \"尖山区\",\n" +
                "        \"230503\": \"岭东区\",\n" +
                "        \"230505\": \"四方台区\",\n" +
                "        \"230506\": \"宝山区\",\n" +
                "        \"230521\": \"集贤县\",\n" +
                "        \"230522\": \"友谊县\",\n" +
                "        \"230523\": \"宝清县\",\n" +
                "        \"230524\": \"饶河县\"\n" +
                "    },\n" +
                "    \"230600\": {\n" +
                "        \"230602\": \"萨尔图区\",\n" +
                "        \"230603\": \"龙凤区\",\n" +
                "        \"230604\": \"让胡路区\",\n" +
                "        \"230605\": \"红岗区\",\n" +
                "        \"230606\": \"大同区\",\n" +
                "        \"230621\": \"肇州县\",\n" +
                "        \"230622\": \"肇源县\",\n" +
                "        \"230623\": \"林甸县\",\n" +
                "        \"230624\": \"杜尔伯特蒙古族自治县\"\n" +
                "    },\n" +
                "    \"230700\": {\n" +
                "        \"230702\": \"伊春区\",\n" +
                "        \"230703\": \"南岔区\",\n" +
                "        \"230704\": \"友好区\",\n" +
                "        \"230705\": \"西林区\",\n" +
                "        \"230706\": \"翠峦区\",\n" +
                "        \"230707\": \"新青区\",\n" +
                "        \"230708\": \"美溪区\",\n" +
                "        \"230709\": \"金山屯区\",\n" +
                "        \"230710\": \"五营区\",\n" +
                "        \"230711\": \"乌马河区\",\n" +
                "        \"230712\": \"汤旺河区\",\n" +
                "        \"230713\": \"带岭区\",\n" +
                "        \"230714\": \"乌伊岭区\",\n" +
                "        \"230715\": \"红星区\",\n" +
                "        \"230716\": \"上甘岭区\",\n" +
                "        \"230722\": \"嘉荫县\",\n" +
                "        \"230781\": \"铁力市\"\n" +
                "    },\n" +
                "    \"230800\": {\n" +
                "        \"230803\": \"向阳区\",\n" +
                "        \"230804\": \"前进区\",\n" +
                "        \"230805\": \"东风区\",\n" +
                "        \"230811\": \"郊区\",\n" +
                "        \"230822\": \"桦南县\",\n" +
                "        \"230826\": \"桦川县\",\n" +
                "        \"230828\": \"汤原县\",\n" +
                "        \"230881\": \"同江市\",\n" +
                "        \"230882\": \"富锦市\",\n" +
                "        \"230883\": \"抚远市\"\n" +
                "    },\n" +
                "    \"230900\": {\n" +
                "        \"230902\": \"新兴区\",\n" +
                "        \"230903\": \"桃山区\",\n" +
                "        \"230904\": \"茄子河区\",\n" +
                "        \"230921\": \"勃利县\"\n" +
                "    },\n" +
                "    \"231000\": {\n" +
                "        \"231002\": \"东安区\",\n" +
                "        \"231003\": \"阳明区\",\n" +
                "        \"231004\": \"爱民区\",\n" +
                "        \"231005\": \"西安区\",\n" +
                "        \"231025\": \"林口县\",\n" +
                "        \"231081\": \"绥芬河市\",\n" +
                "        \"231083\": \"海林市\",\n" +
                "        \"231084\": \"宁安市\",\n" +
                "        \"231085\": \"穆棱市\",\n" +
                "        \"231086\": \"东宁市\"\n" +
                "    },\n" +
                "    \"231100\": {\n" +
                "        \"231102\": \"爱辉区\",\n" +
                "        \"231121\": \"嫩江县\",\n" +
                "        \"231123\": \"逊克县\",\n" +
                "        \"231124\": \"孙吴县\",\n" +
                "        \"231181\": \"北安市\",\n" +
                "        \"231182\": \"五大连池市\"\n" +
                "    },\n" +
                "    \"231200\": {\n" +
                "        \"231202\": \"北林区\",\n" +
                "        \"231221\": \"望奎县\",\n" +
                "        \"231222\": \"兰西县\",\n" +
                "        \"231223\": \"青冈县\",\n" +
                "        \"231224\": \"庆安县\",\n" +
                "        \"231225\": \"明水县\",\n" +
                "        \"231226\": \"绥棱县\",\n" +
                "        \"231281\": \"安达市\",\n" +
                "        \"231282\": \"肇东市\",\n" +
                "        \"231283\": \"海伦市\"\n" +
                "    },\n" +
                "    \"232700\": {\n" +
                "        \"232721\": \"呼玛县\",\n" +
                "        \"232722\": \"塔河县\",\n" +
                "        \"232723\": \"漠河县\"\n" +
                "    },\n" +
                "    \"310000\": {\n" +
                "        \"310100\": \"市辖区\"\n" +
                "    },\n" +
                "    \"310100\": {\n" +
                "        \"310101\": \"黄浦区\",\n" +
                "        \"310104\": \"徐汇区\",\n" +
                "        \"310105\": \"长宁区\",\n" +
                "        \"310106\": \"静安区\",\n" +
                "        \"310107\": \"普陀区\",\n" +
                "        \"310109\": \"虹口区\",\n" +
                "        \"310110\": \"杨浦区\",\n" +
                "        \"310112\": \"闵行区\",\n" +
                "        \"310113\": \"宝山区\",\n" +
                "        \"310114\": \"嘉定区\",\n" +
                "        \"310115\": \"浦东新区\",\n" +
                "        \"310116\": \"金山区\",\n" +
                "        \"310117\": \"松江区\",\n" +
                "        \"310118\": \"青浦区\",\n" +
                "        \"310120\": \"奉贤区\",\n" +
                "        \"310151\": \"崇明区\"\n" +
                "    },\n" +
                "    \"320000\": {\n" +
                "        \"320100\": \"南京市\",\n" +
                "        \"320200\": \"无锡市\",\n" +
                "        \"320300\": \"徐州市\",\n" +
                "        \"320400\": \"常州市\",\n" +
                "        \"320500\": \"苏州市\",\n" +
                "        \"320600\": \"南通市\",\n" +
                "        \"320700\": \"连云港市\",\n" +
                "        \"320800\": \"淮安市\",\n" +
                "        \"320900\": \"盐城市\",\n" +
                "        \"321000\": \"扬州市\",\n" +
                "        \"321100\": \"镇江市\",\n" +
                "        \"321200\": \"泰州市\",\n" +
                "        \"321300\": \"宿迁市\"\n" +
                "    },\n" +
                "    \"320100\": {\n" +
                "        \"320102\": \"玄武区\",\n" +
                "        \"320104\": \"秦淮区\",\n" +
                "        \"320105\": \"建邺区\",\n" +
                "        \"320106\": \"鼓楼区\",\n" +
                "        \"320111\": \"浦口区\",\n" +
                "        \"320113\": \"栖霞区\",\n" +
                "        \"320114\": \"雨花台区\",\n" +
                "        \"320115\": \"江宁区\",\n" +
                "        \"320116\": \"六合区\",\n" +
                "        \"320117\": \"溧水区\",\n" +
                "        \"320118\": \"高淳区\"\n" +
                "    },\n" +
                "    \"320200\": {\n" +
                "        \"320205\": \"锡山区\",\n" +
                "        \"320206\": \"惠山区\",\n" +
                "        \"320211\": \"滨湖区\",\n" +
                "        \"320213\": \"梁溪区\",\n" +
                "        \"320214\": \"新吴区\",\n" +
                "        \"320281\": \"江阴市\",\n" +
                "        \"320282\": \"宜兴市\"\n" +
                "    },\n" +
                "    \"320300\": {\n" +
                "        \"320302\": \"鼓楼区\",\n" +
                "        \"320303\": \"云龙区\",\n" +
                "        \"320305\": \"贾汪区\",\n" +
                "        \"320311\": \"泉山区\",\n" +
                "        \"320312\": \"铜山区\",\n" +
                "        \"320321\": \"丰县\",\n" +
                "        \"320322\": \"沛县\",\n" +
                "        \"320324\": \"睢宁县\",\n" +
                "        \"320381\": \"新沂市\",\n" +
                "        \"320382\": \"邳州市\"\n" +
                "    },\n" +
                "    \"320400\": {\n" +
                "        \"320402\": \"天宁区\",\n" +
                "        \"320404\": \"钟楼区\",\n" +
                "        \"320411\": \"新北区\",\n" +
                "        \"320412\": \"武进区\",\n" +
                "        \"320413\": \"金坛区\",\n" +
                "        \"320481\": \"溧阳市\"\n" +
                "    },\n" +
                "    \"320500\": {\n" +
                "        \"320505\": \"虎丘区\",\n" +
                "        \"320506\": \"吴中区\",\n" +
                "        \"320507\": \"相城区\",\n" +
                "        \"320508\": \"姑苏区\",\n" +
                "        \"320509\": \"吴江区\",\n" +
                "        \"320581\": \"常熟市\",\n" +
                "        \"320582\": \"张家港市\",\n" +
                "        \"320583\": \"昆山市\",\n" +
                "        \"320585\": \"太仓市\"\n" +
                "    },\n" +
                "    \"320600\": {\n" +
                "        \"320602\": \"崇川区\",\n" +
                "        \"320611\": \"港闸区\",\n" +
                "        \"320612\": \"通州区\",\n" +
                "        \"320621\": \"海安县\",\n" +
                "        \"320623\": \"如东县\",\n" +
                "        \"320681\": \"启东市\",\n" +
                "        \"320682\": \"如皋市\",\n" +
                "        \"320684\": \"海门市\"\n" +
                "    },\n" +
                "    \"320700\": {\n" +
                "        \"320703\": \"连云区\",\n" +
                "        \"320706\": \"海州区\",\n" +
                "        \"320707\": \"赣榆区\",\n" +
                "        \"320722\": \"东海县\",\n" +
                "        \"320723\": \"灌云县\",\n" +
                "        \"320724\": \"灌南县\"\n" +
                "    },\n" +
                "    \"320800\": {\n" +
                "        \"320803\": \"淮安区\",\n" +
                "        \"320804\": \"淮阴区\",\n" +
                "        \"320812\": \"清江浦区\",\n" +
                "        \"320813\": \"洪泽区\",\n" +
                "        \"320826\": \"涟水县\",\n" +
                "        \"320830\": \"盱眙县\",\n" +
                "        \"320831\": \"金湖县\"\n" +
                "    },\n" +
                "    \"320900\": {\n" +
                "        \"320902\": \"亭湖区\",\n" +
                "        \"320903\": \"盐都区\",\n" +
                "        \"320904\": \"大丰区\",\n" +
                "        \"320921\": \"响水县\",\n" +
                "        \"320922\": \"滨海县\",\n" +
                "        \"320923\": \"阜宁县\",\n" +
                "        \"320924\": \"射阳县\",\n" +
                "        \"320925\": \"建湖县\",\n" +
                "        \"320981\": \"东台市\"\n" +
                "    },\n" +
                "    \"321000\": {\n" +
                "        \"321002\": \"广陵区\",\n" +
                "        \"321003\": \"邗江区\",\n" +
                "        \"321012\": \"江都区\",\n" +
                "        \"321023\": \"宝应县\",\n" +
                "        \"321081\": \"仪征市\",\n" +
                "        \"321084\": \"高邮市\"\n" +
                "    },\n" +
                "    \"321100\": {\n" +
                "        \"321102\": \"京口区\",\n" +
                "        \"321111\": \"润州区\",\n" +
                "        \"321112\": \"丹徒区\",\n" +
                "        \"321181\": \"丹阳市\",\n" +
                "        \"321182\": \"扬中市\",\n" +
                "        \"321183\": \"句容市\"\n" +
                "    },\n" +
                "    \"321200\": {\n" +
                "        \"321202\": \"海陵区\",\n" +
                "        \"321203\": \"高港区\",\n" +
                "        \"321204\": \"姜堰区\",\n" +
                "        \"321281\": \"兴化市\",\n" +
                "        \"321282\": \"靖江市\",\n" +
                "        \"321283\": \"泰兴市\"\n" +
                "    },\n" +
                "    \"321300\": {\n" +
                "        \"321302\": \"宿城区\",\n" +
                "        \"321311\": \"宿豫区\",\n" +
                "        \"321322\": \"沭阳县\",\n" +
                "        \"321323\": \"泗阳县\",\n" +
                "        \"321324\": \"泗洪县\"\n" +
                "    },\n" +
                "    \"330000\": {\n" +
                "        \"330100\": \"杭州市\",\n" +
                "        \"330200\": \"宁波市\",\n" +
                "        \"330300\": \"温州市\",\n" +
                "        \"330400\": \"嘉兴市\",\n" +
                "        \"330500\": \"湖州市\",\n" +
                "        \"330600\": \"绍兴市\",\n" +
                "        \"330700\": \"金华市\",\n" +
                "        \"330800\": \"衢州市\",\n" +
                "        \"330900\": \"舟山市\",\n" +
                "        \"331000\": \"台州市\",\n" +
                "        \"331100\": \"丽水市\"\n" +
                "    },\n" +
                "    \"330100\": {\n" +
                "        \"330102\": \"上城区\",\n" +
                "        \"330103\": \"下城区\",\n" +
                "        \"330104\": \"江干区\",\n" +
                "        \"330105\": \"拱墅区\",\n" +
                "        \"330106\": \"西湖区\",\n" +
                "        \"330108\": \"滨江区\",\n" +
                "        \"330109\": \"萧山区\",\n" +
                "        \"330110\": \"余杭区\",\n" +
                "        \"330111\": \"富阳区\",\n" +
                "        \"330122\": \"桐庐县\",\n" +
                "        \"330127\": \"淳安县\",\n" +
                "        \"330182\": \"建德市\",\n" +
                "        \"330185\": \"临安市\"\n" +
                "    },\n" +
                "    \"330200\": {\n" +
                "        \"330203\": \"海曙区\",\n" +
                "        \"330204\": \"江东区\",\n" +
                "        \"330205\": \"江北区\",\n" +
                "        \"330206\": \"北仑区\",\n" +
                "        \"330211\": \"镇海区\",\n" +
                "        \"330212\": \"鄞州区\",\n" +
                "        \"330225\": \"象山县\",\n" +
                "        \"330226\": \"宁海县\",\n" +
                "        \"330281\": \"余姚市\",\n" +
                "        \"330282\": \"慈溪市\",\n" +
                "        \"330283\": \"奉化市\"\n" +
                "    },\n" +
                "    \"330300\": {\n" +
                "        \"330302\": \"鹿城区\",\n" +
                "        \"330303\": \"龙湾区\",\n" +
                "        \"330304\": \"瓯海区\",\n" +
                "        \"330305\": \"洞头区\",\n" +
                "        \"330324\": \"永嘉县\",\n" +
                "        \"330326\": \"平阳县\",\n" +
                "        \"330327\": \"苍南县\",\n" +
                "        \"330328\": \"文成县\",\n" +
                "        \"330329\": \"泰顺县\",\n" +
                "        \"330381\": \"瑞安市\",\n" +
                "        \"330382\": \"乐清市\"\n" +
                "    },\n" +
                "    \"330400\": {\n" +
                "        \"330402\": \"南湖区\",\n" +
                "        \"330411\": \"秀洲区\",\n" +
                "        \"330421\": \"嘉善县\",\n" +
                "        \"330424\": \"海盐县\",\n" +
                "        \"330481\": \"海宁市\",\n" +
                "        \"330482\": \"平湖市\",\n" +
                "        \"330483\": \"桐乡市\"\n" +
                "    },\n" +
                "    \"330500\": {\n" +
                "        \"330502\": \"吴兴区\",\n" +
                "        \"330503\": \"南浔区\",\n" +
                "        \"330521\": \"德清县\",\n" +
                "        \"330522\": \"长兴县\",\n" +
                "        \"330523\": \"安吉县\"\n" +
                "    },\n" +
                "    \"330600\": {\n" +
                "        \"330602\": \"越城区\",\n" +
                "        \"330603\": \"柯桥区\",\n" +
                "        \"330604\": \"上虞区\",\n" +
                "        \"330624\": \"新昌县\",\n" +
                "        \"330681\": \"诸暨市\",\n" +
                "        \"330683\": \"嵊州市\"\n" +
                "    },\n" +
                "    \"330700\": {\n" +
                "        \"330702\": \"婺城区\",\n" +
                "        \"330703\": \"金东区\",\n" +
                "        \"330723\": \"武义县\",\n" +
                "        \"330726\": \"浦江县\",\n" +
                "        \"330727\": \"磐安县\",\n" +
                "        \"330781\": \"兰溪市\",\n" +
                "        \"330782\": \"义乌市\",\n" +
                "        \"330783\": \"东阳市\",\n" +
                "        \"330784\": \"永康市\"\n" +
                "    },\n" +
                "    \"330800\": {\n" +
                "        \"330802\": \"柯城区\",\n" +
                "        \"330803\": \"衢江区\",\n" +
                "        \"330822\": \"常山县\",\n" +
                "        \"330824\": \"开化县\",\n" +
                "        \"330825\": \"龙游县\",\n" +
                "        \"330881\": \"江山市\"\n" +
                "    },\n" +
                "    \"330900\": {\n" +
                "        \"330902\": \"定海区\",\n" +
                "        \"330903\": \"普陀区\",\n" +
                "        \"330921\": \"岱山县\",\n" +
                "        \"330922\": \"嵊泗县\"\n" );
        str.append(
                "    },\n" +
                "    \"331000\": {\n" +
                "        \"331002\": \"椒江区\",\n" +
                "        \"331003\": \"黄岩区\",\n" +
                "        \"331004\": \"路桥区\",\n" +
                "        \"331021\": \"玉环县\",\n" +
                "        \"331022\": \"三门县\",\n" +
                "        \"331023\": \"天台县\",\n" +
                "        \"331024\": \"仙居县\",\n" +
                "        \"331081\": \"温岭市\",\n" +
                "        \"331082\": \"临海市\"\n" +
                "    },\n" +
                "    \"331100\": {\n" +
                "        \"331102\": \"莲都区\",\n" +
                "        \"331121\": \"青田县\",\n" +
                "        \"331122\": \"缙云县\",\n" +
                "        \"331123\": \"遂昌县\",\n" +
                "        \"331124\": \"松阳县\",\n" +
                "        \"331125\": \"云和县\",\n" +
                "        \"331126\": \"庆元县\",\n" +
                "        \"331127\": \"景宁畲族自治县\",\n" +
                "        \"331181\": \"龙泉市\"\n" +
                "    },\n" +
                "    \"340000\": {\n" +
                "        \"340100\": \"合肥市\",\n" +
                "        \"340200\": \"芜湖市\",\n" +
                "        \"340300\": \"蚌埠市\",\n" +
                "        \"340400\": \"淮南市\",\n" +
                "        \"340500\": \"马鞍山市\",\n" +
                "        \"340600\": \"淮北市\",\n" +
                "        \"340700\": \"铜陵市\",\n" +
                "        \"340800\": \"安庆市\",\n" +
                "        \"341000\": \"黄山市\",\n" +
                "        \"341100\": \"滁州市\",\n" +
                "        \"341200\": \"阜阳市\",\n" +
                "        \"341300\": \"宿州市\",\n" +
                "        \"341500\": \"六安市\",\n" +
                "        \"341600\": \"亳州市\",\n" +
                "        \"341700\": \"池州市\",\n" +
                "        \"341800\": \"宣城市\"\n" +
                "    },\n" +
                "    \"340100\": {\n" +
                "        \"340102\": \"瑶海区\",\n" +
                "        \"340103\": \"庐阳区\",\n" +
                "        \"340104\": \"蜀山区\",\n" +
                "        \"340111\": \"包河区\",\n" +
                "        \"340121\": \"长丰县\",\n" +
                "        \"340122\": \"肥东县\",\n" +
                "        \"340123\": \"肥西县\",\n" +
                "        \"340124\": \"庐江县\",\n" +
                "        \"340181\": \"巢湖市\"\n" +
                "    },\n" +
                "    \"340200\": {\n" +
                "        \"340202\": \"镜湖区\",\n" +
                "        \"340203\": \"弋江区\",\n" +
                "        \"340207\": \"鸠江区\",\n" +
                "        \"340208\": \"三山区\",\n" +
                "        \"340221\": \"芜湖县\",\n" +
                "        \"340222\": \"繁昌县\",\n" +
                "        \"340223\": \"南陵县\",\n" +
                "        \"340225\": \"无为县\"\n" +
                "    },\n" +
                "    \"340300\": {\n" +
                "        \"340302\": \"龙子湖区\",\n" +
                "        \"340303\": \"蚌山区\",\n" +
                "        \"340304\": \"禹会区\",\n" +
                "        \"340311\": \"淮上区\",\n" +
                "        \"340321\": \"怀远县\",\n" +
                "        \"340322\": \"五河县\",\n" +
                "        \"340323\": \"固镇县\"\n" +
                "    },\n" +
                "    \"340400\": {\n" +
                "        \"340402\": \"大通区\",\n" +
                "        \"340403\": \"田家庵区\",\n" +
                "        \"340404\": \"谢家集区\",\n" +
                "        \"340405\": \"八公山区\",\n" +
                "        \"340406\": \"潘集区\",\n" +
                "        \"340421\": \"凤台县\",\n" +
                "        \"340422\": \"寿县\"\n" +
                "    },\n" +
                "    \"340500\": {\n" +
                "        \"340503\": \"花山区\",\n" +
                "        \"340504\": \"雨山区\",\n" +
                "        \"340506\": \"博望区\",\n" +
                "        \"340521\": \"当涂县\",\n" +
                "        \"340522\": \"含山县\",\n" +
                "        \"340523\": \"和县\"\n" +
                "    },\n" +
                "    \"340600\": {\n" +
                "        \"340602\": \"杜集区\",\n" +
                "        \"340603\": \"相山区\",\n" +
                "        \"340604\": \"烈山区\",\n" +
                "        \"340621\": \"濉溪县\"\n" +
                "    },\n" +
                "    \"340700\": {\n" +
                "        \"340705\": \"铜官区\",\n" +
                "        \"340706\": \"义安区\",\n" +
                "        \"340711\": \"郊区\",\n" +
                "        \"340722\": \"枞阳县\"\n" +
                "    },\n" +
                "    \"340800\": {\n" +
                "        \"340802\": \"迎江区\",\n" +
                "        \"340803\": \"大观区\",\n" +
                "        \"340811\": \"宜秀区\",\n" +
                "        \"340822\": \"怀宁县\",\n" +
                "        \"340824\": \"潜山县\",\n" +
                "        \"340825\": \"太湖县\",\n" +
                "        \"340826\": \"宿松县\",\n" +
                "        \"340827\": \"望江县\",\n" +
                "        \"340828\": \"岳西县\",\n" +
                "        \"340881\": \"桐城市\"\n" +
                "    },\n" +
                "    \"341000\": {\n" +
                "        \"341002\": \"屯溪区\",\n" +
                "        \"341003\": \"黄山区\",\n" +
                "        \"341004\": \"徽州区\",\n" +
                "        \"341021\": \"歙县\",\n" +
                "        \"341022\": \"休宁县\",\n" +
                "        \"341023\": \"黟县\",\n" +
                "        \"341024\": \"祁门县\"\n" +
                "    },\n" +
                "    \"341100\": {\n" +
                "        \"341102\": \"琅琊区\",\n" +
                "        \"341103\": \"南谯区\",\n" +
                "        \"341122\": \"来安县\",\n" +
                "        \"341124\": \"全椒县\",\n" +
                "        \"341125\": \"定远县\",\n" +
                "        \"341126\": \"凤阳县\",\n" +
                "        \"341181\": \"天长市\",\n" +
                "        \"341182\": \"明光市\"\n" +
                "    },\n" +
                "    \"341200\": {\n" +
                "        \"341202\": \"颍州区\",\n" +
                "        \"341203\": \"颍东区\",\n" +
                "        \"341204\": \"颍泉区\",\n" +
                "        \"341221\": \"临泉县\",\n" +
                "        \"341222\": \"太和县\",\n" +
                "        \"341225\": \"阜南县\",\n" +
                "        \"341226\": \"颍上县\",\n" +
                "        \"341282\": \"界首市\"\n" +
                "    },\n" +
                "    \"341300\": {\n" +
                "        \"341302\": \"埇桥区\",\n" +
                "        \"341321\": \"砀山县\",\n" +
                "        \"341322\": \"萧县\",\n" +
                "        \"341323\": \"灵璧县\",\n" +
                "        \"341324\": \"泗县\"\n" +
                "    },\n" +
                "    \"341500\": {\n" +
                "        \"341502\": \"金安区\",\n" +
                "        \"341503\": \"裕安区\",\n" +
                "        \"341504\": \"叶集区\",\n" +
                "        \"341522\": \"霍邱县\",\n" +
                "        \"341523\": \"舒城县\",\n" +
                "        \"341524\": \"金寨县\",\n" +
                "        \"341525\": \"霍山县\"\n" +
                "    },\n" +
                "    \"341600\": {\n" +
                "        \"341602\": \"谯城区\",\n" +
                "        \"341621\": \"涡阳县\",\n" +
                "        \"341622\": \"蒙城县\",\n" +
                "        \"341623\": \"利辛县\"\n" +
                "    },\n" +
                "    \"341700\": {\n" +
                "        \"341702\": \"贵池区\",\n" +
                "        \"341721\": \"东至县\",\n" +
                "        \"341722\": \"石台县\",\n" +
                "        \"341723\": \"青阳县\"\n" +
                "    },\n" +
                "    \"341800\": {\n" +
                "        \"341802\": \"宣州区\",\n" +
                "        \"341821\": \"郎溪县\",\n" +
                "        \"341822\": \"广德县\",\n" +
                "        \"341823\": \"泾县\",\n" +
                "        \"341824\": \"绩溪县\",\n" +
                "        \"341825\": \"旌德县\",\n" +
                "        \"341881\": \"宁国市\"\n" +
                "    },\n" +
                "    \"350000\": {\n" +
                "        \"350100\": \"福州市\",\n" +
                "        \"350200\": \"厦门市\",\n" +
                "        \"350300\": \"莆田市\",\n" +
                "        \"350400\": \"三明市\",\n" +
                "        \"350500\": \"泉州市\",\n" +
                "        \"350600\": \"漳州市\",\n" +
                "        \"350700\": \"南平市\",\n" +
                "        \"350800\": \"龙岩市\",\n" +
                "        \"350900\": \"宁德市\"\n" +
                "    },\n" +
                "    \"350100\": {\n" +
                "        \"350102\": \"鼓楼区\",\n" +
                "        \"350103\": \"台江区\",\n" +
                "        \"350104\": \"仓山区\",\n" +
                "        \"350105\": \"马尾区\",\n" +
                "        \"350111\": \"晋安区\",\n" +
                "        \"350121\": \"闽侯县\",\n" +
                "        \"350122\": \"连江县\",\n" +
                "        \"350123\": \"罗源县\",\n" +
                "        \"350124\": \"闽清县\",\n" +
                "        \"350125\": \"永泰县\",\n" +
                "        \"350128\": \"平潭县\",\n" +
                "        \"350181\": \"福清市\",\n" +
                "        \"350182\": \"长乐市\"\n" +
                "    },\n" +
                "    \"350200\": {\n" +
                "        \"350203\": \"思明区\",\n" +
                "        \"350205\": \"海沧区\",\n" +
                "        \"350206\": \"湖里区\",\n" +
                "        \"350211\": \"集美区\",\n" +
                "        \"350212\": \"同安区\",\n" +
                "        \"350213\": \"翔安区\"\n" +
                "    },\n" +
                "    \"350300\": {\n" +
                "        \"350302\": \"城厢区\",\n" +
                "        \"350303\": \"涵江区\",\n" +
                "        \"350304\": \"荔城区\",\n" +
                "        \"350305\": \"秀屿区\",\n" +
                "        \"350322\": \"仙游县\"\n" +
                "    },\n" +
                "    \"350400\": {\n" +
                "        \"350402\": \"梅列区\",\n" +
                "        \"350403\": \"三元区\",\n" +
                "        \"350421\": \"明溪县\",\n" +
                "        \"350423\": \"清流县\",\n" +
                "        \"350424\": \"宁化县\",\n" +
                "        \"350425\": \"大田县\",\n" +
                "        \"350426\": \"尤溪县\",\n" +
                "        \"350427\": \"沙县\",\n" +
                "        \"350428\": \"将乐县\",\n" +
                "        \"350429\": \"泰宁县\",\n" +
                "        \"350430\": \"建宁县\",\n" +
                "        \"350481\": \"永安市\"\n" +
                "    },\n" +
                "    \"350500\": {\n" +
                "        \"350502\": \"鲤城区\",\n" +
                "        \"350503\": \"丰泽区\",\n" +
                "        \"350504\": \"洛江区\",\n" +
                "        \"350505\": \"泉港区\",\n" +
                "        \"350521\": \"惠安县\",\n" +
                "        \"350524\": \"安溪县\",\n" +
                "        \"350525\": \"永春县\",\n" +
                "        \"350526\": \"德化县\",\n" +
                "        \"350527\": \"金门县\",\n" +
                "        \"350581\": \"石狮市\",\n" +
                "        \"350582\": \"晋江市\",\n" +
                "        \"350583\": \"南安市\"\n" +
                "    },\n" +
                "    \"350600\": {\n" +
                "        \"350602\": \"芗城区\",\n" +
                "        \"350603\": \"龙文区\",\n" +
                "        \"350622\": \"云霄县\",\n" +
                "        \"350623\": \"漳浦县\",\n" +
                "        \"350624\": \"诏安县\",\n" +
                "        \"350625\": \"长泰县\",\n" +
                "        \"350626\": \"东山县\",\n" +
                "        \"350627\": \"南靖县\",\n" +
                "        \"350628\": \"平和县\",\n" +
                "        \"350629\": \"华安县\",\n" +
                "        \"350681\": \"龙海市\"\n" +
                "    },\n" +
                "    \"350700\": {\n" +
                "        \"350702\": \"延平区\",\n" +
                "        \"350703\": \"建阳区\",\n" +
                "        \"350721\": \"顺昌县\",\n" +
                "        \"350722\": \"浦城县\",\n" +
                "        \"350723\": \"光泽县\",\n" +
                "        \"350724\": \"松溪县\",\n" +
                "        \"350725\": \"政和县\",\n" +
                "        \"350781\": \"邵武市\",\n" +
                "        \"350782\": \"武夷山市\",\n" +
                "        \"350783\": \"建瓯市\"\n" +
                "    },\n" +
                "    \"350800\": {\n" +
                "        \"350802\": \"新罗区\",\n" +
                "        \"350803\": \"永定区\",\n" +
                "        \"350821\": \"长汀县\",\n" +
                "        \"350823\": \"上杭县\",\n" +
                "        \"350824\": \"武平县\",\n" +
                "        \"350825\": \"连城县\",\n" +
                "        \"350881\": \"漳平市\"\n" +
                "    },\n" +
                "    \"350900\": {\n" +
                "        \"350902\": \"蕉城区\",\n" +
                "        \"350921\": \"霞浦县\",\n" +
                "        \"350922\": \"古田县\",\n" +
                "        \"350923\": \"屏南县\",\n" +
                "        \"350924\": \"寿宁县\",\n" +
                "        \"350925\": \"周宁县\",\n" +
                "        \"350926\": \"柘荣县\",\n" +
                "        \"350981\": \"福安市\",\n" +
                "        \"350982\": \"福鼎市\"\n" +
                "    },\n" +
                "    \"360000\": {\n" +
                "        \"360100\": \"南昌市\",\n" +
                "        \"360200\": \"景德镇市\",\n" +
                "        \"360300\": \"萍乡市\",\n" +
                "        \"360400\": \"九江市\",\n" +
                "        \"360500\": \"新余市\",\n" +
                "        \"360600\": \"鹰潭市\",\n" +
                "        \"360700\": \"赣州市\",\n" +
                "        \"360800\": \"吉安市\",\n" +
                "        \"360900\": \"宜春市\",\n" +
                "        \"361000\": \"抚州市\",\n" +
                "        \"361100\": \"上饶市\"\n" +
                "    },\n" +
                "    \"360100\": {\n" +
                "        \"360102\": \"东湖区\",\n" +
                "        \"360103\": \"西湖区\",\n" +
                "        \"360104\": \"青云谱区\",\n" +
                "        \"360105\": \"湾里区\",\n" +
                "        \"360111\": \"青山湖区\",\n" +
                "        \"360112\": \"新建区\",\n" +
                "        \"360121\": \"南昌县\",\n" +
                "        \"360123\": \"安义县\",\n" +
                "        \"360124\": \"进贤县\"\n" +
                "    },\n" +
                "    \"360200\": {\n" +
                "        \"360202\": \"昌江区\",\n" +
                "        \"360203\": \"珠山区\",\n" +
                "        \"360222\": \"浮梁县\",\n" +
                "        \"360281\": \"乐平市\"\n" +
                "    },\n" +
                "    \"360300\": {\n" +
                "        \"360302\": \"安源区\",\n" +
                "        \"360313\": \"湘东区\",\n" +
                "        \"360321\": \"莲花县\",\n" +
                "        \"360322\": \"上栗县\",\n" +
                "        \"360323\": \"芦溪县\"\n" +
                "    },\n" +
                "    \"360400\": {\n" +
                "        \"360402\": \"濂溪区\",\n" +
                "        \"360403\": \"浔阳区\",\n" +
                "        \"360421\": \"九江县\",\n" +
                "        \"360423\": \"武宁县\",\n" +
                "        \"360424\": \"修水县\",\n" +
                "        \"360425\": \"永修县\",\n" +
                "        \"360426\": \"德安县\",\n" +
                "        \"360428\": \"都昌县\",\n" +
                "        \"360429\": \"湖口县\",\n" +
                "        \"360430\": \"彭泽县\",\n" +
                "        \"360481\": \"瑞昌市\",\n" +
                "        \"360482\": \"共青城市\",\n" +
                "        \"360483\": \"庐山市\"\n" +
                "    },\n" +
                "    \"360500\": {\n" +
                "        \"360502\": \"渝水区\",\n" +
                "        \"360521\": \"分宜县\"\n" +
                "    },\n" +
                "    \"360600\": {\n" +
                "        \"360602\": \"月湖区\",\n" +
                "        \"360622\": \"余江县\",\n" +
                "        \"360681\": \"贵溪市\"\n" +
                "    },\n" +
                "    \"360700\": {\n" +
                "        \"360702\": \"章贡区\",\n" +
                "        \"360703\": \"南康区\",\n" +
                "        \"360721\": \"赣县\",\n" +
                "        \"360722\": \"信丰县\",\n" +
                "        \"360723\": \"大余县\",\n" +
                "        \"360724\": \"上犹县\",\n" +
                "        \"360725\": \"崇义县\",\n" +
                "        \"360726\": \"安远县\",\n" +
                "        \"360727\": \"龙南县\",\n" +
                "        \"360728\": \"定南县\",\n" +
                "        \"360729\": \"全南县\",\n" +
                "        \"360730\": \"宁都县\",\n" +
                "        \"360731\": \"于都县\",\n" +
                "        \"360732\": \"兴国县\",\n" +
                "        \"360733\": \"会昌县\",\n" +
                "        \"360734\": \"寻乌县\",\n" +
                "        \"360735\": \"石城县\",\n" +
                "        \"360781\": \"瑞金市\"\n" +
                "    },\n" +
                "    \"360800\": {\n" +
                "        \"360802\": \"吉州区\",\n" +
                "        \"360803\": \"青原区\",\n" +
                "        \"360821\": \"吉安县\",\n" +
                "        \"360822\": \"吉水县\",\n" +
                "        \"360823\": \"峡江县\",\n" +
                "        \"360824\": \"新干县\",\n" +
                "        \"360825\": \"永丰县\",\n" +
                "        \"360826\": \"泰和县\",\n" +
                "        \"360827\": \"遂川县\",\n" +
                "        \"360828\": \"万安县\",\n" +
                "        \"360829\": \"安福县\",\n" +
                "        \"360830\": \"永新县\",\n" +
                "        \"360881\": \"井冈山市\"\n" +
                "    },\n" +
                "    \"360900\": {\n" +
                "        \"360902\": \"袁州区\",\n" +
                "        \"360921\": \"奉新县\",\n" +
                "        \"360922\": \"万载县\",\n" +
                "        \"360923\": \"上高县\",\n" +
                "        \"360924\": \"宜丰县\",\n" +
                "        \"360925\": \"靖安县\",\n" +
                "        \"360926\": \"铜鼓县\",\n" +
                "        \"360981\": \"丰城市\",\n" +
                "        \"360982\": \"樟树市\",\n" +
                "        \"360983\": \"高安市\"\n" +
                "    },\n" +
                "    \"361000\": {\n" +
                "        \"361002\": \"临川区\",\n" +
                "        \"361021\": \"南城县\",\n" +
                "        \"361022\": \"黎川县\",\n" +
                "        \"361023\": \"南丰县\",\n" +
                "        \"361024\": \"崇仁县\",\n" +
                "        \"361025\": \"乐安县\",\n" +
                "        \"361026\": \"宜黄县\",\n" +
                "        \"361027\": \"金溪县\",\n" +
                "        \"361028\": \"资溪县\",\n" +
                "        \"361029\": \"东乡县\",\n" +
                "        \"361030\": \"广昌县\"\n" +
                "    },\n" +
                "    \"361100\": {\n" +
                "        \"361102\": \"信州区\",\n" +
                "        \"361103\": \"广丰区\",\n" +
                "        \"361121\": \"上饶县\",\n" +
                "        \"361123\": \"玉山县\",\n" +
                "        \"361124\": \"铅山县\",\n" +
                "        \"361125\": \"横峰县\",\n" +
                "        \"361126\": \"弋阳县\",\n" +
                "        \"361127\": \"余干县\",\n" +
                "        \"361128\": \"鄱阳县\",\n" +
                "        \"361129\": \"万年县\",\n" +
                "        \"361130\": \"婺源县\",\n" +
                "        \"361181\": \"德兴市\"\n" +
                "    },\n" +
                "    \"370000\": {\n" +
                "        \"370100\": \"济南市\",\n" +
                "        \"370200\": \"青岛市\",\n" +
                "        \"370300\": \"淄博市\",\n" +
                "        \"370400\": \"枣庄市\",\n" +
                "        \"370500\": \"东营市\",\n" +
                "        \"370600\": \"烟台市\",\n" +
                "        \"370700\": \"潍坊市\",\n" +
                "        \"370800\": \"济宁市\",\n" +
                "        \"370900\": \"泰安市\",\n" +
                "        \"371000\": \"威海市\",\n" +
                "        \"371100\": \"日照市\",\n" +
                "        \"371200\": \"莱芜市\",\n" +
                "        \"371300\": \"临沂市\",\n" +
                "        \"371400\": \"德州市\",\n" +
                "        \"371500\": \"聊城市\",\n" +
                "        \"371600\": \"滨州市\",\n" +
                "        \"371700\": \"菏泽市\"\n" +
                "    },\n" +
                "    \"370100\": {\n" +
                "        \"370102\": \"历下区\",\n" +
                "        \"370103\": \"市中区\",\n" +
                "        \"370104\": \"槐荫区\",\n" +
                "        \"370105\": \"天桥区\",\n" +
                "        \"370112\": \"历城区\",\n" +
                "        \"370113\": \"长清区\",\n" +
                "        \"370124\": \"平阴县\",\n" +
                "        \"370125\": \"济阳县\",\n" +
                "        \"370126\": \"商河县\",\n" +
                "        \"370181\": \"章丘市\"\n" +
                "    },\n" +
                "    \"370200\": {\n" +
                "        \"370202\": \"市南区\",\n" +
                "        \"370203\": \"市北区\",\n" +
                "        \"370211\": \"黄岛区\",\n" +
                "        \"370212\": \"崂山区\",\n" +
                "        \"370213\": \"李沧区\",\n" +
                "        \"370214\": \"城阳区\",\n" +
                "        \"370281\": \"胶州市\",\n" +
                "        \"370282\": \"即墨市\",\n" +
                "        \"370283\": \"平度市\",\n" +
                "        \"370285\": \"莱西市\"\n" +
                "    },\n" +
                "    \"370300\": {\n" +
                "        \"370302\": \"淄川区\",\n" +
                "        \"370303\": \"张店区\",\n" +
                "        \"370304\": \"博山区\",\n" +
                "        \"370305\": \"临淄区\",\n" +
                "        \"370306\": \"周村区\",\n" +
                "        \"370321\": \"桓台县\",\n" +
                "        \"370322\": \"高青县\",\n" +
                "        \"370323\": \"沂源县\"\n" +
                "    },\n" +
                "    \"370400\": {\n" +
                "        \"370402\": \"市中区\",\n" +
                "        \"370403\": \"薛城区\",\n" +
                "        \"370404\": \"峄城区\",\n" +
                "        \"370405\": \"台儿庄区\",\n" +
                "        \"370406\": \"山亭区\",\n" +
                "        \"370481\": \"滕州市\"\n" +
                "    },\n" +
                "    \"370500\": {\n" +
                "        \"370502\": \"东营区\",\n" +
                "        \"370503\": \"河口区\",\n" +
                "        \"370505\": \"垦利区\",\n" +
                "        \"370522\": \"利津县\",\n" +
                "        \"370523\": \"广饶县\"\n" +
                "    },\n" +
                "    \"370600\": {\n" +
                "        \"370602\": \"芝罘区\",\n" +
                "        \"370611\": \"福山区\",\n" +
                "        \"370612\": \"牟平区\",\n" +
                "        \"370613\": \"莱山区\",\n" +
                "        \"370634\": \"长岛县\",\n" +
                "        \"370681\": \"龙口市\",\n" +
                "        \"370682\": \"莱阳市\",\n" +
                "        \"370683\": \"莱州市\",\n" +
                "        \"370684\": \"蓬莱市\",\n" +
                "        \"370685\": \"招远市\",\n" +
                "        \"370686\": \"栖霞市\",\n" +
                "        \"370687\": \"海阳市\"\n" +
                "    },\n" +
                "    \"370700\": {\n" +
                "        \"370702\": \"潍城区\",\n" +
                "        \"370703\": \"寒亭区\",\n" +
                "        \"370704\": \"坊子区\",\n" +
                "        \"370705\": \"奎文区\",\n" +
                "        \"370724\": \"临朐县\",\n" +
                "        \"370725\": \"昌乐县\",\n" +
                "        \"370781\": \"青州市\",\n" +
                "        \"370782\": \"诸城市\",\n" +
                "        \"370783\": \"寿光市\",\n" +
                "        \"370784\": \"安丘市\",\n" +
                "        \"370785\": \"高密市\",\n" +
                "        \"370786\": \"昌邑市\"\n" +
                "    },\n" +
                "    \"370800\": {\n" +
                "        \"370811\": \"任城区\",\n" +
                "        \"370812\": \"兖州区\",\n" +
                "        \"370826\": \"微山县\",\n" +
                "        \"370827\": \"鱼台县\",\n" +
                "        \"370828\": \"金乡县\",\n" +
                "        \"370829\": \"嘉祥县\",\n" +
                "        \"370830\": \"汶上县\",\n" +
                "        \"370831\": \"泗水县\",\n" +
                "        \"370832\": \"梁山县\",\n" +
                "        \"370881\": \"曲阜市\",\n" +
                "        \"370883\": \"邹城市\"\n" +
                "    },\n" +
                "    \"370900\": {\n" +
                "        \"370902\": \"泰山区\",\n" +
                "        \"370911\": \"岱岳区\",\n" +
                "        \"370921\": \"宁阳县\",\n" +
                "        \"370923\": \"东平县\",\n" +
                "        \"370982\": \"新泰市\",\n" +
                "        \"370983\": \"肥城市\"\n" +
                "    },\n" +
                "    \"371000\": {\n" +
                "        \"371002\": \"环翠区\",\n" +
                "        \"371003\": \"文登区\",\n" +
                "        \"371082\": \"荣成市\",\n" +
                "        \"371083\": \"乳山市\"\n" +
                "    },\n" +
                "    \"371100\": {\n" +
                "        \"371102\": \"东港区\",\n" +
                "        \"371103\": \"岚山区\",\n" +
                "        \"371121\": \"五莲县\",\n" +
                "        \"371122\": \"莒县\"\n" +
                "    },\n" +
                "    \"371200\": {\n" +
                "        \"371202\": \"莱城区\",\n" +
                "        \"371203\": \"钢城区\"\n" +
                "    },\n" +
                "    \"371300\": {\n" +
                "        \"371302\": \"兰山区\",\n" +
                "        \"371311\": \"罗庄区\",\n" +
                "        \"371312\": \"河东区\",\n" +
                "        \"371321\": \"沂南县\",\n" +
                "        \"371322\": \"郯城县\",\n" +
                "        \"371323\": \"沂水县\",\n" +
                "        \"371324\": \"兰陵县\",\n" +
                "        \"371325\": \"费县\",\n" +
                "        \"371326\": \"平邑县\",\n" +
                "        \"371327\": \"莒南县\",\n" +
                "        \"371328\": \"蒙阴县\",\n" +
                "        \"371329\": \"临沭县\"\n" +
                "    },\n" +
                "    \"371400\": {\n" +
                "        \"371402\": \"德城区\",\n" +
                "        \"371403\": \"陵城区\",\n" +
                "        \"371422\": \"宁津县\",\n" +
                "        \"371423\": \"庆云县\",\n" +
                "        \"371424\": \"临邑县\",\n" +
                "        \"371425\": \"齐河县\",\n" +
                "        \"371426\": \"平原县\",\n" +
                "        \"371427\": \"夏津县\",\n" +
                "        \"371428\": \"武城县\",\n" +
                "        \"371481\": \"乐陵市\",\n" +
                "        \"371482\": \"禹城市\"\n" +
                "    },\n" +
                "    \"371500\": {\n" +
                "        \"371502\": \"东昌府区\",\n" +
                "        \"371521\": \"阳谷县\",\n" +
                "        \"371522\": \"莘县\",\n" +
                "        \"371523\": \"茌平县\",\n" +
                "        \"371524\": \"东阿县\",\n" +
                "        \"371525\": \"冠县\",\n" +
                "        \"371526\": \"高唐县\",\n" +
                "        \"371581\": \"临清市\"\n" +
                "    },\n" +
                "    \"371600\": {\n" +
                "        \"371602\": \"滨城区\",\n" +
                "        \"371603\": \"沾化区\",\n" +
                "        \"371621\": \"惠民县\",\n" +
                "        \"371622\": \"阳信县\",\n" +
                "        \"371623\": \"无棣县\",\n" +
                "        \"371625\": \"博兴县\",\n" +
                "        \"371626\": \"邹平县\"\n" +
                "    },\n" +
                "    \"371700\": {\n" +
                "        \"371702\": \"牡丹区\",\n" +
                "        \"371703\": \"定陶区\",\n" +
                "        \"371721\": \"曹县\",\n" +
                "        \"371722\": \"单县\",\n" +
                "        \"371723\": \"成武县\",\n" +
                "        \"371724\": \"巨野县\",\n" +
                "        \"371725\": \"郓城县\",\n" +
                "        \"371726\": \"鄄城县\",\n" +
                "        \"371728\": \"东明县\"\n" +
                "    },\n" +
                "    \"410000\": {\n" +
                "        \"410100\": \"郑州市\",\n" +
                "        \"410200\": \"开封市\",\n" +
                "        \"410300\": \"洛阳市\",\n" +
                "        \"410400\": \"平顶山市\",\n" +
                "        \"410500\": \"安阳市\",\n" +
                "        \"410600\": \"鹤壁市\",\n" +
                "        \"410700\": \"新乡市\",\n" +
                "        \"410800\": \"焦作市\",\n" +
                "        \"410900\": \"濮阳市\",\n" +
                "        \"411000\": \"许昌市\",\n" +
                "        \"411100\": \"漯河市\",\n" +
                "        \"411200\": \"三门峡市\",\n" +
                "        \"411300\": \"南阳市\",\n" +
                "        \"411400\": \"商丘市\",\n" +
                "        \"411500\": \"信阳市\",\n" +
                "        \"411600\": \"周口市\",\n" +
                "        \"411700\": \"驻马店市\",\n" +
                "        \"419001\": \"济源市\"\n" +
                "    },\n" +
                "    \"410100\": {\n" +
                "        \"410102\": \"中原区\",\n" +
                "        \"410103\": \"二七区\",\n" +
                "        \"410104\": \"管城回族区\",\n" +
                "        \"410105\": \"金水区\",\n" +
                "        \"410106\": \"上街区\",\n" +
                "        \"410108\": \"惠济区\",\n" +
                "        \"410122\": \"中牟县\",\n" +
                "        \"410181\": \"巩义市\",\n" +
                "        \"410182\": \"荥阳市\",\n" +
                "        \"410183\": \"新密市\",\n" +
                "        \"410184\": \"新郑市\",\n" +
                "        \"410185\": \"登封市\"\n" +
                "    },\n" +
                "    \"410200\": {\n" +
                "        \"410202\": \"龙亭区\",\n" +
                "        \"410203\": \"顺河回族区\",\n" +
                "        \"410204\": \"鼓楼区\",\n" +
                "        \"410205\": \"禹王台区\",\n" +
                "        \"410211\": \"金明区\",\n" +
                "        \"410212\": \"祥符区\",\n" +
                "        \"410221\": \"杞县\",\n" +
                "        \"410222\": \"通许县\",\n" +
                "        \"410223\": \"尉氏县\",\n" +
                "        \"410225\": \"兰考县\"\n" +
                "    },\n" +
                "    \"410300\": {\n" +
                "        \"410302\": \"老城区\",\n" +
                "        \"410303\": \"西工区\",\n" +
                "        \"410304\": \"瀍河回族区\",\n" +
                "        \"410305\": \"涧西区\",\n" +
                "        \"410306\": \"吉利区\",\n" +
                "        \"410311\": \"洛龙区\",\n" +
                "        \"410322\": \"孟津县\",\n" +
                "        \"410323\": \"新安县\",\n" +
                "        \"410324\": \"栾川县\",\n" +
                "        \"410325\": \"嵩县\",\n" +
                "        \"410326\": \"汝阳县\",\n" +
                "        \"410327\": \"宜阳县\",\n" +
                "        \"410328\": \"洛宁县\",\n" +
                "        \"410329\": \"伊川县\",\n" +
                "        \"410381\": \"偃师市\"\n" +
                "    },\n" +
                "    \"410400\": {\n" +
                "        \"410402\": \"新华区\",\n" +
                "        \"410403\": \"卫东区\",\n" +
                "        \"410404\": \"石龙区\",\n" +
                "        \"410411\": \"湛河区\",\n" +
                "        \"410421\": \"宝丰县\",\n" +
                "        \"410422\": \"叶县\",\n" +
                "        \"410423\": \"鲁山县\",\n" +
                "        \"410425\": \"郏县\",\n" +
                "        \"410481\": \"舞钢市\",\n" +
                "        \"410482\": \"汝州市\"\n" +
                "    },\n" +
                "    \"410500\": {\n" +
                "        \"410502\": \"文峰区\",\n" +
                "        \"410503\": \"北关区\",\n" +
                "        \"410505\": \"殷都区\",\n" +
                "        \"410506\": \"龙安区\",\n" +
                "        \"410522\": \"安阳县\",\n" +
                "        \"410523\": \"汤阴县\",\n" +
                "        \"410526\": \"滑县\",\n" +
                "        \"410527\": \"内黄县\",\n" +
                "        \"410581\": \"林州市\"\n" +
                "    },\n" +
                "    \"410600\": {\n" +
                "        \"410602\": \"鹤山区\",\n" +
                "        \"410603\": \"山城区\",\n" +
                "        \"410611\": \"淇滨区\",\n" +
                "        \"410621\": \"浚县\",\n" +
                "        \"410622\": \"淇县\"\n" +
                "    },\n" +
                "    \"410700\": {\n" +
                "        \"410702\": \"红旗区\",\n" +
                "        \"410703\": \"卫滨区\",\n" +
                "        \"410704\": \"凤泉区\",\n" +
                "        \"410711\": \"牧野区\",\n" +
                "        \"410721\": \"新乡县\",\n" +
                "        \"410724\": \"获嘉县\",\n" +
                "        \"410725\": \"原阳县\",\n" +
                "        \"410726\": \"延津县\",\n" +
                "        \"410727\": \"封丘县\",\n" +
                "        \"410728\": \"长垣县\",\n" +
                "        \"410781\": \"卫辉市\",\n" +
                "        \"410782\": \"辉县市\"\n" +
                "    },\n" +
                "    \"410800\": {\n" +
                "        \"410802\": \"解放区\",\n" +
                "        \"410803\": \"中站区\",\n" +
                "        \"410804\": \"马村区\",\n" +
                "        \"410811\": \"山阳区\",\n" +
                "        \"410821\": \"修武县\",\n" +
                "        \"410822\": \"博爱县\",\n" +
                "        \"410823\": \"武陟县\",\n" +
                "        \"410825\": \"温县\",\n" +
                "        \"410882\": \"沁阳市\",\n" +
                "        \"410883\": \"孟州市\"\n" +
                "    },\n" +
                "    \"410900\": {\n" +
                "        \"410902\": \"华龙区\",\n" +
                "        \"410922\": \"清丰县\",\n" +
                "        \"410923\": \"南乐县\",\n" +
                "        \"410926\": \"范县\",\n" +
                "        \"410927\": \"台前县\",\n" +
                "        \"410928\": \"濮阳县\"\n" +
                "    },\n" +
                "    \"411000\": {\n" +
                "        \"411002\": \"魏都区\",\n" +
                "        \"411023\": \"许昌县\",\n" +
                "        \"411024\": \"鄢陵县\",\n" +
                "        \"411025\": \"襄城县\",\n" +
                "        \"411081\": \"禹州市\",\n" +
                "        \"411082\": \"长葛市\"\n" +
                "    },\n" +
                "    \"411100\": {\n" +
                "        \"411102\": \"源汇区\",\n" +
                "        \"411103\": \"郾城区\",\n" +
                "        \"411104\": \"召陵区\",\n" +
                "        \"411121\": \"舞阳县\",\n" +
                "        \"411122\": \"临颍县\"\n" +
                "    },\n" +
                "    \"411200\": {\n" +
                "        \"411202\": \"湖滨区\",\n" +
                "        \"411203\": \"陕州区\",\n" +
                "        \"411221\": \"渑池县\",\n" +
                "        \"411224\": \"卢氏县\",\n" +
                "        \"411281\": \"义马市\",\n" +
                "        \"411282\": \"灵宝市\"\n" +
                "    },\n" +
                "    \"411300\": {\n" +
                "        \"411302\": \"宛城区\",\n" +
                "        \"411303\": \"卧龙区\",\n" +
                "        \"411321\": \"南召县\",\n" +
                "        \"411322\": \"方城县\",\n" +
                "        \"411323\": \"西峡县\",\n" +
                "        \"411324\": \"镇平县\",\n" +
                "        \"411325\": \"内乡县\",\n" +
                "        \"411326\": \"淅川县\",\n" +
                "        \"411327\": \"社旗县\",\n" +
                "        \"411328\": \"唐河县\",\n" +
                "        \"411329\": \"新野县\",\n" +
                "        \"411330\": \"桐柏县\",\n" +
                "        \"411381\": \"邓州市\"\n" +
                "    },\n" +
                "    \"411400\": {\n" +
                "        \"411402\": \"梁园区\",\n" +
                "        \"411403\": \"睢阳区\",\n" +
                "        \"411421\": \"民权县\",\n" +
                "        \"411422\": \"睢县\",\n" +
                "        \"411423\": \"宁陵县\",\n" +
                "        \"411424\": \"柘城县\",\n" +
                "        \"411425\": \"虞城县\",\n" +
                "        \"411426\": \"夏邑县\",\n" +
                "        \"411481\": \"永城市\"\n" +
                "    },\n" +
                "    \"411500\": {\n" +
                "        \"411502\": \"浉河区\",\n" +
                "        \"411503\": \"平桥区\",\n" +
                "        \"411521\": \"罗山县\",\n" +
                "        \"411522\": \"光山县\",\n" +
                "        \"411523\": \"新县\",\n" +
                "        \"411524\": \"商城县\",\n" +
                "        \"411525\": \"固始县\",\n" +
                "        \"411526\": \"潢川县\",\n" +
                "        \"411527\": \"淮滨县\",\n" +
                "        \"411528\": \"息县\"\n" +
                "    },\n" +
                "    \"411600\": {\n" +
                "        \"411602\": \"川汇区\",\n" +
                "        \"411621\": \"扶沟县\",\n" +
                "        \"411622\": \"西华县\",\n" +
                "        \"411623\": \"商水县\",\n" +
                "        \"411624\": \"沈丘县\",\n" +
                "        \"411625\": \"郸城县\",\n" +
                "        \"411626\": \"淮阳县\",\n" +
                "        \"411627\": \"太康县\",\n" +
                "        \"411628\": \"鹿邑县\",\n" +
                "        \"411681\": \"项城市\"\n" +
                "    },\n" +
                "    \"411700\": {\n" +
                "        \"411702\": \"驿城区\",\n" +
                "        \"411721\": \"西平县\",\n" +
                "        \"411722\": \"上蔡县\",\n" +
                "        \"411723\": \"平舆县\",\n" +
                "        \"411724\": \"正阳县\",\n" +
                "        \"411725\": \"确山县\",\n" +
                "        \"411726\": \"泌阳县\",\n" +
                "        \"411727\": \"汝南县\",\n" +
                "        \"411728\": \"遂平县\",\n" +
                "        \"411729\": \"新蔡县\"\n" +
                "    },\n" +
                "    \"420000\": {\n" +
                "        \"420100\": \"武汉市\",\n" +
                "        \"420200\": \"黄石市\",\n" +
                "        \"420300\": \"十堰市\",\n" +
                "        \"420500\": \"宜昌市\",\n" +
                "        \"420600\": \"襄阳市\",\n" +
                "        \"420700\": \"鄂州市\",\n" +
                "        \"420800\": \"荆门市\",\n" +
                "        \"420900\": \"孝感市\",\n" +
                "        \"421000\": \"荆州市\",\n" +
                "        \"421100\": \"黄冈市\",\n" +
                "        \"421200\": \"咸宁市\",\n" +
                "        \"421300\": \"随州市\",\n" +
                "        \"422800\": \"恩施土家族苗族自治州\",\n" +
                "        \"429004\": \"仙桃市\",\n" +
                "        \"429005\": \"潜江市\",\n" +
                "        \"429006\": \"天门市\",\n" +
                "        \"429021\": \"神农架林区\"\n" +
                "    },\n" +
                "    \"420100\": {\n" +
                "        \"420102\": \"江岸区\",\n" +
                "        \"420103\": \"江汉区\",\n" +
                "        \"420104\": \"硚口区\",\n" +
                "        \"420105\": \"汉阳区\",\n" +
                "        \"420106\": \"武昌区\",\n" +
                "        \"420107\": \"青山区\",\n" +
                "        \"420111\": \"洪山区\",\n" +
                "        \"420112\": \"东西湖区\",\n" +
                "        \"420113\": \"汉南区\",\n" +
                "        \"420114\": \"蔡甸区\",\n" +
                "        \"420115\": \"江夏区\",\n" +
                "        \"420116\": \"黄陂区\",\n" +
                "        \"420117\": \"新洲区\"\n" +
                "    },\n" +
                "    \"420200\": {\n" +
                "        \"420202\": \"黄石港区\",\n" +
                "        \"420203\": \"西塞山区\",\n" +
                "        \"420204\": \"下陆区\",\n" +
                "        \"420205\": \"铁山区\",\n" +
                "        \"420222\": \"阳新县\",\n" +
                "        \"420281\": \"大冶市\"\n" +
                "    },\n" +
                "    \"420300\": {\n" +
                "        \"420302\": \"茅箭区\",\n" +
                "        \"420303\": \"张湾区\",\n" +
                "        \"420304\": \"郧阳区\",\n" +
                "        \"420322\": \"郧西县\",\n" +
                "        \"420323\": \"竹山县\",\n" +
                "        \"420324\": \"竹溪县\",\n" +
                "        \"420325\": \"房县\",\n" +
                "        \"420381\": \"丹江口市\"\n" +
                "    },\n" +
                "    \"420500\": {\n" +
                "        \"420502\": \"西陵区\",\n" +
                "        \"420503\": \"伍家岗区\",\n" +
                "        \"420504\": \"点军区\",\n" +
                "        \"420505\": \"猇亭区\",\n" +
                "        \"420506\": \"夷陵区\",\n" +
                "        \"420525\": \"远安县\",\n" +
                "        \"420526\": \"兴山县\",\n" +
                "        \"420527\": \"秭归县\",\n" +
                "        \"420528\": \"长阳土家族自治县\",\n" +
                "        \"420529\": \"五峰土家族自治县\",\n" +
                "        \"420581\": \"宜都市\",\n" +
                "        \"420582\": \"当阳市\",\n" +
                "        \"420583\": \"枝江市\"\n" +
                "    },\n" +
                "    \"420600\": {\n" +
                "        \"420602\": \"襄城区\",\n" +
                "        \"420606\": \"樊城区\",\n" +
                "        \"420607\": \"襄州区\",\n" +
                "        \"420624\": \"南漳县\",\n" +
                "        \"420625\": \"谷城县\",\n" +
                "        \"420626\": \"保康县\",\n" +
                "        \"420682\": \"老河口市\",\n" +
                "        \"420683\": \"枣阳市\",\n" +
                "        \"420684\": \"宜城市\"\n" +
                "    },\n" +
                "    \"420700\": {\n" +
                "        \"420702\": \"梁子湖区\",\n" +
                "        \"420703\": \"华容区\",\n" +
                "        \"420704\": \"鄂城区\"\n" +
                "    },\n" +
                "    \"420800\": {\n" +
                "        \"420802\": \"东宝区\",\n" +
                "        \"420804\": \"掇刀区\",\n" +
                "        \"420821\": \"京山县\",\n" +
                "        \"420822\": \"沙洋县\",\n" +
                "        \"420881\": \"钟祥市\"\n" +
                "    },\n" +
                "    \"420900\": {\n" +
                "        \"420902\": \"孝南区\",\n" +
                "        \"420921\": \"孝昌县\",\n" +
                "        \"420922\": \"大悟县\",\n" +
                "        \"420923\": \"云梦县\",\n" +
                "        \"420981\": \"应城市\",\n" +
                "        \"420982\": \"安陆市\",\n" +
                "        \"420984\": \"汉川市\"\n" +
                "    },\n" +
                "    \"421000\": {\n" +
                "        \"421002\": \"沙市区\",\n" +
                "        \"421003\": \"荆州区\",\n" +
                "        \"421022\": \"公安县\",\n" +
                "        \"421023\": \"监利县\",\n" +
                "        \"421024\": \"江陵县\",\n" +
                "        \"421081\": \"石首市\",\n" +
                "        \"421083\": \"洪湖市\",\n" +
                "        \"421087\": \"松滋市\"\n" +
                "    },\n" +
                "    \"421100\": {\n" +
                "        \"421102\": \"黄州区\",\n" +
                "        \"421121\": \"团风县\",\n" +
                "        \"421122\": \"红安县\",\n" +
                "        \"421123\": \"罗田县\",\n" +
                "        \"421124\": \"英山县\",\n" +
                "        \"421125\": \"浠水县\",\n" +
                "        \"421126\": \"蕲春县\",\n" +
                "        \"421127\": \"黄梅县\",\n" +
                "        \"421181\": \"麻城市\",\n" +
                "        \"421182\": \"武穴市\"\n" +
                "    },\n" +
                "    \"421200\": {\n" +
                "        \"421202\": \"咸安区\",\n" +
                "        \"421221\": \"嘉鱼县\",\n" +
                "        \"421222\": \"通城县\",\n" +
                "        \"421223\": \"崇阳县\",\n" +
                "        \"421224\": \"通山县\",\n" +
                "        \"421281\": \"赤壁市\"\n" +
                "    },\n" +
                "    \"421300\": {\n" +
                "        \"421303\": \"曾都区\",\n" +
                "        \"421321\": \"随县\",\n" +
                "        \"421381\": \"广水市\"\n" +
                "    },\n" +
                "    \"422800\": {\n" +
                "        \"422801\": \"恩施市\",\n" +
                "        \"422802\": \"利川市\",\n" +
                "        \"422822\": \"建始县\",\n" +
                "        \"422823\": \"巴东县\",\n" +
                "        \"422825\": \"宣恩县\",\n" +
                "        \"422826\": \"咸丰县\",\n" +
                "        \"422827\": \"来凤县\",\n" +
                "        \"422828\": \"鹤峰县\"\n" +
                "    },\n" +
                "    \"430000\": {\n" +
                "        \"430100\": \"长沙市\",\n" +
                "        \"430200\": \"株洲市\",\n" +
                "        \"430300\": \"湘潭市\",\n" +
                "        \"430400\": \"衡阳市\",\n" +
                "        \"430500\": \"邵阳市\",\n" +
                "        \"430600\": \"岳阳市\",\n" +
                "        \"430700\": \"常德市\",\n" +
                "        \"430800\": \"张家界市\",\n" +
                "        \"430900\": \"益阳市\",\n" +
                "        \"431000\": \"郴州市\",\n" +
                "        \"431100\": \"永州市\",\n" +
                "        \"431200\": \"怀化市\",\n" +
                "        \"431300\": \"娄底市\",\n" +
                "        \"433100\": \"湘西土家族苗族自治州\"\n" +
                "    },\n" +
                "    \"430100\": {\n" +
                "        \"430102\": \"芙蓉区\",\n" +
                "        \"430103\": \"天心区\",\n" +
                "        \"430104\": \"岳麓区\",\n" +
                "        \"430105\": \"开福区\",\n" +
                "        \"430111\": \"雨花区\",\n" +
                "        \"430112\": \"望城区\",\n" +
                "        \"430121\": \"长沙县\",\n" +
                "        \"430124\": \"宁乡县\",\n" +
                "        \"430181\": \"浏阳市\"\n" );
        str.append(
                "    },\n" +
                "    \"430200\": {\n" +
                "        \"430202\": \"荷塘区\",\n" +
                "        \"430203\": \"芦淞区\",\n" +
                "        \"430204\": \"石峰区\",\n" +
                "        \"430211\": \"天元区\",\n" +
                "        \"430221\": \"株洲县\",\n" +
                "        \"430223\": \"攸县\",\n" +
                "        \"430224\": \"茶陵县\",\n" +
                "        \"430225\": \"炎陵县\",\n" +
                "        \"430281\": \"醴陵市\"\n" +
                "    },\n" +
                "    \"430300\": {\n" +
                "        \"430302\": \"雨湖区\",\n" +
                "        \"430304\": \"岳塘区\",\n" +
                "        \"430321\": \"湘潭县\",\n" +
                "        \"430381\": \"湘乡市\",\n" +
                "        \"430382\": \"韶山市\"\n" +
                "    },\n" +
                "    \"430400\": {\n" +
                "        \"430405\": \"珠晖区\",\n" +
                "        \"430406\": \"雁峰区\",\n" +
                "        \"430407\": \"石鼓区\",\n" +
                "        \"430408\": \"蒸湘区\",\n" +
                "        \"430412\": \"南岳区\",\n" +
                "        \"430421\": \"衡阳县\",\n" +
                "        \"430422\": \"衡南县\",\n" +
                "        \"430423\": \"衡山县\",\n" +
                "        \"430424\": \"衡东县\",\n" +
                "        \"430426\": \"祁东县\",\n" +
                "        \"430481\": \"耒阳市\",\n" +
                "        \"430482\": \"常宁市\"\n" +
                "    },\n" +
                "    \"430500\": {\n" +
                "        \"430502\": \"双清区\",\n" +
                "        \"430503\": \"大祥区\",\n" +
                "        \"430511\": \"北塔区\",\n" +
                "        \"430521\": \"邵东县\",\n" +
                "        \"430522\": \"新邵县\",\n" +
                "        \"430523\": \"邵阳县\",\n" +
                "        \"430524\": \"隆回县\",\n" +
                "        \"430525\": \"洞口县\",\n" +
                "        \"430527\": \"绥宁县\",\n" +
                "        \"430528\": \"新宁县\",\n" +
                "        \"430529\": \"城步苗族自治县\",\n" +
                "        \"430581\": \"武冈市\"\n" +
                "    },\n" +
                "    \"430600\": {\n" +
                "        \"430602\": \"岳阳楼区\",\n" +
                "        \"430603\": \"云溪区\",\n" +
                "        \"430611\": \"君山区\",\n" +
                "        \"430621\": \"岳阳县\",\n" +
                "        \"430623\": \"华容县\",\n" +
                "        \"430624\": \"湘阴县\",\n" +
                "        \"430626\": \"平江县\",\n" +
                "        \"430681\": \"汨罗市\",\n" +
                "        \"430682\": \"临湘市\"\n" +
                "    },\n" +
                "    \"430700\": {\n" +
                "        \"430702\": \"武陵区\",\n" +
                "        \"430703\": \"鼎城区\",\n" +
                "        \"430721\": \"安乡县\",\n" +
                "        \"430722\": \"汉寿县\",\n" +
                "        \"430723\": \"澧县\",\n" +
                "        \"430724\": \"临澧县\",\n" +
                "        \"430725\": \"桃源县\",\n" +
                "        \"430726\": \"石门县\",\n" +
                "        \"430781\": \"津市市\"\n" +
                "    },\n" +
                "    \"430800\": {\n" +
                "        \"430802\": \"永定区\",\n" +
                "        \"430811\": \"武陵源区\",\n" +
                "        \"430821\": \"慈利县\",\n" +
                "        \"430822\": \"桑植县\"\n" +
                "    },\n" +
                "    \"430900\": {\n" +
                "        \"430902\": \"资阳区\",\n" +
                "        \"430903\": \"赫山区\",\n" +
                "        \"430921\": \"南县\",\n" +
                "        \"430922\": \"桃江县\",\n" +
                "        \"430923\": \"安化县\",\n" +
                "        \"430981\": \"沅江市\"\n" +
                "    },\n" +
                "    \"431000\": {\n" +
                "        \"431002\": \"北湖区\",\n" +
                "        \"431003\": \"苏仙区\",\n" +
                "        \"431021\": \"桂阳县\",\n" +
                "        \"431022\": \"宜章县\",\n" +
                "        \"431023\": \"永兴县\",\n" +
                "        \"431024\": \"嘉禾县\",\n" +
                "        \"431025\": \"临武县\",\n" +
                "        \"431026\": \"汝城县\",\n" +
                "        \"431027\": \"桂东县\",\n" +
                "        \"431028\": \"安仁县\",\n" +
                "        \"431081\": \"资兴市\"\n" +
                "    },\n" +
                "    \"431100\": {\n" +
                "        \"431102\": \"零陵区\",\n" +
                "        \"431103\": \"冷水滩区\",\n" +
                "        \"431121\": \"祁阳县\",\n" +
                "        \"431122\": \"东安县\",\n" +
                "        \"431123\": \"双牌县\",\n" +
                "        \"431124\": \"道县\",\n" +
                "        \"431125\": \"江永县\",\n" +
                "        \"431126\": \"宁远县\",\n" +
                "        \"431127\": \"蓝山县\",\n" +
                "        \"431128\": \"新田县\",\n" +
                "        \"431129\": \"江华瑶族自治县\"\n" +
                "    },\n" +
                "    \"431200\": {\n" +
                "        \"431202\": \"鹤城区\",\n" +
                "        \"431221\": \"中方县\",\n" +
                "        \"431222\": \"沅陵县\",\n" +
                "        \"431223\": \"辰溪县\",\n" +
                "        \"431224\": \"溆浦县\",\n" +
                "        \"431225\": \"会同县\",\n" +
                "        \"431226\": \"麻阳苗族自治县\",\n" +
                "        \"431227\": \"新晃侗族自治县\",\n" +
                "        \"431228\": \"芷江侗族自治县\",\n" +
                "        \"431229\": \"靖州苗族侗族自治县\",\n" +
                "        \"431230\": \"通道侗族自治县\",\n" +
                "        \"431281\": \"洪江市\"\n" +
                "    },\n" +
                "    \"431300\": {\n" +
                "        \"431302\": \"娄星区\",\n" +
                "        \"431321\": \"双峰县\",\n" +
                "        \"431322\": \"新化县\",\n" +
                "        \"431381\": \"冷水江市\",\n" +
                "        \"431382\": \"涟源市\"\n" +
                "    },\n" +
                "    \"433100\": {\n" +
                "        \"433101\": \"吉首市\",\n" +
                "        \"433122\": \"泸溪县\",\n" +
                "        \"433123\": \"凤凰县\",\n" +
                "        \"433124\": \"花垣县\",\n" +
                "        \"433125\": \"保靖县\",\n" +
                "        \"433126\": \"古丈县\",\n" +
                "        \"433127\": \"永顺县\",\n" +
                "        \"433130\": \"龙山县\"\n" +
                "    },\n" +
                "    \"440000\": {\n" +
                "        \"440100\": \"广州市\",\n" +
                "        \"440200\": \"韶关市\",\n" +
                "        \"440300\": \"深圳市\",\n" +
                "        \"440400\": \"珠海市\",\n" +
                "        \"440500\": \"汕头市\",\n" +
                "        \"440600\": \"佛山市\",\n" +
                "        \"440700\": \"江门市\",\n" +
                "        \"440800\": \"湛江市\",\n" +
                "        \"440900\": \"茂名市\",\n" +
                "        \"441200\": \"肇庆市\",\n" +
                "        \"441300\": \"惠州市\",\n" +
                "        \"441400\": \"梅州市\",\n" +
                "        \"441500\": \"汕尾市\",\n" +
                "        \"441600\": \"河源市\",\n" +
                "        \"441700\": \"阳江市\",\n" +
                "        \"441800\": \"清远市\",\n" +
                "        \"441900\": \"东莞市\",\n" +
                "        \"442000\": \"中山市\",\n" +
                "        \"445100\": \"潮州市\",\n" +
                "        \"445200\": \"揭阳市\",\n" +
                "        \"445300\": \"云浮市\"\n" +
                "    },\n" +
                "    \"440100\": {\n" +
                "        \"440103\": \"荔湾区\",\n" +
                "        \"440104\": \"越秀区\",\n" +
                "        \"440105\": \"海珠区\",\n" +
                "        \"440106\": \"天河区\",\n" +
                "        \"440111\": \"白云区\",\n" +
                "        \"440112\": \"黄埔区\",\n" +
                "        \"440113\": \"番禺区\",\n" +
                "        \"440114\": \"花都区\",\n" +
                "        \"440115\": \"南沙区\",\n" +
                "        \"440117\": \"从化区\",\n" +
                "        \"440118\": \"增城区\"\n" +
                "    },\n" +
                "    \"440200\": {\n" +
                "        \"440203\": \"武江区\",\n" +
                "        \"440204\": \"浈江区\",\n" +
                "        \"440205\": \"曲江区\",\n" +
                "        \"440222\": \"始兴县\",\n" +
                "        \"440224\": \"仁化县\",\n" +
                "        \"440229\": \"翁源县\",\n" +
                "        \"440232\": \"乳源瑶族自治县\",\n" +
                "        \"440233\": \"新丰县\",\n" +
                "        \"440281\": \"乐昌市\",\n" +
                "        \"440282\": \"南雄市\"\n" +
                "    },\n" +
                "    \"440300\": {\n" +
                "        \"440303\": \"罗湖区\",\n" +
                "        \"440304\": \"福田区\",\n" +
                "        \"440305\": \"南山区\",\n" +
                "        \"440306\": \"宝安区\",\n" +
                "        \"440307\": \"龙岗区\",\n" +
                "        \"440308\": \"盐田区\"\n" +
                "    },\n" +
                "    \"440400\": {\n" +
                "        \"440402\": \"香洲区\",\n" +
                "        \"440403\": \"斗门区\",\n" +
                "        \"440404\": \"金湾区\"\n" +
                "    },\n" +
                "    \"440500\": {\n" +
                "        \"440507\": \"龙湖区\",\n" +
                "        \"440511\": \"金平区\",\n" +
                "        \"440512\": \"濠江区\",\n" +
                "        \"440513\": \"潮阳区\",\n" +
                "        \"440514\": \"潮南区\",\n" +
                "        \"440515\": \"澄海区\",\n" +
                "        \"440523\": \"南澳县\"\n" +
                "    },\n" +
                "    \"440600\": {\n" +
                "        \"440604\": \"禅城区\",\n" +
                "        \"440605\": \"南海区\",\n" +
                "        \"440606\": \"顺德区\",\n" +
                "        \"440607\": \"三水区\",\n" +
                "        \"440608\": \"高明区\"\n" +
                "    },\n" +
                "    \"440700\": {\n" +
                "        \"440703\": \"蓬江区\",\n" +
                "        \"440704\": \"江海区\",\n" +
                "        \"440705\": \"新会区\",\n" +
                "        \"440781\": \"台山市\",\n" +
                "        \"440783\": \"开平市\",\n" +
                "        \"440784\": \"鹤山市\",\n" +
                "        \"440785\": \"恩平市\"\n" +
                "    },\n" +
                "    \"440800\": {\n" +
                "        \"440802\": \"赤坎区\",\n" +
                "        \"440803\": \"霞山区\",\n" +
                "        \"440804\": \"坡头区\",\n" +
                "        \"440811\": \"麻章区\",\n" +
                "        \"440823\": \"遂溪县\",\n" +
                "        \"440825\": \"徐闻县\",\n" +
                "        \"440881\": \"廉江市\",\n" +
                "        \"440882\": \"雷州市\",\n" +
                "        \"440883\": \"吴川市\"\n" +
                "    },\n" +
                "    \"440900\": {\n" +
                "        \"440902\": \"茂南区\",\n" +
                "        \"440904\": \"电白区\",\n" +
                "        \"440981\": \"高州市\",\n" +
                "        \"440982\": \"化州市\",\n" +
                "        \"440983\": \"信宜市\"\n" +
                "    },\n" +
                "    \"441200\": {\n" +
                "        \"441202\": \"端州区\",\n" +
                "        \"441203\": \"鼎湖区\",\n" +
                "        \"441204\": \"高要区\",\n" +
                "        \"441223\": \"广宁县\",\n" +
                "        \"441224\": \"怀集县\",\n" +
                "        \"441225\": \"封开县\",\n" +
                "        \"441226\": \"德庆县\",\n" +
                "        \"441284\": \"四会市\"\n" +
                "    },\n" +
                "    \"441300\": {\n" +
                "        \"441302\": \"惠城区\",\n" +
                "        \"441303\": \"惠阳区\",\n" +
                "        \"441322\": \"博罗县\",\n" +
                "        \"441323\": \"惠东县\",\n" +
                "        \"441324\": \"龙门县\"\n" +
                "    },\n" +
                "    \"441400\": {\n" +
                "        \"441402\": \"梅江区\",\n" +
                "        \"441403\": \"梅县区\",\n" +
                "        \"441422\": \"大埔县\",\n" +
                "        \"441423\": \"丰顺县\",\n" +
                "        \"441424\": \"五华县\",\n" +
                "        \"441426\": \"平远县\",\n" +
                "        \"441427\": \"蕉岭县\",\n" +
                "        \"441481\": \"兴宁市\"\n" +
                "    },\n" +
                "    \"441500\": {\n" +
                "        \"441502\": \"城区\",\n" +
                "        \"441521\": \"海丰县\",\n" +
                "        \"441523\": \"陆河县\",\n" +
                "        \"441581\": \"陆丰市\"\n" +
                "    },\n" +
                "    \"441600\": {\n" +
                "        \"441602\": \"源城区\",\n" +
                "        \"441621\": \"紫金县\",\n" +
                "        \"441622\": \"龙川县\",\n" +
                "        \"441623\": \"连平县\",\n" +
                "        \"441624\": \"和平县\",\n" +
                "        \"441625\": \"东源县\"\n" +
                "    },\n" +
                "    \"441700\": {\n" +
                "        \"441702\": \"江城区\",\n" +
                "        \"441704\": \"阳东区\",\n" +
                "        \"441721\": \"阳西县\",\n" +
                "        \"441781\": \"阳春市\"\n" +
                "    },\n" +
                "    \"441800\": {\n" +
                "        \"441802\": \"清城区\",\n" +
                "        \"441803\": \"清新区\",\n" +
                "        \"441821\": \"佛冈县\",\n" +
                "        \"441823\": \"阳山县\",\n" +
                "        \"441825\": \"连山壮族瑶族自治县\",\n" +
                "        \"441826\": \"连南瑶族自治县\",\n" +
                "        \"441881\": \"英德市\",\n" +
                "        \"441882\": \"连州市\"\n" +
                "    },\n" +
                "    \"445100\": {\n" +
                "        \"445102\": \"湘桥区\",\n" +
                "        \"445103\": \"潮安区\",\n" +
                "        \"445122\": \"饶平县\"\n" +
                "    },\n" +
                "    \"445200\": {\n" +
                "        \"445202\": \"榕城区\",\n" +
                "        \"445203\": \"揭东区\",\n" +
                "        \"445222\": \"揭西县\",\n" +
                "        \"445224\": \"惠来县\",\n" +
                "        \"445281\": \"普宁市\"\n" +
                "    },\n" +
                "    \"445300\": {\n" +
                "        \"445302\": \"云城区\",\n" +
                "        \"445303\": \"云安区\",\n" +
                "        \"445321\": \"新兴县\",\n" +
                "        \"445322\": \"郁南县\",\n" +
                "        \"445381\": \"罗定市\"\n" +
                "    },\n" +
                "    \"450000\": {\n" +
                "        \"450100\": \"南宁市\",\n" +
                "        \"450200\": \"柳州市\",\n" +
                "        \"450300\": \"桂林市\",\n" +
                "        \"450400\": \"梧州市\",\n" +
                "        \"450500\": \"北海市\",\n" +
                "        \"450600\": \"防城港市\",\n" +
                "        \"450700\": \"钦州市\",\n" +
                "        \"450800\": \"贵港市\",\n" +
                "        \"450900\": \"玉林市\",\n" +
                "        \"451000\": \"百色市\",\n" +
                "        \"451100\": \"贺州市\",\n" +
                "        \"451200\": \"河池市\",\n" +
                "        \"451300\": \"来宾市\",\n" +
                "        \"451400\": \"崇左市\"\n" +
                "    },\n" +
                "    \"450100\": {\n" +
                "        \"450102\": \"兴宁区\",\n" +
                "        \"450103\": \"青秀区\",\n" +
                "        \"450105\": \"江南区\",\n" +
                "        \"450107\": \"西乡塘区\",\n" +
                "        \"450108\": \"良庆区\",\n" +
                "        \"450109\": \"邕宁区\",\n" +
                "        \"450110\": \"武鸣区\",\n" +
                "        \"450123\": \"隆安县\",\n" +
                "        \"450124\": \"马山县\",\n" +
                "        \"450125\": \"上林县\",\n" +
                "        \"450126\": \"宾阳县\",\n" +
                "        \"450127\": \"横县\"\n" +
                "    },\n" +
                "    \"450200\": {\n" +
                "        \"450202\": \"城中区\",\n" +
                "        \"450203\": \"鱼峰区\",\n" +
                "        \"450204\": \"柳南区\",\n" +
                "        \"450205\": \"柳北区\",\n" +
                "        \"450206\": \"柳江区\",\n" +
                "        \"450222\": \"柳城县\",\n" +
                "        \"450223\": \"鹿寨县\",\n" +
                "        \"450224\": \"融安县\",\n" +
                "        \"450225\": \"融水苗族自治县\",\n" +
                "        \"450226\": \"三江侗族自治县\"\n" +
                "    },\n" +
                "    \"450300\": {\n" +
                "        \"450302\": \"秀峰区\",\n" +
                "        \"450303\": \"叠彩区\",\n" +
                "        \"450304\": \"象山区\",\n" +
                "        \"450305\": \"七星区\",\n" +
                "        \"450311\": \"雁山区\",\n" +
                "        \"450312\": \"临桂区\",\n" +
                "        \"450321\": \"阳朔县\",\n" +
                "        \"450323\": \"灵川县\",\n" +
                "        \"450324\": \"全州县\",\n" +
                "        \"450325\": \"兴安县\",\n" +
                "        \"450326\": \"永福县\",\n" +
                "        \"450327\": \"灌阳县\",\n" +
                "        \"450328\": \"龙胜各族自治县\",\n" +
                "        \"450329\": \"资源县\",\n" +
                "        \"450330\": \"平乐县\",\n" +
                "        \"450331\": \"荔浦县\",\n" +
                "        \"450332\": \"恭城瑶族自治县\"\n" +
                "    },\n" +
                "    \"450400\": {\n" +
                "        \"450403\": \"万秀区\",\n" +
                "        \"450405\": \"长洲区\",\n" +
                "        \"450406\": \"龙圩区\",\n" +
                "        \"450421\": \"苍梧县\",\n" +
                "        \"450422\": \"藤县\",\n" +
                "        \"450423\": \"蒙山县\",\n" +
                "        \"450481\": \"岑溪市\"\n" +
                "    },\n" +
                "    \"450500\": {\n" +
                "        \"450502\": \"海城区\",\n" +
                "        \"450503\": \"银海区\",\n" +
                "        \"450512\": \"铁山港区\",\n" +
                "        \"450521\": \"合浦县\"\n" +
                "    },\n" +
                "    \"450600\": {\n" +
                "        \"450602\": \"港口区\",\n" +
                "        \"450603\": \"防城区\",\n" +
                "        \"450621\": \"上思县\",\n" +
                "        \"450681\": \"东兴市\"\n" +
                "    },\n" +
                "    \"450700\": {\n" +
                "        \"450702\": \"钦南区\",\n" +
                "        \"450703\": \"钦北区\",\n" +
                "        \"450721\": \"灵山县\",\n" +
                "        \"450722\": \"浦北县\"\n" +
                "    },\n" +
                "    \"450800\": {\n" +
                "        \"450802\": \"港北区\",\n" +
                "        \"450803\": \"港南区\",\n" +
                "        \"450804\": \"覃塘区\",\n" +
                "        \"450821\": \"平南县\",\n" +
                "        \"450881\": \"桂平市\"\n" +
                "    },\n" +
                "    \"450900\": {\n" +
                "        \"450902\": \"玉州区\",\n" +
                "        \"450903\": \"福绵区\",\n" +
                "        \"450921\": \"容县\",\n" +
                "        \"450922\": \"陆川县\",\n" +
                "        \"450923\": \"博白县\",\n" +
                "        \"450924\": \"兴业县\",\n" +
                "        \"450981\": \"北流市\"\n" +
                "    },\n" +
                "    \"451000\": {\n" +
                "        \"451002\": \"右江区\",\n" +
                "        \"451021\": \"田阳县\",\n" +
                "        \"451022\": \"田东县\",\n" +
                "        \"451023\": \"平果县\",\n" +
                "        \"451024\": \"德保县\",\n" +
                "        \"451026\": \"那坡县\",\n" +
                "        \"451027\": \"凌云县\",\n" +
                "        \"451028\": \"乐业县\",\n" +
                "        \"451029\": \"田林县\",\n" +
                "        \"451030\": \"西林县\",\n" +
                "        \"451031\": \"隆林各族自治县\",\n" +
                "        \"451081\": \"靖西市\"\n" +
                "    },\n" +
                "    \"451100\": {\n" +
                "        \"451102\": \"八步区\",\n" +
                "        \"451103\": \"平桂区\",\n" +
                "        \"451121\": \"昭平县\",\n" +
                "        \"451122\": \"钟山县\",\n" +
                "        \"451123\": \"富川瑶族自治县\"\n" +
                "    },\n" +
                "    \"451200\": {\n" +
                "        \"451202\": \"金城江区\",\n" +
                "        \"451221\": \"南丹县\",\n" +
                "        \"451222\": \"天峨县\",\n" +
                "        \"451223\": \"凤山县\",\n" +
                "        \"451224\": \"东兰县\",\n" +
                "        \"451225\": \"罗城仫佬族自治县\",\n" +
                "        \"451226\": \"环江毛南族自治县\",\n" +
                "        \"451227\": \"巴马瑶族自治县\",\n" +
                "        \"451228\": \"都安瑶族自治县\",\n" +
                "        \"451229\": \"大化瑶族自治县\",\n" +
                "        \"451281\": \"宜州市\"\n" +
                "    },\n" +
                "    \"451300\": {\n" +
                "        \"451302\": \"兴宾区\",\n" +
                "        \"451321\": \"忻城县\",\n" +
                "        \"451322\": \"象州县\",\n" +
                "        \"451323\": \"武宣县\",\n" +
                "        \"451324\": \"金秀瑶族自治县\",\n" +
                "        \"451381\": \"合山市\"\n" +
                "    },\n" +
                "    \"451400\": {\n" +
                "        \"451402\": \"江州区\",\n" +
                "        \"451421\": \"扶绥县\",\n" +
                "        \"451422\": \"宁明县\",\n" +
                "        \"451423\": \"龙州县\",\n" +
                "        \"451424\": \"大新县\",\n" +
                "        \"451425\": \"天等县\",\n" +
                "        \"451481\": \"凭祥市\"\n" +
                "    },\n" +
                "    \"460000\": {\n" +
                "        \"460100\": \"海口市\",\n" +
                "        \"460200\": \"三亚市\",\n" +
                "        \"460300\": \"三沙市\",\n" +
                "        \"460400\": \"儋州市\",\n" +
                "        \"469001\": \"五指山市\",\n" +
                "        \"469002\": \"琼海市\",\n" +
                "        \"469005\": \"文昌市\",\n" +
                "        \"469006\": \"万宁市\",\n" +
                "        \"469007\": \"东方市\",\n" +
                "        \"469021\": \"定安县\",\n" +
                "        \"469022\": \"屯昌县\",\n" +
                "        \"469023\": \"澄迈县\",\n" +
                "        \"469024\": \"临高县\",\n" +
                "        \"469025\": \"白沙黎族自治县\",\n" +
                "        \"469026\": \"昌江黎族自治县\",\n" +
                "        \"469027\": \"乐东黎族自治县\",\n" +
                "        \"469028\": \"陵水黎族自治县\",\n" +
                "        \"469029\": \"保亭黎族苗族自治县\",\n" +
                "        \"469030\": \"琼中黎族苗族自治县\"\n" +
                "    },\n" +
                "    \"460100\": {\n" +
                "        \"460105\": \"秀英区\",\n" +
                "        \"460106\": \"龙华区\",\n" +
                "        \"460107\": \"琼山区\",\n" +
                "        \"460108\": \"美兰区\"\n" +
                "    },\n" +
                "    \"460200\": {\n" +
                "        \"460202\": \"海棠区\",\n" +
                "        \"460203\": \"吉阳区\",\n" +
                "        \"460204\": \"天涯区\",\n" +
                "        \"460205\": \"崖州区\"\n" +
                "    },\n" +
                "    \"500000\": {\n" +
                "        \"500100\": \"市辖区\",\n" +
                "        \"500200\": \"县\"\n" +
                "    },\n" +
                "    \"500100\": {\n" +
                "        \"500101\": \"万州区\",\n" +
                "        \"500102\": \"涪陵区\",\n" +
                "        \"500103\": \"渝中区\",\n" +
                "        \"500104\": \"大渡口区\",\n" +
                "        \"500105\": \"江北区\",\n" +
                "        \"500106\": \"沙坪坝区\",\n" +
                "        \"500107\": \"九龙坡区\",\n" +
                "        \"500108\": \"南岸区\",\n" +
                "        \"500109\": \"北碚区\",\n" +
                "        \"500110\": \"綦江区\",\n" +
                "        \"500111\": \"大足区\",\n" +
                "        \"500112\": \"渝北区\",\n" +
                "        \"500113\": \"巴南区\",\n" +
                "        \"500114\": \"黔江区\",\n" +
                "        \"500115\": \"长寿区\",\n" +
                "        \"500116\": \"江津区\",\n" +
                "        \"500117\": \"合川区\",\n" +
                "        \"500118\": \"永川区\",\n" +
                "        \"500119\": \"南川区\",\n" +
                "        \"500120\": \"璧山区\",\n" +
                "        \"500151\": \"铜梁区\",\n" +
                "        \"500152\": \"潼南区\",\n" +
                "        \"500153\": \"荣昌区\",\n" +
                "        \"500154\": \"开州区\"\n" +
                "    },\n" +
                "    \"500200\": {\n" +
                "        \"500228\": \"梁平县\",\n" +
                "        \"500229\": \"城口县\",\n" +
                "        \"500230\": \"丰都县\",\n" +
                "        \"500231\": \"垫江县\",\n" +
                "        \"500232\": \"武隆县\",\n" +
                "        \"500233\": \"忠县\",\n" +
                "        \"500235\": \"云阳县\",\n" +
                "        \"500236\": \"奉节县\",\n" +
                "        \"500237\": \"巫山县\",\n" +
                "        \"500238\": \"巫溪县\",\n" +
                "        \"500240\": \"石柱土家族自治县\",\n" +
                "        \"500241\": \"秀山土家族苗族自治县\",\n" +
                "        \"500242\": \"酉阳土家族苗族自治县\",\n" +
                "        \"500243\": \"彭水苗族土家族自治县\"\n" +
                "    },\n" +
                "    \"510000\": {\n" +
                "        \"510100\": \"成都市\",\n" +
                "        \"510300\": \"自贡市\",\n" +
                "        \"510400\": \"攀枝花市\",\n" +
                "        \"510500\": \"泸州市\",\n" +
                "        \"510600\": \"德阳市\",\n" +
                "        \"510700\": \"绵阳市\",\n" +
                "        \"510800\": \"广元市\",\n" +
                "        \"510900\": \"遂宁市\",\n" +
                "        \"511000\": \"内江市\",\n" +
                "        \"511100\": \"乐山市\",\n" +
                "        \"511300\": \"南充市\",\n" +
                "        \"511400\": \"眉山市\",\n" +
                "        \"511500\": \"宜宾市\",\n" +
                "        \"511600\": \"广安市\",\n" +
                "        \"511700\": \"达州市\",\n" +
                "        \"511800\": \"雅安市\",\n" +
                "        \"511900\": \"巴中市\",\n" +
                "        \"512000\": \"资阳市\",\n" +
                "        \"513200\": \"阿坝藏族羌族自治州\",\n" +
                "        \"513300\": \"甘孜藏族自治州\",\n" +
                "        \"513400\": \"凉山彝族自治州\"\n" +
                "    },\n" +
                "    \"510100\": {\n" +
                "        \"510104\": \"锦江区\",\n" +
                "        \"510105\": \"青羊区\",\n" +
                "        \"510106\": \"金牛区\",\n" +
                "        \"510107\": \"武侯区\",\n" +
                "        \"510108\": \"成华区\",\n" +
                "        \"510112\": \"龙泉驿区\",\n" +
                "        \"510113\": \"青白江区\",\n" +
                "        \"510114\": \"新都区\",\n" +
                "        \"510115\": \"温江区\",\n" +
                "        \"510116\": \"双流区\",\n" +
                "        \"510121\": \"金堂县\",\n" +
                "        \"510124\": \"郫县\",\n" +
                "        \"510129\": \"大邑县\",\n" +
                "        \"510131\": \"蒲江县\",\n" +
                "        \"510132\": \"新津县\",\n" +
                "        \"510181\": \"都江堰市\",\n" +
                "        \"510182\": \"彭州市\",\n" +
                "        \"510183\": \"邛崃市\",\n" +
                "        \"510184\": \"崇州市\",\n" +
                "        \"510185\": \"简阳市\"\n" +
                "    },\n" +
                "    \"510300\": {\n" +
                "        \"510302\": \"自流井区\",\n" +
                "        \"510303\": \"贡井区\",\n" +
                "        \"510304\": \"大安区\",\n" +
                "        \"510311\": \"沿滩区\",\n" +
                "        \"510321\": \"荣县\",\n" +
                "        \"510322\": \"富顺县\"\n" +
                "    },\n" +
                "    \"510400\": {\n" +
                "        \"510402\": \"东区\",\n" +
                "        \"510403\": \"西区\",\n" +
                "        \"510411\": \"仁和区\",\n" +
                "        \"510421\": \"米易县\",\n" +
                "        \"510422\": \"盐边县\"\n" +
                "    },\n" +
                "    \"510500\": {\n" +
                "        \"510502\": \"江阳区\",\n" +
                "        \"510503\": \"纳溪区\",\n" +
                "        \"510504\": \"龙马潭区\",\n" +
                "        \"510521\": \"泸县\",\n" +
                "        \"510522\": \"合江县\",\n" +
                "        \"510524\": \"叙永县\",\n" +
                "        \"510525\": \"古蔺县\"\n" +
                "    },\n" +
                "    \"510600\": {\n" +
                "        \"510603\": \"旌阳区\",\n" +
                "        \"510623\": \"中江县\",\n" +
                "        \"510626\": \"罗江县\",\n" +
                "        \"510681\": \"广汉市\",\n" +
                "        \"510682\": \"什邡市\",\n" +
                "        \"510683\": \"绵竹市\"\n" +
                "    },\n" +
                "    \"510700\": {\n" +
                "        \"510703\": \"涪城区\",\n" +
                "        \"510704\": \"游仙区\",\n" +
                "        \"510705\": \"安州区\",\n" +
                "        \"510722\": \"三台县\",\n" +
                "        \"510723\": \"盐亭县\",\n" +
                "        \"510725\": \"梓潼县\",\n" +
                "        \"510726\": \"北川羌族自治县\",\n" +
                "        \"510727\": \"平武县\",\n" +
                "        \"510781\": \"江油市\"\n" +
                "    },\n" +
                "    \"510800\": {\n" +
                "        \"510802\": \"利州区\",\n" +
                "        \"510811\": \"昭化区\",\n" +
                "        \"510812\": \"朝天区\",\n" +
                "        \"510821\": \"旺苍县\",\n" +
                "        \"510822\": \"青川县\",\n" +
                "        \"510823\": \"剑阁县\",\n" +
                "        \"510824\": \"苍溪县\"\n" +
                "    },\n" +
                "    \"510900\": {\n" +
                "        \"510903\": \"船山区\",\n" +
                "        \"510904\": \"安居区\",\n" +
                "        \"510921\": \"蓬溪县\",\n" +
                "        \"510922\": \"射洪县\",\n" +
                "        \"510923\": \"大英县\"\n" +
                "    },\n" +
                "    \"511000\": {\n" +
                "        \"511002\": \"市中区\",\n" +
                "        \"511011\": \"东兴区\",\n" +
                "        \"511024\": \"威远县\",\n" +
                "        \"511025\": \"资中县\",\n" +
                "        \"511028\": \"隆昌县\"\n" +
                "    },\n" +
                "    \"511100\": {\n" +
                "        \"511102\": \"市中区\",\n" +
                "        \"511111\": \"沙湾区\",\n" +
                "        \"511112\": \"五通桥区\",\n" +
                "        \"511113\": \"金口河区\",\n" +
                "        \"511123\": \"犍为县\",\n" +
                "        \"511124\": \"井研县\",\n" +
                "        \"511126\": \"夹江县\",\n" +
                "        \"511129\": \"沐川县\",\n" +
                "        \"511132\": \"峨边彝族自治县\",\n" +
                "        \"511133\": \"马边彝族自治县\",\n" +
                "        \"511181\": \"峨眉山市\"\n" +
                "    },\n" +
                "    \"511300\": {\n" +
                "        \"511302\": \"顺庆区\",\n" +
                "        \"511303\": \"高坪区\",\n" +
                "        \"511304\": \"嘉陵区\",\n" +
                "        \"511321\": \"南部县\",\n" +
                "        \"511322\": \"营山县\",\n" +
                "        \"511323\": \"蓬安县\",\n" +
                "        \"511324\": \"仪陇县\",\n" +
                "        \"511325\": \"西充县\",\n" +
                "        \"511381\": \"阆中市\"\n" +
                "    },\n" +
                "    \"511400\": {\n" +
                "        \"511402\": \"东坡区\",\n" +
                "        \"511403\": \"彭山区\",\n" +
                "        \"511421\": \"仁寿县\",\n" +
                "        \"511423\": \"洪雅县\",\n" +
                "        \"511424\": \"丹棱县\",\n" +
                "        \"511425\": \"青神县\"\n" +
                "    },\n" +
                "    \"511500\": {\n" +
                "        \"511502\": \"翠屏区\",\n" +
                "        \"511503\": \"南溪区\",\n" +
                "        \"511521\": \"宜宾县\",\n" +
                "        \"511523\": \"江安县\",\n" +
                "        \"511524\": \"长宁县\",\n" +
                "        \"511525\": \"高县\",\n" +
                "        \"511526\": \"珙县\",\n" +
                "        \"511527\": \"筠连县\",\n" +
                "        \"511528\": \"兴文县\",\n" +
                "        \"511529\": \"屏山县\"\n" +
                "    },\n" +
                "    \"511600\": {\n" +
                "        \"511602\": \"广安区\",\n" +
                "        \"511603\": \"前锋区\",\n" +
                "        \"511621\": \"岳池县\",\n" +
                "        \"511622\": \"武胜县\",\n" +
                "        \"511623\": \"邻水县\",\n" +
                "        \"511681\": \"华蓥市\"\n" +
                "    },\n" +
                "    \"511700\": {\n" +
                "        \"511702\": \"通川区\",\n" +
                "        \"511703\": \"达川区\",\n" +
                "        \"511722\": \"宣汉县\",\n" +
                "        \"511723\": \"开江县\",\n" +
                "        \"511724\": \"大竹县\",\n" +
                "        \"511725\": \"渠县\",\n" +
                "        \"511781\": \"万源市\"\n" +
                "    },\n" +
                "    \"511800\": {\n" +
                "        \"511802\": \"雨城区\",\n" +
                "        \"511803\": \"名山区\",\n" +
                "        \"511822\": \"荥经县\",\n" +
                "        \"511823\": \"汉源县\",\n" +
                "        \"511824\": \"石棉县\",\n" +
                "        \"511825\": \"天全县\",\n" +
                "        \"511826\": \"芦山县\",\n" +
                "        \"511827\": \"宝兴县\"\n" +
                "    },\n" +
                "    \"511900\": {\n" +
                "        \"511902\": \"巴州区\",\n" +
                "        \"511903\": \"恩阳区\",\n" +
                "        \"511921\": \"通江县\",\n" +
                "        \"511922\": \"南江县\",\n" +
                "        \"511923\": \"平昌县\"\n" +
                "    },\n" +
                "    \"512000\": {\n" +
                "        \"512002\": \"雁江区\",\n" +
                "        \"512021\": \"安岳县\",\n" +
                "        \"512022\": \"乐至县\"\n" +
                "    },\n" +
                "    \"513200\": {\n" +
                "        \"513201\": \"马尔康市\",\n" +
                "        \"513221\": \"汶川县\",\n" +
                "        \"513222\": \"理县\",\n" +
                "        \"513223\": \"茂县\",\n" +
                "        \"513224\": \"松潘县\",\n" +
                "        \"513225\": \"九寨沟县\",\n" +
                "        \"513226\": \"金川县\",\n" +
                "        \"513227\": \"小金县\",\n" +
                "        \"513228\": \"黑水县\",\n" +
                "        \"513230\": \"壤塘县\",\n" +
                "        \"513231\": \"阿坝县\",\n" +
                "        \"513232\": \"若尔盖县\",\n" +
                "        \"513233\": \"红原县\"\n" +
                "    },\n" +
                "    \"513300\": {\n" +
                "        \"513301\": \"康定市\",\n" +
                "        \"513322\": \"泸定县\",\n" +
                "        \"513323\": \"丹巴县\",\n" +
                "        \"513324\": \"九龙县\",\n" +
                "        \"513325\": \"雅江县\",\n" +
                "        \"513326\": \"道孚县\",\n" +
                "        \"513327\": \"炉霍县\",\n" +
                "        \"513328\": \"甘孜县\",\n" +
                "        \"513329\": \"新龙县\",\n" +
                "        \"513330\": \"德格县\",\n" +
                "        \"513331\": \"白玉县\",\n" +
                "        \"513332\": \"石渠县\",\n" +
                "        \"513333\": \"色达县\",\n" +
                "        \"513334\": \"理塘县\",\n" +
                "        \"513335\": \"巴塘县\",\n" +
                "        \"513336\": \"乡城县\",\n" +
                "        \"513337\": \"稻城县\",\n" +
                "        \"513338\": \"得荣县\"\n" +
                "    },\n" +
                "    \"513400\": {\n" +
                "        \"513401\": \"西昌市\",\n" +
                "        \"513422\": \"木里藏族自治县\",\n" +
                "        \"513423\": \"盐源县\",\n" +
                "        \"513424\": \"德昌县\",\n" +
                "        \"513425\": \"会理县\",\n" +
                "        \"513426\": \"会东县\",\n" +
                "        \"513427\": \"宁南县\",\n" +
                "        \"513428\": \"普格县\",\n" +
                "        \"513429\": \"布拖县\",\n" +
                "        \"513430\": \"金阳县\",\n" +
                "        \"513431\": \"昭觉县\",\n" +
                "        \"513432\": \"喜德县\",\n" +
                "        \"513433\": \"冕宁县\",\n" +
                "        \"513434\": \"越西县\",\n" +
                "        \"513435\": \"甘洛县\",\n" +
                "        \"513436\": \"美姑县\",\n" +
                "        \"513437\": \"雷波县\"\n" +
                "    },\n" +
                "    \"520000\": {\n" +
                "        \"520100\": \"贵阳市\",\n" +
                "        \"520200\": \"六盘水市\",\n" +
                "        \"520300\": \"遵义市\",\n" +
                "        \"520400\": \"安顺市\",\n" +
                "        \"520500\": \"毕节市\",\n" +
                "        \"520600\": \"铜仁市\",\n" +
                "        \"522300\": \"黔西南布依族苗族自治州\",\n" +
                "        \"522600\": \"黔东南苗族侗族自治州\",\n" +
                "        \"522700\": \"黔南布依族苗族自治州\"\n" +
                "    },\n" +
                "    \"520100\": {\n" +
                "        \"520102\": \"南明区\",\n" +
                "        \"520103\": \"云岩区\",\n" +
                "        \"520111\": \"花溪区\",\n" +
                "        \"520112\": \"乌当区\",\n" +
                "        \"520113\": \"白云区\",\n" +
                "        \"520115\": \"观山湖区\",\n" +
                "        \"520121\": \"开阳县\",\n" +
                "        \"520122\": \"息烽县\",\n" +
                "        \"520123\": \"修文县\",\n" +
                "        \"520181\": \"清镇市\"\n" +
                "    },\n" +
                "    \"520200\": {\n" +
                "        \"520201\": \"钟山区\",\n" +
                "        \"520203\": \"六枝特区\",\n" +
                "        \"520221\": \"水城县\",\n" +
                "        \"520222\": \"盘县\"\n" +
                "    },\n" +
                "    \"520300\": {\n" +
                "        \"520302\": \"红花岗区\",\n" +
                "        \"520303\": \"汇川区\",\n" +
                "        \"520304\": \"播州区\",\n" +
                "        \"520322\": \"桐梓县\",\n" +
                "        \"520323\": \"绥阳县\",\n" +
                "        \"520324\": \"正安县\",\n" +
                "        \"520325\": \"道真仡佬族苗族自治县\",\n" +
                "        \"520326\": \"务川仡佬族苗族自治县\",\n" +
                "        \"520327\": \"凤冈县\",\n" +
                "        \"520328\": \"湄潭县\",\n" +
                "        \"520329\": \"余庆县\",\n" +
                "        \"520330\": \"习水县\",\n" +
                "        \"520381\": \"赤水市\",\n" +
                "        \"520382\": \"仁怀市\"\n" +
                "    },\n" +
                "    \"520400\": {\n" +
                "        \"520402\": \"西秀区\",\n" +
                "        \"520403\": \"平坝区\",\n" +
                "        \"520422\": \"普定县\",\n" +
                "        \"520423\": \"镇宁布依族苗族自治县\",\n" +
                "        \"520424\": \"关岭布依族苗族自治县\",\n" +
                "        \"520425\": \"紫云苗族布依族自治县\"\n" +
                "    },\n" +
                "    \"520500\": {\n" +
                "        \"520502\": \"七星关区\",\n" +
                "        \"520521\": \"大方县\",\n" +
                "        \"520522\": \"黔西县\",\n" +
                "        \"520523\": \"金沙县\",\n" +
                "        \"520524\": \"织金县\",\n" +
                "        \"520525\": \"纳雍县\",\n" +
                "        \"520526\": \"威宁彝族回族苗族自治县\",\n" +
                "        \"520527\": \"赫章县\"\n" +
                "    },\n" +
                "    \"520600\": {\n" +
                "        \"520602\": \"碧江区\",\n" +
                "        \"520603\": \"万山区\",\n" +
                "        \"520621\": \"江口县\",\n" +
                "        \"520622\": \"玉屏侗族自治县\",\n" +
                "        \"520623\": \"石阡县\",\n" +
                "        \"520624\": \"思南县\",\n" +
                "        \"520625\": \"印江土家族苗族自治县\",\n" +
                "        \"520626\": \"德江县\",\n" +
                "        \"520627\": \"沿河土家族自治县\",\n" +
                "        \"520628\": \"松桃苗族自治县\"\n" +
                "    },\n" +
                "    \"522300\": {\n" +
                "        \"522301\": \"兴义市\",\n" +
                "        \"522322\": \"兴仁县\",\n" +
                "        \"522323\": \"普安县\",\n" +
                "        \"522324\": \"晴隆县\",\n" +
                "        \"522325\": \"贞丰县\",\n" +
                "        \"522326\": \"望谟县\",\n" +
                "        \"522327\": \"册亨县\",\n" +
                "        \"522328\": \"安龙县\"\n" +
                "    },\n" +
                "    \"522600\": {\n" +
                "        \"522601\": \"凯里市\",\n" +
                "        \"522622\": \"黄平县\",\n" +
                "        \"522623\": \"施秉县\",\n" +
                "        \"522624\": \"三穗县\",\n" +
                "        \"522625\": \"镇远县\",\n" +
                "        \"522626\": \"岑巩县\",\n" +
                "        \"522627\": \"天柱县\",\n" +
                "        \"522628\": \"锦屏县\",\n" +
                "        \"522629\": \"剑河县\",\n" +
                "        \"522630\": \"台江县\",\n" +
                "        \"522631\": \"黎平县\",\n" +
                "        \"522632\": \"榕江县\",\n" +
                "        \"522633\": \"从江县\",\n" +
                "        \"522634\": \"雷山县\",\n" +
                "        \"522635\": \"麻江县\",\n" +
                "        \"522636\": \"丹寨县\"\n" +
                "    },\n" +
                "    \"522700\": {\n" +
                "        \"522701\": \"都匀市\",\n" +
                "        \"522702\": \"福泉市\",\n" +
                "        \"522722\": \"荔波县\",\n" +
                "        \"522723\": \"贵定县\",\n" +
                "        \"522725\": \"瓮安县\",\n" +
                "        \"522726\": \"独山县\",\n" +
                "        \"522727\": \"平塘县\",\n" +
                "        \"522728\": \"罗甸县\",\n" +
                "        \"522729\": \"长顺县\",\n" +
                "        \"522730\": \"龙里县\",\n" +
                "        \"522731\": \"惠水县\",\n" +
                "        \"522732\": \"三都水族自治县\"\n" +
                "    },\n" +
                "    \"530000\": {\n" +
                "        \"530100\": \"昆明市\",\n" +
                "        \"530300\": \"曲靖市\",\n" +
                "        \"530400\": \"玉溪市\",\n" +
                "        \"530500\": \"保山市\",\n" +
                "        \"530600\": \"昭通市\",\n" +
                "        \"530700\": \"丽江市\",\n" +
                "        \"530800\": \"普洱市\",\n" +
                "        \"530900\": \"临沧市\",\n" +
                "        \"532300\": \"楚雄彝族自治州\",\n" +
                "        \"532500\": \"红河哈尼族彝族自治州\",\n" +
                "        \"532600\": \"文山壮族苗族自治州\",\n" +
                "        \"532800\": \"西双版纳傣族自治州\",\n" +
                "        \"532900\": \"大理白族自治州\",\n" +
                "        \"533100\": \"德宏傣族景颇族自治州\",\n" +
                "        \"533300\": \"怒江傈僳族自治州\",\n" +
                "        \"533400\": \"迪庆藏族自治州\"\n" +
                "    },\n" +
                "    \"530100\": {\n" +
                "        \"530102\": \"五华区\",\n" +
                "        \"530103\": \"盘龙区\",\n" +
                "        \"530111\": \"官渡区\",\n" +
                "        \"530112\": \"西山区\",\n" +
                "        \"530113\": \"东川区\",\n" +
                "        \"530114\": \"呈贡区\",\n" +
                "        \"530122\": \"晋宁县\",\n" +
                "        \"530124\": \"富民县\",\n" +
                "        \"530125\": \"宜良县\",\n" +
                "        \"530126\": \"石林彝族自治县\",\n" +
                "        \"530127\": \"嵩明县\",\n" +
                "        \"530128\": \"禄劝彝族苗族自治县\",\n" +
                "        \"530129\": \"寻甸回族彝族自治县\",\n" +
                "        \"530181\": \"安宁市\"\n" +
                "    },\n" +
                "    \"530300\": {\n" +
                "        \"530302\": \"麒麟区\",\n" +
                "        \"530303\": \"沾益区\",\n" +
                "        \"530321\": \"马龙县\",\n" +
                "        \"530322\": \"陆良县\",\n" +
                "        \"530323\": \"师宗县\",\n" +
                "        \"530324\": \"罗平县\",\n" +
                "        \"530325\": \"富源县\",\n" +
                "        \"530326\": \"会泽县\",\n" +
                "        \"530381\": \"宣威市\"\n" +
                "    },\n" +
                "    \"530400\": {\n" +
                "        \"530402\": \"红塔区\",\n" +
                "        \"530403\": \"江川区\",\n" +
                "        \"530422\": \"澄江县\",\n" +
                "        \"530423\": \"通海县\",\n" +
                "        \"530424\": \"华宁县\",\n" +
                "        \"530425\": \"易门县\",\n" +
                "        \"530426\": \"峨山彝族自治县\",\n" +
                "        \"530427\": \"新平彝族傣族自治县\",\n" +
                "        \"530428\": \"元江哈尼族彝族傣族自治县\"\n" +
                "    },\n" +
                "    \"530500\": {\n" +
                "        \"530502\": \"隆阳区\",\n" +
                "        \"530521\": \"施甸县\",\n" +
                "        \"530523\": \"龙陵县\",\n" +
                "        \"530524\": \"昌宁县\",\n" +
                "        \"530581\": \"腾冲市\"\n" +
                "    },\n" +
                "    \"530600\": {\n" +
                "        \"530602\": \"昭阳区\",\n" +
                "        \"530621\": \"鲁甸县\",\n" +
                "        \"530622\": \"巧家县\",\n" +
                "        \"530623\": \"盐津县\",\n" +
                "        \"530624\": \"大关县\",\n" +
                "        \"530625\": \"永善县\",\n" +
                "        \"530626\": \"绥江县\",\n" +
                "        \"530627\": \"镇雄县\",\n" +
                "        \"530628\": \"彝良县\",\n" +
                "        \"530629\": \"威信县\",\n" +
                "        \"530630\": \"水富县\"\n" +
                "    },\n" +
                "    \"530700\": {\n" +
                "        \"530702\": \"古城区\",\n" +
                "        \"530721\": \"玉龙纳西族自治县\",\n" +
                "        \"530722\": \"永胜县\",\n" );
        str.append(
                "        \"530723\": \"华坪县\",\n" +
                "        \"530724\": \"宁蒗彝族自治县\"\n" +
                "    },\n" +
                "    \"530800\": {\n" +
                "        \"530802\": \"思茅区\",\n" +
                "        \"530821\": \"宁洱哈尼族彝族自治县\",\n" +
                "        \"530822\": \"墨江哈尼族自治县\",\n" +
                "        \"530823\": \"景东彝族自治县\",\n" +
                "        \"530824\": \"景谷傣族彝族自治县\",\n" +
                "        \"530825\": \"镇沅彝族哈尼族拉祜族自治县\",\n" +
                "        \"530826\": \"江城哈尼族彝族自治县\",\n" +
                "        \"530827\": \"孟连傣族拉祜族佤族自治县\",\n" +
                "        \"530828\": \"澜沧拉祜族自治县\",\n" +
                "        \"530829\": \"西盟佤族自治县\"\n" +
                "    },\n" +
                "    \"530900\": {\n" +
                "        \"530902\": \"临翔区\",\n" +
                "        \"530921\": \"凤庆县\",\n" +
                "        \"530922\": \"云县\",\n" +
                "        \"530923\": \"永德县\",\n" +
                "        \"530924\": \"镇康县\",\n" +
                "        \"530925\": \"双江拉祜族佤族布朗族傣族自治县\",\n" +
                "        \"530926\": \"耿马傣族佤族自治县\",\n" +
                "        \"530927\": \"沧源佤族自治县\"\n" +
                "    },\n" +
                "    \"532300\": {\n" +
                "        \"532301\": \"楚雄市\",\n" +
                "        \"532322\": \"双柏县\",\n" +
                "        \"532323\": \"牟定县\",\n" +
                "        \"532324\": \"南华县\",\n" +
                "        \"532325\": \"姚安县\",\n" +
                "        \"532326\": \"大姚县\",\n" +
                "        \"532327\": \"永仁县\",\n" +
                "        \"532328\": \"元谋县\",\n" +
                "        \"532329\": \"武定县\",\n" +
                "        \"532331\": \"禄丰县\"\n" +
                "    },\n" +
                "    \"532500\": {\n" +
                "        \"532501\": \"个旧市\",\n" +
                "        \"532502\": \"开远市\",\n" +
                "        \"532503\": \"蒙自市\",\n" +
                "        \"532504\": \"弥勒市\",\n" +
                "        \"532523\": \"屏边苗族自治县\",\n" +
                "        \"532524\": \"建水县\",\n" +
                "        \"532525\": \"石屏县\",\n" +
                "        \"532527\": \"泸西县\",\n" +
                "        \"532528\": \"元阳县\",\n" +
                "        \"532529\": \"红河县\",\n" +
                "        \"532530\": \"金平苗族瑶族傣族自治县\",\n" +
                "        \"532531\": \"绿春县\",\n" +
                "        \"532532\": \"河口瑶族自治县\"\n" +
                "    },\n" +
                "    \"532600\": {\n" +
                "        \"532601\": \"文山市\",\n" +
                "        \"532622\": \"砚山县\",\n" +
                "        \"532623\": \"西畴县\",\n" +
                "        \"532624\": \"麻栗坡县\",\n" +
                "        \"532625\": \"马关县\",\n" +
                "        \"532626\": \"丘北县\",\n" +
                "        \"532627\": \"广南县\",\n" +
                "        \"532628\": \"富宁县\"\n" +
                "    },\n" +
                "    \"532800\": {\n" +
                "        \"532801\": \"景洪市\",\n" +
                "        \"532822\": \"勐海县\",\n" +
                "        \"532823\": \"勐腊县\"\n" +
                "    },\n" +
                "    \"532900\": {\n" +
                "        \"532901\": \"大理市\",\n" +
                "        \"532922\": \"漾濞彝族自治县\",\n" +
                "        \"532923\": \"祥云县\",\n" +
                "        \"532924\": \"宾川县\",\n" +
                "        \"532925\": \"弥渡县\",\n" +
                "        \"532926\": \"南涧彝族自治县\",\n" +
                "        \"532927\": \"巍山彝族回族自治县\",\n" +
                "        \"532928\": \"永平县\",\n" +
                "        \"532929\": \"云龙县\",\n" +
                "        \"532930\": \"洱源县\",\n" +
                "        \"532931\": \"剑川县\",\n" +
                "        \"532932\": \"鹤庆县\"\n" +
                "    },\n" +
                "    \"533100\": {\n" +
                "        \"533102\": \"瑞丽市\",\n" +
                "        \"533103\": \"芒市\",\n" +
                "        \"533122\": \"梁河县\",\n" +
                "        \"533123\": \"盈江县\",\n" +
                "        \"533124\": \"陇川县\"\n" +
                "    },\n" +
                "    \"533300\": {\n" +
                "        \"533301\": \"泸水市\",\n" +
                "        \"533323\": \"福贡县\",\n" +
                "        \"533324\": \"贡山独龙族怒族自治县\",\n" +
                "        \"533325\": \"兰坪白族普米族自治县\"\n" +
                "    },\n" +
                "    \"533400\": {\n" +
                "        \"533401\": \"香格里拉市\",\n" +
                "        \"533422\": \"德钦县\",\n" +
                "        \"533423\": \"维西傈僳族自治县\"\n" +
                "    },\n" +
                "    \"540000\": {\n" +
                "        \"540100\": \"拉萨市\",\n" +
                "        \"540200\": \"日喀则市\",\n" +
                "        \"540300\": \"昌都市\",\n" +
                "        \"540400\": \"林芝市\",\n" +
                "        \"540500\": \"山南市\",\n" +
                "        \"542400\": \"那曲地区\",\n" +
                "        \"542500\": \"阿里地区\"\n" +
                "    },\n" +
                "    \"540100\": {\n" +
                "        \"540102\": \"城关区\",\n" +
                "        \"540103\": \"堆龙德庆区\",\n" +
                "        \"540121\": \"林周县\",\n" +
                "        \"540122\": \"当雄县\",\n" +
                "        \"540123\": \"尼木县\",\n" +
                "        \"540124\": \"曲水县\",\n" +
                "        \"540126\": \"达孜县\",\n" +
                "        \"540127\": \"墨竹工卡县\"\n" +
                "    },\n" +
                "    \"540200\": {\n" +
                "        \"540202\": \"桑珠孜区\",\n" +
                "        \"540221\": \"南木林县\",\n" +
                "        \"540222\": \"江孜县\",\n" +
                "        \"540223\": \"定日县\",\n" +
                "        \"540224\": \"萨迦县\",\n" +
                "        \"540225\": \"拉孜县\",\n" +
                "        \"540226\": \"昂仁县\",\n" +
                "        \"540227\": \"谢通门县\",\n" +
                "        \"540228\": \"白朗县\",\n" +
                "        \"540229\": \"仁布县\",\n" +
                "        \"540230\": \"康马县\",\n" +
                "        \"540231\": \"定结县\",\n" +
                "        \"540232\": \"仲巴县\",\n" +
                "        \"540233\": \"亚东县\",\n" +
                "        \"540234\": \"吉隆县\",\n" +
                "        \"540235\": \"聂拉木县\",\n" +
                "        \"540236\": \"萨嘎县\",\n" +
                "        \"540237\": \"岗巴县\"\n" +
                "    },\n" +
                "    \"540300\": {\n" +
                "        \"540302\": \"卡若区\",\n" +
                "        \"540321\": \"江达县\",\n" +
                "        \"540322\": \"贡觉县\",\n" +
                "        \"540323\": \"类乌齐县\",\n" +
                "        \"540324\": \"丁青县\",\n" +
                "        \"540325\": \"察雅县\",\n" +
                "        \"540326\": \"八宿县\",\n" +
                "        \"540327\": \"左贡县\",\n" +
                "        \"540328\": \"芒康县\",\n" +
                "        \"540329\": \"洛隆县\",\n" +
                "        \"540330\": \"边坝县\"\n" +
                "    },\n" +
                "    \"540400\": {\n" +
                "        \"540402\": \"巴宜区\",\n" +
                "        \"540421\": \"工布江达县\",\n" +
                "        \"540422\": \"米林县\",\n" +
                "        \"540423\": \"墨脱县\",\n" +
                "        \"540424\": \"波密县\",\n" +
                "        \"540425\": \"察隅县\",\n" +
                "        \"540426\": \"朗县\"\n" +
                "    },\n" +
                "    \"540500\": {\n" +
                "        \"540502\": \"乃东区\",\n" +
                "        \"540521\": \"扎囊县\",\n" +
                "        \"540522\": \"贡嘎县\",\n" +
                "        \"540523\": \"桑日县\",\n" +
                "        \"540524\": \"琼结县\",\n" +
                "        \"540525\": \"曲松县\",\n" +
                "        \"540526\": \"措美县\",\n" +
                "        \"540527\": \"洛扎县\",\n" +
                "        \"540528\": \"加查县\",\n" +
                "        \"540529\": \"隆子县\",\n" +
                "        \"540530\": \"错那县\",\n" +
                "        \"540531\": \"浪卡子县\"\n" +
                "    },\n" +
                "    \"542400\": {\n" +
                "        \"542421\": \"那曲县\",\n" +
                "        \"542422\": \"嘉黎县\",\n" +
                "        \"542423\": \"比如县\",\n" +
                "        \"542424\": \"聂荣县\",\n" +
                "        \"542425\": \"安多县\",\n" +
                "        \"542426\": \"申扎县\",\n" +
                "        \"542427\": \"索县\",\n" +
                "        \"542428\": \"班戈县\",\n" +
                "        \"542429\": \"巴青县\",\n" +
                "        \"542430\": \"尼玛县\",\n" +
                "        \"542431\": \"双湖县\"\n" +
                "    },\n" +
                "    \"542500\": {\n" +
                "        \"542521\": \"普兰县\",\n" +
                "        \"542522\": \"札达县\",\n" +
                "        \"542523\": \"噶尔县\",\n" +
                "        \"542524\": \"日土县\",\n" +
                "        \"542525\": \"革吉县\",\n" +
                "        \"542526\": \"改则县\",\n" +
                "        \"542527\": \"措勤县\"\n" +
                "    },\n" +
                "    \"610000\": {\n" +
                "        \"610100\": \"西安市\",\n" +
                "        \"610200\": \"铜川市\",\n" +
                "        \"610300\": \"宝鸡市\",\n" +
                "        \"610400\": \"咸阳市\",\n" +
                "        \"610500\": \"渭南市\",\n" +
                "        \"610600\": \"延安市\",\n" +
                "        \"610700\": \"汉中市\",\n" +
                "        \"610800\": \"榆林市\",\n" +
                "        \"610900\": \"安康市\",\n" +
                "        \"611000\": \"商洛市\"\n" +
                "    },\n" +
                "    \"610100\": {\n" +
                "        \"610102\": \"新城区\",\n" +
                "        \"610103\": \"碑林区\",\n" +
                "        \"610104\": \"莲湖区\",\n" +
                "        \"610111\": \"灞桥区\",\n" +
                "        \"610112\": \"未央区\",\n" +
                "        \"610113\": \"雁塔区\",\n" +
                "        \"610114\": \"阎良区\",\n" +
                "        \"610115\": \"临潼区\",\n" +
                "        \"610116\": \"长安区\",\n" +
                "        \"610117\": \"高陵区\",\n" +
                "        \"610122\": \"蓝田县\",\n" +
                "        \"610124\": \"周至县\",\n" +
                "        \"610125\": \"户县\"\n" +
                "    },\n" +
                "    \"610200\": {\n" +
                "        \"610202\": \"王益区\",\n" +
                "        \"610203\": \"印台区\",\n" +
                "        \"610204\": \"耀州区\",\n" +
                "        \"610222\": \"宜君县\"\n" +
                "    },\n" +
                "    \"610300\": {\n" +
                "        \"610302\": \"渭滨区\",\n" +
                "        \"610303\": \"金台区\",\n" +
                "        \"610304\": \"陈仓区\",\n" +
                "        \"610322\": \"凤翔县\",\n" +
                "        \"610323\": \"岐山县\",\n" +
                "        \"610324\": \"扶风县\",\n" +
                "        \"610326\": \"眉县\",\n" +
                "        \"610327\": \"陇县\",\n" +
                "        \"610328\": \"千阳县\",\n" +
                "        \"610329\": \"麟游县\",\n" +
                "        \"610330\": \"凤县\",\n" +
                "        \"610331\": \"太白县\"\n" +
                "    },\n" +
                "    \"610400\": {\n" +
                "        \"610402\": \"秦都区\",\n" +
                "        \"610403\": \"杨陵区\",\n" +
                "        \"610404\": \"渭城区\",\n" +
                "        \"610422\": \"三原县\",\n" +
                "        \"610423\": \"泾阳县\",\n" +
                "        \"610424\": \"乾县\",\n" +
                "        \"610425\": \"礼泉县\",\n" +
                "        \"610426\": \"永寿县\",\n" +
                "        \"610427\": \"彬县\",\n" +
                "        \"610428\": \"长武县\",\n" +
                "        \"610429\": \"旬邑县\",\n" +
                "        \"610430\": \"淳化县\",\n" +
                "        \"610431\": \"武功县\",\n" +
                "        \"610481\": \"兴平市\"\n" +
                "    },\n" +
                "    \"610500\": {\n" +
                "        \"610502\": \"临渭区\",\n" +
                "        \"610503\": \"华州区\",\n" +
                "        \"610522\": \"潼关县\",\n" +
                "        \"610523\": \"大荔县\",\n" +
                "        \"610524\": \"合阳县\",\n" +
                "        \"610525\": \"澄城县\",\n" +
                "        \"610526\": \"蒲城县\",\n" +
                "        \"610527\": \"白水县\",\n" +
                "        \"610528\": \"富平县\",\n" +
                "        \"610581\": \"韩城市\",\n" +
                "        \"610582\": \"华阴市\"\n" +
                "    },\n" +
                "    \"610600\": {\n" +
                "        \"610602\": \"宝塔区\",\n" +
                "        \"610603\": \"安塞区\",\n" +
                "        \"610621\": \"延长县\",\n" +
                "        \"610622\": \"延川县\",\n" +
                "        \"610623\": \"子长县\",\n" +
                "        \"610625\": \"志丹县\",\n" +
                "        \"610626\": \"吴起县\",\n" +
                "        \"610627\": \"甘泉县\",\n" +
                "        \"610628\": \"富县\",\n" +
                "        \"610629\": \"洛川县\",\n" +
                "        \"610630\": \"宜川县\",\n" +
                "        \"610631\": \"黄龙县\",\n" +
                "        \"610632\": \"黄陵县\"\n" +
                "    },\n" +
                "    \"610700\": {\n" +
                "        \"610702\": \"汉台区\",\n" +
                "        \"610721\": \"南郑县\",\n" +
                "        \"610722\": \"城固县\",\n" +
                "        \"610723\": \"洋县\",\n" +
                "        \"610724\": \"西乡县\",\n" +
                "        \"610725\": \"勉县\",\n" +
                "        \"610726\": \"宁强县\",\n" +
                "        \"610727\": \"略阳县\",\n" +
                "        \"610728\": \"镇巴县\",\n" +
                "        \"610729\": \"留坝县\",\n" +
                "        \"610730\": \"佛坪县\"\n" +
                "    },\n" +
                "    \"610800\": {\n" +
                "        \"610802\": \"榆阳区\",\n" +
                "        \"610803\": \"横山区\",\n" +
                "        \"610821\": \"神木县\",\n" +
                "        \"610822\": \"府谷县\",\n" +
                "        \"610824\": \"靖边县\",\n" +
                "        \"610825\": \"定边县\",\n" +
                "        \"610826\": \"绥德县\",\n" +
                "        \"610827\": \"米脂县\",\n" +
                "        \"610828\": \"佳县\",\n" +
                "        \"610829\": \"吴堡县\",\n" +
                "        \"610830\": \"清涧县\",\n" +
                "        \"610831\": \"子洲县\"\n" +
                "    },\n" +
                "    \"610900\": {\n" +
                "        \"610902\": \"汉滨区\",\n" +
                "        \"610921\": \"汉阴县\",\n" +
                "        \"610922\": \"石泉县\",\n" +
                "        \"610923\": \"宁陕县\",\n" +
                "        \"610924\": \"紫阳县\",\n" +
                "        \"610925\": \"岚皋县\",\n" +
                "        \"610926\": \"平利县\",\n" +
                "        \"610927\": \"镇坪县\",\n" +
                "        \"610928\": \"旬阳县\",\n" +
                "        \"610929\": \"白河县\"\n" +
                "    },\n" +
                "    \"611000\": {\n" +
                "        \"611002\": \"商州区\",\n" +
                "        \"611021\": \"洛南县\",\n" +
                "        \"611022\": \"丹凤县\",\n" +
                "        \"611023\": \"商南县\",\n" +
                "        \"611024\": \"山阳县\",\n" +
                "        \"611025\": \"镇安县\",\n" +
                "        \"611026\": \"柞水县\"\n" +
                "    },\n" +
                "    \"620000\": {\n" +
                "        \"620100\": \"兰州市\",\n" +
                "        \"620200\": \"嘉峪关市\",\n" +
                "        \"620300\": \"金昌市\",\n" +
                "        \"620400\": \"白银市\",\n" +
                "        \"620500\": \"天水市\",\n" +
                "        \"620600\": \"武威市\",\n" +
                "        \"620700\": \"张掖市\",\n" +
                "        \"620800\": \"平凉市\",\n" +
                "        \"620900\": \"酒泉市\",\n" +
                "        \"621000\": \"庆阳市\",\n" +
                "        \"621100\": \"定西市\",\n" +
                "        \"621200\": \"陇南市\",\n" +
                "        \"622900\": \"临夏回族自治州\",\n" +
                "        \"623000\": \"甘南藏族自治州\"\n" +
                "    },\n" +
                "    \"620100\": {\n" +
                "        \"620102\": \"城关区\",\n" +
                "        \"620103\": \"七里河区\",\n" +
                "        \"620104\": \"西固区\",\n" +
                "        \"620105\": \"安宁区\",\n" +
                "        \"620111\": \"红古区\",\n" +
                "        \"620121\": \"永登县\",\n" +
                "        \"620122\": \"皋兰县\",\n" +
                "        \"620123\": \"榆中县\"\n" +
                "    },\n" +
                "    \"620300\": {\n" +
                "        \"620302\": \"金川区\",\n" +
                "        \"620321\": \"永昌县\"\n" +
                "    },\n" +
                "    \"620400\": {\n" +
                "        \"620402\": \"白银区\",\n" +
                "        \"620403\": \"平川区\",\n" +
                "        \"620421\": \"靖远县\",\n" +
                "        \"620422\": \"会宁县\",\n" +
                "        \"620423\": \"景泰县\"\n" +
                "    },\n" +
                "    \"620500\": {\n" +
                "        \"620502\": \"秦州区\",\n" +
                "        \"620503\": \"麦积区\",\n" +
                "        \"620521\": \"清水县\",\n" +
                "        \"620522\": \"秦安县\",\n" +
                "        \"620523\": \"甘谷县\",\n" +
                "        \"620524\": \"武山县\",\n" +
                "        \"620525\": \"张家川回族自治县\"\n" +
                "    },\n" +
                "    \"620600\": {\n" +
                "        \"620602\": \"凉州区\",\n" +
                "        \"620621\": \"民勤县\",\n" +
                "        \"620622\": \"古浪县\",\n" +
                "        \"620623\": \"天祝藏族自治县\"\n" +
                "    },\n" +
                "    \"620700\": {\n" +
                "        \"620702\": \"甘州区\",\n" +
                "        \"620721\": \"肃南裕固族自治县\",\n" +
                "        \"620722\": \"民乐县\",\n" +
                "        \"620723\": \"临泽县\",\n" +
                "        \"620724\": \"高台县\",\n" +
                "        \"620725\": \"山丹县\"\n" +
                "    },\n" +
                "    \"620800\": {\n" +
                "        \"620802\": \"崆峒区\",\n" +
                "        \"620821\": \"泾川县\",\n" +
                "        \"620822\": \"灵台县\",\n" +
                "        \"620823\": \"崇信县\",\n" +
                "        \"620824\": \"华亭县\",\n" +
                "        \"620825\": \"庄浪县\",\n" +
                "        \"620826\": \"静宁县\"\n" +
                "    },\n" +
                "    \"620900\": {\n" +
                "        \"620902\": \"肃州区\",\n" +
                "        \"620921\": \"金塔县\",\n" +
                "        \"620922\": \"瓜州县\",\n" +
                "        \"620923\": \"肃北蒙古族自治县\",\n" +
                "        \"620924\": \"阿克塞哈萨克族自治县\",\n" +
                "        \"620981\": \"玉门市\",\n" +
                "        \"620982\": \"敦煌市\"\n" +
                "    },\n" +
                "    \"621000\": {\n" +
                "        \"621002\": \"西峰区\",\n" +
                "        \"621021\": \"庆城县\",\n" +
                "        \"621022\": \"环县\",\n" +
                "        \"621023\": \"华池县\",\n" +
                "        \"621024\": \"合水县\",\n" +
                "        \"621025\": \"正宁县\",\n" +
                "        \"621026\": \"宁县\",\n" +
                "        \"621027\": \"镇原县\"\n" +
                "    },\n" +
                "    \"621100\": {\n" +
                "        \"621102\": \"安定区\",\n" +
                "        \"621121\": \"通渭县\",\n" +
                "        \"621122\": \"陇西县\",\n" +
                "        \"621123\": \"渭源县\",\n" +
                "        \"621124\": \"临洮县\",\n" +
                "        \"621125\": \"漳县\",\n" +
                "        \"621126\": \"岷县\"\n" +
                "    },\n" +
                "    \"621200\": {\n" +
                "        \"621202\": \"武都区\",\n" +
                "        \"621221\": \"成县\",\n" +
                "        \"621222\": \"文县\",\n" +
                "        \"621223\": \"宕昌县\",\n" +
                "        \"621224\": \"康县\",\n" +
                "        \"621225\": \"西和县\",\n" +
                "        \"621226\": \"礼县\",\n" +
                "        \"621227\": \"徽县\",\n" +
                "        \"621228\": \"两当县\"\n" +
                "    },\n" +
                "    \"622900\": {\n" +
                "        \"622901\": \"临夏市\",\n" +
                "        \"622921\": \"临夏县\",\n" +
                "        \"622922\": \"康乐县\",\n" +
                "        \"622923\": \"永靖县\",\n" +
                "        \"622924\": \"广河县\",\n" +
                "        \"622925\": \"和政县\",\n" +
                "        \"622926\": \"东乡族自治县\",\n" +
                "        \"622927\": \"积石山保安族东乡族撒拉族自治县\"\n" +
                "    },\n" +
                "    \"623000\": {\n" +
                "        \"623001\": \"合作市\",\n" +
                "        \"623021\": \"临潭县\",\n" +
                "        \"623022\": \"卓尼县\",\n" +
                "        \"623023\": \"舟曲县\",\n" +
                "        \"623024\": \"迭部县\",\n" +
                "        \"623025\": \"玛曲县\",\n" +
                "        \"623026\": \"碌曲县\",\n" +
                "        \"623027\": \"夏河县\"\n" +
                "    },\n" +
                "    \"630000\": {\n" +
                "        \"630100\": \"西宁市\",\n" +
                "        \"630200\": \"海东市\",\n" +
                "        \"632200\": \"海北藏族自治州\",\n" +
                "        \"632300\": \"黄南藏族自治州\",\n" +
                "        \"632500\": \"海南藏族自治州\",\n" +
                "        \"632600\": \"果洛藏族自治州\",\n" +
                "        \"632700\": \"玉树藏族自治州\",\n" +
                "        \"632800\": \"海西蒙古族藏族自治州\"\n" +
                "    },\n" +
                "    \"630100\": {\n" +
                "        \"630102\": \"城东区\",\n" +
                "        \"630103\": \"城中区\",\n" +
                "        \"630104\": \"城西区\",\n" +
                "        \"630105\": \"城北区\",\n" +
                "        \"630121\": \"大通回族土族自治县\",\n" +
                "        \"630122\": \"湟中县\",\n" +
                "        \"630123\": \"湟源县\"\n" +
                "    },\n" +
                "    \"630200\": {\n" +
                "        \"630202\": \"乐都区\",\n" +
                "        \"630203\": \"平安区\",\n" +
                "        \"630222\": \"民和回族土族自治县\",\n" +
                "        \"630223\": \"互助土族自治县\",\n" +
                "        \"630224\": \"化隆回族自治县\",\n" +
                "        \"630225\": \"循化撒拉族自治县\"\n" +
                "    },\n" +
                "    \"632200\": {\n" +
                "        \"632221\": \"门源回族自治县\",\n" +
                "        \"632222\": \"祁连县\",\n" +
                "        \"632223\": \"海晏县\",\n" +
                "        \"632224\": \"刚察县\"\n" +
                "    },\n" +
                "    \"632300\": {\n" +
                "        \"632321\": \"同仁县\",\n" +
                "        \"632322\": \"尖扎县\",\n" +
                "        \"632323\": \"泽库县\",\n" +
                "        \"632324\": \"河南蒙古族自治县\"\n" +
                "    },\n" +
                "    \"632500\": {\n" +
                "        \"632521\": \"共和县\",\n" +
                "        \"632522\": \"同德县\",\n" +
                "        \"632523\": \"贵德县\",\n" +
                "        \"632524\": \"兴海县\",\n" +
                "        \"632525\": \"贵南县\"\n" +
                "    },\n" +
                "    \"632600\": {\n" +
                "        \"632621\": \"玛沁县\",\n" +
                "        \"632622\": \"班玛县\",\n" +
                "        \"632623\": \"甘德县\",\n" +
                "        \"632624\": \"达日县\",\n" +
                "        \"632625\": \"久治县\",\n" +
                "        \"632626\": \"玛多县\"\n" +
                "    },\n" +
                "    \"632700\": {\n" +
                "        \"632701\": \"玉树市\",\n" +
                "        \"632722\": \"杂多县\",\n" +
                "        \"632723\": \"称多县\",\n" +
                "        \"632724\": \"治多县\",\n" +
                "        \"632725\": \"囊谦县\",\n" +
                "        \"632726\": \"曲麻莱县\"\n" +
                "    },\n" +
                "    \"632800\": {\n" +
                "        \"632801\": \"格尔木市\",\n" +
                "        \"632802\": \"德令哈市\",\n" +
                "        \"632821\": \"乌兰县\",\n" +
                "        \"632822\": \"都兰县\",\n" +
                "        \"632823\": \"天峻县\"\n" +
                "    },\n" +
                "    \"640000\": {\n" +
                "        \"640100\": \"银川市\",\n" +
                "        \"640200\": \"石嘴山市\",\n" +
                "        \"640300\": \"吴忠市\",\n" +
                "        \"640400\": \"固原市\",\n" +
                "        \"640500\": \"中卫市\"\n" +
                "    },\n" +
                "    \"640100\": {\n" +
                "        \"640104\": \"兴庆区\",\n" +
                "        \"640105\": \"西夏区\",\n" +
                "        \"640106\": \"金凤区\",\n" +
                "        \"640121\": \"永宁县\",\n" +
                "        \"640122\": \"贺兰县\",\n" +
                "        \"640181\": \"灵武市\"\n" +
                "    },\n" +
                "    \"640200\": {\n" +
                "        \"640202\": \"大武口区\",\n" +
                "        \"640205\": \"惠农区\",\n" +
                "        \"640221\": \"平罗县\"\n" +
                "    },\n" +
                "    \"640300\": {\n" +
                "        \"640302\": \"利通区\",\n" +
                "        \"640303\": \"红寺堡区\",\n" +
                "        \"640323\": \"盐池县\",\n" +
                "        \"640324\": \"同心县\",\n" +
                "        \"640381\": \"青铜峡市\"\n" +
                "    },\n" +
                "    \"640400\": {\n" +
                "        \"640402\": \"原州区\",\n" +
                "        \"640422\": \"西吉县\",\n" +
                "        \"640423\": \"隆德县\",\n" +
                "        \"640424\": \"泾源县\",\n" +
                "        \"640425\": \"彭阳县\"\n" +
                "    },\n" +
                "    \"640500\": {\n" +
                "        \"640502\": \"沙坡头区\",\n" +
                "        \"640521\": \"中宁县\",\n" +
                "        \"640522\": \"海原县\"\n" +
                "    },\n" +
                "    \"650000\": {\n" +
                "        \"650100\": \"乌鲁木齐市\",\n" +
                "        \"650200\": \"克拉玛依市\",\n" +
                "        \"650400\": \"吐鲁番市\",\n" +
                "        \"650500\": \"哈密市\",\n" +
                "        \"652300\": \"昌吉回族自治州\",\n" +
                "        \"652700\": \"博尔塔拉蒙古自治州\",\n" +
                "        \"652800\": \"巴音郭楞蒙古自治州\",\n" +
                "        \"652900\": \"阿克苏地区\",\n" +
                "        \"653000\": \"克孜勒苏柯尔克孜自治州\",\n" +
                "        \"653100\": \"喀什地区\",\n" +
                "        \"653200\": \"和田地区\",\n" +
                "        \"654000\": \"伊犁哈萨克自治州\",\n" +
                "        \"654200\": \"塔城地区\",\n" +
                "        \"654300\": \"阿勒泰地区\",\n" +
                "        \"659001\": \"石河子市\",\n" +
                "        \"659002\": \"阿拉尔市\",\n" +
                "        \"659003\": \"图木舒克市\",\n" +
                "        \"659004\": \"五家渠市\",\n" +
                "        \"659006\": \"铁门关市\"\n" +
                "    },\n" +
                "    \"650100\": {\n" +
                "        \"650102\": \"天山区\",\n" +
                "        \"650103\": \"沙依巴克区\",\n" +
                "        \"650104\": \"新市区\",\n" +
                "        \"650105\": \"水磨沟区\",\n" +
                "        \"650106\": \"头屯河区\",\n" +
                "        \"650107\": \"达坂城区\",\n" +
                "        \"650109\": \"米东区\",\n" +
                "        \"650121\": \"乌鲁木齐县\"\n" +
                "    },\n" +
                "    \"650200\": {\n" +
                "        \"650202\": \"独山子区\",\n" +
                "        \"650203\": \"克拉玛依区\",\n" +
                "        \"650204\": \"白碱滩区\",\n" +
                "        \"650205\": \"乌尔禾区\"\n" +
                "    },\n" +
                "    \"650400\": {\n" +
                "        \"650402\": \"高昌区\",\n" +
                "        \"650421\": \"鄯善县\",\n" +
                "        \"650422\": \"托克逊县\"\n" +
                "    },\n" +
                "    \"650500\": {\n" +
                "        \"650502\": \"伊州区\",\n" +
                "        \"650521\": \"巴里坤哈萨克自治县\",\n" +
                "        \"650522\": \"伊吾县\"\n" +
                "    },\n" +
                "    \"652300\": {\n" +
                "        \"652301\": \"昌吉市\",\n" +
                "        \"652302\": \"阜康市\",\n" +
                "        \"652323\": \"呼图壁县\",\n" +
                "        \"652324\": \"玛纳斯县\",\n" +
                "        \"652325\": \"奇台县\",\n" +
                "        \"652327\": \"吉木萨尔县\",\n" +
                "        \"652328\": \"木垒哈萨克自治县\"\n" +
                "    },\n" +
                "    \"652700\": {\n" +
                "        \"652701\": \"博乐市\",\n" +
                "        \"652702\": \"阿拉山口市\",\n" +
                "        \"652722\": \"精河县\",\n" +
                "        \"652723\": \"温泉县\"\n" +
                "    },\n" +
                "    \"652800\": {\n" +
                "        \"652801\": \"库尔勒市\",\n" +
                "        \"652822\": \"轮台县\",\n" +
                "        \"652823\": \"尉犁县\",\n" +
                "        \"652824\": \"若羌县\",\n" +
                "        \"652825\": \"且末县\",\n" +
                "        \"652826\": \"焉耆回族自治县\",\n" +
                "        \"652827\": \"和静县\",\n" +
                "        \"652828\": \"和硕县\",\n" +
                "        \"652829\": \"博湖县\"\n" +
                "    },\n" +
                "    \"652900\": {\n" +
                "        \"652901\": \"阿克苏市\",\n" +
                "        \"652922\": \"温宿县\",\n" +
                "        \"652923\": \"库车县\",\n" +
                "        \"652924\": \"沙雅县\",\n" +
                "        \"652925\": \"新和县\",\n" +
                "        \"652926\": \"拜城县\",\n" +
                "        \"652927\": \"乌什县\",\n" +
                "        \"652928\": \"阿瓦提县\",\n" +
                "        \"652929\": \"柯坪县\"\n" +
                "    },\n" +
                "    \"653000\": {\n" +
                "        \"653001\": \"阿图什市\",\n" +
                "        \"653022\": \"阿克陶县\",\n" +
                "        \"653023\": \"阿合奇县\",\n" +
                "        \"653024\": \"乌恰县\"\n" +
                "    },\n" +
                "    \"653100\": {\n" +
                "        \"653101\": \"喀什市\",\n" +
                "        \"653121\": \"疏附县\",\n" +
                "        \"653122\": \"疏勒县\",\n" +
                "        \"653123\": \"英吉沙县\",\n" +
                "        \"653124\": \"泽普县\",\n" +
                "        \"653125\": \"莎车县\",\n" +
                "        \"653126\": \"叶城县\",\n" +
                "        \"653127\": \"麦盖提县\",\n" +
                "        \"653128\": \"岳普湖县\",\n" +
                "        \"653129\": \"伽师县\",\n" +
                "        \"653130\": \"巴楚县\",\n" +
                "        \"653131\": \"塔什库尔干塔吉克自治县\"\n" +
                "    },\n" +
                "    \"653200\": {\n" +
                "        \"653201\": \"和田市\",\n" +
                "        \"653221\": \"和田县\",\n" +
                "        \"653222\": \"墨玉县\",\n" +
                "        \"653223\": \"皮山县\",\n" +
                "        \"653224\": \"洛浦县\",\n" +
                "        \"653225\": \"策勒县\",\n" +
                "        \"653226\": \"于田县\",\n" +
                "        \"653227\": \"民丰县\"\n" +
                "    },\n" +
                "    \"654000\": {\n" +
                "        \"654002\": \"伊宁市\",\n" +
                "        \"654003\": \"奎屯市\",\n" +
                "        \"654004\": \"霍尔果斯市\",\n" +
                "        \"654021\": \"伊宁县\",\n" +
                "        \"654022\": \"察布查尔锡伯自治县\",\n" +
                "        \"654023\": \"霍城县\",\n" +
                "        \"654024\": \"巩留县\",\n" +
                "        \"654025\": \"新源县\",\n" +
                "        \"654026\": \"昭苏县\",\n" +
                "        \"654027\": \"特克斯县\",\n" +
                "        \"654028\": \"尼勒克县\"\n" +
                "    },\n" +
                "    \"654200\": {\n" +
                "        \"654201\": \"塔城市\",\n" +
                "        \"654202\": \"乌苏市\",\n" +
                "        \"654221\": \"额敏县\",\n" +
                "        \"654223\": \"沙湾县\",\n" +
                "        \"654224\": \"托里县\",\n" +
                "        \"654225\": \"裕民县\",\n" +
                "        \"654226\": \"和布克赛尔蒙古自治县\"\n" +
                "    },\n" +
                "    \"654300\": {\n" +
                "        \"654301\": \"阿勒泰市\",\n" +
                "        \"654321\": \"布尔津县\",\n" +
                "        \"654322\": \"富蕴县\",\n" +
                "        \"654323\": \"福海县\",\n" +
                "        \"654324\": \"哈巴河县\",\n" +
                "        \"654325\": \"青河县\",\n" +
                "        \"654326\": \"吉木乃县\"\n" +
                "    },\n" +
                "    \"810000\": {\n" +
                "        \"810001\": \"中西區\",\n" +
                "        \"810002\": \"灣仔區\",\n" +
                "        \"810003\": \"東區\",\n" +
                "        \"810004\": \"南區\",\n" +
                "        \"810005\": \"油尖旺區\",\n" +
                "        \"810006\": \"深水埗區\",\n" +
                "        \"810007\": \"九龍城區\",\n" +
                "        \"810008\": \"黃大仙區\",\n" +
                "        \"810009\": \"觀塘區\",\n" +
                "        \"810010\": \"荃灣區\",\n" +
                "        \"810011\": \"屯門區\",\n" +
                "        \"810012\": \"元朗區\",\n" +
                "        \"810013\": \"北區\",\n" +
                "        \"810014\": \"大埔區\",\n" +
                "        \"810015\": \"西貢區\",\n" +
                "        \"810016\": \"沙田區\",\n" +
                "        \"810017\": \"葵青區\",\n" +
                "        \"810018\": \"離島區\"\n" +
                "    },\n" +
                "    \"820000\": {\n" +
                "        \"820001\": \"花地瑪堂區\",\n" +
                "        \"820002\": \"花王堂區\",\n" +
                "        \"820003\": \"望德堂區\",\n" +
                "        \"820004\": \"大堂區\",\n" +
                "        \"820005\": \"風順堂區\",\n" +
                "        \"820006\": \"嘉模堂區\",\n" +
                "        \"820007\": \"路氹填海區\",\n" +
                "        \"820008\": \"聖方濟各堂區\"\n" +
                "    }\n" +
                "}");

        return JSONObject.parseObject(str.toString());
    }
}
