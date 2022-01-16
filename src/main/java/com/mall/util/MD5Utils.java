package com.mall.util;


import com.mall.common.Constant;
import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    public static String getMD5Str(String strValue)throws NoSuchAlgorithmException{
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return Base64.encodeBase64String(md5.digest((strValue+ Constant.SALT).getBytes()));
    }

    //测试密码加密
    public static void main(String[] args) {
        String md5 = null;
/*密码不正确 用来生成新密码*/
        try {
            md5 = getMD5Str("123456");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(md5);
    }
}
