package com.example.update.record.mapper;

import com.example.update.record.domain.Resource;

import java.util.List;

public interface ResourceMapper {

    int deleteByPrimaryKey(Long id);

    int insertSelective(Resource record);

    int updateByPrimaryKeySelective(Resource record);

    Resource selectResourceById(Long id);
}