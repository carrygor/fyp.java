package cn.com.youplus.apps.common.model;

import cn.com.youplus.model.auto.entity.apps.UpAppsQuestionItem;

import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/11/7.
 */
public class QuestionItem implements Serializable {

    private Long id;

    private String questionItemType;

    private String key;

    private String value;

    private String display;

    private String placeholder;

    private Integer row;

    private Integer column;

    private Integer isShowEditor;

    private Long showQuestionId;

    private Integer sort;

    private String editorType;

    private String iconSelect;

    private String unique;

    private String iconUnselect;

    private String editorEditorType;
    private String editorPlaceholder;
    private Integer editorMaxLength;
    private Integer editorMinLength;
    private Integer isEditorRequired;

    private Integer maxLength;
    private Integer minLength;
    private Integer isRequired;

    private Integer score;


    public QuestionItem() {

    }

    public QuestionItem(UpAppsQuestionItem item) {
        setId(item.getId());
        setColumn(item.getColumn());
        setRow(item.getRow());
        setKey(item.getKey());
        setValue(item.getValue());
        setDisplay(item.getDisplay());
        setQuestionItemType(item.getQuestionItemType());
        setShowQuestionId(item.getShowQuestionId());
        setIconSelect(item.getIconSelect());
        setIconUnselect(item.getIconUnselect());
        setSort(item.getSort());
        setUnique(item.getUniqueKey());
        setScore(item.getScore());

        setIsShowEditor(item.getIsShowEditor());
        setEditorEditorType(item.getEditorEditorType());
        setEditorMinLength(item.getEditorMinLength());
        setEditorMaxLength(item.getEditorMaxLength());
        setEditorPlaceholder(item.getEditorPlaceholder());
        setIsEditorRequired(item.getIsEditorRequired());

        setEditorType(item.getEditorType());
        setPlaceholder(item.getPlaceholder());
        setMinLength(item.getMinLength());
        setMaxLength(item.getMaxLength());

        setIsRequired(item.getIsRequired());
    }

    public Long getId() {
        return id;
    }

    public QuestionItem setId(Long id) {
        this.id = id;
        return this;
    }

    public String getQuestionItemType() {
        return questionItemType;
    }

    public QuestionItem setQuestionItemType(String questionItemType) {
        this.questionItemType = questionItemType;
        return this;
    }

    public String getKey() {
        return key;
    }

    public QuestionItem setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public QuestionItem setValue(String value) {
        this.value = value;
        return this;
    }

    public String getDisplay() {
        return display;
    }

    public QuestionItem setDisplay(String display) {
        this.display = display;
        return this;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public QuestionItem setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public Integer getRow() {
        return row;
    }

    public QuestionItem setRow(Integer row) {
        this.row = row;
        return this;
    }

    public Integer getColumn() {
        return column;
    }

    public QuestionItem setColumn(Integer column) {
        this.column = column;
        return this;
    }

    public Integer getIsShowEditor() {
        return isShowEditor;
    }

    public QuestionItem setIsShowEditor(Integer isShowEditor) {
        this.isShowEditor = isShowEditor;
        return this;
    }

    public String getEditorPlaceholder() {
        return editorPlaceholder;
    }

    public QuestionItem setEditorPlaceholder(String editorPlaceholder) {
        this.editorPlaceholder = editorPlaceholder;
        return this;
    }

    public Long getShowQuestionId() {
        return showQuestionId;
    }

    public QuestionItem setShowQuestionId(Long showQuestionId) {
        this.showQuestionId = showQuestionId;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public QuestionItem setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public String getEditorType() {
        return editorType;
    }

    public QuestionItem setEditorType(String editorType) {
        this.editorType = editorType;
        return this;
    }

    public String getIconSelect() {
        return iconSelect;
    }

    public QuestionItem setIconSelect(String iconSelect) {
        this.iconSelect = iconSelect;
        return this;
    }

    public String getIconUnselect() {
        return iconUnselect;
    }

    public QuestionItem setIconUnselect(String iconUnselect) {
        this.iconUnselect = iconUnselect;
        return this;
    }

    public String getUnique() {
        return unique;
    }

    public QuestionItem setUnique(String unique) {
        this.unique = unique;
        return this;
    }

    public String getEditorEditorType() {
        return editorEditorType;
    }

    public QuestionItem setEditorEditorType(String editorEditorType) {
        this.editorEditorType = editorEditorType;
        return this;
    }

    public Integer getEditorMaxLength() {
        return editorMaxLength;
    }

    public QuestionItem setEditorMaxLength(Integer editorMaxLength) {
        this.editorMaxLength = editorMaxLength;
        return this;
    }

    public Integer getEditorMinLength() {
        return editorMinLength;
    }

    public QuestionItem setEditorMinLength(Integer editorMinLength) {
        this.editorMinLength = editorMinLength;
        return this;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public QuestionItem setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public QuestionItem setMinLength(Integer minLength) {
        this.minLength = minLength;
        return this;
    }

    public Integer getIsEditorRequired() {
        return isEditorRequired;
    }

    public QuestionItem setIsEditorRequired(Integer isEditorRequired) {
        this.isEditorRequired = isEditorRequired;
        return this;
    }

    public Integer getIsRequired() {
        return isRequired;
    }

    public QuestionItem setIsRequired(Integer isRequired) {
        this.isRequired = isRequired;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public QuestionItem setScore(Integer score) {
        this.score = score;
        return this;
    }
}
