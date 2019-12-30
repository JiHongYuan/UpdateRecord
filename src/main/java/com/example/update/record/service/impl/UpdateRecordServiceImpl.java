package com.example.update.record.service.impl;

import com.example.update.record.domain.UpdateRecord;
import com.example.update.record.mapper.UpdateRecordMapper;
import com.example.update.record.service.IUpdateRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * updateRecordService业务层处理
 *
 * @author szqingwa
 * @date 2019-12-05
 */
@Service
public class UpdateRecordServiceImpl implements IUpdateRecordService {
    @Autowired
    private UpdateRecordMapper updateRecordMapper;

    /**
     * 查询updateRecord
     *
     * @param id updateRecordID
     * @return updateRecord
     */
    @Override
    public UpdateRecord selectUpdateRecordById(Long id) {
        return updateRecordMapper.selectUpdateRecordById(id);
    }

    /**
     * 查询updateRecord列表
     *
     * @param updateRecord updateRecord
     * @return updateRecord
     */
    @Override
    public List<UpdateRecord> selectUpdateRecordList(UpdateRecord updateRecord) {
        return updateRecordMapper.selectUpdateRecordList(updateRecord);
    }

    /**
     * 新增updateRecord
     *
     * @param updateRecord updateRecord
     * @return 结果
     */
    @Override
    public int insertUpdateRecord(UpdateRecord updateRecord) {
        return updateRecordMapper.insertUpdateRecord(updateRecord);
    }

    /**
     * 修改updateRecord
     *
     * @param updateRecord updateRecord
     * @return 结果
     */
    @Override
    public int updateUpdateRecord(UpdateRecord updateRecord) {
        return updateRecordMapper.updateUpdateRecord(updateRecord);
    }

    /**
     * 删除updateRecord信息
     *
     * @param id updateRecordID
     * @return 结果
     */
    @Override
    public int deleteUpdateRecordById(Long id) {
        return updateRecordMapper.deleteUpdateRecordById(id);
    }
}
