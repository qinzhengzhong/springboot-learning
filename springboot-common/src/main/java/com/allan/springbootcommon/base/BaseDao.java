package com.allan.springbootcommon.base;

import java.io.Serializable;

public interface BaseDao<T, PK extends Serializable> {
	int deleteByPrimaryKey(Integer userId);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
