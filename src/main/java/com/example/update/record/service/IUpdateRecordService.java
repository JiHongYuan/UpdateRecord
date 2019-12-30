package com.example.update.record.service;


import com.example.update.record.domain.UpdateRecord;

import java.util.List;

/**
 * UpdateRecordService接口
 *
 * @author szqingwa
 * @date 2019-12-05
 */
public interface IUpdateRecordService {
    /**
     * 查询UpdateRecord
     *
     * @param id updateRecord
     * @return updateRecord
     */
    public UpdateRecord selectUpdateRecordById(Long id);

    /**
     * 查询UpdateRecord列表
     *
     * @param updateRecord updateRecord
     * @return updateRecord
     */
    public List<UpdateRecord> selectUpdateRecordList(UpdateRecord updateRecord);

    /**
     * 新增UpdateRecord
     *
     * @param updateRecord updateRecord
     * @return 结果
     */
    public int insertUpdateRecord(UpdateRecord updateRecord);

    /**
     * 修改UpdateRecord
     *
     * @param updateRecord updateRecord
     * @return 结果
     */
    public int updateUpdateRecord(UpdateRecord updateRecord);

    /**
     * 删除UpdateRecord信息
     *
     * @param id updateRecord
     * @return 结果
     */
    public int deleteUpdateRecordById(Long id);
}
