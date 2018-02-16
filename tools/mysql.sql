
-- 2017-12-27 报表映射
CREATE TABLE up_tenants_report_mapping
(
    id BIGINT(20) DEFAULT 0 NOT NULL,
    field_name VARCHAR(32) DEFAULT '' COMMENT '字段名称',
    show_name VARCHAR(32) DEFAULT '' COMMENT '显示名称',
    type VARCHAR(32) DEFAULT '' COMMENT '映射类型',
    data_type VARCHAR(32) DEFAULT '' COMMENT '数据类型',
    category VARCHAR(32) DEFAULT '' COMMENT '所属分类',
    question_id BIGINT(20) DEFAULT 0 COMMENT '题目id',
    questionnaire_id BIGINT(20) DEFAULT 0 COMMENT '问卷id',
    add_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'
);

