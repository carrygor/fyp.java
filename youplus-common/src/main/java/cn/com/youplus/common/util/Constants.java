package cn.com.youplus.common.util;

import java.text.SimpleDateFormat;

/**
 * Created by 严汉羽 on 2017/10/12.
 */
public class Constants {
    /**
     * 一天的毫秒数
     */
    public static final int COOKIE_AGE = 3600 * 24 * 365;
    public static final long ONE_DAY = 24 * 3600 * 1000;
    public static final long ONE_DAY_LESS = 24 * 3600 * 1000 - 1;
    public static final long ONE_MINUTE = 60 * 1000;
    public static final long ONE_HOUR = 60 * 60 * 1000;
    public static final long ONE_MONTH = 30 * ONE_DAY;
    public static final long ONE_WEEK = 7 * ONE_DAY;

    /**
     * NPS 比较天数
     */
    public static final long NPS_DAYS = 90L;
    public static final long RECORD_DAYS = 30L;


    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATETIME_FORMAT_4_FILE = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
    public static final SimpleDateFormat DATETIME_INSURANCE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATETIME_ORDER_TIME_MINUTE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final String DEFAULT_DATE = "1971-01-01";
    public static final String DEFAULT_BIRTHDAY = "1900-01-01";

    public static final String REQUEST_TIME_KEY = "request_time";
    public static final String REQUEST_MODULE_KEY = "request_module";
    public static final String REQUEST_TENANTS_COMPANY_KEY = "request_tenants_company";
    public static final String REQUEST_TENANTS_USER_KEY = "request_tenants_user";


    /**
     * NPS指数
     */
    public static final int NPS_LOW_SCORE = 6;
    public static final int NPS_HIGHT_SCORE = 8;

    /**
     * 微信的Openid长度
     */
    public static final int WEIXIN_OPENID_LENGTH = 28;

    /**
     * 密码相关
     */
    public static final int PASSWORD_SALT_LENGTH = 16;
    public static final String DEFAULT_PASSWORD = "123456";

    /**
     * REST请求的加密字段
     */
    public static final String CMS_PARAM_SIGN = "sign";
    public static final String CMS_PARAM_TOKEN = "token";
    public static final String CMS_SUBJECT_KEY = "cms_subject";

    public static final String TENANTS_PARAM_SIGN = "sign";
    public static final String TENANTS_PARAM_TOKEN = "token";
    public static final String TENANTS_SUBJECT_KEY = "tenants_subject";
    public static final String TENANTS_FUNCTION_PREFIX = "F";

    public static final String APPS_PARAM_SIGN = "sign";
    public static final String APPS_PARAM_NOISE = "noise";
    public static final String APPS_PARAM_TOKEN = "token";
    public static final String APPS_NOISE_PRIFIX = "NOISE_";
    public static final String APPS_SUBJECT_KEY = "apps_subject";

    /**
     * DB关键字
     */
    public static final String COLUMN_ALL = "全部";
    public static final String COLUMN_COMPANY_ID = "TENANTS_COMPANY_ID";
    public static final String COLUMN_ADD_TIME = "ADD_TIME";
    public static final String COLUMN_QUICK_TAG = "QUICK_TAG";
    public static final String COLUMN_SERVICE_TYPE = "SERVICE_TYPE";

    /**
     * 微信公众号关键字
     */
    public static final String WEIXIN_SPECIAL_TD = "TD";
    public static final String WEIXIN_SPECIAL_DY = "DY";

    /**
     * Cookies
     */
    public static final String COOKIES_QUESTIONNIRE = "SGHDLKSDSILKMDHIUIOUIUHGBJHJK795H6E";
    public static final String COOKIES_YOUPLUS_ACCESS_TOKEN = "SJHAKFDLKJSAFUIJKHJHKJJHKJKJ787HJHJ";
    public static final String COOKIES_WEBAPP_ACCESS_TOKEN = "HJFKSDLJFLIOFOSDFOSEDIGF67832454JHH";
    public static final String COOKIES_TIHRDPARTY_ACCESS_TOKEN = "UWJHKWJFHUIVISAFSIDFHUDSHGIU67234HH";
    public static final String IP_KEY = "IP_KEY";
}
