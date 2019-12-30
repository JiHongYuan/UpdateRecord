package com.example.update.record.mapper;


import com.example.update.record.domain.UpdateRecord;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper接口
 *
 * @author szqingwa
 * @date 2019-12-05
 */
@Component
public interface UpdateRecordMapper {
    /**
     * 查询
     *
     * @param id ID
     * @return
     */
    public UpdateRecord selectUpdateRecordById(Long id);

    /**
     * 查询列表
     *
     * @param updateRecord
     * @return 集合
     */
    public List<UpdateRecord> selectUpdateRecordList(UpdateRecord updateRecord);

    /**
     * 新增
     *
     * @param updateRecord
     * @return 结果
     */
    public int insertUpdateRecord(UpdateRecord updateRecord);

    /**
     * 修改
     *
     * @param updateRecord
     * @return 结果
     */
    public int updateUpdateRecord(UpdateRecord updateRecord);

    /**
     * 删除
     *
     * @param id ID
     * @return 结果
     */
    public int deleteUpdateRecordById(Long id);

}
