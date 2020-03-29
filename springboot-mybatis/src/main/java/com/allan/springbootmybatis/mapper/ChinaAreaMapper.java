package com.allan.springbootmybatis.mapper;

import com.allan.springbootmybatis.entity.ChinaArea;

public interface ChinaAreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChinaArea record);

    int insertSelective(ChinaArea record);

    ChinaArea selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChinaArea record);

    int updateByPrimaryKey(ChinaArea record);
}