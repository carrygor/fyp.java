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
 * 
 * </p>
 *
 * @author 严汉羽
 */
@TableName("up_apps_question_item")
public class UpAppsQuestionItem extends Model<UpAppsQuestionItem> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id自增长
     */
	private Long id;
    /**
     * 问卷id
     */
	@TableField("questionnaire_id")
	private Long questionnaireId;
    /**
     * 题目id
     */
	@TableField("question_id")
	private Long questionId;
    /**
     * 题目类型
     */
	@TableField("question_item_type")
	private String questionItemType;
    /**
     * 标号
     */
	private String key;
    /**
     * 答案内容
     */
	private String value;
    /**
     * 答案显示到前端的内容
     */
	private String display;
    /**
     * 如果是填空题，填空的提示
     */
	private String placeholder;
    /**
     * 行号
     */
	private Integer row;
    /**
     * 如果是表格型的题目的列号
     */
	private Integer column;
    /**
     * 选定后，是否显示编辑框
     */
	@TableField("is_show_editor")
	private Integer isShowEditor;
    /**
     * 编辑框的placeholder
     */
	@TableField("editor_placeholder")
	private String editorPlaceholder;
    /**
     * id类型
     */
	@TableField("show_question_id")
	private Long showQuestionId;
    /**
     * 排序，默认1
     */
	private Integer sort;
    /**
     * 乐观锁
     */
	@Version
	private Integer version;
    /**
     * 评分题目对应的分数
     */
	private Integer score;
    /**
     * 逻辑删除
     */
	@TableField("logic_delete")
    @TableLogic
	private Integer logicDelete;
	@TableField("add_time")
	private Date addTime;
	@TableField("update_time")
	private Date updateTime;
    /**
     * 区域的快速索引
     */
	@TableField("quick_tag")
	private String quickTag;
    /**
     * 编辑器的类型
     */
	@TableField("editor_type")
	private String editorType;
    /**
     * 选中状态时候的icon
     */
	@TableField("icon_select")
	private String iconSelect;
    /**
     * 未选中状态的icon，如果未填则使用默认的
     */
	@TableField("icon_unselect")
	private String iconUnselect;
    /**
     * 记录前端使用的uniquekey
     */
	@TableField("unique_key")
	private String uniqueKey;
    /**
     * 是否必填
     */
	@TableField("is_required")
	private Integer isRequired;
    /**
     * 如果是填空题的话，可以填入最小的长度
     */
	@TableField("min_length")
	private Integer minLength;
    /**
     * 如果是填空题的话，填入的最大长度
     */
	@TableField("max_length")
	private Integer maxLength;
    /**
     * 填写附加填空时，是否必须的
     */
	@TableField("is_editor_required")
	private Integer isEditorRequired;
    /**
     * 附加填空的最小长度
     */
	@TableField("editor_min_length")
	private Integer editorMinLength;
    /**
     * 附加填空的最大长度
     */
	@TableField("editor_max_length")
	private Integer editorMaxLength;
    /**
     * 附加编辑器的类型
     */
	@TableField("editor_editor_type")
	private String editorEditorType;


	public Long getId() {
		return id;
	}

	public UpAppsQuestionItem setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getQuestionnaireId() {
		return questionnaireId;
	}

	public UpAppsQuestionItem setQuestionnaireId(Long questionnaireId) {
		this.questionnaireId = questionnaireId;
		return this;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public UpAppsQuestionItem setQuestionId(Long questionId) {
		this.questionId = questionId;
		return this;
	}

	public String getQuestionItemType() {
		return questionItemType;
	}

	public UpAppsQuestionItem setQuestionItemType(String questionItemType) {
		this.questionItemType = questionItemType;
		return this;
	}

	public String getKey() {
		return key;
	}

	public UpAppsQuestionItem setKey(String key) {
		this.key = key;
		return this;
	}

	public String getValue() {
		return value;
	}

	public UpAppsQuestionItem setValue(String value) {
		this.value = value;
		return this;
	}

	public String getDisplay() {
		return display;
	}

	public UpAppsQuestionItem setDisplay(String display) {
		this.display = display;
		return this;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public UpAppsQuestionItem setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
		return this;
	}

	public Integer getRow() {
		return row;
	}

	public UpAppsQuestionItem setRow(Integer row) {
		this.row = row;
		return this;
	}

	public Integer getColumn() {
		return column;
	}

	public UpAppsQuestionItem setColumn(Integer column) {
		this.column = column;
		return this;
	}

	public Integer getIsShowEditor() {
		return isShowEditor;
	}

	public UpAppsQuestionItem setIsShowEditor(Integer isShowEditor) {
		this.isShowEditor = isShowEditor;
		return this;
	}

	public String getEditorPlaceholder() {
		return editorPlaceholder;
	}

	public UpAppsQuestionItem setEditorPlaceholder(String editorPlaceholder) {
		this.editorPlaceholder = editorPlaceholder;
		return this;
	}

	public Long getShowQuestionId() {
		return showQuestionId;
	}

	public UpAppsQuestionItem setShowQuestionId(Long showQuestionId) {
		this.showQuestionId = showQuestionId;
		return this;
	}

	public Integer getSort() {
		return sort;
	}

	public UpAppsQuestionItem setSort(Integer sort) {
		this.sort = sort;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public UpAppsQuestionItem setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Integer getScore() {
		return score;
	}

	public UpAppsQuestionItem setScore(Integer score) {
		this.score = score;
		return this;
	}

	public Integer getLogicDelete() {
		return logicDelete;
	}

	public UpAppsQuestionItem setLogicDelete(Integer logicDelete) {
		this.logicDelete = logicDelete;
		return this;
	}

	public Date getAddTime() {
		return addTime;
	}

	public UpAppsQuestionItem setAddTime(Date addTime) {
		this.addTime = addTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public UpAppsQuestionItem setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getQuickTag() {
		return quickTag;
	}

	public UpAppsQuestionItem setQuickTag(String quickTag) {
		this.quickTag = quickTag;
		return this;
	}

	public String getEditorType() {
		return editorType;
	}

	public UpAppsQuestionItem setEditorType(String editorType) {
		this.editorType = editorType;
		return this;
	}

	public String getIconSelect() {
		return iconSelect;
	}

	public UpAppsQuestionItem setIconSelect(String iconSelect) {
		this.iconSelect = iconSelect;
		return this;
	}

	public String getIconUnselect() {
		return iconUnselect;
	}

	public UpAppsQuestionItem setIconUnselect(String iconUnselect) {
		this.iconUnselect = iconUnselect;
		return this;
	}

	public String getUniqueKey() {
		return uniqueKey;
	}

	public UpAppsQuestionItem setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
		return this;
	}

	public Integer getIsRequired() {
		return isRequired;
	}

	public UpAppsQuestionItem setIsRequired(Integer isRequired) {
		this.isRequired = isRequired;
		return this;
	}

	public Integer getMinLength() {
		return minLength;
	}

	public UpAppsQuestionItem setMinLength(Integer minLength) {
		this.minLength = minLength;
		return this;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public UpAppsQuestionItem setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
		return this;
	}

	public Integer getIsEditorRequired() {
		return isEditorRequired;
	}

	public UpAppsQuestionItem setIsEditorRequired(Integer isEditorRequired) {
		this.isEditorRequired = isEditorRequired;
		return this;
	}

	public Integer getEditorMinLength() {
		return editorMinLength;
	}

	public UpAppsQuestionItem setEditorMinLength(Integer editorMinLength) {
		this.editorMinLength = editorMinLength;
		return this;
	}

	public Integer getEditorMaxLength() {
		return editorMaxLength;
	}

	public UpAppsQuestionItem setEditorMaxLength(Integer editorMaxLength) {
		this.editorMaxLength = editorMaxLength;
		return this;
	}

	public String getEditorEditorType() {
		return editorEditorType;
	}

	public UpAppsQuestionItem setEditorEditorType(String editorEditorType) {
		this.editorEditorType = editorEditorType;
		return this;
	}

	public static final String ID = "id";

	public static final String QUESTIONNAIRE_ID = "questionnaire_id";

	public static final String QUESTION_ID = "question_id";

	public static final String QUESTION_ITEM_TYPE = "question_item_type";

	public static final String KEY = "key";

	public static final String VALUE = "value";

	public static final String DISPLAY = "display";

	public static final String PLACEHOLDER = "placeholder";

	public static final String ROW = "row";

	public static final String COLUMN = "column";

	public static final String IS_SHOW_EDITOR = "is_show_editor";

	public static final String EDITOR_PLACEHOLDER = "editor_placeholder";

	public static final String SHOW_QUESTION_ID = "show_question_id";

	public static final String SORT = "sort";

	public static final String VERSION = "version";

	public static final String SCORE = "score";

	public static final String LOGIC_DELETE = "logic_delete";

	public static final String ADD_TIME = "add_time";

	public static final String UPDATE_TIME = "update_time";

	public static final String QUICK_TAG = "quick_tag";

	public static final String EDITOR_TYPE = "editor_type";

	public static final String ICON_SELECT = "icon_select";

	public static final String ICON_UNSELECT = "icon_unselect";

	public static final String UNIQUE_KEY = "unique_key";

	public static final String IS_REQUIRED = "is_required";

	public static final String MIN_LENGTH = "min_length";

	public static final String MAX_LENGTH = "max_length";

	public static final String IS_EDITOR_REQUIRED = "is_editor_required";

	public static final String EDITOR_MIN_LENGTH = "editor_min_length";

	public static final String EDITOR_MAX_LENGTH = "editor_max_length";

	public static final String EDITOR_EDITOR_TYPE = "editor_editor_type";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UpAppsQuestionItem{" +
			"id=" + id +
			", questionnaireId=" + questionnaireId +
			", questionId=" + questionId +
			", questionItemType=" + questionItemType +
			", key=" + key +
			", value=" + value +
			", display=" + display +
			", placeholder=" + placeholder +
			", row=" + row +
			", column=" + column +
			", isShowEditor=" + isShowEditor +
			", editorPlaceholder=" + editorPlaceholder +
			", showQuestionId=" + showQuestionId +
			", sort=" + sort +
			", version=" + version +
			", score=" + score +
			", logicDelete=" + logicDelete +
			", addTime=" + addTime +
			", updateTime=" + updateTime +
			", quickTag=" + quickTag +
			", editorType=" + editorType +
			", iconSelect=" + iconSelect +
			", iconUnselect=" + iconUnselect +
			", uniqueKey=" + uniqueKey +
			", isRequired=" + isRequired +
			", minLength=" + minLength +
			", maxLength=" + maxLength +
			", isEditorRequired=" + isEditorRequired +
			", editorMinLength=" + editorMinLength +
			", editorMaxLength=" + editorMaxLength +
			", editorEditorType=" + editorEditorType +
			"}";
	}
}
