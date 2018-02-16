package cn.com.youplus.common.model.log;

import cn.com.youplus.common.model.log.annotation.UpLogItem;
import cn.com.youplus.common.model.log.annotation.UpLogStore;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 严汉羽
 */
@UpLogStore(name = "question-stat-log")
public class QuestionStatLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @UpLogItem(name="addTime")
    private Date addTime;

    @UpLogItem(name = "answerSheetId")
    private Long answerSheetId;

    @UpLogItem(name = "questionnaireId")
    private Long questionnaireId;

    @UpLogItem(name = "questionId")
    private Long questionId;

    @UpLogItem(name = "questionType")
    private String questionType;

    @UpLogItem(name = "questionItemId")
    private Long questionItemId;

    @UpLogItem(name = "quickTag")
    private String quickTag;

    @UpLogItem(name = "regionId")
    private Long regionId;

    @UpLogItem(name = "score")
    private int score;

    @UpLogItem(name = "value")
    private String value;

    @UpLogItem(name = "inputContent")
    private String inputContent;

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Long getAnswerSheetId() {
        return answerSheetId;
    }

    public void setAnswerSheetId(Long answerSheetId) {
        this.answerSheetId = answerSheetId;
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public Long getQuestionItemId() {
        return questionItemId;
    }

    public void setQuestionItemId(Long questionItemId) {
        this.questionItemId = questionItemId;
    }

    public String getQuickTag() {
        return quickTag;
    }

    public void setQuickTag(String quickTag) {
        this.quickTag = quickTag;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getInputContent() {
        return inputContent;
    }

    public void setInputContent(String inputContent) {
        this.inputContent = inputContent;
    }

    @Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
