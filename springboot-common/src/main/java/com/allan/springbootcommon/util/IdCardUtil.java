package com.allan.springbootcommon.util;

import java.util.Calendar;

/**
 * @author qinzz
 * @version V1.0
 * @Project: project
 * @Package: com.allan.Util
 * @Description: TODO
 * @Creation Date:2018-06-01 10:08
 */
public class IdCardUtil {
    /**
     * 中国公民身份证号码最小长度。
     */
    public final static int CHINA_ID_MIN_LENGTH = 15;

    /**
     * 中国公民身份证号码最大长度。
     */
    public final static int CHINA_ID_MAX_LENGTH = 18;


    /**
     * 根据身份编号获取年龄
     *
     * @param idCard 身份编号
     * @return 年龄
     */
    public static int getAgeByIdCard(String idCard) {
        int iAge = 0;
        Calendar cal = Calendar.getInstance();
        String year = idCard.substring(6, 10);
        int iCurrYear = cal.get(Calendar.YEAR);
        iAge = iCurrYear - Integer.valueOf(year);
        return iAge;
    }

    /**
     * 根据身份编号获取生日
     *
     * @param idCard 身份编号
     * @return 生日(yyyyMMdd)
     */
    public static String getBirthByIdCard(String idCard) {
        return idCard.substring(6, 14);
    }

    /**
     * 根据身份编号获取生日年
     *
     * @param idCard 身份编号
     * @return 生日(yyyy)
     */
    public static Short getYearByIdCard(String idCard) {
        return Short.valueOf(idCard.substring(6, 10));
    }

    /**
     * 根据身份编号获取生日月
     *
     * @param idCard 身份编号
     * @return 生日(MM)
     */
    public static Short getMonthByIdCard(String idCard) {
        return Short.valueOf(idCard.substring(10, 12));
    }

    /**
     * 根据身份编号获取生日天
     *
     * @param idCard 身份编号
     * @return 生日(dd)
     */
    public static Short getDateByIdCard(String idCard) {
        return Short.valueOf(idCard.substring(12, 14));
    }

    /**
     * 根据身份编号获取性别
     *
     * @param idCard 身份编号
     * @return 性别(M - 男 ， F - 女 ， N - 未知)
     */
    public static String getGenderByIdCard(String idCard) {
        String sGender = "未知";
        if (idCard.length() == CHINA_ID_MIN_LENGTH) {
            idCard = IDcardValidatorUtil.convertIdcarBy15bit(idCard);//将15位的身份证转成18位身份证
        }
        String sCardNum = idCard.substring(16, 17);
        if (Integer.parseInt(sCardNum) % 2 != 0) {
            sGender = "男";//1
        } else {
            sGender = "女";//2
        }
        return sGender;
    }

    /**
     * 身份证号隐藏中间几位
     *
     * @param idCard 身份证号码
     * @param start  开始位置
     * @param end    结束位置
     * @return 隐藏后的身份证号
     */
    public static String idCardFormat(String idCard, int start, int end) {
        StringBuilder sb = new StringBuilder(idCard);
        if (idCard.length() == CHINA_ID_MAX_LENGTH) {
            sb.replace(start, end, "*********");
        }
        if (idCard.length() == CHINA_ID_MIN_LENGTH) {
            sb.replace(start, end, "*********");
        }
        return sb.toString();
    }

    public static void main(String[] a) {
        String idcard = "";
        /*String sex= getGenderByIdCard(idcard);
        System.out.println("性别:" + sex);
        int age= getAgeByIdCard(idcard);
        System.out.println("年龄:" + age);
        Short nian=getYearByIdCard(idcard);
        Short yue=getMonthByIdCard(idcard);
        Short ri=getDateByIdCard(idcard);
        System.out.print(nian+"年"+yue+"月"+ri+"日");
        String sr=getBirthByIdCard(idcard);
        System.out.println(",生日:" + sr);*/
        System.out.println(idCardFormat(idcard, 6, 15));
        System.out.println(getGenderByIdCard(idcard));
    }

}