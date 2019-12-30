package com.example.update.record.service.impl;

import com.example.update.record.annotation.Update;
import com.example.update.record.domain.Resource;
import com.example.update.record.enums.BusinessType;
import com.example.update.record.enums.UpdateRecordEnum;
import com.example.update.record.mapper.ResourceMapper;
import com.example.update.record.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiHongYuan
 * @Title: ResourceServiceImpl
 * @ProjectName TestSpring
 * @date 2019/3/2421:09
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    @Update(updateRecordEnum = UpdateRecordEnum.RESOURCE, id = "id", primaryKey = "id", businessType = BusinessType.DELETE)
    public int deleteByPrimaryKey(Resource record) {
        return resourceMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    @Update(updateRecordEnum = UpdateRecordEnum.RESOURCE, id = "id", primaryKey = "id", businessType = BusinessType.INSERT)
    public int insertSelective(Resource record) {
        return resourceMapper.insertSelective(record);
    }

    @Override
    @Update(updateRecordEnum = UpdateRecordEnum.RESOURCE, id = "id", primaryKey = "id", businessType = BusinessType.UPDATE)
    public int updateByPrimaryKeySelective(Resource record) {
        return resourceMapper.updateByPrimaryKeySelective(record);
    }
}
