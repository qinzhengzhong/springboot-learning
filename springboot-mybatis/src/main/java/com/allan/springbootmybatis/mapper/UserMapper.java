package com.allan.springbootmybatis.mapper;

import com.allan.springbootmybatis.base.BaseDao;
import com.allan.springbootmybatis.entity.User;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseDao<User, String> {
	
	List<User> queryUserList(Map<String, Object> params);
    
}