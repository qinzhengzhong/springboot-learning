package com.allan.springbootmybatis.mapper;

import com.allan.springbootmybatis.entity.AirportInfo;

public interface AirportInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AirportInfo record);

    int insertSelective(AirportInfo record);

    AirportInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AirportInfo record);

    int updateByPrimaryKey(AirportInfo record);
}