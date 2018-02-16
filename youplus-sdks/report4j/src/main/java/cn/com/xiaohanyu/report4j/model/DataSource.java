package cn.com.xiaohanyu.report4j.model;

import cn.com.xiaohanyu.report4j.enums.DataSourceTypeEnum;
import cn.com.xiaohanyu.report4j.enums.DataTypeEnum;
import java.io.Serializable;

/**
 * Created by 严汉羽 on 2017/11/21.
 */
public class DataSource implements Serializable {

    private String tableName;

    private DataSourceTypeEnum dataSourceType;

    private DataTypeEnum dataType;

    private Object data;
}
