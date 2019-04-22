package cn.easyliao.transfer.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * Created by dell on 2017/7/25.
 */
public class Base64 {

    // 加密
    public static String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    // 解密
    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //String[] token = result.split("&&&");
        //result = token[0];

        return result;
    }

    /*public static void main(String[] args) {

        System.out.println(getFromBase64("a2ZfMTAwM19kZjk0MzJhOS0wZWYzLTQ3NGUtYmE1OS1jYjViZmI5MDlhOTc="));

    }*/
}