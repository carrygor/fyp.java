package cn.com.youplus.apps.common.model.enums;

public enum QuestionnaireAttrEnum {

    LINK_DEVICE_CHECK("link-device-check", "LONG", "link", "限定每台电脑或者手机只能回答一次"),
    LINK_USER_CHECK("link-user-check", "LONG", "link", "限定每个用户只能回答一次（答题需要登陆验证）"),
    LINK_NEED_INFO("link-need-info", "LONG", "link", "问卷提交前需要顾客输入个人信息进行验证"),
    LINK_RANDOM_QUESTION("link-random-question", "STRING", "link", "问卷从题目设置的题库中随机选题"),


    WEIXIN_NEED_INFO("weixin-need-info", "LONG", "weixin", "问卷提交前需要顾客输入个人信息进行验证"),
    WEIXIN_USER_CHECK("weixin-user-check", "LONG", "weixin", "限定每个用户只能回答一次（答题需要登陆验证）"),
    WEIXIN_TIME_LIMIT("weixin-time-limit", "LONG", "weixin", "限定提交问卷的有效时间"),
    WEIXIN_USER_LOGIN("weixin-user-login", "LONG", "weixin", "需要用户授权个人信息"),
    WEIXIN_RANDOM_QUESTION("weixin-random-question", "STRING", "weixin", "问卷从题目设置的题库中随机选题"),


    NEED_INFO("need-info", "LONG", "all", "问卷提交前需要顾客输入个人信息进行验证"),
    DEVICE_CHECK("device-check", "LONG", "all", "限定每台电脑或者手机只能回答一次"),
    USER_LOGIN("user-login", "LONG", "all", "需要用户授权个人信息"),
    VERIFICATION_CODE("verification-code", "LONG", "all", "验证码"),
    RANDOM_QUESTION("random-question", "STRING", "all", "问卷从题目设置的题库中随机选题"),
    EACH_QUESTION("each-question", "LONG", "all", "打开后题目将逐题出现"),
    IP_AREA_CHECK("ip-area-check", "LONG", "all", "只有ip地址所在地区符合问卷地区才能答卷"),

    LIMIT_TOTAL_ANSWER_SHEET("limit-total-answer-sheet", "LONG", "all", "限制总答卷数"),
    LIMIT_EACH_TIMES_ANSWER_SHEET("limit-each-times-answer-sheet", "LONG", "all", "每个时间段限制的答卷数"),
    LIMIT_END_TIME("limit-end-time", "LONG", "all", "限制答卷结束时间"),
    LIMIT_SMS_NUM("limit-sms-num", "LONG", "all", "限定短信条数"),
    LIMIT_PHONE_NUM("limit-phone-num", "LONG", "all", "限定问卷回答者的范围"),

    CREATE_ALL_LINK("create-all-link", "LONG", "all", "是否生成过批量链接"),


    ;
    private String name;
    private String dataType;
    private String entrance;
    private String desc;

    QuestionnaireAttrEnum(String name, String dataType, String entrance, String desc) {
        this.name = name;
        this.dataType = dataType;
        this.entrance = entrance;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDataType() {
        return dataType;
    }

    public String getEntrance() {
        return entrance;
    }

    public String getDesc() {
        return desc;
    }
}
