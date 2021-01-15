package com.allan.springbootmybatis.mapper;

import com.allan.springbootmybatis.entity.ChinaArea;

import java.util.List;

public interface ChinaAreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChinaArea record);

    int insertSelective(ChinaArea record);

    ChinaArea selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChinaArea record);

    int updateByPrimaryKey(ChinaArea record);

    List<ChinaArea> selectByName(String city);
}