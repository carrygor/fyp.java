package cn.com.xiaohanyu.report4j.datasource;

import cn.com.xiaohanyu.echarts4j.utils.StringUtil;
import cn.com.xiaohanyu.report4j.Utils.ReflectionUtils;
import cn.com.xiaohanyu.report4j.enums.DataSourceFromEnum;
import cn.com.xiaohanyu.report4j.enums.DataSourceStatusEnum;
import cn.com.xiaohanyu.report4j.enums.DataSourceTypeEnum;
import cn.com.xiaohanyu.report4j.exception.DsConfigParseException;
import cn.com.xiaohanyu.report4j.exception.DsDuplicateSourceException;
import cn.com.xiaohanyu.report4j.exception.DsNotFoundException;
import cn.com.xiaohanyu.report4j.exception.DsUnsupportDataTypeException;
import cn.com.youplus.common.form.ReportFilterForm;
import cn.com.youplus.common.model.resources.AliyunProperties;
import cn.com.youplus.common.mybatis.FilterWarpper;
import cn.com.youplus.common.util.Constants;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.IService;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.context.FelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Created by 严汉羽 on 2017/11/27.
 */
public class DataSource implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(DataSource.class);

    //region 属性
    /**
     * 数据源的状态
     */
    private DataSourceStatusEnum status = DataSourceStatusEnum.NULL;

    /**
     * 数据源的名称，同一请求中，不能出现两个相同名称的ds
     */
    private String name;
    public static String NAME="name";

    /**
     * 数据源的类型,默认为MyBatis
     */
    private DataSourceTypeEnum dataSourceType = DataSourceTypeEnum.MyBatis;
    public static String DATA_SOURCE_TYPE = "dataSourceType";

    /**
     * 数据的配置文件形式，默认为本地resuorces
     */
    private DataSourceFromEnum dataSourceFrom = DataSourceFromEnum.Resources;
    public static String DATA_SOURCE_FROM = "dataSourceFrom";

    /**
     * 数据源的路径
     */
    private String sourcePath;
    public static String SOURCE_PATH = "sourcePath";

    /**
     * 数据源的路径
     */
    private JSONObject jsonObject;
    public static String JSON_OBJECT = "jsonObject";
    /**
     * 依赖的数据源
     */
    private List<String> dependencies;
    public static String DEPENDENCIES = "dependencies";

    private Map<String, DataSource> dataSourceMap;

    private ApplicationContext applicationContext;

    private Object formContext;

    MyBatisDataSource myBatisDataSource;
    FormDataSource formDataSource;
    CombinationDataSource combinationDataSource;

    private Object data;
    //endregion

    //region 构造函数

    /**
     * 给一个默认的
     */
    public DataSource() {
    }

    /**
     * 通过JSON配置构造一个DS
     * @param jsonObject 输入的json数据
     */
    public DataSource(JSONObject jsonObject) throws Exception {
        setDataSourceType(DataSourceTypeEnum.valueOf(jsonObject.getString(DATA_SOURCE_TYPE)));
        String from = jsonObject.getString(DATA_SOURCE_FROM);
        if (from != null && from.length() > 0) {
            try {
                setDataSourceFrom(DataSourceFromEnum.valueOf(from));
            } catch (Exception e) {
                throw new DsConfigParseException("不存在的数据来源:" + from);
            }
        }
        setSourcePath(jsonObject.getString(SOURCE_PATH));
        setJsonObject(jsonObject.getJSONObject(JSON_OBJECT));

        JSONArray jsonArray = jsonObject.getJSONArray(DEPENDENCIES);
        if (jsonArray != null && jsonArray.size() > 0) {
            dependencies = new ArrayList<>();
            for (Object str : jsonArray) {
                dependencies.add(str.toString());
            }
        }
        setStatus(DataSourceStatusEnum.INITIAL);
    }



    public DataSource fetchData(String dataSourceName) throws Exception {
        AliyunProperties aliyunProperties = (AliyunProperties)applicationContext.getBean("aliyunProperties");
        if (dataSourceMap == null || dataSourceMap.size() == 0) {
            return null;
        }
        DataSource dataSource = dataSourceMap.get(dataSourceName);
        if (dataSource == null) {
            throw new DsNotFoundException("找不到数据源: " + dataSourceName);
        }

        if (dataSource.getStatus().getCode() < DataSourceStatusEnum.LOADED.getCode()) {
            throw new Exception("数据源" + dataSourceName +"状态错误:" + dataSource.getStatus());
        }

        if (dataSource.getStatus() == DataSourceStatusEnum.FETCHED) {
            return dataSource;
        }

        List<String> dependencies = dataSource.getDependencies();
        if (dependencies != null && dependencies.size() > 0) {
            for (String dependency : dependencies) {
                fetchData(dependency);
            }
        }

        dataSource.fetchData();
        return dataSource;
    }

    /**
     * 通过JSON配置构造一个DS
     */
    public DataSource load() throws Exception {
        StringBuilder stringBuilder = new StringBuilder(512 * 1024);
        boolean isFromJson = false;
        switch (dataSourceFrom) {
            case OSS:
                URL url = new URL(sourcePath);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                if (conn == null) {
                    throw new Exception("网络连接失败:" + sourcePath);
                }
                DataInputStream input = new DataInputStream(conn.getInputStream());
                int i = input.read();
                while (i != -1){
                    stringBuilder.append((char)i);
                    i=input.read();
                }
                input.close();
                break;
            case Resources:
                BufferedReader br=new BufferedReader(new FileReader( sourcePath));
                i = br.read();
                while (i != -1){
                    stringBuilder.append((char)i);
                    i = br.read();
                }
                br.close();
                break;
            case LocalStorage:
                URL schemaUrl = DataSource.class.getResource(sourcePath);
                if (schemaUrl == null) {
                    throw new Exception("文件不存在:");
                }
                br = new BufferedReader(new FileReader( schemaUrl.getPath()));
                i=br.read();
                while (i != -1){
                    stringBuilder.append((char)i);
                    i = br.read();
                }
                br.close();
                break;
            case JsonObject:
                isFromJson = true;
                break;
            default:
                throw new DsNotFoundException("不支持的来源类型:" + dataSourceFrom.name());
        }
        if (isFromJson) {
            switch (dataSourceType) {
                case MyBatis:
                    myBatisDataSource = JSONObject.toJavaObject(jsonObject, MyBatisDataSource.class);
                    break;
                case Form:
                    formDataSource = JSONObject.toJavaObject(jsonObject, FormDataSource.class);
                    break;
                case Combination:
                    combinationDataSource = JSONObject.toJavaObject(jsonObject, CombinationDataSource.class);
                    break;
            }
        } else {
            switch (dataSourceType) {
                case MyBatis:
                    myBatisDataSource = JSONObject.parseObject(stringBuilder.toString(), MyBatisDataSource.class);
                    break;
                case Form:
                    formDataSource = JSONObject.parseObject(stringBuilder.toString(), FormDataSource.class);
                    break;
                case Combination:
                    combinationDataSource = JSONObject.parseObject(stringBuilder.toString(), CombinationDataSource.class);
                    break;
            }
        }
        setStatus(DataSourceStatusEnum.LOADED);
        return this;
    }

    public DataSource fetchData() throws Exception {
        ReportFilterForm filterForm = new ReportFilterForm();
        filterForm.setStartDate(new Date(new Date().getTime() - Constants.ONE_MONTH).getTime());
        filterForm.setEndDate(new Date().getTime());

        if (dataSourceMap != null && dependencies != null && dependencies.size() > 0) {
            for (String dependency : dependencies) {
                DataSource dataSource = dataSourceMap.get(dependency);

                if (dataSource == null) {
                    throw new DsNotFoundException("数据源[" + dependency + "]不存在");
                }

                if (dataSource.getStatus().getCode() < DataSourceStatusEnum.LOADED.getCode()) {
                    throw new DsNotFoundException("数据源[" + dependency + "]状态错误:" + dataSource.getStatus());
                }

                if (dataSource.getStatus() == DataSourceStatusEnum.FETCHED) {
                    continue;
                }
                dataSource.fetchData();
            }
        }

        switch(dataSourceType) {
            case Form:
                this.data = ReflectionUtils.invoke(formContext, "get" + StringUtil.toUpperCaseFirstOne(formDataSource.getProperityName()));
                break;
            case MyBatis:
                Class clazz = Class.forName(myBatisDataSource.getBeanClassName());
                IService service = (IService)applicationContext.getBean(myBatisDataSource.getServiceName());
                this.data = service.selectMaps( myBatisDataSource.asumbleWrapper(new FilterWarpper()
                        .filter(filterForm, clazz)));
                break;
            case Combination:
                FelEngine e = FelEngine.instance;
                FelContext ctx = e.getContext();

                if (dependencies == null ||
                        dependencies.size() == 0 ||
                        dataSourceMap == null ||
                        dataSourceMap.size() ==0) {
                    throw new DsNotFoundException("组合数据源，依赖的数据不能为空");
                }
                //塞进数据源
                for (String dependency : dependencies) {
                    ctx.set(dependency, dataSourceMap.get(dependency));
                }

                switch (combinationDataSource.getDataTypeEnum()) {
                    case Object:
                        Map<String, Object> map = new HashMap<>();
                        for (String key : combinationDataSource.getFomulas().keySet()) {
                            map.put(key, e.eval(combinationDataSource.getFomulas().get(key), ctx));
                        }
                        this.data = map;
                        break;
                    case Array:
                        List arrayList = new ArrayList<>();
                        int min = Integer.MAX_VALUE;
                        for (String dependency : dependencies) {
                            List list = (List)ctx.get(dependency);
                            if (min > list.size()) {
                                min = list.size();
                            }
                        }
                        while(min-- > 0) {
                            arrayList.add(e.eval(combinationDataSource.getFomula(), ctx));
                        }
                        this.data = arrayList;
                        break;
                    case Page:
                    default:
                        throw new DsUnsupportDataTypeException("不支持的数据类型:" + combinationDataSource.getDataTypeEnum().name());
                }
                break;
        }

        return this;
    }
    //endregion

    //region G&S
    public DataSourceTypeEnum getDataSourceType() {
        return dataSourceType;
    }

    public DataSource setDataSourceType(DataSourceTypeEnum dataSourceType) {
        this.dataSourceType = dataSourceType;
        return this;
    }

    public DataSourceFromEnum getDataSourceFrom() {
        return dataSourceFrom;
    }

    public DataSource setDataSourceFrom(DataSourceFromEnum dataSourceFrom) {
        this.dataSourceFrom = dataSourceFrom;
        return this;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public DataSource setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
        return this;
    }

    public String getName() {
        return name;
    }

    public DataSource setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getDependencies() {
        return dependencies;
    }

    public DataSource setDependencies(List<String> dependencies) {
        this.dependencies = dependencies;
        return this;
    }

    public DataSourceStatusEnum getStatus() {
        return status;
    }

    public DataSource setStatus(DataSourceStatusEnum status) {
        this.status = status;
        return this;
    }

    public Map<String, DataSource> getDataSourceMap() {
        return dataSourceMap;
    }

    public DataSource setDataSourceMap(Map<String, DataSource> dataSourceMap) {
        this.dataSourceMap = dataSourceMap;
        return this;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public DataSource setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        return this;
    }

    public Object getFormContext() {
        return formContext;
    }

    public DataSource setFormContext(Object formContext) {
        this.formContext = formContext;
        return this;
    }

    public Object getData() {
        return data;
    }

    public DataSource setData(Object data) {
        this.data = data;
        return this;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public DataSource setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        return this;
    }

    //endregion
}
