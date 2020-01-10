package com.example.myapplication;

import com.blankj.utilcode.util.EncryptUtils;

public class Test {
    public static void main(String[] args) {
        String pwd="123456";
        System.out.println("加密前的:"+pwd);
        System.out.println("加密后的:"+ EncryptUtils.encryptMD5ToString(pwd));
    }
}
