package cn.com.youplus.common.model.tablestore;

import cn.com.youplus.common.tablestore.TableStoreServiceImpl;
import cn.com.youplus.common.util.StringUtil;
import cn.com.youplus.common.util.ValueHelper;
import cn.com.youplus.common.validation.annotation.Check;
import com.hannea.annotation.TableColumn;
import com.hannea.annotation.TableEntity;
import com.hannea.annotation.TableKey;
import com.hannea.tablestore.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 该类主要作用是用来规范TableStore的Model类，没有实质的功能
 * Created by 严汉羽 on 2018/1/4.
 */
public class TableStoreBaseModel {
    private static final Logger logger = LoggerFactory.getLogger(TableStoreBaseModel.class);

    /**
     * 根据类名得到storeTable
     * @param clazz
     * @return 组装得到的StoreTable
     */
    public static StoreTable asmublyStoreTable(Class<? extends TableStoreBaseModel> clazz) {
        StoreTable storeTable = new StoreTable();
        TableEntity tableEntity =  clazz.getAnnotation(TableEntity.class);
        if(tableEntity != null && !ValueHelper.isNone(tableEntity.name())){
            storeTable.setTableName(tableEntity.name());
        }else {
            storeTable.setTableName(StringUtil.humpToLine2(clazz.getName()));
        }

        List<PrimaryKeySchemaObject> primaryKeySchemaObjectList = new ArrayList<>();
        Class objectClass = clazz;
        //考虑到继承
        while (objectClass != null && objectClass != Object.class) {
            Field[] field = objectClass.getDeclaredFields();// 获取方法参数（实体）的field
            for (int j = 0; j < field.length; j++) {
                TableKey tableKey = field[j].getAnnotation(TableKey.class);// 获取方法参数（实体）的field上的注解Check
                if (tableKey != null) {
                    if (ValueHelper.isNone(tableKey.name())) {
                        if (tableKey.autoInc()) {
                            primaryKeySchemaObjectList.add(new PrimaryKeySchemaObject(field[j].getName(), tableKey.type(), tableKey.sort(), PrimaryKeyOptionObject.AUTO_INCREMENT));
                        } else {
                            primaryKeySchemaObjectList.add(new PrimaryKeySchemaObject(field[j].getName(), tableKey.type(), tableKey.sort()));
                        }
                    } else {
                        if (tableKey.autoInc()) {
                            primaryKeySchemaObjectList.add(new PrimaryKeySchemaObject(tableKey.name(), tableKey.type(), tableKey.sort(), PrimaryKeyOptionObject.AUTO_INCREMENT));
                        } else {
                            primaryKeySchemaObjectList.add(new PrimaryKeySchemaObject(tableKey.name(), tableKey.type(), tableKey.sort()));
                        }
                    }
                }
            }
            objectClass = objectClass.getSuperclass();
        }

        if (primaryKeySchemaObjectList.size() == 0) {
            throw new RuntimeException("该类不包含键注解，请至少注解一个Key");
        }

        primaryKeySchemaObjectList.sort(new Comparator<PrimaryKeySchemaObject>() {
            @Override
            public int compare(PrimaryKeySchemaObject o1, PrimaryKeySchemaObject o2) {
                if (o1.getSort() > o2.getSort()) {
                    return 1;
                }

                if (o1.getSort() < o2.getSort()) {
                    return 1;
                }
                throw new RuntimeException("不能包含两个Key的Sort同时为:" + o1.getSort());
            }
        });

        storeTable.setPrimaryKeySchemaObjectList(primaryKeySchemaObjectList);
        return storeTable;
    }

    /**
     * 根据类名得到storeTable
     * @param object
     * @return
     */
    public static StoreTableRow asmublyStoreTableTableRow(TableStoreBaseModel object) {
        /**
         * 主键
         */
        Map<String, PrimaryKeyValueObject> primaryKeyValue = new HashMap<>();
        /**
         * 属性列
         */
        Map<String, ColumnValueObject> columnValueObjectMap = new HashMap<>();

        String tableEntityName = null;
        try {
            //表名
            TableEntity tableEntity = object.getClass().getAnnotation(TableEntity.class);
            if(tableEntity != null && !ValueHelper.isNone(tableEntity.name())){
                tableEntityName = tableEntity.name();
            }else {
                tableEntityName = StringUtil.humpToLine2(object.getClass().getName());
            }

            Class objectClass = object.getClass();
            //考虑到继承
            while (objectClass != null && objectClass != Object.class) {
                Field[] fields = objectClass.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    TableKey annotationTableKey = field.getAnnotation(TableKey.class);
                    if (annotationTableKey != null) {
                        //主键 主键不可以为空
                        String keyName = StringUtils.isBlank(annotationTableKey.name()) ? field.getName() : annotationTableKey.name();
                        if (field.get(object) == null) {
                            return null;
                        }
                        primaryKeyValue.put(keyName, new PrimaryKeyValueObject(field.get(object), annotationTableKey.type(), annotationTableKey.sort()));
                    }
                    TableColumn annotationTableColumn = field.getAnnotation(TableColumn.class);
                    if (annotationTableColumn != null) {
                        //属性列
                        String keyName = StringUtils.isBlank(annotationTableColumn.name()) ? field.getName() : annotationTableColumn.name();
                        columnValueObjectMap.put(keyName, new ColumnValueObject(field.get(object), annotationTableColumn.type()));
                    }
                }
                objectClass = objectClass.getSuperclass();
            }
        }catch (IllegalAccessException e) {
            logger.error("[initTable] error :"+ e);
            return null;
        }
        if(primaryKeyValue.isEmpty()){
            logger.error("[initTable] primary key none");
            return null;
        }
        StoreTableRow row = new StoreTableRow();
        row.setTableName(tableEntityName);
        row.setColumnValueObjectMap(columnValueObjectMap);
        row.setPrimaryKeyValueObjectMap(primaryKeyValue);
        return row;
    }

    /**
     * 根据类名得到storeTable,只装载键，用于查询
     * @param object
     * @return
     */
    public static StoreTableRow asmublyStoreTableTableRow4Get(TableStoreBaseModel object) {
        /**
         * 主键
         */
        Map<String, PrimaryKeyValueObject> primaryKeyValue = new HashMap<>();
        /**
         * 属性列
         */
        Map<String, ColumnValueObject> columnValueObjectMap = new HashMap<>();

        String tableEntityName = null;
        try {
            //表名
            TableEntity tableEntity = object.getClass().getAnnotation(TableEntity.class);
            if(tableEntity != null && !ValueHelper.isNone(tableEntity.name())){
                tableEntityName = tableEntity.name();
            }else {
                tableEntityName = StringUtil.humpToLine2(object.getClass().getName());
            }

            Class objectClass = object.getClass();
            //考虑到继承
            while (objectClass != null && objectClass != Object.class) {
                Field[] fields = objectClass.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    TableKey annotationTableKey = field.getAnnotation(TableKey.class);
                    if (annotationTableKey != null) {
                        //主键 主键不可以为空
                        String keyName = StringUtils.isBlank(annotationTableKey.name()) ? field.getName() : annotationTableKey.name();
                        if (field.get(object) == null) {
                            return null;
                        }
                        primaryKeyValue.put(keyName, new PrimaryKeyValueObject(field.get(object), annotationTableKey.type(), annotationTableKey.sort()));
                    }
                }
                objectClass = objectClass.getSuperclass();
            }
        }catch (IllegalAccessException e) {
            return null;
        }
        if(primaryKeyValue.isEmpty()){
            return null;
        }
        StoreTableRow row = new StoreTableRow();
        row.setTableName(tableEntityName);
        row.setPrimaryKeyValueObjectMap(primaryKeyValue);
        return row;
    }
}
