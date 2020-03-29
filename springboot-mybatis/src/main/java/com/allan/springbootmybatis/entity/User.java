package com.allan.springbootmybatis.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 
* @ClassName: User
* @Description: TODO(这里用一句话描述这个类的作用)
* @author qinzz
* @date 2018年8月21日
*
 */
@Data
public class User implements Serializable{
    private Integer userId;

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

    private Integer diagnoseType;

    private Date diagnoseTime;

    private Date sourceTime;

    private Date cerateTime;

    private Integer isDelete;

    private Integer userState;

}