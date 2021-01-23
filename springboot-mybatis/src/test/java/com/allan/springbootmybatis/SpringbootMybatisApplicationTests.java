package com.allan.springbootmybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SpringbootMybatisApplicationTests {

//    @Test
    public void contextLoads() {
    }

    public static void main(String[] args) {
        String a = "商南";

        System.out.println(a.substring(0,a.length()-1));
    }

}
