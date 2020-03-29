package com.allan.springbootmybatis.base;


import java.io.Serializable;

public abstract  class BaseServiceImpl<T,PK extends Serializable> implements BaseService<T,PK> {
	
	public abstract BaseDao<T, PK> getDao();

	@Override
	public int deleteByPrimaryKey(Integer userId) {
		// TODO Auto-generated method stub
		return getDao().deleteByPrimaryKey(userId);
	}

	@Override
	public int insert(T record) {
		// TODO Auto-generated method stub
		return getDao().insert(record);
	}

	@Override
	public int insertSelective(T record) {
		// TODO Auto-generated method stub
		return getDao().insertSelective(record);
	}

	@Override
	public T selectByPrimaryKey(Integer userId) {
		// TODO Auto-generated method stub
		return getDao().selectByPrimaryKey(userId);
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		// TODO Auto-generated method stub
		return getDao().updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(T record) {
		// TODO Auto-generated method stub
		return getDao().updateByPrimaryKey(record);
	}

}
