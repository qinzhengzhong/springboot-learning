package com.allan.springbootmybatis.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.allan.springbootmybatis.util.DateUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: BaseController
 * @Description:返回结果封装
 * @author qinzz
 * @date 2018年8月20日
 *
 */
public class BaseController {

	private static final int SUCCESS_CODE = 200;
	private static final int FAIL_CODE = 300;

	/**
	 * 返回失败结果
	 *
	 * @param failMessage
	 *            提示内容
	 * @return
	 */
	public String getFailResult(String failMessage) {
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		Map<String, Object> result = new HashMap<>();
		result.put("code", FAIL_CODE);
		result.put("message", failMessage);
		result.put("time", DateUtil.getDateNow("yyyy-MM-dd hh:mm:ss"));
		return JSON.toJSONString(result, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.PrettyFormat);
	}

	/**
	 * 返回成功结果
	 *
	 * @param object
	 *            结果集
	 * @return
	 */
	public String getSuccessResult(Object object, long total) {
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		Map<String, Object> result = new HashMap<>();
		result.put("total", total);
		result.put("code", SUCCESS_CODE);
		result.put("message", "成功");
		result.put("data", object);
		result.put("time", DateUtil.getDateNow("yyyy-MM-dd hh:mm:ss"));
		return JSON.toJSONString(result, SerializerFeature.PrettyFormat, // json格式化输出
				SerializerFeature.WriteNullStringAsEmpty, // 字符类型字段如果为null,输出为"",而非null
				SerializerFeature.DisableCircularReferenceDetect, // 消除对同一对象循环引用的问题(没有的可能出现”ref":".jsonArray[0]”})
				SerializerFeature.WriteDateUseDateFormat, // 时间格式化操作(默认为：yyyy-MM-dd HH:mm:ss)
				SerializerFeature.WriteNullListAsEmpty); // List字段如果为null,输出为[],而非null
	}

	/**
	 * 返回成功结果
	 *
	 * @param object
	 *            结果集(不带数据总量)
	 * @return
	 */
	public String getSuccessResult(Object object) {
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		Map<String, Object> result = new HashMap<>();
		result.put("code", "200");
		result.put("message", "成功");
		result.put("data", object);
		result.put("time", DateUtil.getDateNow("yyyy-MM-dd hh:mm:ss"));
		return JSON.toJSONString(result, SerializerFeature.PrettyFormat, // json格式化输出
				SerializerFeature.WriteNullStringAsEmpty, // 字符类型字段如果为null,输出为"",而非null
				SerializerFeature.DisableCircularReferenceDetect, // 消除对同一对象循环引用的问题(没有的可能出现”ref":".jsonArray[0]”})
				SerializerFeature.WriteDateUseDateFormat, // 时间格式化操作(默认为：yyyy-MM-dd HH:mm:ss)
				SerializerFeature.WriteNullListAsEmpty); // List字段如果为null,输出为[],而非null
	}

}
