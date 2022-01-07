package com.example.project1;

import com.example.project1.util.Md5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Project1ApplicationTests {

    @Test
    void contextLoads() {
        String s = Md5Util.MD5("1993wang"+"admin");
        String b = "B9E2B4BAE9302D14FE5F02C3CCDD11E2";
        System.out.println(s);
    }
    @Test
    void md5s() {
        String s = Md5Util.MD5("1993wang"+"admin");

        String b = "B9E2B4BAE9302D14FE5F02C3CCDD11E2";
        System.out.println(b.equals(s));
    }

}
