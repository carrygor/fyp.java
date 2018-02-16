package cn.com.xiaohanyu.report4j.datasource;

import cn.com.xiaohanyu.report4j.enums.DataTypeEnum;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Created by 严汉羽 on 2017/11/22.
 */
public class CombinationDataSource {

    //region 从配置文件中得到的

    /**
     * 数据的类型
     */
    private DataTypeEnum dataTypeEnum;

    /**
     * 字段名称
     */
    private String fomula;

    /**
     * 字段名称
     */
    private Map<String,String> fomulas;
    //endregion

    public CombinationDataSource() {}

    public DataTypeEnum getDataTypeEnum() {
        return dataTypeEnum;
    }

    public CombinationDataSource setDataTypeEnum(DataTypeEnum dataTypeEnum) {
        this.dataTypeEnum = dataTypeEnum;
        return this;
    }

    public String getFomula() {
        return fomula;
    }

    public CombinationDataSource setFomula(String fomula) {
        this.fomula = fomula;
        return this;
    }

    public Map<String, String> getFomulas() {
        return fomulas;
    }

    public CombinationDataSource setFomulas(Map<String, String> fomulas) {
        this.fomulas = fomulas;
        return this;
    }
}
