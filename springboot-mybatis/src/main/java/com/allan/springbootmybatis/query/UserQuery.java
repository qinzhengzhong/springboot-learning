package com.allan.springbootmybatis.query;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 * @ClassName: UserQuery
 * @Description: 列表查询
 * @author qinzz
 * @date 2018年8月21日
 *
 */
@Data
public class UserQuery implements Serializable {

	private String userName;

	private String password;

	private String phone;

	private String gender;

	private String national;

	private String address;

	private Date birthday;

	private Integer age;

	private String icd;

	private String icdName;

	private Date diagnoseTime;

	private Date sourceTime;

	private Integer isDelete;

	private Integer userState;
}
