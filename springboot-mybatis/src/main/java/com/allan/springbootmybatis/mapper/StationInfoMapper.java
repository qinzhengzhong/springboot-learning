package com.allan.springbootmybatis.mapper;


import com.allan.springbootmybatis.entity.StationInfo;

public interface StationInfoMapper {

    int deleteByPrimaryKey(String id);


    int insert(StationInfo record);


    int insertSelective(StationInfo record);


    StationInfo selectByPrimaryKey(String id);


    int updateByPrimaryKeySelective(StationInfo record);

    int updateByPrimaryKey(StationInfo record);
}