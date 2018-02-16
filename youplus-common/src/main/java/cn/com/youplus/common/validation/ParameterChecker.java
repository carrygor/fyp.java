package cn.com.youplus.common.validation;

import cn.com.youplus.common.exception.data.InvalidParameterException;
import cn.com.youplus.common.util.Constants;
import cn.com.youplus.common.util.StringHelper;
import cn.com.youplus.common.util.ValueHelper;
import cn.com.youplus.common.validation.annotation.Check;
import cn.com.youplus.common.validation.annotation.Valid;
import org.aspectj.lang.ProceedingJoinPoint;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;


/**
 * 这是一个Abstract类，为了区分不同的server对部分校验的区分，如token
 * Created by 严汉羽 on 2017/7/6.
 */

public abstract class ParameterChecker {

    private final static Logger logger = Logger.getLogger(ParameterChecker.class);

    public void checkValid(ProceedingJoinPoint joinPoint) throws Exception{
        Object[] args = null;
        Method method = null;
        Object target = null;
        String methodName = null;
        String str = "";
        try {
            methodName = joinPoint.getSignature().getName();
            target = joinPoint.getTarget();
            method = getMethodByClassAndName(target.getClass(), methodName);
            Annotation[][] annotations = method.getParameterAnnotations();
            args = joinPoint.getArgs(); // 方法的参数
            if (annotations != null) {
                for (int i = 0; i < annotations.length; i++) {
                    Annotation[] anno = annotations[i];
                    for (int j = 0; j < anno.length; j++) {
                        if (annotations[i][j].annotationType().equals(
                                Valid.class)) {
                            str = checkParam(args[i]);
                            if (StringUtils.hasText(str)) {
                                throw new InvalidParameterException("参数校验错误:" + str);
                            }
                        }
                    }
                }
            }
        } catch (Throwable e) {
            logger.error("参数校验异常" + e);
            throw new InvalidParameterException("参数校验错误:" + str);
        }
    }

    /**
     * 校验参数
     *
     * @param args
     * @return
     * @throws Exception
     */
    private String checkParam(Object args) throws Exception {
        String retStr = "";

        //这里一定要处理父类的字段，否则继承就无效了
        Class objectClass = args.getClass();
        while (objectClass != null && objectClass != Object.class) {
            Field[] field = objectClass.getDeclaredFields();// 获取方法参数（实体）的field
            for (int j = 0; j < field.length; j++) {
                Check check = field[j].getAnnotation(Check.class);// 获取方法参数（实体）的field上的注解Check
                if (check != null) {
                    String str = validateFiled(check, field[j], args);
                    if (StringUtils.hasText(str)) {
                        retStr = str;
                        return retStr;
                    }
                }
            }
            objectClass = objectClass.getSuperclass();
        }
        return retStr;
    }

    public abstract String validateToken(String token);
    public abstract String validatemodule(String token);

    /**
     * 校验参数规则
     *
     * @param check
     * @param field
     * @param args
     * @return
     * @throws Exception
     */
    public String validateFiled(Check check, Field field, Object args)
            throws Exception {
        field.setAccessible(true);
        // 获取field长度
        int length = 0;
        if (field.get(args) != null) {
            length = (String.valueOf(field.get(args))).length();
        }
        if (check.notNull()) {
            ValueHelper.isNone((String)field.get(args));
            if (ValueHelper.isNone((String)field.get(args))) {
                return field.getName() + "不能为空";
            }
        }
        if (check.maxLen() > 0 && (length > check.maxLen())) {
            return field.getName() + "长度不能大于" + check.maxLen();
        }

        if (check.minLen() > 0 && (length < check.minLen())) {
            return field.getName() + "长度不能小于" + check.minLen();
        }

        if (check.numeric() && field.get(args) != null) {
            try {
                new BigDecimal(String.valueOf(field.get(args)));
            } catch (Exception e) {
                return field.getName() + "必须为数值型";
            }
        }
        if (check.minNum() != -999999) {
            try {
                long fieldValue = Long.parseLong(String.valueOf(field.get(args)));
                if (fieldValue < check.minNum()) {
                    return field.getName() + "必须不小于" + check.minNum();
                }
            } catch (Exception e) {
                return field.getName() + "必须为数值型，且不小于" + check.minNum();
            }
        }

        if (check.maxNum() != -1) {
            try {
                long fieldValue = Long
                        .parseLong(String.valueOf(field.get(args)));
                if (fieldValue > check.maxNum()) {
                    return field.getName() + "必须不大于" + check.maxNum();
                }
            } catch (Exception e) {
                return field.getName() + "必须为数值型，且不大于" + check.maxNum();
            }
        }

        if (check.idcard()) {
            try {
                String tips = StringHelper.IDCardValidate(String.valueOf(field.get(args)));
                if(tips.equals("YES") == false){
                    return tips;
                }
            } catch (Exception e) {
                return field.getName() + "身份证号码错误！";
            }
        }

        if (check.module()) {
            String result = validatemodule(String.valueOf(field.get(args)));
            if (!ValueHelper.isNone(result)) {
                return result;
            }
        }

        if (check.phone()) {
            if(!StringHelper.isPhoneLegal(String.valueOf(field.get(args)))) {
                return field.getName() + "：手机号码不合法";
            }
        }

        if (check.token()) {
            String result = validateToken(String.valueOf(field.get(args)));
            if (!ValueHelper.isNone(result)) {
                return result;
            }
        }

        if (check.isDecimal()) {
            BigDecimal decimal = (BigDecimal)field.get(args);

            if (decimal.compareTo(BigDecimal.ZERO) > -1 &&
                    decimal.compareTo(new BigDecimal("100000")) < 0 ) { //合法范围

            } else {
                return field.getName() + "的数值应该在 0~99999.99";
            }
        }

        if (check.date()) {
            String date = (String)field.get(args);
            try{
                Constants.DATE_FORMAT.parse(date);
            } catch (Exception e) {
                return field.getName() + "[" + date + "]不是合法的日期格式";
            }
        }
        if (check.datetime()) {
            String date = (String)field.get(args);
            try{
                Constants.DATETIME_FORMAT.parse(date);
            } catch (Exception e) {
                return field.getName() + "[" + date + "]不是合法的日期时间格式";
            }
        }

        if (check.timestamp()) {
            try {
                long fieldValue = Long
                        .parseLong(String.valueOf(field.get(args)));
                if (fieldValue < 0) {
                    return field.getName() + "为非法时间戳[" + field.get(args) + "]";
                }
            } catch (Exception e) {
                return field.getName() + "为非法时间戳";
            }
        }
        return "";
    }




    /**
     * 根据类和方法名得到方法
     */
    @SuppressWarnings("rawtypes")
    public Method getMethodByClassAndName(Class c, String methodName)
            throws Exception {
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }
}
