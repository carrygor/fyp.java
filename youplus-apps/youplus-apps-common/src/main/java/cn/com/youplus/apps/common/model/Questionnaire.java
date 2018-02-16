package cn.com.youplus.apps.common.model;

import cn.com.youplus.model.auto.entity.apps.UpAppsQuestion;
import cn.com.youplus.model.auto.entity.apps.UpAppsQuestionnaire;
import cn.com.youplus.model.auto.entity.apps.UpAppsQuestionnaireTheme;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 严汉羽 on 2017/11/7.
 */
public class Questionnaire implements Serializable {

    private String title;

    private String subTitle;

    private Long id;

    private Long tenantsCompanyId;

    private Integer questionNum;

    private Integer requiredOptionNum;

    private Long themeId;

    private String bkgColor;

    private String selectedColor;

    private String unselectedColor;

    private String bkgImg;

    private String status;

    private Date addTime;

    private Date updateTime;

    private List<Question> questionList;

    private List<String> npsDimensionList;

    private String[] serviceTypeList;

    private List<String> periodList;

    public Questionnaire() {}

    public Questionnaire(UpAppsQuestionnaire questionnaire,
                         UpAppsQuestionnaireTheme theme,
                         List<UpAppsQuestion> questions) {
        if (questionnaire == null || theme == null) {
            return;
        }

        setId(questionnaire.getId());
        setQuestionNum(questionnaire.getQuestionNum());
        setRequiredOptionNum(questionnaire.getRequiredOptionNum());
        setStatus(questionnaire.getStatus());
        setTenantsCompanyId(questionnaire.getTenantsCompanyId());
        setTitle(questionnaire.getTitle());
        setSubTitle(questionnaire.getSubTitle());
        setAddTime(questionnaire.getAddTime());
        setUpdateTime(questionnaire.getUpdateTime());

        //主题相关
        setThemeId(questionnaire.getThemeId());
        setBkgColor(theme.getBkgColor());
        setBkgImg(theme.getBkgImg());
        setSelectedColor(theme.getSelectedColor());
        setUnselectedColor((theme.getUnselectedColor()));

        //选项相关
        try {
            this.npsDimensionList = JSONArray.parseArray(questionnaire.getNpsJson(), String.class);
            JSONObject serviceTypeJson = JSONObject.parseObject(questionnaire.getServiceTypeJson());
            if (serviceTypeJson != null) {
                JSONArray serviceArray = serviceTypeJson.getJSONArray("services");

                if (serviceArray != null) {
                    this.serviceTypeList = new String[serviceArray.size()];
                    this.serviceTypeList = serviceArray.toArray(this.serviceTypeList);
                }
            }
        } catch (Exception e) {
            //DO nothing
        }

         if (questions != null) {
             questions.forEach(q -> {
                 addQuestion(new Question(q));
             });
         }
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

    public Long getTenantsCompanyId() {
        return tenantsCompanyId;
    }

    public Integer getQuestionNum() {
        return questionNum;
    }

    public Integer getRequiredOptionNum() {
        return requiredOptionNum;
    }

    public Long getThemeId() {
        return themeId;
    }

    public String getBkgColor() {
        return bkgColor;
    }

    public String getBkgImg() {
        return bkgImg;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public Questionnaire setTitle(String title) {
        this.title = title;
        return this;
    }

    public Questionnaire setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public Questionnaire setId(Long id) {
        this.id = id;
        return this;
    }

    public Questionnaire setTenantsCompanyId(Long tenantsCompanyId) {
        this.tenantsCompanyId = tenantsCompanyId;
        return this;
    }

    public Questionnaire setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
        return this;
    }

    public Questionnaire setRequiredOptionNum(Integer requiredOptionNum) {
        this.requiredOptionNum = requiredOptionNum;
        return this;
    }

    public Questionnaire setThemeId(Long themeId) {
        this.themeId = themeId;
        return this;
    }

    public Questionnaire setBkgColor(String bkgColor) {
        this.bkgColor = bkgColor;
        return this;
    }

    public Questionnaire setBkgImg(String bkgImg) {
        this.bkgImg = bkgImg;
        return this;
    }

    public Questionnaire setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
        return this;
    }

    public String getSelectedColor() {
        return selectedColor;
    }

    public Questionnaire setSelectedColor(String selectedColor) {
        this.selectedColor = selectedColor;
        return this;
    }

    public String getUnselectedColor() {
        return unselectedColor;
    }

    public Questionnaire setUnselectedColor(String unselectedColor) {
        this.unselectedColor = unselectedColor;
        return this;
    }

    public Questionnaire addQuestion(Question question) {
        if (this.questionList == null ) {
            this.questionList = new ArrayList<>();
        }
        this.questionList.add(question);

        return this;
    }

    public String getStatus() {
        return status;
    }

    public Questionnaire setStatus(String status) {
        this.status = status;
        return this;
    }

    public Date getAddTime() {
        return addTime;
    }

    public Questionnaire setAddTime(Date addTime) {
        this.addTime = addTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Questionnaire setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public List<String> getNpsDimensionList() {
        return npsDimensionList;
    }

    public Questionnaire setNpsDimensionList(List<String> npsDimensionList) {
        this.npsDimensionList = npsDimensionList;
        return this;
    }

    public String[] getServiceTypeList() {
        return serviceTypeList;
    }

    public Questionnaire setServiceTypeList(String[] serviceTypeList) {
        this.serviceTypeList = serviceTypeList;
        return this;
    }

    public List<String> getPeriodList() {
        return periodList;
    }

    public Questionnaire setPeriodList(List<String> periodList) {
        this.periodList = periodList;
        return this;
    }
}
