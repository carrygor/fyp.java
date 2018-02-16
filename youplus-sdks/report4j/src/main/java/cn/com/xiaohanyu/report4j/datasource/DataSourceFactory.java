package cn.com.xiaohanyu.report4j.datasource;

import cn.com.xiaohanyu.report4j.enums.DataSourceStatusEnum;
import cn.com.xiaohanyu.report4j.enums.DataSourceTypeEnum;
import cn.com.xiaohanyu.report4j.exception.DsDuplicateSourceException;
import cn.com.xiaohanyu.report4j.exception.DsNotFoundException;
import cn.com.youplus.common.model.resources.AliyunProperties;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 严汉羽 on 2017/11/27.
 */
public class DataSourceFactory {

    private Object contextForm;

    ApplicationContext applicationContext;

    Object formContext;

    Map<String, DataSource> dataSourceMap;

    /**
     * 扫描数据源
     * @param bean
     * @throws Exception
     */
    public void scanDataSources(Object bean) throws Exception {
        if (bean instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject)bean;
            //尝试是否为ds
            String dataSourceType = jsonObject.getString(DataSource.DATA_SOURCE_TYPE);
            boolean isDataSource;
            try {
                DataSourceTypeEnum.valueOf(dataSourceType);
                isDataSource = true;
            } catch (Exception e) {
                isDataSource = false;
            }
            if (isDataSource) {
                if (dataSourceMap == null) {
                    dataSourceMap = new HashMap<>();
                }
                //判断是否有重名
                String name = jsonObject.getString(DataSource.NAME);
                if (dataSourceMap.containsKey(name)) {
                    throw new DsDuplicateSourceException("已经有同名的数据源了:" + name);
                }
                dataSourceMap.put(name, new DataSource(jsonObject));
            } else {
                for (Object object : jsonObject.values()) {
                    scanDataSources(object);
                }
            }
        } else if (bean instanceof JSONArray) {
            for (Object object : (JSONArray) bean) {
                scanDataSources(object);
            }
        }
    }

    public void loadConfig() throws Exception {
        AliyunProperties aliyunProperties = (AliyunProperties)applicationContext.getBean("aliyunProperties");
        if (dataSourceMap == null || dataSourceMap.size() == 0) {
            return;
        }

        for (DataSource dataSource : dataSourceMap.values()) {
            if (dataSource.getStatus() == DataSourceStatusEnum.INITIAL) {
                dataSource.load();
            }
        }
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

    //region G&S

    public Object getContextForm() {
        return contextForm;
    }

    public DataSourceFactory setContextForm(Object contextForm) {
        this.contextForm = contextForm;
        return this;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public DataSourceFactory setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        return this;
    }

    public Object getFormContext() {
        return formContext;
    }

    public DataSourceFactory setFormContext(Object formContext) {
        this.formContext = formContext;
        return this;
    }

    public Map<String, DataSource> getDataSourceMap() {
        return dataSourceMap;
    }

    public DataSourceFactory setDataSourceMap(Map<String, DataSource> dataSourceMap) {
        this.dataSourceMap = dataSourceMap;
        return this;
    }

    //endregion
}
