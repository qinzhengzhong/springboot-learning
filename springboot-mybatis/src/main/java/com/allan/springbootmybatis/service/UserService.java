package com.allan.springbootmybatis.service;

import com.allan.springbootmybatis.base.BaseService;
import com.allan.springbootmybatis.entity.User;
import com.allan.springbootmybatis.query.UserQuery;

import java.util.List;

public interface UserService extends BaseService<User,String> {
	
	/**
	 * 获取用户列表
	* @Title: queryUserList  
	* @Description: TODO(这里用一句话描述这个方法的作用)  	* @param @param user
	* @param @return    参数  
	* @return List<User>    返回类型  
	* @throws
	 */
	List<User> queryUserList(UserQuery user);
	/**
	 * 
	* @Title: getUserById  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param userId
	* @param @return    参数  
	* @return User    返回类型  
	* @throws
	 */
	User getUserById(Integer userId);
}
