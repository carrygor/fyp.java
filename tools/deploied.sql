
---一下脚本在2017-12-22执行
ALTER TABLE up_apps_questionnaire MODIFY status ENUM('编辑中', '收集中','待收集',  '已停用', '已过期', '已删除') DEFAULT '编辑中';

ALTER TABLE up_apps_question ADD unique_key VARCHAR(64) DEFAULT '' NULL COMMENT '记录前端使用的unique';
ALTER TABLE up_apps_question ADD is_random_sort INT DEFAULT 0 NULL COMMENT '是否随机排序题目';
ALTER TABLE up_apps_question_item ADD unique_key VARCHAR(64) DEFAULT '' NULL COMMENT '记录前端使用的uniquekey';

ALTER TABLE up_apps_question_item ADD is_required INT DEFAULT 0 NULL COMMENT '是否必填';
ALTER TABLE up_apps_question_item ADD min_length INT DEFAULT 0 NULL COMMENT '如果是填空题的话，可以填入最小的长度';
ALTER TABLE up_apps_question_item ADD max_length INT DEFAULT 0 NULL COMMENT '如果是填空题的话，填入的最大长度';
ALTER TABLE up_apps_question_item ADD is_editor_required INT DEFAULT 0 NULL COMMENT '填写附加填空时，是否必须的';
ALTER TABLE up_apps_question_item ADD editor_min_length INT DEFAULT 0 NULL COMMENT '附加填空的最小长度';
ALTER TABLE up_apps_question_item ADD editor_max_length INT DEFAULT 0 NULL COMMENT '附加填空的最大长度';
ALTER TABLE up_apps_question_item ADD editor_editor_type VARCHAR(32) DEFAULT '' NULL COMMENT '附加填空的类型';

ALTER TABLE up_apps_questionnaire ADD delete_time TIMESTAMP NULL COMMENT '问卷删除时间';

ALTER TABLE up_apps_question MODIFY display_rule TEXT COMMENT '显示逻辑';

ALTER TABLE up_apps_answer_sheet ADD entrance ENUM('LINK', 'LINK_OAUTH', 'WEIXIN', 'WEIXIN_BASE_OAUTH', 'WEIXIN_INFO_OAUTH', 'WEIXIN_THIRDPATY_BASE_OAUTH', 'WEIXIN_THIRDPARTY_INFO_OAUTH') DEFAULT 'LINK' NULL COMMENT '来源';
ALTER TABLE up_apps_answer_sheet ADD broswer VARCHAR(64) DEFAULT '' NULL COMMENT '浏览器属性';



---- 这个载20171226 被部署到生产环境

---- 任务进度管理
CREATE TABLE up_tenants_task
(
    id BIGINT(20) DEFAULT 0,
    tenants_company_id BIGINT(20) DEFAULT 0,
    total_num INT DEFAULT 0 COMMENT '总进度数',
    current_completed_num INT DEFAULT 0 COMMENT '当前完成数量',
    last_message VARCHAR(256) DEFAULT '' COMMENT '最后消息',
    messages_json TEXT COMMENT '消息列表',
    download VARCHAR(256) DEFAULT '' COMMENT '如果有要下载的附件，则存储在这里。',
    add_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
ALTER TABLE up_tenants_task COMMENT = '任务进度管理';


ALTER TABLE up_tenants_task ADD questionnaire_id BIGINT(20) DEFAULT 0 NULL COMMENT '问卷id';
ALTER TABLE up_tenants_task ADD status ENUM('进行中', '已完成', '意外终止') DEFAULT '进行中' NULL;
ALTER TABLE up_tenants_task ADD task_type ENUM('问卷原始数据导出', '待添加') DEFAULT '问卷原始数据导出' NULL COMMENT '任务类型';
ALTER TABLE up_tenants_task
  MODIFY COLUMN task_type ENUM('问卷原始数据导出', '待添加') DEFAULT '问卷原始数据导出' COMMENT '任务类型' AFTER tenants_company_id,
  MODIFY COLUMN status ENUM('进行中', '已完成', '意外终止') DEFAULT '进行中' AFTER task_type;


  -- 2017-12-29
ALTER TABLE up_tenants_task ADD quick_tag VARCHAR(256) DEFAULT '' NULL COMMENT '快速索引';
ALTER TABLE up_tenants_task ADD region_filter VARCHAR(256) DEFAULT '' NULL COMMENT '网点索引';

ALTER TABLE up_tenants_task ADD version INT DEFAULT 1 NULL;
ALTER TABLE up_tenants_task ADD logic_delete INT DEFAULT 1 NULL;


-- V 1.0 上线部署
-- 微信公众号 ---- applied at : 20180102
ALTER TABLE up_weixin_account ADD account_type ENUM('网站应用', '公众号', '第三方公众号') DEFAULT '第三方公众号' NULL COMMENT '账户的类型';
ALTER TABLE up_weixin_user DROP at_expired_time;
ALTER TABLE up_weixin_user DROP resfresh_at;
ALTER TABLE up_weixin_user DROP resfresh_at_expired_time;
ALTER TABLE up_weixin_user DROP refresh_token;
ALTER TABLE up_weixin_user ADD xcx_access_token VARCHAR(255) DEFAULT '' NULL COMMENT '小程序的access token';
ALTER TABLE up_weixin_user ADD webapp_access_token VARCHAR(255) DEFAULT '' NULL COMMENT '优加网页应用的access_token';
ALTER TABLE up_weixin_user ADD thirdparty_access_token VARCHAR(255) DEFAULT '' NULL COMMENT '第三方公众号的access_token';
ALTER TABLE up_weixin_user CHANGE access_token youplus_access_token VARCHAR(255) NOT NULL DEFAULT '' COMMENT '优加公众号的accesstoken，也用于cookies索引';
ALTER TABLE up_weixin_user CHANGE openid thirdparty_openid VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'appid';
ALTER TABLE up_weixin_user CHANGE appid thirdparty_appid VARCHAR(64) DEFAULT '' COMMENT '微信公众号的appid';
ALTER TABLE up_weixin_user ADD webapp_openid VARCHAR(64) DEFAULT '' NULL COMMENT '网页应用的openid';
ALTER TABLE up_weixin_user ADD youplus_openid VARCHAR(64) DEFAULT '' NULL COMMENT '优加公众号的openid';
ALTER TABLE up_weixin_user
  MODIFY COLUMN unionid VARCHAR(64) DEFAULT '' AFTER id,
  MODIFY COLUMN youplus_openid VARCHAR(64) DEFAULT '' COMMENT '优加公众号的openid' AFTER unionid,
  MODIFY COLUMN webapp_openid VARCHAR(64) DEFAULT '' COMMENT '网页应用的openid' AFTER youplus_openid,
  MODIFY COLUMN thirdparty_access_token VARCHAR(255) DEFAULT '' COMMENT '第三方公众号的access_token' AFTER youplus_access_token,
  MODIFY COLUMN webapp_access_token VARCHAR(255) DEFAULT '' COMMENT '优加网页应用的access_token' AFTER thirdparty_access_token,
  MODIFY COLUMN xcx_access_token VARCHAR(255) DEFAULT '' COMMENT '小程序的access token' AFTER webapp_access_token,
  MODIFY COLUMN information_json TEXT COMMENT '个人信息的json数据包' AFTER is_subscribe;


-- 给题目添加分数  applied at : 20180102
ALTER TABLE up_apps_question_item ADD score INT DEFAULT 0 NULL COMMENT '评分题目对应的分数';
ALTER TABLE up_apps_question_item
  MODIFY COLUMN score INT DEFAULT 0 COMMENT '评分题目对应的分数' AFTER version;


-- 问卷报表设置  applied at : 20180102
ALTER TABLE up_apps_questionnaire
  MODIFY COLUMN service_type_json TEXT COMMENT '服务类型选项' AFTER nps_json,
  MODIFY COLUMN period_json TEXT COMMENT '时间段' AFTER is_default;



--2018-1-4 电话列表 delpied at 20180105
CREATE TABLE up_apps_phone_list
(
    id BIGINT(20) DEFAULT 0,
    phone_num VARCHAR(16) DEFAULT '' COMMENT '电话号码',
    type ENUM('whiteList', 'blackList') DEFAULT 'whiteList' COMMENT '名单类型',
    questionnaire_id BIGINT(20) DEFAULT 0 COMMENT '问卷id',
    logic_delete INT(11) DEFAULT 1,
    version INT(11) DEFAULT 0,
    add_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--2018-1-11 新的任务类型 delpied at 20180111
ALTER TABLE up_tenants_task MODIFY task_type ENUM('问卷原始数据导出', '数据统计运算', '待添加') DEFAULT '问卷原始数据导出' COMMENT '任务类型';



-- 2018-1-24 --deplied at 20180131
ALTER TABLE up_apps_questionnaire ADD project_name VARCHAR(32) DEFAULT '' NOT NULL COMMENT '项目名称';
ALTER TABLE up_apps_questionnaire MODIFY title TEXT COMMENT '网点用户id';

-- 2018-02-07 --deplied at 20180209
ALTER TABLE up_apps_question MODIFY question_type ENUM('单选', '多选', '手机验证', '排序', '填空', '评分', '服务方式', '是非', '总体满意度', '分项满意度', 'NPS', '个人信息', '时间', '其他') NOT NULL DEFAULT '单选' COMMENT '题目类型';

ALTER TABLE up_notification_sms_record ADD cookie VARCHAR(256) DEFAULT '' NOT NULL;
ALTER TABLE up_notification_sms_record ADD openid VARCHAR(256) DEFAULT '' NOT NULL;
ALTER TABLE up_notification_sms_record ADD ip VARCHAR(256) DEFAULT '' NULL;
ALTER TABLE up_notification_sms_record ADD company_id BIGINT(20) DEFAULT 0 NOT NULL;
ALTER TABLE up_notification_sms_record ADD questionnaire_id BIGINT(20) DEFAULT 0 NOT NULL;
ALTER TABLE up_notification_sms_record ADD day VARCHAR(32) DEFAULT '' NOT NULL COMMENT '日期';