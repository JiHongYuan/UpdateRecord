package com.example.update.record.aspectj;

import com.example.update.record.annotation.Update;
import com.example.update.record.domain.UpdateField;
import com.example.update.record.domain.UpdateRecord;
import com.example.update.record.enums.BusinessType;
import com.example.update.record.service.IUpdateRecordService;
import com.example.update.record.utils.ReflectUtils;
import com.example.update.record.utils.SpringUtils;
import com.example.update.record.utils.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jihongyuan
 * @date 2019/12/4 18:42
 */

@Aspect
@Component
public class UpdateRecordAspect {
    @Resource
    private IUpdateRecordService updateRecordService;

    private SqlSession sqlSession = SpringUtils.getBean(SqlSession.class);

    private final static String DEFAULT_PACKAGE_MODEL_NAME = "domain";
    private final static String DEFAULT_PACKAGE_MAPPER_NAME = "mapper";
    private final static String SPLIT = "|";
    private final static String BEFORE = "before";
    private final static String AFTER = "after";

    @Pointcut("@annotation(com.example.update.record.annotation.Update)")
    public void logPointCut() {
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        handle(joinPoint, BEFORE);
    }

    @After("logPointCut()")
    public void doAfterReturning(JoinPoint joinPoint) {
        handle(joinPoint, AFTER);
    }

    protected void handle(final JoinPoint joinPoint, final String point) {
        try {
            Update update = getAnnotationLog(joinPoint);
            Object obj = joinPoint.getArgs()[0];
            if (update == null) {
                return;
            }

            BusinessType businessType = update.businessType();
            switch (businessType) {
                case UPDATE:
                    if (BEFORE.equals(point)) {
                        handleUpdateRecord(update, obj);
                    }
                    break;
                case INSERT:
                    if (BEFORE.equals(point)) {
                        break;
                    } else if (AFTER.equals(point)) {
                        handleInsertRecord(update, obj);
                    }
                    break;
                case DELETE:
                    if (BEFORE.equals(point)) {
                        handleRemoveRecord(update, obj);
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void handleUpdateRecord(Update update, Object newObj) {
        try {
            Class clazz = newObj.getClass();

            //根据默认方法名称 查询 update之前数据
            Object oldObj = sqlSession.selectOne(getName(clazz, update.primaryKey()), ReflectUtils.invokeGetter(newObj, update.primaryKey()));
            Object id = ReflectUtils.invokeGetter(oldObj, update.id());

            List<UpdateField> newList = getClassField(clazz, newObj);
            List<UpdateField> oldList = getClassField(clazz, oldObj);

            // 删除field
            oldList.remove(0);
            UpdateField fieldUpdateType = newList.remove(0);
            for (int i = 0; i < newList.size(); i++) {
                UpdateField oldUpdateField = oldList.get(i);
                UpdateField newUpdateField = newList.get(i);

                UpdateRecord updateRecord = new UpdateRecord();
                updateRecord.setTypeId(String.valueOf(id));
                updateRecord.setType(update.updateRecordEnum().getType());

                Object oldProperty = oldUpdateField.getProperty();
                Object newProperty = newUpdateField.getProperty();

                if (oldProperty == null) {
                    if (newProperty == null || (StringUtils.isEmpty(newProperty))) {
                        break;
                    }
                } else if (oldProperty.getClass() == String.class && StringUtils.isEmpty(oldProperty.toString())) {
                    if (newProperty == null || (StringUtils.isEmpty(newProperty))) {
                        break;
                    }
                } else if (!oldUpdateField.getProperty().equals(newUpdateField.getProperty())) {
                    updateRecord.setOldText(oldProperty.toString());
                }

                updateRecordService.insertUpdateRecord(setUpdateRecord(updateRecord, newUpdateField, fieldUpdateType));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleInsertRecord(Update update, Object obj) {
        try {
            Class clazz = obj.getClass();

            Object id = ReflectUtils.invokeGetter(obj, update.id());
            List<UpdateField> list = getClassField(clazz, obj);

            UpdateField fieldUpdateType = list.remove(0);
            for (UpdateField updateField : list) {
                UpdateRecord updateRecord = new UpdateRecord();
                updateRecord.setTypeId(String.valueOf(id));
                updateRecord.setType(update.updateRecordEnum().getType());
                updateRecordService.insertUpdateRecord(setUpdateRecord(updateRecord, updateField, fieldUpdateType));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleRemoveRecord(Update update, Object obj) {
        try {
            Class clazz = obj.getClass();
            String primaryKey = update.primaryKey();
            obj = sqlSession.selectOne(getName(clazz, primaryKey), ReflectUtils.invokeGetter(obj, primaryKey));

            Object id = ReflectUtils.invokeGetter(obj, update.id());
            List<UpdateField> list = getClassField(clazz, obj);

            UpdateField fieldUpdateType = list.remove(0);
            for (UpdateField updateField : list) {
                UpdateRecord updateRecord = new UpdateRecord();
                updateRecord.setTypeId(String.valueOf(id));
                updateRecord.setType(update.updateRecordEnum().getType());
                updateRecordService.insertUpdateRecord(setUpdateRecord(updateRecord, updateField, fieldUpdateType));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param updateRecord    updateRecord
     * @param updateField     item
     * @param fieldUpdateType field
     */
    private UpdateRecord setUpdateRecord(UpdateRecord updateRecord, UpdateField updateField, UpdateField fieldUpdateType) {
        String fieldRemark = "";
        String field = "";
        if (fieldUpdateType != null && updateField.getUpdate().flag()) {
            field = fieldUpdateType.getField() + SPLIT;
            fieldRemark = fieldUpdateType.getProperty() + SPLIT;
        }

        Update update = updateField.getUpdate();
        updateRecord.setField(field + updateField.getField());
        updateRecord.setFieldRemark(fieldRemark + updateField.getUpdate().remark());
        updateRecord.setNewText(String.valueOf(updateField.getProperty()));
//        updateRecord.setAction();
        return updateRecord;
    }


    /**
     * 获取类成员的名称和注解
     */
    private List<UpdateField> getClassField(Class clazz, Object obj) {
        List<UpdateField> list = new ArrayList<>();
        // 占位
        list.add(0, null);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Update.class)) {
                Update update = field.getAnnotation(Update.class);

                Object value = typeFormatter(ReflectUtils.invokeGetter(obj, field.getName()));
                // 解析内容表达式
                value = StringUtils.isNotEmpty(update.readConverterExp()) ? reverseByExp(value, update.readConverterExp()) : value;

                if (StringUtils.isNotEmpty(update.remark())) {
                    UpdateField updateField = new UpdateField();
                    updateField.setUpdate(field.getAnnotation(Update.class));
                    updateField.setField(field.getName());
                    updateField.setProperty(value);
                    list.add(updateField);
                }

                // pojo 只允许一个field属性 代表当前数据的标识
                if ((update.field())) {
                    list.set(0, new UpdateField(field.getName(), update, value));
                }
            }
        }
        return list;
    }

    private Object typeFormatter(Object value) {
        //  针对BigDecimal类型处理
        if (value instanceof BigDecimal) {
            value = ((BigDecimal) value).stripTrailingZeros().toPlainString();
        }

        return value;
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private Update getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(Update.class);
        }
        return null;
    }

    /**
     * 根据当前class 类名 获取默认mapper名称以及默认ById方法
     */
    private String getName(Class clazz, String id) {
        String methodName = clazz.getName().replaceAll(DEFAULT_PACKAGE_MODEL_NAME, DEFAULT_PACKAGE_MAPPER_NAME);
        methodName = methodName.replaceAll(clazz.getSimpleName(), clazz.getSimpleName() + "Mapper");
        methodName += ".select" + StringUtils.capitalize(clazz.getSimpleName()) + "By" + StringUtils.capitalize(id);
        return methodName;
    }

    /**
     * 反向解析值
     */
    private static Object reverseByExp(Object propertyValue, String converterExp) {
        for (String item : converterExp.split("\\|")) {
            String[] itemArray = item.split("=");
            if (itemArray[0].equals(propertyValue)) {
                return itemArray[1];
            }
        }
        return propertyValue;
    }

}