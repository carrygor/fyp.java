package cn.com.xiaohanyu.report4j.datasource;

import cn.com.xiaohanyu.report4j.enums.DataTypeEnum;
import org.springframework.context.ApplicationContext;

/**
 * Created by 严汉羽 on 2017/11/22.
 */
public class FormDataSource {

    //region 从配置文件中得到的

    /**
     * 数据的类型
     */
    private DataTypeEnum dataTypeEnum;

    /**
     * 字段名称
     */
    private String properityName;
    //endregion


    public DataTypeEnum getDataTypeEnum() {
        return dataTypeEnum;
    }

    public FormDataSource setDataTypeEnum(DataTypeEnum dataTypeEnum) {
        this.dataTypeEnum = dataTypeEnum;
        return this;
    }

    public String getProperityName() {
        return properityName;
    }

    public FormDataSource setProperityName(String properityName) {
        this.properityName = properityName;
        return this;
    }
}
