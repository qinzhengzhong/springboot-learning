package com.allan.springbootmybatis.mapper;

import com.allan.springbootmybatis.entity.HotelCity;

public interface HotelCityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HotelCity record);

    int insertSelective(HotelCity record);

    HotelCity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HotelCity record);

    int updateByPrimaryKey(HotelCity record);
}