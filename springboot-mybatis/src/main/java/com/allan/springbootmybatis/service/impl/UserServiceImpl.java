package com.allan.springbootmybatis.service.impl;

import com.allan.springbootmybatis.base.BaseDao;
import com.allan.springbootmybatis.base.BaseServiceImpl;
import com.allan.springbootmybatis.entity.User;
import com.allan.springbootmybatis.mapper.UserMapper;
import com.allan.springbootmybatis.query.UserQuery;
import com.allan.springbootmybatis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends BaseServiceImpl<User,String> implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private static final String CACHE_KEY_USER = "QueryUserCache";


	@Autowired
	UserMapper userMapper;

	@Override
	public BaseDao<User, String> getDao() {
		return userMapper;
	}

	@Override
	@Cacheable(value=CACHE_KEY_USER,key="'userId:'+#userId")//开启Redis支持时，可在Redis客户端看到该缓存
	public User getUserById(Integer userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public List<User> queryUserList(UserQuery user) {
		Map<String, Object> params=new HashMap();
		params.put("condition", user);
		return userMapper.queryUserList(params);
	}


}
