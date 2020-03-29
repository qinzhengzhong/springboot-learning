package com.allan.springbootcommon.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * package:    cc.s2m.web.followUpVisit.util
 * create by:  xj
 * description: 特殊字符处理工具类
 * create at:  2017/12/8
 */
public class SpecialCharUtils {
    private static String regEx =  "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）_——+|{}【】‘；：”“’。，、？]"; //特殊字符
    private static String regExUnderLine =  "/^\\w+$/"; //只能由英文、数字、下划线组成
    private static String REGEX_MOBILE = "^((16[0-9])|(19[0-9])|(17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"; //手机号
    private static String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"; //邮箱
    private static String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)|(^\\d{17}(\\d|X|x)$)";//身份证|身份证末尾是X的类型
    private static String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";//验证URL
    private static String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";//验证IP地址
    private static String reg = "[1-9]\\d{5}"; //邮编
    private static String regGH="(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|" +
            "(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)"; //固话

    /**
     * 除了strChar字符,清理掉所有其他特殊字符
     * @param str
     * @param strChar
     * @return
     * @throws PatternSyntaxException
     */
    public static String getSpecialChar(String str,String strChar) throws PatternSyntaxException {
        if(StringUtils.isNotEmpty(strChar)){
            regEx = regEx.replace(strChar,"");
        }
        // 清除掉所有特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 判断字符串是不是特殊字符
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static boolean checkSpecialChar(String str,String strChar) throws PatternSyntaxException {
        if(StringUtils.isNotEmpty(strChar)){
            regEx = regEx.replace(strChar,"");
        }
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 校验手机号
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 校验邮箱
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 校验身份证
     * @param idCard
     * @return
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * 校验url
     * @param url
     * @return
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }

    /**
     * 校验IP地址
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }

    /**
     * 校验邮编
     * @param postCode
     * @return
     */
    public static boolean isPostCode(String postCode){
        return Pattern.matches(reg, postCode);
    }

    /**
     * 区号+座机号码+分机号码
     * @param fixedPhone
     * @return
     */
    public static boolean isFixedPhone(String fixedPhone){
        return Pattern.matches(regGH, fixedPhone);
    }

    /**
     * 只能由英文、数字、下划线组成
     * @param coding
     * @return编码
     */
    public static boolean checkUnderLine(String coding){
            return Pattern.matches(regExUnderLine, coding);
    }

}

