package cn.com.youplus.model.auto.entity.apps;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.Version;
import java.io.Serializable;

/**
 * <p>
 * 问卷属性
 * </p>
 *
 * @author 严汉羽
 */
@TableName("up_apps_questionnaire_attribute")
public class UpAppsQuestionnaireAttribute extends Model<UpAppsQuestionnaireAttribute> implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 问卷id
     */
	@TableField("questionnaire_id")
	private Long questionnaireId;
    /**
     * 数据类型
     */
	@TableField("data_type")
	private String dataType;
    /**
     * 属性名称
     */
	@TableField("attribute_name")
	private String attributeName;
    /**
     * 属性的值
     */
	@TableField("attribute_string_value")
	private String attributeStringValue;
	@TableField("attribute_long_value")
	private Long attributeLongValue;
	@TableField("attribute_date_value")
	private Date attributeDateValue;
	@TableField("attribute_time_value")
	private Date attributeTimeValue;
	@TableField("attribute_datetime_value")
	private Date attributeDatetimeValue;
	@TableField("attribute_double_value")
	private Double attributeDoubleValue;
	@TableField("attribute_timestamp_value")
	private Date attributeTimestampValue;
	@TableField("logic_delete")
    @TableLogic
	private Integer logicDelete;
	@TableField("add_time")
	private Date addTime;
	@TableField("update_time")
	private Date updateTime;


	public Long getId() {
		return id;
	}

	public UpAppsQuestionnaireAttribute setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getQuestionnaireId() {
		return questionnaireId;
	}

	public UpAppsQuestionnaireAttribute setQuestionnaireId(Long questionnaireId) {
		this.questionnaireId = questionnaireId;
		return this;
	}

	public String getDataType() {
		return dataType;
	}

	public UpAppsQuestionnaireAttribute setDataType(String dataType) {
		this.dataType = dataType;
		return this;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public UpAppsQuestionnaireAttribute setAttributeName(String attributeName) {
		this.attributeName = attributeName;
		return this;
	}

	public String getAttributeStringValue() {
		return attributeStringValue;
	}

	public UpAppsQuestionnaireAttribute setAttributeStringValue(String attributeStringValue) {
		this.attributeStringValue = attributeStringValue;
		return this;
	}

	public Long getAttributeLongValue() {
		return attributeLongValue;
	}

	public UpAppsQuestionnaireAttribute setAttributeLongValue(Long attributeLongValue) {
		this.attributeLongValue = attributeLongValue;
		return this;
	}

	public Date getAttributeDateValue() {
		return attributeDateValue;
	}

	public UpAppsQuestionnaireAttribute setAttributeDateValue(Date attributeDateValue) {
		this.attributeDateValue = attributeDateValue;
		return this;
	}

	public Date getAttributeTimeValue() {
		return attributeTimeValue;
	}

	public UpAppsQuestionnaireAttribute setAttributeTimeValue(Date attributeTimeValue) {
		this.attributeTimeValue = attributeTimeValue;
		return this;
	}

	public Date getAttributeDatetimeValue() {
		return attributeDatetimeValue;
	}

	public UpAppsQuestionnaireAttribute setAttributeDatetimeValue(Date attributeDatetimeValue) {
		this.attributeDatetimeValue = attributeDatetimeValue;
		return this;
	}

	public Double getAttributeDoubleValue() {
		return attributeDoubleValue;
	}

	public UpAppsQuestionnaireAttribute setAttributeDoubleValue(Double attributeDoubleValue) {
		this.attributeDoubleValue = attributeDoubleValue;
		return this;
	}

	public Date getAttributeTimestampValue() {
		return attributeTimestampValue;
	}

	public UpAppsQuestionnaireAttribute setAttributeTimestampValue(Date attributeTimestampValue) {
		this.attributeTimestampValue = attributeTimestampValue;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpAppsQuestionnaireAttribute setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpAppsQuestionnaireAttribute setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpAppsQuestionnaireAttribute setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public static final String ID = "id";

	public static final String QUESTIONNAIRE_ID = "questionnaire_id";

	public static final String DATA_TYPE = "data_type";

	public static final String ATTRIBUTE_NAME = "attribute_name";

	public static final String ATTRIBUTE_STRING_VALUE = "attribute_string_value";

	public static final String ATTRIBUTE_LONG_VALUE = "attribute_long_value";

	public static final String ATTRIBUTE_DATE_VALUE = "attribute_date_value";

	public static final String ATTRIBUTE_TIME_VALUE = "attribute_time_value";

	public static final String ATTRIBUTE_DATETIME_VALUE = "attribute_datetime_value";

	public static final String ATTRIBUTE_DOUBLE_VALUE = "attribute_double_value";

	public static final String ATTRIBUTE_TIMESTAMP_VALUE = "attribute_timestamp_value";

	public static final String LOGIC_DELETE = "logic_delete";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpAppsQuestionnaireAttribute{" +
			"id=" + id +
			", questionnaireId=" + questionnaireId +
			", dataType=" + dataType +
			", attributeName=" + attributeName +
			", attributeStringValue=" + attributeStringValue +
			", attributeLongValue=" + attributeLongValue +
			", attributeDateValue=" + attributeDateValue +
			", attributeTimeValue=" + attributeTimeValue +
			", attributeDatetimeValue=" + attributeDatetimeValue +
			", attributeDoubleValue=" + attributeDoubleValue +
			", attributeTimestampValue=" + attributeTimestampValue +
			", logicDelete=" + logicDelete +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			"}";
	}
}
