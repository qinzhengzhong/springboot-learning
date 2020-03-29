package com.allan.springbootmybatis.base;

import java.io.Serializable;

/**
 * 
    * @ClassName: BaseService  
    * @Description: 基础工具类
    * @author qinzz  
    * @date 2018年8月20日  
    *
 */
public interface BaseService<T,PK extends Serializable> {
	
	public abstract BaseDao<T, PK> getDao();
	
	int deleteByPrimaryKey(Integer userId);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

}
