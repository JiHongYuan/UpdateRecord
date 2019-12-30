package com.example.update.record.domain;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * biz_update_record
 *
 * @author szqingwa
 * @date 2019-12-05
 */
@Data
@Slf4j
public class UpdateRecord {
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 标识
     */
    private String type;

    /**
     * 标识ID
     */
    private String typeId;

    /**
     * 字段
     */
    private String field;

    /**
     * 字段描述
     */
    private String fieldRemark;

    /**
     * 旧值
     */
    private String oldText;

    /**
     * 新值
     */
    private String newText;

    /**
     * 描述
     */
    private String remark;

    private String action;

    private Date createTime;

}
