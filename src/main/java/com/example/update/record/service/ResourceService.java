package com.example.update.record.service;

import com.example.update.record.domain.Resource;

import java.util.List;

/**
 * @author jiHongYuan
 * @Title: resourceService
 * @ProjectName TestSpring
 * @date 2019/3/2421:02
 */
public interface ResourceService {

    int deleteByPrimaryKey(Resource record);

    int insertSelective(Resource record);

    int updateByPrimaryKeySelective(Resource record);

}
