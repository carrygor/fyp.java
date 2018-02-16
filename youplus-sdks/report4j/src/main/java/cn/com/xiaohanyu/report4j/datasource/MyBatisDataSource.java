package cn.com.xiaohanyu.report4j.datasource;

import cn.com.xiaohanyu.echarts4j.utils.StringUtil;
import cn.com.xiaohanyu.report4j.enums.DataTypeEnum;
import cn.com.xiaohanyu.report4j.model.Column;
import cn.com.xiaohanyu.report4j.model.Criteria;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.entity.Columns;
import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * Created by 严汉羽 on 2017/11/22.
 */
public class MyBatisDataSource {

    //region 从配置文件中得到的

    /**
     * 操作对应的数据源Service名称，必须是Spring IOC 管理的Bean
     */
    private String serviceName;

    /**
     * 类名称
     */
    private String beanClassName;

    /**
     * 数据的类型
     */
    private DataTypeEnum dataTypeEnum;

    /**
     * 字段列表，可以是
     */
    private List<Column> columns;

    /**
     * 查询条件
     */
    private List<Criteria> criterias;

    //endregion

    //region 运行时得到的，或者配置进来的

    /**
     * 页面大小
     */
    private int pageSize;

    /**
     * 页码
     */
    private int pageNum;

    /**
     * 对象的类型
     */
    private Class clazz;

    /**
     * 得到的类型
     */
    private IService service;

    /**
     * 表单查询，查询条件，需要判定是否存在
     */
    private List<Criteria> formCriterias;

    private Object data;
    //endregion

    public MyBatisDataSource() {

    }

    /**
     * 组装一个条件
     * @param opertion
     * @param wrapper
     * @return
     */
    Wrapper asumbleOneWrapper(Criteria opertion, Wrapper wrapper) {
        switch (opertion.getCriteriaTypeEnum()) {
            //region 比较操作
            case EQ:
                wrapper.eq(opertion.getField(), opertion.getParamOne());
                break;
            case GE:
                wrapper.ge(opertion.getField(), opertion.getParamOne());
                break;
            case GT:
                wrapper.gt(opertion.getField(), opertion.getParamOne());
                break;
            case LE:
                wrapper.le(opertion.getField(), opertion.getParamOne());
                break;
            case LT:
                wrapper.lt(opertion.getField(), opertion.getParamOne());
                break;

            //endregion

            //region 包含
            case IN:
                wrapper.in(opertion.getField(), opertion.getParamOne());
                break;
            case NotIN:
                wrapper.notIn(opertion.getField(), opertion.getParamOne());
                break;
            //endregion

            //region 模糊匹配
            case Like:
                wrapper.like(opertion.getField(), opertion.getParamOne());
                break;
            case Llike:
                wrapper.like(opertion.getField(), opertion.getParamOne(), SqlLike.LEFT);
                break;
            case Rlike:
                wrapper.like(opertion.getField(), opertion.getParamOne(), SqlLike.RIGHT);
                break;
            case NotLike:
                wrapper.notLike(opertion.getField(), opertion.getParamOne());
                break;
            case NotLlike:
                wrapper.notLike(opertion.getField(), opertion.getParamOne(), SqlLike.LEFT);
                break;
            case NotRlike:
                wrapper.notLike(opertion.getField(), opertion.getParamOne(), SqlLike.RIGHT);
                break;
            //endregion

            //region 条件域
            case Or:
                wrapper.or();
                break;
            case OrNew:
                wrapper.orNew();
                break;
            case And:
                wrapper.and();
                break;
            case AndNew:
                wrapper.andNew();
                break;
            //endregion

            //region 存在
            case Exists:
                wrapper.exists(opertion.getField());
                break;
            case NotExists:
                wrapper.notExists(opertion.getField());
                break;
            case IsNull:
                wrapper.isNull(opertion.getField());
                break;
            case IsNotNull:
                wrapper.isNotNull(opertion.getField());
                break;
            //endregion

            //region 区间
            case Between:
                wrapper.between(opertion.getField(), opertion.getParamOne(), opertion.getParamTwo());
                break;
            case NotBetween:
                wrapper.notBetween(opertion.getField(), opertion.getParamOne(), opertion.getParamTwo());
                break;
            //endregion

            //region 功能
            case GroupBy:
                wrapper.groupBy(opertion.getField());
                break;
            case OrderAsc:
                wrapper.orderBy(opertion.getField(), true);
                break;
            case OrderDesc:
                wrapper.orderBy(opertion.getField(), false);
                break;
            case Where:
                wrapper.where(opertion.getField());
                break;
            case Having:
                wrapper.having(opertion.getField());
                break;
            //endregion
        }
        return wrapper;
    }

    public Wrapper asumbleWrapper(Wrapper wrapper) {
        if (criterias == null || criterias.size() == 0) {
            return wrapper;
        }

        //组装columns
        if (columns != null && columns.size() > 0) {
            Columns columns = Columns.create();
            for (Column column : this.columns) {
                columns.column(column.getField(), column.getAs());
            }
            wrapper.setSqlSelect(columns);
        }

        if (formCriterias != null && formCriterias.size() > 0) {
            for (Criteria criteria : criterias) {
                try {
                    clazz.getDeclaredField(StringUtil.humpToLine(criteria.getField()).toUpperCase());
                    asumbleOneWrapper(criteria, wrapper);
                } catch (NoSuchFieldException e) {
                }
            }
        }

        for (Criteria opertion : criterias) {
            asumbleOneWrapper(opertion, wrapper);
        }

        return wrapper;
    }

    public String getServiceName() {
        return serviceName;
    }

    public MyBatisDataSource setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public MyBatisDataSource setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        return this;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public MyBatisDataSource setColumns(List<Column> columns) {
        this.columns = columns;
        return this;
    }

    public List<Criteria> getCriterias() {
        return criterias;
    }

    public MyBatisDataSource setCriterias(List<Criteria> criterias) {
        this.criterias = criterias;
        return this;
    }

    public void setInputSource(Object data) {
        this.data = data;
    }

    public Object fetchData(ApplicationContext applicationContext) throws ClassNotFoundException {
        clazz = Class.forName(getBeanClassName());
        service = (IService)applicationContext.getBean(getServiceName());

        Wrapper wrapper = asumbleWrapper(new EntityWrapper());
        switch (dataTypeEnum) {
            case Array:
                this.data = service.selectMaps(wrapper);
                break;
            case Object:
                this.data = service.selectMap(wrapper);
                break;
            case Page:
                //TODO 这里要来一个page配置
                Page<Map<String, Object>> pages = new Page<Map<String, Object>>(1 , 10);
                this.data = service.selectMapsPage(pages, wrapper);
                break;
        }
        return data;
    }

    public Object getData() {
        return data;
    }

    public Object fetchData() throws Exception {
        return null;
    }

    public DataTypeEnum getDataType() {
        return dataTypeEnum;
    }

    public DataTypeEnum getDataTypeEnum() {
        return dataTypeEnum;
    }

    public MyBatisDataSource setDataTypeEnum(DataTypeEnum dataTypeEnum) {
        this.dataTypeEnum = dataTypeEnum;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public MyBatisDataSource setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getPageNum() {
        return pageNum;
    }

    public MyBatisDataSource setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }
}
