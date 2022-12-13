package com.example.guyunwu.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class MD5Util {

    //参数1:输入明文密码
    public static String getMd5Code(String password) {
        StringBuilder sb = null;
        try {
            //创建加密对象
            //参数1: 算法名字
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            //进行加密  返回加密之后结果也是字节
            byte[] digest = messageDigest.digest(password.getBytes());
            sb = new StringBuilder();
            for (byte b : digest) {
                //位运算
                int len = b & 0xff;    //0  0x0 0x1 0x2 0x3 0x4 0x9  10  0xa   15  0xf  16 0x10 170x11
                if (len <= 15) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(len));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert sb != null;
        return sb.toString();
    }


    //生成随机的盐
    private static String getSalt(int n) {
        char[] code = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(code[new Random().nextInt(code.length)]);
        }
        return sb.toString();
    }
}
