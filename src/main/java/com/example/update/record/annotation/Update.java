package com.example.update.record.annotation;


import com.example.update.record.enums.BusinessType;
import com.example.update.record.enums.UpdateRecordEnum;

import java.lang.annotation.*;

/**
 * @author jihongyuan
 * @date 2019/12/4 18:44
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Update {

    /**
     * 类型
     */
    UpdateRecordEnum updateRecordEnum() default UpdateRecordEnum.OTHER;

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.UPDATE;

    /**
     * 插入的ID
     */
    String id() default "";

    /**
     * update查询的主键
     */
    String primaryKey() default "id";

    /**
     * 说明内容
     */
    String remark() default "";

    /**
     * 读取内容转表达式 (如: 0=男,1=女,2=未知)
     */
    String readConverterExp() default "";

    //****************************************************************

    /**
     * 标识字段
     */
    boolean field() default false;

    /**
     * 默认拼接标识字段
     */
    boolean flag() default true;
}
