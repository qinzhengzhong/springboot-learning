package com.allan.springbootcommon.util;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 生成数据库密码密文工具
 *
 * @Auther: qinzhengzhong
 * @Date: 2020/3/21 11:57
 * @Description:
 */
public class EncryptConfigUtil {
    @Autowired
//    StringEncryptor stringEncryptor;//密码解码器自动注入


//    @org.junit.Test
    public void encryptPwd() {
        String possword="Qinzz@allan123";
//        String result = stringEncryptor.encrypt(possword);
//        System.out.println(result);
    }
}
