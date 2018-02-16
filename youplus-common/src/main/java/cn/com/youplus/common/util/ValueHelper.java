package cn.com.youplus.common.util;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by pan on 2016/8/31.
 */
public class ValueHelper {

    public static boolean isNone(Map map) {
        return map == null || map.size() == 0;
    }

    public static boolean isNone(List lst) {
        return lst == null || lst.size() == 0;
    }

    public static boolean isNone(String str) {
        return str == null || "".equals(str.trim());
    }

    public static boolean isNone(Integer value){
        return value == null;
    }

    public static boolean isNoneOrZero(Long value){
        return value == null || value == 0;
    }

    public static boolean isNone(String ... args){
        for(String s : args){
            if(ValueHelper.isNone(s))
                return true;
        }
        return false;
    }

    public static boolean isNone(Date date) {
        if (date == null) {
            return true;
        }
        return false;
    }

    public static boolean isDateStr(String visitDate) {

        if(ValueHelper.isNone(visitDate)) {
            return false;
        }
        try {
            Constants.DATE_FORMAT.parse(visitDate);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static Integer tryParse(String str, Integer defVal) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return  defVal;
        }
    }

    public static Long tryParseLong(Object str, Long defVal) {
        try {
            return Long.parseLong(str.toString());
        } catch (Exception e) {
            return  defVal;
        }
    }

    public static Integer tryParse(Object str, Integer defVal) {
        try {
            return Integer.parseInt(str.toString());
        } catch (Exception e) {
            return  defVal;
        }
    }

    public static Double tryParseDouble(Object str, Double defVal) {
        try {
            return Double.parseDouble(str.toString());
        } catch (Exception e) {
            return  defVal;
        }
    }

    public static Object tryParseJson(Object str, Object defVal) {
        try {
            return JSONObject.parse(str.toString());
        } catch (Exception e) {
            return  defVal;
        }
    }


    /**
     * 四舍五入百分号，保留两位小数
     * @param val
     * @return
     */
    public static String rintPrecentage(float val) {
        int intVal = (int)Math.rint(val * 10000);
        BigDecimal bigDecimal = new BigDecimal((double)intVal / 100);
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).toString() + "%";
    }

    public static BigDecimal rintPrecentage(Object score, Object total) {
        if (score == null || total == null) {
            return BigDecimal.ZERO;
        }

        int hi = tryParse(score.toString(),0);
        int ti = tryParse(total.toString(),0);

        if (ti == 0) {
            return BigDecimal.ZERO;
        }

        return rint((float)hi * 100 / ti);
    }

    public static BigDecimal rint(double val) {
        int intVal = (int)Math.rint(val * 100);
        BigDecimal bigDecimal = new BigDecimal((double)intVal / 100);
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal rint(BigDecimal val) {
        int intVal = (int)Math.rint(val.floatValue() * 100);
        BigDecimal bigDecimal = new BigDecimal((double)intVal / 100);
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static int rintInt(BigDecimal val) {
        return (int)Math.rint(val.floatValue() * 100);
    }

    public static int rintInt(float val) {
        return (int)Math.rint(val * 100);
    }

    public static int rintCompare(float val1, float val2) {
        int int1 = (int)Math.rint(val1 * 100);
        int int2 = (int)Math.rint(val2 * 100);

        if (int1 == int2) {
            return 0;
        }

        if (int1 > int2) {
            return 1;
        }
        return -1;
    }

    public static int rintCompare(BigDecimal val1, BigDecimal val2) {
        int int1 = (int)Math.rint(val1.floatValue() * 100);
        int int2 = (int)Math.rint(val2.floatValue() * 100);

        if (int1 == int2) {
            return 0;
        }

        if (int1 > int2) {
            return 1;
        }
        return -1;
    }

    public static int dateMinusToMinutes(Date date1, Date date2) {
        long time = date1.getTime() - date2.getTime();
        return (int) Math.rint((double)time/ 60000);
    }

    public static int getDays(Date date) {
        if (date == null) {
            return 0;
        }
        Long days = date.getTime() / Constants.ONE_DAY;
        return days.intValue();
    }

    public static int getHours(Date date) {
        if (date == null) {
            return 0;
        }
        Long hours = date.getTime() / Constants.ONE_HOUR;
        return hours.intValue();
    }

    public static BigDecimal getNps(Object h, Object l, Object total) {
        if (h == null || l == null || total == null) {
            return BigDecimal.ZERO;
        }

        int hi = tryParse(h.toString(),0);
        int li = tryParse(l.toString(),0);
        int ti = tryParse(total.toString(),0);

        if (hi == 0 || li == 0 || ti == 0) {
            return BigDecimal.ZERO;
        }
        return rint(((float)hi * 100 / ti) - ((float)li * 100 / ti));
    }

    public static String getNpsStr(Object h, Object l, Object total) {
        BigDecimal decimal = getNps(h, l, total);
        if (decimal.floatValue() > 0) {
            return "+" + decimal + "%";
        } else {
            return decimal + "%";
        }
    }
}
