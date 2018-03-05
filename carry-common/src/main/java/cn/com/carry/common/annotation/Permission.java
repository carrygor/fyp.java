package cn.com.carry.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 严汉羽 on 2017/7/6.
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {

    /**
     * 以下是间隔符号
     */
    String NEXT = ",";
    /**
     * 以下是权限对应的字符串
     */
    String SHOW = "显示";
    String GET = "查看";
    String LIST = "列表";
    String DELETE = "删除";
    String ADD = "添加";
    String EDIT = "编辑";
    String AUDIT = "审核";

    /**
     * CMS 资源
     */
    /**
     * 一级
     */
    String C_系统设置 = "系统设置:";
    String C_权限管理 = "权限管理:";
    String C_客户管理 = "客户管理:";

    /**
     * 二级
     */
    String C_用户管理 = "用户管理:";
    String C_角色管理 = "角色管理:";
    String C_租户角色管理 = "租户角色管理:";
    String C_权限设置 = "权限设置:";
    String C_租户权限设置 = "租户权限设置:";
    String C_客户列表 = "客户列表:";

    /**
     * TENANTS 菜单
     */
    /**
     * 首页
     */
    String T_首页 = "首页:";

    /**
     * 顾客反馈
     */
    String T_系统管理 = "系统管理:";

    String T_组织管理 = "组织管理:";
    String T_用户管理 = "用户管理:";
    String T_账户管理 = "账户管理:";

    /**
     * Tenants 资源
     */
    String T_报告接口 = "报告接口:";

    String T_总体满意度 = "总体满意度:";
    String T_NPS净推荐值 = "NPS净推荐值:";
    String T_警报 = "警报:";
    String T_反馈提交 = "反馈提交:";
    String T_分项成绩 = "分项成绩:";
    String T_导出报告 = "导出报告:";

    /**
     * 功能块
     */
    String F_反馈收集 = "反馈收集:";

    String F_微信端 = "微信端";
    String F_支付宝端 = "支付宝端";
    String F_短信 = "短信";


    String F_顾客反馈 = "顾客反馈:";

    String F_概览分析 = "概览分析";
    String F_提交率分析 = "提交率分析";
    String F_满意度分析 = "满意度分析";
    String F_满意度趋势 = "满意度趋势";
    String F_低分选项分析 = "低分选项分析";


    String F_体验报告 = "体验报告:";

    String F_顾客评论分析 = "顾客评论分析";


    String F_警报处理 = "警报处理:";

    String F_警报处理_子内容 = "警报处理";
    String F_低分报告 = "低分报告";
    String F_平台对比报告 = "平台对比报告";
    String F_追踪报告 = "追踪报告";
    String F_原始数据报告 = "原始数据报告";


    /**
     * 要检查的权限,包括Action,首字母小写的驼峰形式
     */
    String value() default "";

    String function() default "";

}
