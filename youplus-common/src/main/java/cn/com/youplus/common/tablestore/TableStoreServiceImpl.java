package cn.com.youplus.common.tablestore;

import cn.com.youplus.common.model.tablestore.TableStoreBaseModel;
import cn.com.youplus.common.util.StringUtil;
import cn.com.youplus.common.util.ValueHelper;
import com.alibaba.fastjson.JSONObject;
import com.alicloud.openservices.tablestore.ClientException;
import com.alicloud.openservices.tablestore.SyncClient;
import com.alicloud.openservices.tablestore.TableStoreException;
import com.alicloud.openservices.tablestore.core.utils.Preconditions;
import com.alicloud.openservices.tablestore.model.*;
import com.alicloud.openservices.tablestore.model.condition.SingleColumnValueCondition;
import com.alicloud.openservices.tablestore.model.filter.Filter;
import com.alicloud.openservices.tablestore.model.internal.CreateTableRequestEx;
import com.hannea.annotation.TableEntity;
import com.hannea.annotation.TableKey;
import com.hannea.constant.ColumnTypeObject;
import com.hannea.constant.PrimaryKeyTypeObject;
import com.hannea.tablestore.*;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.*;


/**
 * Class
 * 调用之前必须调用initTable 进行设置，否则会出现问题
 * @author 严汉羽
 * @date 2017/11/03
 */
public class TableStoreServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(TableStoreServiceImpl.class);

    protected SyncClient syncClient;

    public SyncClient getSyncClient() {
        return syncClient;
    }

    public TableStoreServiceImpl setSyncClient(SyncClient syncClient) {
        this.syncClient = syncClient;
        return this;
    }

    private List<Column> covert(Map<String, ColumnValueObject> column) throws Exception {
        List<Column> list = new ArrayList<Column>();
        for (String ck : column.keySet()) {
            ColumnValueObject cv = column.get(ck);
            Column c = null;
            switch (cv.getType()) {
                case DATE:
                    c = new Column(ck, ColumnValue.fromLong(cv.getValue() == null ? 0 : ((Date)cv.getValue()).getTime()));
                    break;
                case LONG:
                case INTEGER:
                    c = new Column(ck, ColumnValue.fromLong(ValueHelper.tryParseLong(cv.getValue(), 0L)));
                    break;
                case ARRAY:
                case MAP:
                    c = new Column(ck, ColumnValue.fromString(JSONObject.toJSONString(cv.getValue())));
                    break;
                case STRING:
                    c = new Column(ck, ColumnValue.fromString(String.valueOf(cv.getValue())));
                    break;
                case BOOLEAN:
                    c = new Column(ck, ColumnValue.fromBoolean(cv.getValue() == null ? false : (Boolean) cv.getValue()));
                    break;
                case DOUBLE:
                case FLOAT:
                    c = new Column(ck, ColumnValue.fromDouble(ValueHelper.tryParseDouble(cv.getValue(), 0D)));
                    break;
                case BINARY:
                    c = new Column(ck, ColumnValue.fromBinary((byte[]) cv.getValue()));
                    break;
                default:
                    throw new Exception("covert,不支持的类型:"+ cv.getType().name());
            }
            if (c != null) {
                list.add(c);
            }
        }
        return list;
    }

    private List<Column> covertSelective(Map<String, ColumnValueObject> column) throws Exception {
        List<Column> list = new ArrayList<Column>();
        for (String ck : column.keySet()) {
            ColumnValueObject cv = column.get(ck);
            Column c = null;
            if(cv.getValue() == null){
                continue;
            }
            switch (cv.getType()) {
                case DATE:
                    c = new Column(ck, ColumnValue.fromLong(((Date)cv.getValue()).getTime()));
                    break;
                case LONG:
                case INTEGER:
                    c = new Column(ck, ColumnValue.fromLong(Long.valueOf(String.valueOf(cv.getValue()))));
                    break;
                case ARRAY:
                case MAP:
                    c = new Column(ck, ColumnValue.fromString(JSONObject.toJSONString(cv.getValue())));
                    break;
                case STRING:
                    c = new Column(ck, ColumnValue.fromString(String.valueOf(cv.getValue())));
                    break;
                case BOOLEAN:
                    c = new Column(ck, ColumnValue.fromBoolean((Boolean) cv.getValue()));
                    break;
                case FLOAT:
                case DOUBLE:
                    c = new Column(ck, ColumnValue.fromDouble(Double.valueOf(String.valueOf(cv.getValue()))));
                    break;
                case BINARY:
                    c = new Column(ck, ColumnValue.fromBinary((byte[]) cv.getValue()));
                    break;
                default:
                    throw new Exception("covert,不支持的类型:"+ cv.getType().name());
            }
            if (c != null) {
                list.add(c);
            }
        }
        return list;
    }

    private PrimaryKey buildKey(Map<String, PrimaryKeyValueObject> primaryKey) {
        // 主键
        int size = primaryKey.size();
        PrimaryKeyColumn[] arr = new PrimaryKeyColumn[size];

        for (String pk : primaryKey.keySet()) {
            PrimaryKeyValueObject pkv = primaryKey.get(pk);
            switch (pkv.getType()) {
                case INTEGER:
                    PrimaryKeyValue valueInt= PrimaryKeyValue.fromLong(Long.valueOf(String.valueOf(pkv.getValue())));
                    Preconditions.checkArgument(pk != null && !pk.isEmpty(), "The name of primary key should not be null or empty.");
                    Preconditions.checkNotNull(valueInt, "The value of primary key should not be null.");
                    arr[pkv.getSort()] = new PrimaryKeyColumn(pk, valueInt);
                    break;
                case STRING:
                    PrimaryKeyValue valueStr= PrimaryKeyValue.fromString(String.valueOf(pkv.getValue()));
                    Preconditions.checkArgument(pk != null && !pk.isEmpty(), "The name of primary key should not be null or empty.");
                    Preconditions.checkNotNull(valueStr, "The value of primary key should not be null.");
                    arr[pkv.getSort()] = new PrimaryKeyColumn(pk, valueStr);
                    break;
                case BINARY:
                    PrimaryKeyValue valueBinary= PrimaryKeyValue.fromString(String.valueOf(pkv.getValue()));
                    Preconditions.checkArgument(pk != null && !pk.isEmpty(), "The name of primary key should not be null or empty.");
                    Preconditions.checkNotNull(valueBinary, "The value of primary key should not be null.");
                    arr[pkv.getSort()] = new PrimaryKeyColumn(pk, valueBinary);
                    break;
                default:
                    break;
            }
        }
        PrimaryKey primaryKeys = new PrimaryKey(arr);
        return primaryKeys;
    }

    //region 表操作
    /**
     * 判断表是否存在
     * @param table 判断的对象
     * @return 是否存在
     */
    public boolean exist(StoreTable table) {
        return exist(table.getTableName());
    }

    /**
     * 判断表是否存在
     * @param tableName 判断的对象
     * @return 是否存在
     */
    public boolean exist(String tableName) {
        DescribeTableRequest request = new DescribeTableRequest(tableName);
        try {
            DescribeTableResponse response = syncClient.describeTable(request);
            TableMeta tableMeta = response.getTableMeta();
            if (tableMeta != null && tableMeta.getTableName() != null) {
                return true;
            }
        } catch (TableStoreException e) {
            return false;
        } catch (ClientException e) {
            return false;
        }
        return false;
    }

    /**
     * 根据对象创建表
     * @param clazz 需要创建表的类
     * @return 创建是否成功
     */
    public <T extends TableStoreBaseModel> boolean createTable(Class<T> clazz) {
        StoreTable table = TableStoreBaseModel.asmublyStoreTable(clazz);

        if (this.exist(table)) {
            return false;
        }

        TableMeta tableMeta = new TableMeta(table.getTableName());
        List<PrimaryKeySchemaObject> primaryKey = table.getPrimaryKeySchemaObjectList();
        for (PrimaryKeySchemaObject k : primaryKey) {

            if (k.getOption() != null) {
                switch (k.getType()) {
                    case INTEGER:
                        tableMeta.addPrimaryKeyColumn(
                                new PrimaryKeySchema(k.getName(), PrimaryKeyType.INTEGER, PrimaryKeyOption.AUTO_INCREMENT));
                        break;
                    case STRING:
                        tableMeta.addPrimaryKeyColumn(
                                new PrimaryKeySchema(k.getName(), PrimaryKeyType.STRING, PrimaryKeyOption.AUTO_INCREMENT));
                        break;
                    case BINARY:
                        tableMeta.addPrimaryKeyColumn(
                                new PrimaryKeySchema(k.getName(), PrimaryKeyType.BINARY, PrimaryKeyOption.AUTO_INCREMENT));
                        break;
                    default:
                        break;
                }
            } else {
                switch (k.getType()) {
                    case INTEGER:
                        tableMeta.addPrimaryKeyColumn(new PrimaryKeySchema(k.getName(), PrimaryKeyType.INTEGER));
                        break;
                    case STRING:
                        tableMeta.addPrimaryKeyColumn(new PrimaryKeySchema(k.getName(), PrimaryKeyType.STRING));
                        break;
                    case BINARY:
                        tableMeta.addPrimaryKeyColumn(new PrimaryKeySchema(k.getName(), PrimaryKeyType.BINARY));
                        break;
                    default:
                        break;
                }
            }
        }
        // 数据的过期时间 单位 -1代表永不过期. 为 365 * 24 * 3600.
        int timeToLive = -1;
        // 保存的最大版本数
        int maxVersions = 1;
        TableOptions tableOptions = new TableOptions(timeToLive, maxVersions);
        CreateTableRequestEx request = new CreateTableRequestEx(tableMeta, tableOptions);
        CreateTableResponse r = syncClient.createTable(request);
        return r.getRequestId() != null;
    }

    //endregion

    //region 写操作
    /**
     * 写入一条记录
     * @param row 写入记录的对象
     * @return
     */
    public <T extends TableStoreBaseModel> boolean putRow(T row) throws Exception {
        return putRow(TableStoreBaseModel.asmublyStoreTableTableRow(row));
    }

    /**
     * 写入一条记录
     * @param row 写入的TableRow对象
     * @return 写入是否成功
     */
    public boolean putRow(StoreTableRow row) throws Exception {
        return putRow(row.getTableName(), row.getPrimaryKeyValueObjectMap(), row.getColumnValueObjectMap());
    }

    /**
     * 写入一条记录
     * @param tableName
     * @param primaryKey
     * @param column
     * @return
     */
    public boolean putRow(String tableName, Map<String, PrimaryKeyValueObject> primaryKey,
                          Map<String, ColumnValueObject> column) throws Exception {
        if(StringUtils.isBlank(tableName)){
                logger.error("[putRow] you need set table name first,put error");
                return false;
        }
        PrimaryKey primaryKeys = buildKey(primaryKey);
        RowPutChange rowPutChange = new RowPutChange(tableName, primaryKeys);
        List<Column> list = covert(column);
        for (Column c : list) {
            rowPutChange.addColumn(c);
        }
        PutRowResponse r = syncClient.putRow(new PutRowRequest(rowPutChange));
        return r.getRequestId() != null;
    }

    /**
     * 有选择地写入记录
     * @param row 待写入的记录
     * @return 是否写入成功
     */
    public <T extends TableStoreBaseModel> boolean putRowSelective(T row) throws Exception {
        return putRowSelective(TableStoreBaseModel.asmublyStoreTableTableRow(row));
    }

    /**
     * 有选择地写入记录
     * @param row 待写入的记录
     * @return 是否写入成功
     */
    public boolean putRowSelective(StoreTableRow row) throws Exception {
        return putRowSelective(row.getTableName(), row.getPrimaryKeyValueObjectMap(), row.getColumnValueObjectMap());
    }



    /**
     * 有选择地写入记录
     * @param tableName
     * @param primaryKey
     * @param column
     * @return
     */
    public boolean putRowSelective(String tableName, Map<String, PrimaryKeyValueObject> primaryKey,
                          Map<String, ColumnValueObject> column) throws Exception {
        if(StringUtils.isBlank(tableName)){
                logger.error("[putRowSelective] you need set table name first,put error");
                return false;
        }
        PrimaryKey primaryKeys = buildKey(primaryKey);
        RowPutChange rowPutChange = new RowPutChange(tableName, primaryKeys);
        List<Column> list = covertSelective(column);
        for (Column c : list) {
            rowPutChange.addColumn(c);
        }
        PutRowResponse r = syncClient.putRow(new PutRowRequest(rowPutChange));
        return r.getRequestId() != null;
    }

    //endregion

    //region 读

    public <T extends TableStoreBaseModel> T row2Data(Row row, T t) throws Exception {
        return row2Data(row, t, true);
    }
    public <T extends TableStoreBaseModel> T row2Data(Row row, T t, boolean isCopyKey) throws Exception {
        if (row == null) {
            return t;
        }

        PrimaryKeyColumn[] keys = row.getPrimaryKey().getPrimaryKeyColumns();
        for (PrimaryKeyColumn c : keys) {
            try {
                PropertyDescriptor pd;
                if (c.getName().substring(0, 2).equals("is")) {
                    String field = c.getName();
                    String readMethodName = "get" + field.substring(2, 3).toUpperCase() + field.substring(3, field.length());
                    String writeMethodName = "set" + field.substring(2, 3).toUpperCase() + field.substring(3, field.length());
                    pd = new PropertyDescriptor(c.getName(), t.getClass(), readMethodName, writeMethodName);
                } else {
                    pd = new PropertyDescriptor(c.getName(), t.getClass());
                }
                Method method = pd.getWriteMethod();//获得写方法

                PrimaryKeyValue cv = c.getValue();

                switch (cv.getType()) {
                    case STRING:
                        method.invoke(t, cv.asString());
                        break;
                    case INTEGER:
                        Parameter[] typeVariables = method.getParameters();
                        String name = typeVariables[0].getType().getSimpleName();
                        switch (name) {
                            case "int":
                            case "Integer":
                                method.invoke(t, (int) cv.asLong());
                                break;
                            case "byte":
                            case "Byte":
                                method.invoke(t, (byte) cv.asLong());
                                break;
                            case "long":
                            case "Long":
                                method.invoke(t, cv.asLong());
                                break;
                            case "short":
                            case "Short":
                                method.invoke(t, (short) cv.asLong());
                                break;
                            case "Date":
                                method.invoke(t, cv.asLong() == 0 ? null : new Date(cv.asLong()));
                                break;
                            default:
                                throw new Exception("不支持的数据类型");
                        }
                        break;
                    case BINARY:
                        method.invoke(t, cv.asBinary());
                        break;
                    default:
                        throw new Exception("不支持的数据类型");
                }

            } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
                //
                logger.error("读取数据出错了", e);
            }
        }
        Column[] cols = row.getColumns();

        long updateTimestamp = 0;
        for (Column c : cols) {
            long timestamp = c.getTimestamp();
            if (timestamp > updateTimestamp) {
                updateTimestamp = timestamp;
            }
            try {
                PropertyDescriptor pd;
                if (c.getName().substring(0, 2).equals("is")) {
                    String field = c.getName();
                    String readMethodName = "get" + field.substring(2, 3).toUpperCase() + field.substring(3, field.length());
                    String writeMethodName = "set" + field.substring(2, 3).toUpperCase() + field.substring(3, field.length());
                    pd = new PropertyDescriptor(c.getName(), t.getClass(), readMethodName, writeMethodName);
                } else {
                    pd = new PropertyDescriptor(c.getName(), t.getClass());
                }
                Method method = pd.getWriteMethod();//获得写方法

                ColumnValue cv = c.getValue();

                switch (cv.getType()) {
                    case STRING:
                        Parameter typeVariable = method.getParameters()[0];
                        String name = typeVariable.getType().getSimpleName();

                        if (name.contains("List")) {
                            //todo :
                            ParameterizedType t1 = (ParameterizedType)typeVariable.getParameterizedType();
                            Class clazz = (Class)t1.getActualTypeArguments()[0];

                            method.invoke(t, JSONObject.parseArray(cv.asString(), clazz));
                        } else if (name.contains("Map")) {
                            //todo :
                            method.invoke(t, JSONObject.parseObject(cv.asString(), Object.class));
                        } else {
                            method.invoke(t, cv.asString());
                        }
                        break;
                    case INTEGER:
                        typeVariable = method.getParameters()[0];
                        name = typeVariable.getType().getSimpleName();
                        switch (name) {
                            case "int":
                            case "Integer":
                                method.invoke(t, (int) cv.asLong());
                                break;
                            case "byte":
                            case "Byte":
                                method.invoke(t, (byte) cv.asLong());
                                break;
                            case "long":
                            case "Long":
                                method.invoke(t, cv.asLong());
                                break;
                            case "short":
                            case "Short":
                                method.invoke(t, (short) cv.asLong());
                                break;
                            case "Date":
                                method.invoke(t, cv.asLong() == 0 ? null : new Date(cv.asLong()));
                                break;
                            default:
                                throw new Exception("不支持的数据类型");
                        }
                        break;
                    case BOOLEAN:
                        method.invoke(t, cv.asBoolean());
                        break;
                    case DOUBLE:
                        typeVariable = method.getParameters()[0];
                        name = typeVariable.getType().getSimpleName();
                        switch (name) {
                            case "float":
                            case "Float":
                                method.invoke(t, (float) cv.asDouble());
                                break;
                            case "double":
                            case "Double":
                                method.invoke(t, cv.asDouble());
                                break;
                        }
                        break;
                    case BINARY:
                        method.invoke(t, cv.asBinary());
                        break;
                    default:
                        throw new Exception("不支持的数据类型");
                }

            } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
                //
                logger.error("读取数据出错了", e);
            }

        }

        if (updateTimestamp > 0) {
            // TODO: 2018/1/17/017
            PropertyDescriptor pd = new PropertyDescriptor("updateTime", t.getClass());
            Method method = pd.getWriteMethod();//获得写方法
            if (method != null) {
                method.invoke(t, new Date(updateTimestamp));
            }
        }

        return t;
    }

    /**
     * 读取一条记录
     * @param t 记录条件
     * @return 是否读写成功
     * @throws Exception
     */
    public <T extends TableStoreBaseModel> boolean getRow(T t) throws Exception {
        StoreTableRow row = TableStoreBaseModel.asmublyStoreTableTableRow4Get(t);
        PrimaryKey primaryKeys = buildKey(row.getPrimaryKeyValueObjectMap());
        // 读一行
        SingleRowQueryCriteria criteria = new SingleRowQueryCriteria(row.getTableName(), primaryKeys);
        // 设置读取版本
        criteria.setMaxVersions(1);
        GetRowResponse getRowResponse = syncClient.getRow(new GetRowRequest(criteria));
        if (getRowResponse == null) {
            return false;
        }
        Row rows = getRowResponse.getRow();
        if (rows == null) {
            return false;
        }
        row2Data(rows, t);
        return getRowResponse.getRequestId() != null;
    }

    /**
     * 读取一条记录
     * @param row 记录条件
     * @return 是否读写成功
     */
    public boolean getRow(StoreTableRow row) {
        PrimaryKey primaryKeys = buildKey(row.getPrimaryKeyValueObjectMap());
        // 读一行
        SingleRowQueryCriteria criteria = new SingleRowQueryCriteria(row.getTableName(), primaryKeys);
        // 设置读取版本
        criteria.setMaxVersions(1);
        GetRowResponse getRowResponse = syncClient.getRow(new GetRowRequest(criteria));
        if (getRowResponse == null) {
            return false;
        }
        Row rows = getRowResponse.getRow();
        if (rows == null) {
            return false;
        }
        Column[] cols = rows.getColumns();
        Map<String, ColumnValueObject> v = new LinkedHashMap<>();
        for (Column c : cols) {
            ColumnValue cv = c.getValue();
            ColumnValueObject cvo = null;
            switch (cv.getType()) {
                case STRING:
                    cvo = new ColumnValueObject(cv.asString(), ColumnTypeObject.STRING);
                    break;
                case INTEGER:
                    cvo = new ColumnValueObject(cv.asLong(), ColumnTypeObject.INTEGER);
                    break;
                case BOOLEAN:
                    cvo = new ColumnValueObject(cv.asBoolean(), ColumnTypeObject.BOOLEAN);
                    break;
                case DOUBLE:
                    cvo = new ColumnValueObject(cv.asDouble(), ColumnTypeObject.DOUBLE);
                    break;
                case BINARY:
                    cvo = new ColumnValueObject(cv.asBinary(), ColumnTypeObject.BINARY);
                    break;

                default:
                    break;
            }
            v.put(c.getName(), cvo);
        }
        row.setColumnValueObjectMap(v);
        return getRowResponse.getRequestId() != null;
    }

    //endregion

    //region 删
    /**
     * 删除一条记录
     * @param t
     * @return
     */
    public <T extends TableStoreBaseModel> boolean deleteRow(T t) {
        StoreTableRow row = TableStoreBaseModel.asmublyStoreTableTableRow4Get(t);
        PrimaryKey primaryKeys = buildKey(row.getPrimaryKeyValueObjectMap());
        RowDeleteChange rowDeleteChange = new RowDeleteChange(row.getTableName(), primaryKeys);
        DeleteRowResponse r = syncClient.deleteRow(new DeleteRowRequest(rowDeleteChange));
        return r.getRequestId() != null;
    }

    /**
     * 删除一条记录
     * @param row
     * @return
     */
    public boolean deleteRow(StoreTableRow row) {
        PrimaryKey primaryKeys = buildKey(row.getPrimaryKeyValueObjectMap());
        RowDeleteChange rowDeleteChange = new RowDeleteChange(row.getTableName(), primaryKeys);
        DeleteRowResponse r = syncClient.deleteRow(new DeleteRowRequest(rowDeleteChange));
        return r.getRequestId() != null;
    }

    //endregion

    //region 改
    public <T extends TableStoreBaseModel> boolean updateRowSelective(T t) throws Exception {
        return updateRowSelective(TableStoreBaseModel.asmublyStoreTableTableRow(t));
    }

    public boolean updateRowSelective(StoreTableRow row) throws Exception {
        PrimaryKey primaryKeys = buildKey(row.getPrimaryKeyValueObjectMap());
        RowUpdateChange rowUpdateChange = new RowUpdateChange(row.getTableName(), primaryKeys);
        List<Column> list = covertSelective(row.getColumnValueObjectMap());
        for (Column c : list) {
            rowUpdateChange.put(c);
        }
        UpdateRowResponse r = syncClient.updateRow(new UpdateRowRequest(rowUpdateChange));
        return r.getRequestId() != null;
    }

    public <T extends TableStoreBaseModel> boolean updateRow(T t) throws Exception {
        return updateRow(TableStoreBaseModel.asmublyStoreTableTableRow(t));
    }

    public boolean updateRow(StoreTableRow row) throws Exception {
        PrimaryKey primaryKeys = buildKey(row.getPrimaryKeyValueObjectMap());
        RowUpdateChange rowUpdateChange = new RowUpdateChange(row.getTableName(), primaryKeys);
        List<Column> list = covert(row.getColumnValueObjectMap());
        for (Column c : list) {
            rowUpdateChange.put(c);
        }
        UpdateRowResponse r = syncClient.updateRow(new UpdateRowRequest(rowUpdateChange));
        return r.getRequestId() != null;
    }

    public boolean updateRow(String tablName, Map<String, PrimaryKeyValueObject> primaryKeyMap,List<Column> list){
        PrimaryKey primaryKeys = buildKey(primaryKeyMap);
        RowUpdateChange rowUpdateChange = new RowUpdateChange(tablName, primaryKeys);
        for (Column c : list) {
            rowUpdateChange.put(c);
        }
        UpdateRowResponse r = syncClient.updateRow(new UpdateRowRequest(rowUpdateChange));
        return r.getRequestId() != null;
    }

    //endregion

    /**
     * 请注意column支持int long double bigdecimal
     * @param storeTableRow
     * @param columnNameList
     * @return
     */
    public boolean updateIncrement(StoreTableRow storeTableRow, List<String> columnNameList){
        PrimaryKey primaryKeys = buildKey(storeTableRow.getPrimaryKeyValueObjectMap());
        SingleRowQueryCriteria criteria = new SingleRowQueryCriteria(storeTableRow.getTableName());
        criteria.setPrimaryKey(primaryKeys);
        criteria.setMaxVersions(1);
        GetRowRequest request = new GetRowRequest(criteria);
        RowUpdateChange rowUpdateChange = new RowUpdateChange(storeTableRow.getTableName(), primaryKeys);
        if (columnNameList != null) {
            Map<String, ColumnValueObject> map = storeTableRow.getColumnValueObjectMap();
            Map<String, PrimaryKeyValueObject> primaryMap = storeTableRow.getPrimaryKeyValueObjectMap();
            Map<String, ColumnValueObject> colMap = new HashMap<>();
            for(Map.Entry<String,ColumnValueObject> entry : map.entrySet()){
                if(!(columnNameList.contains(entry.getKey()) || primaryMap.containsKey(entry.getKey()))){
                    colMap.put(entry.getKey(),entry.getValue());
                }
            }
            for (String colName : columnNameList) {
                while (true) {
                    boolean succeed = getAndSet(request, rowUpdateChange, colName, map,colMap);
                    if (!succeed) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                        }
                    } else {
                        break;
                    }
                }
            }

        }
        return true;
    }

    public boolean updateIncrementAfterRowCheck(StoreTableRow storeTableRow, Row row, List<String> columnNameList){
        PrimaryKey primaryKeys = buildKey(storeTableRow.getPrimaryKeyValueObjectMap());
        SingleRowQueryCriteria criteria = new SingleRowQueryCriteria(storeTableRow.getTableName());
        criteria.setPrimaryKey(primaryKeys);
        criteria.setMaxVersions(1);
        GetRowRequest request = new GetRowRequest(criteria);
        RowUpdateChange rowUpdateChange = new RowUpdateChange(storeTableRow.getTableName(), primaryKeys);
        if(columnNameList != null) {
            Map<String, ColumnValueObject> map = storeTableRow.getColumnValueObjectMap();
            Map<String, PrimaryKeyValueObject> primaryMap = storeTableRow.getPrimaryKeyValueObjectMap();
            Map<String, ColumnValueObject> colMap = new HashMap<>();
            for(Map.Entry<String,ColumnValueObject> entry : map.entrySet()){
                if(!(columnNameList.contains(entry.getKey()) || primaryMap.containsKey(entry.getKey()))){
                    colMap.put(entry.getKey(),entry.getValue());
                }
            }
            for(String colName : columnNameList) {
                while (true) {
                    boolean succeed = getAndSet(request, rowUpdateChange, colName, storeTableRow.getColumnValueObjectMap(),colMap);
                    if (!succeed) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return true;
    }

    public boolean getAndSet(GetRowRequest request, RowUpdateChange rowUpdateChange,String columnName,Map<String,ColumnValueObject> map,Map<String,ColumnValueObject> colMap){
        Row row = this.syncClient.getRow(request).getRow();
        if(columnName != null ) {
            ColumnValueObject columnValueObject = map.get(columnName);
            if(columnValueObject != null && columnValueObject.getType() == ColumnTypeObject.STRING){
                rowUpdateChange.put(columnName, ColumnValue.fromString(columnValueObject.getValue().toString()));
            }else if(columnValueObject != null && columnValueObject.getType() != ColumnTypeObject.STRING) {
                Condition cond = new Condition();
                if (row != null) {
                    Column column = row.getLatestColumn(columnName);
                    if (column != null) {
                        ColumnValue oldColumnValue = column.getValue();
                        SingleColumnValueCondition single = new SingleColumnValueCondition(columnName,
                                SingleColumnValueCondition.CompareOperator.EQUAL, oldColumnValue);
                        cond.setColumnCondition(single);
                        rowUpdateChange.put(columnName, newColumnValue(columnName, oldColumnValue, map));
                    } else {
                        SingleColumnValueCondition single = new SingleColumnValueCondition(columnName,
                                SingleColumnValueCondition.CompareOperator.EQUAL, oldNoneColumValue(columnName, map));
                        cond.setColumnCondition(single);
                        rowUpdateChange.put(columnName, newColumnValue(columnName, null, map));
                    }
                    rowUpdateChange.setCondition(cond);
                } else {
                    SingleColumnValueCondition single = new SingleColumnValueCondition(columnName,
                            SingleColumnValueCondition.CompareOperator.EQUAL, oldNoneColumValue(columnName, map));
                    cond.setColumnCondition(single);
                    rowUpdateChange.put(columnName, newColumnValue(columnName, null, map));
                    for (Map.Entry<String, ColumnValueObject> entry : colMap.entrySet()) {
                        rowUpdateChange.put(entry.getKey(), newColumnValue(entry.getKey(), null, map));
                    }
                }
            }
        }

        try {
            syncClient.updateRow(new UpdateRowRequest(rowUpdateChange));
        } catch (TableStoreException e) {
            if (e.getErrorCode().equals("OTSConditionCheckFail")) {
                return false;
            }
        }
        return true;
    }

    private ColumnValue oldNoneColumValue(String columnName,Map<String,ColumnValueObject> map){
        ColumnValueObject valueObject = map.get(columnName);
        if(valueObject.getType() == ColumnTypeObject.INTEGER ){
            return ColumnValue.fromLong(0);
        }else if(valueObject.getType() == ColumnTypeObject.DOUBLE){
            return ColumnValue.fromDouble(BigDecimal.ZERO.doubleValue());
        }else if(valueObject.getType() == ColumnTypeObject.STRING){
            return ColumnValue.fromString("");
        }
        return null;
    }

    private ColumnValue newColumnValue(String columnName,ColumnValue oldColumnValue,Map<String,ColumnValueObject> map){
        ColumnValueObject valueObject = map.get(columnName);
        if(valueObject.getType() == ColumnTypeObject.INTEGER ){
            return oldColumnValue == null ? ColumnValue.fromLong(Long.valueOf(String.valueOf(valueObject.getValue()))) :ColumnValue.fromLong(oldColumnValue.asLong() + Long.valueOf(String.valueOf(valueObject.getValue())));
        }else if(valueObject.getType() == ColumnTypeObject.DOUBLE){
            return oldColumnValue == null ? ColumnValue.fromDouble(new BigDecimal(String.valueOf(valueObject.getValue())).setScale(2,BigDecimal.ROUND_DOWN).doubleValue()) : ColumnValue.fromDouble(new BigDecimal(String.valueOf(oldColumnValue.asDouble())).add(new BigDecimal(String.valueOf(valueObject.getValue()))).setScale(2,BigDecimal.ROUND_DOWN).doubleValue());
        }else if(valueObject.getType() == ColumnTypeObject.STRING){
            return ColumnValue.fromString(String.valueOf(valueObject.getValue()));
        }
        return null;
    }

    public<T extends TableStoreBaseModel> List<T> batchGetRow(List<T> list) throws Exception {
        if (list == null || list.size() == 0) {
            return list;
        }

        String tableName;
        Map<Integer, Integer> sortIndex = new HashedMap();
        List<Integer> sortList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        List<Field> fieldList = new ArrayList<>();
        List<PrimaryKeyTypeObject> typeObjectList = new ArrayList<>();
        T object = list.get(0);
        try {
            //表名
            TableEntity tableEntity = object.getClass().getAnnotation(TableEntity.class);
            if(tableEntity != null && !ValueHelper.isNone(tableEntity.name())){
                tableName = tableEntity.name();
            }else {
                tableName = StringUtil.humpToLine2(object.getClass().getSimpleName());
            }

            Class objectClass = object.getClass();
            //考虑到继承
            Integer i = 0;
            while (objectClass != null && objectClass != Object.class) {
                Field[] fields = objectClass.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    TableKey annotationTableKey = field.getAnnotation(TableKey.class);
                    if (annotationTableKey != null) {
                        //主键 主键不可以为空
                        String keyName = StringUtils.isBlank(annotationTableKey.name()) ? field.getName() : annotationTableKey.name();
                        sortIndex.put(annotationTableKey.sort(), i);
                        sortList.add(annotationTableKey.sort());
                        i++;
                        nameList.add(keyName);
                        fieldList.add(field);
                        typeObjectList.add(annotationTableKey.type());
                    }
                }
                objectClass = objectClass.getSuperclass();
            }
        }catch (Exception e) {
            return null;
        }
        sortList.sort( (o1, o2)-> o1 > o2 ? 1 : o1.equals(o2) ? 0 : -1);

        MultiRowQueryCriteria multiRowQueryCriteria = new MultiRowQueryCriteria(tableName);
        for (T t : list) {
            PrimaryKeyColumn [] primaryKeys = new PrimaryKeyColumn[sortList.size()];
            int i = 0;
            for (Integer sort : sortList) {
                int offset = sortIndex.get(sort);
                String name = nameList.get(offset);
                Object value = fieldList.get(offset).get(t);
                PrimaryKeyColumn column;
                switch (typeObjectList.get(offset)) {
                    case BINARY:
                        column = new PrimaryKeyColumn(name, PrimaryKeyValue.fromBinary((byte[]) value));
                        break;
                    case STRING:
                        column = new PrimaryKeyColumn(name, PrimaryKeyValue.fromString((String) value));
                        break;
                    case INTEGER:
                        column = new PrimaryKeyColumn(name, PrimaryKeyValue.fromLong(Long.valueOf(value.toString())));
                        break;
                    default:
                        throw new Exception("不支持的KEY类型");
                }
                primaryKeys[i] = column;
                i++;
            }
            multiRowQueryCriteria.addRow(new PrimaryKey(primaryKeys));
        }
        multiRowQueryCriteria.setMaxVersions(1);

        BatchGetRowRequest request = new BatchGetRowRequest();
        request.addMultiRowQueryCriteria(multiRowQueryCriteria);
        BatchGetRowResponse response = syncClient.batchGetRow(request);
        List<T> result = new ArrayList<>();
        if(response.isAllSucceed()) {
            List<BatchGetRowResponse.RowResult> rows = response.getBatchGetRowResult(tableName);
            for (BatchGetRowResponse.RowResult rowResult : rows) {
                if(rowResult.isSucceed() && rowResult.getRow() != null) {
                    result.add(row2Data(rowResult.getRow(), (T) object.getClass().newInstance()));
                }
            }
        }

        return result;
    }

    public List<Row> getRange(StoreTableRow storeTableRow, Filter filter, Map<String, PrimaryKeyValue> startPkValue, Map<String,PrimaryKeyValue> endPkValue) {
        RangeIteratorParameter rangeIteratorParameter = new RangeIteratorParameter(storeTableRow.getTableName());

        // 设置起始主键
        PrimaryKeyBuilder primaryKeyBuilder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
        for(Map.Entry<String,PrimaryKeyValue> entry : startPkValue.entrySet()) {
            primaryKeyBuilder.addPrimaryKeyColumn(entry.getKey(), entry.getValue());
        }
        rangeIteratorParameter.setInclusiveStartPrimaryKey(primaryKeyBuilder.build());

        // 设置结束主键
        primaryKeyBuilder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
        for(Map.Entry<String,PrimaryKeyValue> entry : endPkValue.entrySet()) {
            primaryKeyBuilder.addPrimaryKeyColumn(entry.getKey(), entry.getValue());
        }
        rangeIteratorParameter.setExclusiveEndPrimaryKey(primaryKeyBuilder.build());

        rangeIteratorParameter.setMaxVersions(1);
        if(filter != null){
            rangeIteratorParameter.setFilter(filter);
        }
        Iterator<Row> iterator = syncClient.createRangeIterator(rangeIteratorParameter);
        List<Row> rows = new ArrayList<>();
        while (iterator.hasNext()) {
            Row row = iterator.next();
            rows.add(row);
        }
        return rows;
    }

    /**
     * 只支持对 int long 以及 double的操作
     * @param tableName
     * @param primaryKey
     * @param columnName
     * @param increment
     */
    public void atomicIncrement(String tableName, PrimaryKey primaryKey, String columnName, Object increment) {
        while (true) {
            boolean succeed = getAndSet(tableName, primaryKey, columnName, increment);
            if (!succeed) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
            } else {
                break;
            }
        }
    }

    public boolean getAndSet(String tableName, PrimaryKey primaryKey, String columnName, Object increment) {
        SingleRowQueryCriteria criteria = new SingleRowQueryCriteria(tableName);
        criteria.setPrimaryKey(primaryKey);
        criteria.setMaxVersions(1);

        GetRowRequest request = new GetRowRequest(criteria);
        Row row = this.syncClient.getRow(request).getRow();
        //检查递增的类型
        if(!(increment instanceof Integer || increment instanceof Double || increment instanceof Long)){
            throw new IllegalStateException("The type of column you want to do increment is not INTEGER or Double or Long.");
        }
        long initLongValue = 0;
        double initDoubleValue = 0.0;
        ColumnValue oldValue = null;
        if (row != null) {
            Column column = row.getLatestColumn(columnName);
            if (column != null && (increment instanceof Integer || increment instanceof Long) && column.getValue().getType() != ColumnType.INTEGER ) {
                throw new IllegalStateException("The type of column you want to do increment is not INTEGER.");
            }
            if(column != null && (increment instanceof Double) && column.getValue().getType() != ColumnType.DOUBLE){
                throw new IllegalStateException("The type of column you want to do increment is not Double.");
            }
            if (column != null && (increment instanceof Integer || increment instanceof Long) ) {
                initLongValue = column.getValue().asLong();
                oldValue = ColumnValue.fromLong(initLongValue);
            }
            if (column != null && (increment instanceof Double) ) {
                initDoubleValue = column.getValue().asDouble();
                oldValue = ColumnValue.fromDouble(initDoubleValue);
            }
        }
        ColumnValue newValue;
        if(increment instanceof Integer || increment instanceof Long) {
            newValue = ColumnValue.fromLong((initLongValue + ((long)increment)));
        }else {
            newValue = ColumnValue.fromDouble(new BigDecimal(String.valueOf(initDoubleValue)).add(new BigDecimal(String.valueOf(increment))).setScale(2,BigDecimal.ROUND_DOWN).doubleValue());
        }
        RowUpdateChange rowChange = new RowUpdateChange(tableName, primaryKey);
        rowChange.put(columnName, newValue);

        Condition cond = new Condition();
        SingleColumnValueCondition columnCondition = new SingleColumnValueCondition(columnName,
                SingleColumnValueCondition.CompareOperator.EQUAL, oldValue);
        columnCondition.setPassIfMissing(true);
        columnCondition.setLatestVersionsOnly(true);
        cond.setColumnCondition(columnCondition);
        rowChange.setCondition(cond);

        try {
            UpdateRowRequest updateRowRequest = new UpdateRowRequest(rowChange);
            this.syncClient.updateRow(updateRowRequest);
        } catch (TableStoreException e) {
            if (e.getErrorCode().equals("OTSConditionCheckFail")) {
                return false;
            }
        }
        return true;
    }

    public void setFieldValue(Map<String, Object> map, Object bean) throws Exception{
        Class<?> cls = bean.getClass();
        Method methods[] = cls.getDeclaredMethods();
        Field fields[] = cls.getDeclaredFields();

        for(Field field:fields){
            String fldtype = field.getType().getSimpleName();
            String fldSetName = field.getName();
            String setMethod = pareSetName(fldSetName);
            if(!checkMethod(methods, setMethod)){
                continue;
            }
            Object value = map.get(fldSetName);
            Method method = cls.getMethod(setMethod, field.getType());
            if(null != value){
                if("String".equals(fldtype)){
                    method.invoke(bean, String.valueOf(value));
                }else if("Double".equals(fldtype)){
                    method.invoke(bean, (Double)value);
                }else if("Integer".equals(fldtype)){
                    int val = Integer.valueOf(String.valueOf(value));
                    method.invoke(bean, val);
                }else if("Long".equalsIgnoreCase(fldtype)){
                    long val = Long.valueOf(String.valueOf(value));
                    method.invoke(bean, val);
                }else if("BigDecimal".equalsIgnoreCase(fldtype)){
                    BigDecimal val = new BigDecimal(String.valueOf(value));
                    method.invoke(bean, val);
                }
            }

        }
    }

    /**
     * 拼接某属性set 方法
     * @param fldname
     * @return
     */
    public String pareSetName(String fldname){
        if(null == fldname || "".equals(fldname)){
            return null;
        }
        String pro = "set"+fldname.substring(0,1).toUpperCase()+fldname.substring(1);
        return pro;
    }

    /**
     * 判断该方法是否存在
     * @param methods
     * @param met
     * @return
     */
    public boolean checkMethod(Method methods[],String met){
        if(null != methods ){
            for(Method method:methods){
                if(met.equals(method.getName())){
                    return true;
                }
            }
        }
        return false;
    }
}
