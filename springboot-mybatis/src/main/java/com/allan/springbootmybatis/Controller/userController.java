package com.allan.springbootmybatis.Controller;


import com.allan.springbootmybatis.base.BaseController;
import com.allan.springbootmybatis.entity.User;
import com.allan.springbootmybatis.query.UserQuery;
import com.allan.springbootmybatis.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 
 * @ClassName: testController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author qinzz
 * @date 2018年8月20日
 *
 */
@RestController
@RequestMapping("/user")
public class userController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(userController.class);
	@Autowired
	UserService userServer;

	/**
	 * 
	 * @Title: test
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 * @author qinzz  
	 * @date 2018年8月22日 
	 * @return
	 */
	@GetMapping("/hello")
	public String test() {
		String msg = "hello,this is a springboot demo";
		logger.info(getSuccessResult(msg));
		return getSuccessResult(msg);
	}
	
	/**
	 * 
	 * @Title: queryUserList  
	 * @Description: 列表查询  
	 * @param @param pageNum 当前页
	 * @param @param pageSize 每页数量
	 * @param @param user 查询条件
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	@GetMapping("/queryUserList")
	public String queryUserList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
								@RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize, UserQuery user) {
		List<User> userList=Lists.newArrayList();
		PageInfo<User> pageInfo = null;
		try {
			PageHelper.startPage(pageNum,pageSize);
			user.setAge(24);
			userList = userServer.queryUserList(user);
			pageInfo = new PageInfo<User>(userList);
		} catch (Exception e) {
			e.printStackTrace();
			return getFailResult(e.getMessage());
		}
		return getSuccessResult(pageInfo);
	}

	/**
	 * 
	* @Title: getUserInfo  
	* @Description: 获取用户详情 
	* @param @param userId
	* @param @return    参数  
	* @return String    返回类型  
	* @throws
	 */
	@GetMapping("/userById")
	public String getUserInfo(Integer userId) {
		User user = null;
		try {
			if (userId == null) {
				return getFailResult("用户ID不能为空");
			}
			user = userServer.getUserById(userId);
			Preconditions.checkNotNull(user, "用户不存在");
		} catch (Exception e) {
			e.printStackTrace();
			return getFailResult(e.getMessage());
		}

		return getSuccessResult(user);
	}

}
