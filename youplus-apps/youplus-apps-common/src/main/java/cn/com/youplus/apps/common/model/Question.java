package cn.com.youplus.apps.common.model;

import cn.com.youplus.common.model.enums.QuestionTypeEnum;
import cn.com.youplus.model.auto.entity.apps.UpAppsQuestion;
import cn.com.youplus.model.auto.entity.apps.UpAppsQuestionItem;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 严汉羽 on 2017/11/7.
 */
public class Question implements Serializable {

    private String title;

    private String subTitle;

    private Long id;

    private Long questionnaireId;

    private String questionType;

    private int questionTypeCode;

    private Integer isRequired;

    private Integer optionsNum;

    private Integer questionOrder;

    private Integer isVisible;

    private Integer goodScore;

    private Integer badScore;

    private Integer isNps;

    private Integer isScore;

    private String scoreDimenssion;

    private String displayRule;

    private String unique;

    private List<QuestionItem> itemList;

    public Question() {}

    public Question(UpAppsQuestion question) {
        setId(question.getId());
        setBadScore(question.getBadScore());
        setGoodScore(question.getGoodScore());
        setIsNps(question.getIsNps());
        setIsRequired(question.getIsRequired());
        setScoreDimenssion(question.getScoreDimenssion());
        setOptionsNum(question.getOptionsNum());
        setIsVisible(question.getIsVisible());
        setQuestionnaireId(question.getQuestionnaireId());
        setQuestionOrder(question.getQuestionOrder());
        setTitle(question.getTitle());
        setIsScore(question.getIsScore());
        setSubTitle(question.getSubTitle());
        setQuestionType(question.getQuestionType());
        setDisplayRule(question.getDisplayRule());
        setQuestionTypeCode(QuestionTypeEnum.valueOf(question.getQuestionType()).getCode());
        setUnique(question.getUniqueKey());

        //子项目
        List<UpAppsQuestionItem> items = JSONObject.parseArray(question.getQuestionJson(), UpAppsQuestionItem.class);
        if (items != null && items.size() > 0) {
            items.forEach(item -> {
                addItem(new QuestionItem(item));
            });
        } else {
            this.itemList = new ArrayList<>();
        }
    }

    public Integer getIsScore() {
        return isScore;
    }

    public Question setIsScore(Integer isScore) {
        this.isScore = isScore;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public Long getId() {
        return id;
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public String getQuestionType() {
        return questionType;
    }



    public Integer getIsRequired() {
        return isRequired;
    }

    public Integer getOptionsNum() {
        return optionsNum;
    }

    public Integer getQuestionOrder() {
        return questionOrder;
    }

    public Integer getIsVisible() {
        return isVisible;
    }

    public Integer getGoodScore() {
        return goodScore;
    }

    public Integer getBadScore() {
        return badScore;
    }

    public Integer getIsNps() {
        return isNps;
    }

    public String getScoreDimenssion() {
        return scoreDimenssion;
    }

    public Question setTitle(String title) {
        this.title = title;
        return this;
    }

    public Question setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public Question setId(Long id) {
        this.id = id;
        return this;
    }

    public Question setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
        return this;
    }

    public Question setQuestionType(String questionType) {
        this.questionType = questionType;
        return this;
    }

    public Question setIsRequired(Integer isRequired) {
        this.isRequired = isRequired;
        return this;
    }

    public Question setOptionsNum(Integer optionsNum) {
        this.optionsNum = optionsNum;
        return this;
    }

    public Question setQuestionOrder(Integer questionOrder) {
        this.questionOrder = questionOrder;
        return this;
    }

    public Question setIsVisible(Integer isVisible) {
        this.isVisible = isVisible;
        return this;
    }

    public Question setGoodScore(Integer goodScore) {
        this.goodScore = goodScore;
        return this;
    }

    public Question setBadScore(Integer badScore) {
        this.badScore = badScore;
        return this;
    }

    public Question setIsNps(Integer isNps) {
        this.isNps = isNps;
        return this;
    }

    public Question setScoreDimenssion(String scoreDimenssion) {
        this.scoreDimenssion = scoreDimenssion;
        return this;
    }

    public List<QuestionItem> getItemList() {
        return itemList;
    }

    public Question setItemList(List<QuestionItem> itemList) {
        this.itemList = itemList;
        return this;
    }

    public int getQuestionTypeCode() {
        return questionTypeCode;
    }

    public Question setQuestionTypeCode(int questionTypeCode) {
        this.questionTypeCode = questionTypeCode;
        return this;
    }

    public Question addItem(QuestionItem questionItem) {
        if (this.itemList == null ) {
            this.itemList = new ArrayList<>();
        }
        this.itemList.add(questionItem);
        return this;
    }

    public String getDisplayRule() {
        return displayRule;
    }

    public Question setDisplayRule(String displayRule) {
        this.displayRule = displayRule;
        return this;
    }

    public String getUnique() {
        return unique;
    }

    public Question setUnique(String unique) {
        this.unique = unique;
        return this;
    }
}
