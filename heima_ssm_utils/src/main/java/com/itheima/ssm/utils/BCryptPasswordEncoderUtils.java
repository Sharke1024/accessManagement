package com.itheima.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码加密类
 */
public class BCryptPasswordEncoderUtils {


    private  static BCryptPasswordEncoder bCryptPasswordEncoder  = new BCryptPasswordEncoder();

    public static String passwordEncoder(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String password = "bob";
        String pwd = passwordEncoder(password);
        //$2a$10$MmrC8y4FNmuah/aJ67Nuku5mIg0evuB9sN5es/0LFJvcSu1AubHca
        //$2a$10$F0cVFswXbgclmb8itXPxsOd8.lSqSKP9Sl.oOd8mX.kBp0QucBHfi
        System.out.println(pwd+"   " +pwd.length());
    }

}
