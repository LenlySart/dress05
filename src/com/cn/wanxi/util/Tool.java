package com.cn.wanxi.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Tool {
    /**
     * 将传递过去的数组转换为字符串
     *
     * @param arr
     * @return
     */
    public static String arrayToString(String[] arr) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                s.append(arr[i]);
            } else {
                s.append(arr[i]).append(",");
            }
        }
        return s.toString();
    }

    /**
     * 密码加密md5
     */
    public static String encoderByMd5(String str){
        //确定计算方法
        MessageDigest md5 = null;
        String newstr = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return newstr;
    }

    /**
     * 将传过来的值转换成int类型
     * 如果为空设一个默认值
     * @param value
     * @return
     */
    public static Integer stringToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 将传过来的值进行判断并附上默认值
     * @param value
     * @return
     */
    public static String nullToString(String value) {
        return value == null ? "" : value;
    }
    /**
     * 将传过来的值进行判断并附上默认值
     * @param value
     * @return
     */
    public static BigDecimal nullBigDecimal(BigDecimal value) {
        return value;
    }

    /**
     * 把传过来的值转换成BigDecimal
     */
    public static BigDecimal bigDecimal(String value){

        try {
            return new BigDecimal(value);
        }catch (Exception e){
            return null;
        }
    }
}
