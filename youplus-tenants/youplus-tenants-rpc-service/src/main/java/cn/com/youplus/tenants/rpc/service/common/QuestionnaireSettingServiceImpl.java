package cn.com.youplus.tenants.rpc.service.common;

import cn.com.youplus.apps.api.auto.*;
import cn.com.youplus.base.common.QRCode.QRCodeUtil;
import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.model.base.BaseResponse;
import cn.com.youplus.common.model.enums.QuestionItemTypeEnum;
import cn.com.youplus.common.model.enums.QuestionTypeEnum;
import cn.com.youplus.common.model.enums.QuestionnaireStatusEnum;
import cn.com.youplus.common.model.enums.QuestionnaireTypeEnum;
import cn.com.youplus.common.model.resources.AliyunProperties;
import cn.com.youplus.common.model.resources.SystemConfig;
import cn.com.youplus.common.util.StringHelper;
import cn.com.youplus.model.auto.entity.apps.*;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsLevel;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsRegion;
import cn.com.youplus.tenants.api.auto.UpTenantsCompanyService;
import cn.com.youplus.tenants.api.auto.UpTenantsLevelService;
import cn.com.youplus.tenants.api.auto.UpTenantsRegionService;
import cn.com.youplus.tenants.api.common.QuestionnaireSettingService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.zxing.WriterException;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@Service("questionnaireSettingService")
public class QuestionnaireSettingServiceImpl implements QuestionnaireSettingService {

    //region autowire
    private static Logger logger = Logger.getLogger(QuestionnaireSettingServiceImpl.class);

    @Autowired
    private UpAppsQuestionService upAppsQuestionService;

    @Autowired
    private UpAppsQuestionnaireService upAppsQuestionnaireService;

    @Autowired
    private UpAppsQuestionnaireThemeService upAppsQuestionnaireThemeService;

    @Autowired
    private UpAppsQuestionItemService upAppsQuestionItemService;

    @Autowired
    private UpAppsQuestionnaireAttributeService upAppsQuestionnaireAttributeService;

    @Autowired
    private QuestionnaireSettingService questionnaireSettingService;

    @Autowired
    private UpTenantsCompanyService upTenantsCompanyService;

    @Autowired
    private UpTenantsLevelService upTenantsLevelService;

    @Autowired
    private UpTenantsRegionService upTenantsRegionService;

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private OSSClient ossClient;

    @Autowired
    private AliyunProperties aliyunProperties;

    @Autowired
    private TaskExecutor taskExecutor;

//    @Autowired
//    private AliyunOssService aliyunOssService;

    //endregion

    public static final String QUESTIONNAIRE_ID_KEY = "id";
    public static final String QUESTION_ARRAY_KEY = "list";
    public static final String QUESTION_ITEM_ARRAY_KEY = "list";

    boolean isExists(List<String> set, Long id) {
        String idstr = id.toString();
        for (String i : set) {
            if (idstr.equals(i)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Long copyQuestionnaire(Long questionnaireId, String title, UpTenantsCompany company) throws AlertException {

        UpAppsQuestionnaire oldQuestionnaire = upAppsQuestionnaireService.selectById(questionnaireId);
        UpAppsQuestionnaire newQuestionnaire = new UpAppsQuestionnaire();
        newQuestionnaire.setProjectName(title)
                .setTenantsCompanyId(company.getId())
                .setAddTime(new Date())
                .setUpdateTime(new Date());
        newQuestionnaire = upAppsQuestionnaireService.mInsert(newQuestionnaire);
        Map<String, Object> data = getQuestionnaireData(oldQuestionnaire);

        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(data));
        jsonObject.put("themeId", "0");
        jsonObject.put("id", newQuestionnaire.getId().toString());
//        jsonObject.put("title", title);
        JSONArray list = jsonObject.getJSONArray("list");
        for (Object questionObject : list) {
            JSONObject question = (JSONObject) questionObject;
            question.put("id", "0");
            JSONObject displayRule = JSONObject.parseObject(question.getString("displayRule"));
            question.put("displayRule", displayRule);
            JSONArray items = question.getJSONArray("list");
            for (Object itemObject : items) {
                JSONObject item = (JSONObject) itemObject;
                item.put("id", "0");
            }
        }
        editQuestionnaire(jsonObject.toJSONString(), company, true);

        return newQuestionnaire.getId();
    }

    @Override
    public Map<String, Object> getQuestionnaireData(UpAppsQuestionnaire questionnaire){
        UpAppsQuestionnaireTheme theme = upAppsQuestionnaireThemeService.selectById(questionnaire.getThemeId());

        if (theme == null) {
            theme = new UpAppsQuestionnaireTheme();
        }
        Map<String, Object> result = new HashMap<>();
        //预览二维码
        UpTenantsCompany company = upTenantsCompanyService.selectById(questionnaire.getTenantsCompanyId());
        try {
            String prefix = systemConfig.getWebUrl() + "/#/welcome/";
            //prefix = String.format(prefix, company.getDomainName());
            String suffix = URLEncoder.encode("{\"q\":\"" + questionnaire.getId() + "\",\"r\":111,\"entrance\":\"LINK\",\"preview_mode\":true}", "utf-8");
            String url = prefix + suffix;
            result.put("QRCodeImg", questionnaireSettingService.UrlToQrcodeAndUpload(url, "png", 256));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //主题相关
        result.put("alreadySubmitImg", theme.getAlreadySubmitImg());
        result.put("bannerImg", theme.getBannerImg());
        result.put("startImg", theme.getStartImg());
        result.put("endImg", theme.getEndImg());
        result.put("selectIcon", theme.getSelectIcon());
        result.put("unselectIcon", theme.getUnselectIcon());
        result.put("themeColor", theme.getThemeColor());
        //问卷
        result.put("title", questionnaire.getTitle());
        result.put("themeId", questionnaire.getThemeId() + "");
        result.put("id", questionnaire.getId() + "");
        result.put("statusCode", QuestionnaireStatusEnum.valueOf(questionnaire.getStatus()).getCode());
        //题目
        List<UpAppsQuestion> questions = upAppsQuestionService.selectList(
                new EntityWrapper<UpAppsQuestion>()
                        .eq(UpAppsQuestion.QUESTIONNAIRE_ID, questionnaire.getId())
                        .orderBy(UpAppsQuestion.QUESTION_ORDER)
                        .orderBy(UpAppsQuestion.ADD_TIME, false)
        );

        List<Map<String, Object>> questList = new ArrayList<>();
        for(UpAppsQuestion question : questions) {
            Map<String, Object> q = new HashMap<>();
            q.put("id", question.getId() + "");
            q.put("Randomsort", question.getIsRandomSort() > 0);
            q.put("required", question.getIsRequired() > 0);
            q.put("order", question.getQuestionOrder());
            q.put("type", QuestionTypeEnum.valueOf(question.getQuestionType()).getCode());
            q.put("unique", question.getUniqueKey());
            q.put("title", question.getTitle());
            q.put("subTitle", question.getSubTitle());
            try {
                JSONObject rule = JSONObject.parseObject(question.getDisplayRule());
                q.put("displayRule", rule);
            } catch (Exception e) {
                logger.debug("", e);
                logger.info("解析错误");
                q.put("displayRule", "");
            }
            q.put("displayRule", question.getDisplayRule());

            List<Map<String, Object>> items = new ArrayList<>();
            try {
                JSONArray itemsObject = JSONObject.parseArray(question.getQuestionJson());

                for(int i = 0; i < itemsObject.size(); i++) {
                    JSONObject jsonObject = (JSONObject)itemsObject.get(i);
                    Map<String, Object> item = new HashMap<>();
                    item.put("id",jsonObject.getString("id"));
                    item.put("unique",jsonObject.getString("uniqueKey"));
                    item.put("questionItemType",jsonObject.getString("questionItemType"));
                    item.put("key",jsonObject.getString("key"));
                    item.put("value",jsonObject.getString("value"));
                    item.put("display",jsonObject.getString("display"));
                    item.put("isRequired", jsonObject.getString("isRequired"));
                    item.put("score", jsonObject.getString("score"));

                    //填空题
                    item.put("editorType", jsonObject.getString("editorType"));
                    item.put("placeholder",jsonObject.getString("placeholder"));
                    item.put("minLength", jsonObject.getString("minLength"));
                    item.put("maxLength", jsonObject.getString("maxLength"));

                    //附加填空题
                    item.put("isShowEditor", jsonObject.getIntValue("isShowEditor") > 0);
                    item.put("isEditorRequired", jsonObject.getIntValue("isEditorRequired") > 0);
                    item.put("editorEditorType",jsonObject.getString("editorEditorType"));
                    item.put("editorPlaceholder",jsonObject.getString("editorPlaceholder"));
                    item.put("editorMinLength", jsonObject.getString("editorMinLength"));
                    item.put("editorMaxLength", jsonObject.getString("editorMaxLength"));
                    items.add(item);
                }
                q.put("list", items);
            } catch (Exception e) {
                logger.debug("", e);
                logger.info("解析错误");
            }
            questList.add(q);
        }
        result.put("list", questList);
        return result;
    }

    @Override
    public Map<String, UpAppsQuestionnaireAttribute> getQuestionnaireAttrMap(Long questionnaireId) {
        List<UpAppsQuestionnaireAttribute> attributeList = upAppsQuestionnaireAttributeService.selectList(
                new EntityWrapper<UpAppsQuestionnaireAttribute>()
                        .eq(UpAppsQuestionnaireAttribute.QUESTIONNAIRE_ID, questionnaireId)
        );
        Map<String, UpAppsQuestionnaireAttribute> map = new HashMap<>();
        for (UpAppsQuestionnaireAttribute attribute : attributeList) {
            map.put(attribute.getAttributeName(), attribute);
        }
        return map;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public JSONObject editQuestionnaire(String jsonStr, UpTenantsCompany company, boolean isVip) throws AlertException {
        JSONObject json = JSONObject.parseObject(jsonStr);
        Long qId = 0L, themeId = 0L;
        try {
            qId = json.getLongValue(QUESTIONNAIRE_ID_KEY);
            themeId = json.getLongValue("themeId");
        } catch (Exception e) {
            //do nothing
        }

        UpAppsQuestionnaireTheme theme = null;
        if (themeId > 0) {
            theme = upAppsQuestionnaireThemeService.selectById(themeId);
        }
        if (theme == null) {
            theme = new UpAppsQuestionnaireTheme();
            theme.setAddTime(new Date());
        }
        theme.setBkgImg(json.getString("backgroundImg"))   //背景图片
                .setIconImg(json.getString("iconImg"))   //icon图片
                .setThemeColor(json.getString("themeColor"))    //主题颜色
                .setStartImg(json.getString("startImg"))
                .setEndImg(json.getString("endImg"))
                .setBannerImg(json.getString("bannerImg"))
                .setAlreadySubmitImg(json.getString("alreadySubmitImg"))
                .setSelectIcon(json.getString("selectIcon"))
                .setUnselectIcon(json.getString("unselectIcon"))
                .setBkgColor(json.getString("backgroundColor"))  //背景颜色
//                .setSelectedColor() //选中颜色
//                .setUnselectedColor()   //未选中颜色
                .setUpdateTime(new Date());

        if (theme.getId() == null) {
            theme = upAppsQuestionnaireThemeService.mInsert(theme);
            logger.info("新建主题");
            logger.info(theme);
        } else {
            upAppsQuestionnaireThemeService.updateById(theme);
            logger.info("更新主题");
            logger.info(theme);
        }

        int questionNum = 0;
        int requiredNum = 0;

        UpAppsQuestionnaire questionnaire = null;
        if (qId > 0) {
            questionnaire = upAppsQuestionnaireService.selectById(qId);
        }
        if (questionnaire == null) {
            questionnaire = new UpAppsQuestionnaire();
            questionnaire.setAddTime(new Date());
            questionnaire.setStatus(QuestionnaireStatusEnum.编辑中.getType()); //新建的需要时编辑中
        }
        questionnaire.setTitle(json.getString("title"))
                .setTenantsCompanyId(company.getId())
//                .setSubTitle(json.getString("subTitle"))  //副标题
//                .setDescription()   //描述
//                .setQuickTag()
//                .setPeriodJson()
//                .setServiceTypeJson()
//                .setSiteName(company.getName())  //网点名称
//                .setExpiredTime()   //过期时间
//                .setIsDefault() //是否默认
//                .setNpsJson()
                .setQuestionnaireType(QuestionnaireTypeEnum.普通.getType()) //问卷类型
                .setThemeId(theme.getId())
                .setSort(99)  //问卷排序
                .setQuestionnaireJson(jsonStr)
                .setUpdateTime(new Date());

        if (questionnaire.getId() != null) {
            upAppsQuestionnaireService.updateById(questionnaire);
            logger.info("更新问卷");
            logger.info(questionnaire);
        } else {
            questionnaire = upAppsQuestionnaireService.mInsert(questionnaire);
            qId = questionnaire.getId();
            logger.info("新建问卷");
            logger.info(questionnaire);
        }

        JSONArray questionArray = json.getJSONArray(QUESTION_ARRAY_KEY);

        List<String> questionIdList = new ArrayList<>();
        List<String> questionItemIdList = new ArrayList<>();

        for (int questionIndex = 0; questionIndex < questionArray.size(); questionIndex++) {
            JSONObject questionJson = questionArray.getJSONObject(questionIndex);

            Long questionId = 0L;
            UpAppsQuestion question = null;
            try {
                questionId = questionJson.getLongValue("id");

                if (isExists(questionIdList, questionId)) {
                    logger.info("重复id，需要重新添加一个题目");
                    questionId = 0L;
                }
            } catch (Exception e) {
                //do nothing
            }

            if (questionId > 0) {
                question = upAppsQuestionService.selectById(questionId);
            }
            if (question == null) {
                question = new UpAppsQuestion();
                question.setAddTime(new Date());
            }

            JSONArray itemArray = questionJson.getJSONArray(QUESTION_ITEM_ARRAY_KEY);
            List<UpAppsQuestionItem> questionJson4Save = new ArrayList<>();
            QuestionTypeEnum questionType = QuestionTypeEnum.codeOf(questionJson.getIntValue("type"));
            boolean isRequired = questionJson.getBooleanValue("required");
            boolean isScore = false;
            int itemArraySize = 0;
            if (itemArray != null) {
                itemArraySize = itemArray.size();
            }

            if (questionType.equals(QuestionTypeEnum.总体满意度) ||
                    questionType.equals(QuestionTypeEnum.评分) ||
                    questionType.equals(QuestionTypeEnum.分项满意度)) {
                isScore = true;
            }
            questionNum++;
            if (isRequired) {
                requiredNum++;
            }

            Object displayRule = questionJson.get("displayRule");
            if (displayRule instanceof JSONObject) {
                JSONObject j = (JSONObject) displayRule;
                Object jArr = j.get("rules");
                if (jArr instanceof JSONArray && ((JSONArray) jArr).size() > 0) {
                    question.setDisplayRule(JSONObject.toJSONString(displayRule));
                    question.setIsVisible(0);
                } else {
                    question.setDisplayRule("");
                    question.setIsVisible(1);
                }
            } else {
                question.setDisplayRule("");
                question.setIsVisible(1);
            }

            question.setQuestionnaireId(qId)
                    .setQuestionOrder(questionIndex + 1)
                    .setTitle(questionJson.getString("title")) //标题
                    .setSubTitle(questionJson.getString("subTitle"))  //副标题
                    .setUniqueKey(questionJson.getString("unique"))
//                    .setDescription()   //描述
//                    .setIsVisible() //是否可见，如果之前没设置，则为默认值1
//                    .setQucikTag()
//                    .setGoodScore()
//                    .setBadScore()
//                    .setScoreDimenssion()
                    .setQuestionType(questionType.getType())  //问题类型
                    .setIsRequired(isRequired ? 1 : 0)    //是否必填
                    .setOptionsNum(itemArraySize)    //选项数目
                    .setIsRandomSort(questionJson.getBooleanValue("onFinally") ? 1 : 0)
                    .setIsNps(questionType.equals(QuestionTypeEnum.NPS) ? 1 : 0) //是否nps题
                    .setIsScore(isScore ? 1 : 0)   //是否满意度题目
                    .setUpdateTime(new Date());

            if (question.getId() == null) {
                question = upAppsQuestionService.mInsert(question);
                logger.info("新建题目");
                logger.info(question);
                questionIdList.add(question.getId().toString());
            } else {
                questionIdList.add(question.getId().toString());
                //upAppsQuestionService.updateById(question);
            }

            for (int i = 0; i < itemArraySize; i++) {
                JSONObject itemJson = itemArray.getJSONObject(i);

                Long questionItemId = 0L;
                UpAppsQuestionItem item = null;
                try {
                    questionItemId = itemJson.getLongValue("id");
                    if (isExists(questionItemIdList, questionItemId)) {
                        logger.info("重复id，需要重新添加一个item");
                        questionItemId = 0L;
                    }
                } catch (Exception e) {
                    //do nothing
                }

                if (questionItemId > 0) {
                    item = upAppsQuestionItemService.selectById(questionItemId);
                }
                if (item == null) {
                    item = new UpAppsQuestionItem();
                    item.setAddTime(new Date());
                }

                switch (questionType) {
                    case 是非:
                    case 多选:
                    case 单选:
                        item.setQuestionItemType(QuestionItemTypeEnum.选项.getType());
                        break;
                    case 排序:
                    case 评分:
                    case NPS:
                    case 总体满意度:
                    case 分项满意度:
                        item.setQuestionItemType(QuestionItemTypeEnum.分数选择.getType());
                        break;
                    case 手机验证:
                        if (!isVip) {
                            throw new AlertException("您还没有开通短信验证码题型功能，请联系优加工作人员进行开通。");
                        }
                    default:
                        item.setQuestionItemType(QuestionItemTypeEnum.填空.getType());
                        break;
                }

                item.setQuestionnaireId(qId)
//                        .setQuestionItemType()  //选项类型
//                        .setRow()   //行号
//                        .setColumn()    //列
//                        .setEditorPlaceholder() //编辑框提示
//                        .setShowQuestionId()
//                        .setSort()  //排序
//                        .setQuickTag()
                        .setQuestionId(question.getId())
                        .setSort(i + 1)
                        .setKey(itemJson.getString("key"))   //标号
                        .setValue(itemJson.getString("value")) //内容
                        .setDisplay(itemJson.getString("display"))   //展示内容
                        .setPlaceholder(itemJson.getString("placeholder"))   //填空题提示
                        .setEditorType(itemJson.getString("editorType"))    //编辑器类型
                        .setMinLength(itemJson.getInteger("minLength"))
                        .setMaxLength(itemJson.getInteger("maxLength"))
                        .setIsRequired(itemJson.getInteger("isRequired"))
                        .setUniqueKey(itemJson.getString("unique"))
                        .setIsShowEditor((itemJson.getBoolean("isShowEditor") == null || !itemJson.getBoolean("isShowEditor")) ? 0 : 1)  //选定后是否显示编辑框
                        .setIsEditorRequired((itemJson.getBoolean("isEditorRequired") == null || !itemJson.getBoolean("isEditorRequired")) ? 0 : 1)
                        .setEditorPlaceholder(itemJson.getString("editorPlaceholder"))
                        .setEditorMinLength(itemJson.getInteger("editorMinLength"))
                        .setEditorMaxLength(itemJson.getInteger("editorMaxLength"))
                        .setEditorEditorType(itemJson.getString("editorEditorType"))
                        .setScore(itemJson.getInteger("score"))
                        .setUpdateTime(new Date());

                if (item.getId() == null) {
                    item = upAppsQuestionItemService.mInsert(item);
                    logger.info("新建选项");
                    logger.info(item);
                    questionItemIdList.add(item.getId().toString());
                } else {
                    upAppsQuestionItemService.updateById(item);
                    questionItemIdList.add(item.getId().toString());
                    logger.info("更新选项");
                    logger.info(item);
                }
                questionJson4Save.add(item);
            }
            question.setQuestionJson(JSONArray.toJSONString(questionJson4Save))
                    .setUpdateTime(new Date());
            upAppsQuestionService.updateById(question);
            logger.info("更新题目");
            logger.info(question);
        }

        //update
        json.put(QUESTIONNAIRE_ID_KEY, qId);
        questionnaire.setQuestionNum(questionNum)   //问题数
                .setRequiredOptionNum(requiredNum) //必答题数
                .setQuestionnaireJson(json.toJSONString())
                .setUpdateTime(new Date());
        upAppsQuestionnaireService.updateById(questionnaire);
        deleteAllQuestion(qId, questionIdList, questionItemIdList);
        return json;
    }

    /**
     * 删除多余的题目和选项
     *
     * @param questionnaireId    问卷id
     * @param questIdList        题目id列表
     * @param questionItemIdList 选项id列表
     */
    private void deleteAllQuestion(Long questionnaireId, List questIdList, List questionItemIdList) {
        Wrapper<UpAppsQuestion> questionWrapper = new EntityWrapper<UpAppsQuestion>()
                .eq(UpAppsQuestion.QUESTIONNAIRE_ID, questionnaireId);

        if (questIdList != null && questIdList.size() > 0) {
            questionWrapper.notIn(UpAppsQuestion.ID, questIdList);
        }
        upAppsQuestionService.delete(questionWrapper);

        Wrapper<UpAppsQuestionItem> questionItemWrapper = new EntityWrapper<UpAppsQuestionItem>()
                .eq(UpAppsQuestionItem.QUESTIONNAIRE_ID, questionnaireId);

        if (questionItemIdList != null && questionItemIdList.size() > 0) {
            questionItemWrapper.notIn(UpAppsQuestionItem.ID, questionItemIdList);
        }
        upAppsQuestionItemService.delete(questionItemWrapper);
    }

    @Override
    public String UrlToQrcodeAndUpload(String url, String format, int size) throws IOException, WriterException {
        BufferedImage bufferedImage = QRCodeUtil.EncodeUrlToBufferedImage(url, size);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, format, os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());

        String qrcode = uploadOneFile(is, format);
        is.close();
        os.close();
        return qrcode;
    }

    @Override
    public String UrlToQrcodeAndUpload(String url, String content, String format, int size) throws IOException, WriterException {
        BufferedImage bufferedImage = QRCodeUtil.EncodeUrlToBufferedImage(url, content, size);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, format, os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());

        String qrcode = uploadOneFile(is, format);
        is.close();
        os.close();
        return qrcode;
    }

    @Override
    public boolean linkAllDataCheck(UpTenantsCompany company, Long questionnaireId) {
        String key = "excel/" + company.getDomainName() + "-" + questionnaireId + ".zip";
        return ossClient.doesObjectExist(aliyunProperties.getAliyunOssBucket(), key);
    }

    @Override
    public void linkAllDataDelete(UpTenantsCompany company, Long questionnaireId) {
        String key = "excel/" + company.getDomainName() + "-" + questionnaireId + ".zip";
        ossClient.deleteObject(aliyunProperties.getAliyunOssBucket(), key);
    }

    @Override
    public void createBatchLinkAndQrcodeWithNewThread(UpTenantsCompany company, Long questionnaireId, int size) {
        taskExecutor.execute(new QrcodeJob(this, company, questionnaireId, size));
    }

    public class QrcodeJob implements Runnable {

        private QuestionnaireSettingService questionnaireSettingService;
        private UpTenantsCompany company;
        private Long questionnaireId;
        private int size;

        public QrcodeJob() {
        }

        public QrcodeJob(QuestionnaireSettingService questionnaireSettingService, UpTenantsCompany company, Long questionnaireId, int size) {
            this.questionnaireSettingService = questionnaireSettingService;
            this.company = company;
            this.questionnaireId = questionnaireId;
            this.size = size;
        }

        @Override
        public void run() {
            try {
                questionnaireSettingService.createBatchLinkAndQrcode(company, questionnaireId, size);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public String createBatchLinkAndQrcode(UpTenantsCompany company, Long questionnaireId, int size) throws Exception {

        List<UpTenantsRegion> regionList = upTenantsRegionService.selectList(
                new EntityWrapper<UpTenantsRegion>()
                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, company.getId())
                        .eq(UpTenantsRegion.IS_STORE, 1)
        );
        List<UpTenantsRegion> totalRegionList = upTenantsRegionService.selectList(
                new EntityWrapper<UpTenantsRegion>()
                        .eq(UpTenantsRegion.TENANTS_COMPANY_ID, company.getId())
        );
        Map<String, String> regionMap = new HashMap<>();
        for (UpTenantsRegion region : totalRegionList) {
            regionMap.put(region.getId().toString(), region.getName());
        }
        List<UpTenantsLevel> levelList = upTenantsLevelService.selectList(
                new EntityWrapper<UpTenantsLevel>()
                        .eq(UpTenantsLevel.TENANTS_COMPANY_ID, company.getId())
                        .orderBy(UpTenantsLevel.LEVEL, true)
        );

        String pathPreffix;
        String OS = System.getProperty("os.name");
        if(OS.toLowerCase().startsWith("win")){
            pathPreffix = "D:/";
        } else {
            pathPreffix = "/tmp/";
        }

        File folder = new File(pathPreffix  + company.getDomainName() + "-" + questionnaireId);
//        File qrcodeFile = new File(zipFile.getAbsolutePath() + "/二维码");
//        if (!zipFile.exists()) {
//            zipFile.mkdir();
//        }
        if (folder.exists()) {
            logger.info("该公司的二维码资源已存在");
            if (folder.isDirectory()) {
                String[] children = folder.list();
                for (int i=0; i < children.length; i++) {
                    File delete = new File(folder, children[i]);
                    delete.delete();
                }
            }
        } else {
            folder.mkdir();
        }

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet xssfSheet = xssfWorkbook.createSheet("链接");
        XSSFRow rowTitle = xssfSheet.createRow(0);
        for (int i = 0; i < levelList.size(); i++) {
            XSSFCell cell = rowTitle.createCell(i);
            cell.setCellValue(levelList.get(i).getName());
        }
        XSSFCell storeCell = rowTitle.createCell(levelList.size());
        storeCell.setCellValue("网点");
        XSSFCell linkCell = rowTitle.createCell(levelList.size() + 1);
        linkCell.setCellValue("链接");

        int rowIndex = 1;
        for (UpTenantsRegion region : regionList) {
            //二维码压缩包
            String prefix = systemConfig.getAppUrlWithVersion() + "/q/qrconnect";
            String suffix = "?q=" + questionnaireId + "&r=" + region.getId();
            String url = prefix + suffix;
            BufferedImage bufferedImage = QRCodeUtil.EncodeUrlToBufferedImage(url, size);
            ImageIO.write(bufferedImage, "png", new File(folder.getAbsolutePath() + "/" + region.getName() + new Date().getTime() + ".png"));

            //excel
            XSSFRow rowContent = xssfSheet.createRow(rowIndex++);
            String[] ids = region.getQuickTag().split(",");
            for (int i = 1; i<ids.length;i++) {
                XSSFCell cell = rowContent.createCell(i - 1);
                cell.setCellValue(regionMap.get(ids[i]));
            }

            XSSFCell storeCellContent = rowContent.createCell(levelList.size());
            storeCellContent.setCellValue(region.getName());
            XSSFCell linkCellContent = rowContent.createCell(levelList.size() + 1);
            linkCellContent.setCellValue(url);
        }

        FileOutputStream fout = new FileOutputStream(folder.getAbsoluteFile() + "/链接.xlsx");
        xssfWorkbook.write(fout);
        fout.close();

//        fileToZip(qrcodeFile.getAbsolutePath(), qrcodeFile.getParentFile().getAbsolutePath(), qrcodeFile.getName());
//        File deleteFile = new File(qrcodeFile.getAbsolutePath());
//        String[] children = deleteFile.list();
//        for (int i=0; i < children.length; i++) {
//            File delete = new File(deleteFile, children[i]);
//            delete.delete();
//        }
//        deleteFile.delete();
        File zipFile = new File(folder.getParentFile().getAbsolutePath() + "/" + folder.getName() + ".zip");
        if (zipFile.exists()) {
            zipFile.delete();
        }
        fileToZip(folder.getAbsolutePath(), folder.getParentFile().getAbsolutePath(), folder.getName());
        zipFile = new File(folder.getParentFile().getAbsolutePath() + "/" + folder.getName() + ".zip");
        String key = "excel/" + zipFile.getName();
        key = uploadZipFile(new FileInputStream(zipFile), key);

        return key;
    }

    public static boolean fileToZip(String sourceFilePath,String zipFilePath,String fileName){
        boolean flag = false;
        File sourceFile = new File(sourceFilePath);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;

        if(sourceFile.exists() == false){
            System.out.println("待压缩的文件目录："+sourceFilePath+"不存在.");
        }else{
            try {
                File zipFile = new File(zipFilePath + "/" + fileName +".zip");
                if(zipFile.exists()){
                    System.out.println(zipFilePath + "目录下存在名字为:" + fileName +".zip" +"打包文件.");
                }else{
                    File[] sourceFiles = sourceFile.listFiles();
                    if(null == sourceFiles || sourceFiles.length<1){
                        System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");
                    }else{
                        fos = new FileOutputStream(zipFile);
                        zos = new ZipOutputStream(new BufferedOutputStream(fos));
                        byte[] bufs = new byte[1024*10];
                        for(int i=0;i<sourceFiles.length;i++){
                            //创建ZIP实体，并添加进压缩包
                            ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                            zos.putNextEntry(zipEntry);
                            //读取待压缩的文件并写进压缩包里
                            fis = new FileInputStream(sourceFiles[i]);
                            bis = new BufferedInputStream(fis, 1024*10);
                            int read = 0;
                            while((read=bis.read(bufs, 0, 1024*10)) != -1){
                                zos.write(bufs,0,read);
                            }
                        }
                        flag = true;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally{
                //关闭流
                try {
                    if(null != bis) bis.close();
                    if(null != zos) zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
        return flag;
    }

    public String uploadZipFile(InputStream is, String key) {
        if (is == null) {
            return null;
        }
        try {
            ossClient.putObject(aliyunProperties.getAliyunOssBucket(), key, is);
        } catch (Exception e) {
            return null;
        }
        return key;
    }

    private Set<String> fileFormatSet = new HashSet<String>() {
        {
            //图片格式
            add(".png");
            add(".jpg");
            add(".bmp");
            add(".gif");
            //视频格式
            add(".amr");
            add(".mov");
            add(".mp4");
            add(".flv");
            add(".xlsx");
            add(".xls");
        }
    };

    @Override
    public String uploadOneFile(InputStream is, String suffix) {
        BaseResponse<String> response = new BaseResponse<>();
        if (null == is) {
            return null;
        }
        if (!suffix.startsWith(".")) {
            suffix = "." + suffix;
        }
        if (!fileFormatSet.contains(suffix.toLowerCase())) {
            return null;
        }

        String key, prefix, previewImg = null;
        try {
            prefix = StringHelper.getImgName();
            key = prefix + suffix;

            ossClient.putObject(aliyunProperties.getAliyunOssBucket(), key, is);
        } catch (Exception e) {
            return null;
        }
        return key;
    }

}