package cn.com.youplus.apps.rpc.service.common;

import cn.com.youplus.apps.api.auto.*;
import cn.com.youplus.apps.api.common.QuestionnaireService;
import cn.com.youplus.common.exception.interaction.AlertException;
import cn.com.youplus.common.model.enums.QuestionItemTypeEnum;
import cn.com.youplus.common.model.enums.QuestionTypeEnum;
import cn.com.youplus.common.model.enums.QuestionnaireStatusEnum;
import cn.com.youplus.common.model.enums.QuestionnaireTypeEnum;
import cn.com.youplus.model.auto.entity.apps.*;
import cn.com.youplus.model.auto.entity.tenants.UpTenantsCompany;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 严汉羽 on 2017/6/29.
 */
@Service("questionnaireService")
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(QuestionnaireServiceImpl.class);

    @Autowired
    private UpAppsQuestionnaireService upAppsQuestionnaireService;

    @Autowired
    private UpAppsQuestionnaireAttributeService upAppsQuestionnaireAttributeService;

    @Autowired
    private UpAppsQuestionnaireThemeService upAppsQuestionnaireThemeService;

    @Autowired
    private UpAppsQuestionService upAppsQuestionService;

    @Autowired
    private UpAppsQuestionItemService upAppsQuestionItemService;

    public static final String QUESTIONNAIRE_ID_KEY = "id";
    public static final String QUESTION_ARRAY_KEY = "list";
    public static final String QUESTION_ITEM_ARRAY_KEY = "list";

    //region 问卷属性
    private UpAppsQuestionnaireAttribute getAttr(Long questionnaireId, String name) {
        try {
            return upAppsQuestionnaireAttributeService.selectOne(
                    new EntityWrapper<UpAppsQuestionnaireAttribute>()
                            .eq(UpAppsQuestionnaireAttribute.QUESTIONNAIRE_ID, questionnaireId)
                            .eq(UpAppsQuestionnaireAttribute.ATTRIBUTE_NAME, name)
            );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getStringValue(Long questionnaireId, String name) {
        UpAppsQuestionnaireAttribute attribute = getAttr(questionnaireId, name);
        if (attribute == null) {
            return null;
        }
        return attribute.getAttributeStringValue();
    }

    @Override
    public Long getLongValue(Long questionnaireId, String name) {
        UpAppsQuestionnaireAttribute attribute = getAttr(questionnaireId, name);
        if (attribute == null) {
            return 0L;
        }
        return attribute.getAttributeLongValue();
    }

    @Override
    public Boolean getLongValueToBoolean(Long questionnaireId, String name) {
        UpAppsQuestionnaireAttribute attribute = getAttr(questionnaireId, name);
        if (attribute == null) {
            return false;
        }
        return attribute.getAttributeLongValue() >= 1;
    }

    @Override
    public Double getDoubleValue(Long questionnaireId, String name) {
        UpAppsQuestionnaireAttribute attribute = getAttr(questionnaireId, name);
        if (attribute == null) {
            return null;
        }
        return attribute.getAttributeDoubleValue();
    }

    @Override
    public Date getDateValue(Long questionnaireId, String name) {
        UpAppsQuestionnaireAttribute attribute = getAttr(questionnaireId, name);
        if (attribute == null) {
            return null;
        }
        return attribute.getAttributeDateValue();
    }

    boolean isExists(List<String> set, Long id) {
        String idstr = id.toString();
        for (String i : set) {
            if (idstr.equals(i)) {
                return true;
            }
        }
        return false;
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

    //endregion

}
