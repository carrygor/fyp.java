package cn.com.youplus.common.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by pan on 2016/9/12.
 */
public class StringHelper {

    /**
     * 随机字符串，唯一
     * @return
     */
    public static String getRandomStr() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取噪音字符串
     * @return
     */
    public static String getNonceStr() {
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 当前时间戳字符串
     * @return
     */
    public static String getTimestampStr() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    /**
     * 随机数字串
     * @param len 数字串长度
     * @return
     */
    public static String getRandomNumber(int len) {
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < len; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * 生成随机的OPENID
     * @param header 头
     * @return
     */
    public static String getRandomOpenid(String header) {
        StringBuilder sb = new StringBuilder(header);
        int len = Constants.WEIXIN_OPENID_LENGTH - header.length();
        return sb.append(StringHelper.getRandomLetterNo(len)).toString();
    }


    /**
     * 生成i到y的随机数
     */
    public static int getRandomNumber(int min,int max) {
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
    }
    /**
     * 生成随机货号
     */
    public static String MathRandGoodNo(int len){
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < len; i++) {
            sb.append(random.nextInt(16));
        }
        return sb.toString();
    }

    /**
     * 订单号
     * @return
     */
    public synchronized static String getOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
        return sdf.format(new Date()) + getRandomNumber(2);
    }

    /**
     * 生成带日期信息的图片名称，不带后缀
     * @return
     */
    public static String getImgName() {
        StringBuilder sb = new StringBuilder("upload/");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        sb.append(date.getYear() + 1900).append("/")
                .append(date.getMonth() + 1).append("/")
                .append(date.getDate()).append("/")
                .append(sdf.format(date)).append(getRandomNumber(4));
        return sb.toString();
    }

    /**
     * 随机字母数字混合
     * @param len
     * @return
     */
    public static String getRandomLetterNo(int len) {
        String lns = "ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0,count = lns.length(); i < len; i++) {
            sb.append(lns.charAt(random.nextInt(count)));
        }
        return sb.toString();
    }


    /**
     * 验证身份证
     * @param IDStr
     * @return "YES" 代表合法的身份证 ,其他值代表错误信息
     * @throws ParseException
     */
    public static String IDCardValidate(String IDStr) {
        IDStr = IDStr.toUpperCase();
        String tipInfo = "YES";// 记录错误信息
        String Ai = "";

        if(null == IDStr || IDStr.trim().isEmpty())
            return "身份证号码长度应该为15位或18位。";

        // 判断号码的长度 15位或18位
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            tipInfo = "身份证号码长度应该为15位或18位。";
            return tipInfo;
        }
        // 18位身份证前17位位数字，如果是15位的身份证则所有号码都为数字
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            tipInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            return tipInfo;
        }
        // 判断出生年月是否有效
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 日期
        if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
            tipInfo = "身份证出生日期无效。";
            return tipInfo;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                tipInfo = "身份证生日不在有效范围。";
                return tipInfo;
            }
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        } catch (ParseException e) {
            //e.printStackTrace();
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            tipInfo = "身份证月份无效";
            return tipInfo;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            tipInfo = "身份证日期无效";
            return tipInfo;
        }
        // 判断地区码是否有效
        Hashtable areacode = GetAreaCode();
        // 如果身份证前两位的地区码不在Hashtable，则地区码有误
        if (areacode.get(Ai.substring(0, 2)) == null) {
            tipInfo = "身份证地区编码错误。";
            return tipInfo;
        }
        if (isVarifyCode(Ai, IDStr) == false) {
            tipInfo = "身份证校验码无效，不是合法的身份证号码";
            return tipInfo;
        }
        return tipInfo;
    }

    /*
     * 判断第18位校验码是否正确 第18位校验码的计算方式： 1. 对前17位数字本体码加权求和 公式为：S = Sum(Ai * Wi), i =
     * 0, ... , 16 其中Ai表示第i个位置上的身份证号码数字值，Wi表示第i位置上的加权因子，其各位对应的值依次为： 7 9 10 5 8 4
     * 2 1 6 3 7 9 10 5 8 4 2 2. 用11对计算结果取模 Y = mod(S, 11) 3. 根据模的值得到对应的校验码
     * 对应关系为： Y值： 0 1 2 3 4 5 6 7 8 9 10 校验码： 1 0 X 9 8 7 6 5 4 3 2
     */
    private static boolean isVarifyCode(String Ai, String IDStr) {
        String[] VarifyCode = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2" };
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum = sum + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
        }
        int modValue = sum % 11;
        String strVerifyCode = VarifyCode[modValue];
        Ai = Ai + strVerifyCode;
        if (IDStr.length() == 18) {
            if (Ai.equals(IDStr) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将所有地址编码保存在一个Hashtable中
     *
     * @return Hashtable 对象
     */
    private static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    /**
     * 判断字符串是否为数字,0-9重复0次或者多次
     *
     * @param strnum
     * @return
     */
    private static boolean isNumeric(String strnum) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(strnum);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功能：判断字符串出生日期是否符合正则表达式：包括年月日，闰年、平年和每月31天、30天和闰月的28天或者29天
     *
     * @param strDate
     * @return boolean
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern.compile(
                "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 校验邮箱
     * @param value
     * @return
     */
    public static boolean checkEmail(String value){
        boolean flag=false;
        Pattern p1 = null;
        Matcher m = null;
        p1 = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        m = p1.matcher(value);
        flag = m.matches();
        return flag;
    }

    /**
     * 校验有效用户名
     */
    public static boolean isUserNameLegal(String str)throws PatternSyntaxException {
        if (str == null) {
            return false;
        }
        boolean flag=false;
        Pattern p1 = null;
        Matcher m = null;
        p1 = Pattern.compile("^([a-z]|[A-Z]|[0-9]|@|_)+$");
        m = p1.matcher(str);
        flag = m.matches();
        return flag;
    }

    /**
     * 用户编号合法
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static boolean isUserNumLegal(String str) throws PatternSyntaxException {
        if (str == null) {
            return false;
        }

        boolean flag=false;
        Pattern p1 = null;
        Matcher m = null;
        p1 = Pattern.compile("^([a-z]|[A-Z]|[0-9])+$");
        m = p1.matcher(str);
        flag = m.matches();
        return flag;
    }

    /**
     * 初始密码
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static boolean isFirstPasswordLegal(String str) throws PatternSyntaxException {
        if (str == null) {
            return false;
        }

        boolean flag=false;
        Pattern p1 = null;
        Matcher m = null;
        p1 = Pattern.compile("^([a-z]|[A-Z]|[0-9])+$");
        m = p1.matcher(str);
        flag = m.matches();
        return flag;
    }

    /**
     * 大陆号码或香港号码均可
     */
    public static boolean isPhoneLegal(String str)throws PatternSyntaxException {
        if (str == null) {
            return false;
        }
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
    }

    /**
     * 提交方法
     */
    public static boolean isMethod(String str){
        return "POST".equals(str) ? true : ("GET".equals(str) ? true : false);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str)throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * vaild token
     */
    public static boolean isValidToken(String str){
        try {
            String result = AESUtil.AESDecodeForWeb(str);

            if (result.length() < 3) {
                return false;
            }
            String up = result.substring(0,1);
            String down = result.substring(result.length() - 1,result.length());
            String real = result.substring(1, result.length() - 1);

            int s = Integer.parseInt(up);
            int p = Integer.parseInt(down);
            long ireal = Long.parseLong(real);

            if (p != ((ireal / 32768)%10)) {
                return  false;
            }

            if (s != ((ireal / 67546)%10)) {
                return  false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 根据身份证，获取生日
     * @param certificateNo
     * @return
     */
    public static Date parseCertificateNo(String certificateNo) {

        String myRegExpIDCardNo = "^\\d{6}(((19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])\\d{3}([0-9]|x|X))|(\\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])\\d{3}))$";
        boolean valid= Pattern.matches(myRegExpIDCardNo,certificateNo)||(certificateNo.length() == 17 && Pattern.matches(myRegExpIDCardNo,certificateNo.substring(0,15)));
        if(!valid){
            try {
                return Constants.DATE_FORMAT.parse(Constants.DEFAULT_BIRTHDAY);
            } catch (ParseException e) {
                e.printStackTrace();
                return new  Date();
            }
        }
        int idxSexStart = 16;
        int birthYearSpan = 4;
        //如果是15位的证件号码
        if(certificateNo.length() == 15) {
            idxSexStart = 14;
            birthYearSpan = 2;
        }
        //出生日期
        String year = (birthYearSpan == 2 ? "19" : "") + certificateNo.substring(6, 6 + birthYearSpan);
        String month = certificateNo.substring(6 + birthYearSpan, 6 + birthYearSpan + 2);
        String day = certificateNo.substring(8 + birthYearSpan, 8 + birthYearSpan + 2);
        String birthday = year + '/' + month + '/' + day;
        try {
            return Constants.DATE_FORMAT.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
            return new  Date();
        }
    }
}
