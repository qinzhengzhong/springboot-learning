package com.allan.springbootcommon.util;

import java.util.UUID;

/**
 * 生成uuid
 * @author qinzz
 */
public class UUIDUtils {

    /**
     * 获取无符号的uuid
     * @author liegou
     * @return String
     * @throws
     */
    public static String getUuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    /**
     *  获取有符号的uuid
     * @author liegou
     * @return String
     * @throws
     */
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }
}
