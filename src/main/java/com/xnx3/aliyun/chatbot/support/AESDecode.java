package com.xnx3.aliyun.chatbot.support;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.crypto.spec.SecretKeySpec;

import javax.crypto.Mac;
 
public class AESDecode {
    /*
     * 计算MD5+BASE64
     */
    public static String MD5Base64(byte[] s) throws UnsupportedEncodingException {
        if (s == null){
            return null;
        }
        String encodeStr = "";
        //string 编码必须为utf-8
        MessageDigest mdTemp;
        try {
            mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(s);
            byte[] md5Bytes = mdTemp.digest();
            //BASE64Encoder b64Encoder = new BASE64Encoder();
            //encodeStr = b64Encoder.encode(md5Bytes);
            /* java 1.8以上版本支持 */
            Encoder encoder = Base64.getEncoder();
            encodeStr = encoder.encodeToString(md5Bytes);
            
        } catch (Exception e) {
            throw new Error("Failed to generate MD5 : " + e.getMessage());
        }
        return encodeStr;
    }
    /*
     * 计算MD5+BASE64
     */
    public static String MD5Base64(String s) throws Exception {
        if (s == null)
            return null;
        String encodeStr = "";
        //编码为utf-8
        byte[] utfBytes = s.getBytes("UTF-8");
        MessageDigest mdTemp;
        try {
            mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(utfBytes);
            byte[] md5Bytes = mdTemp.digest();
           
            Encoder encoder = Base64.getEncoder();
            encodeStr = encoder.encodeToString(md5Bytes);
            
//            BASE64Encoder b64Encoder = new BASE64Encoder();
//            encodeStr = b64Encoder.encode(md5Bytes);
        } catch (Exception e) {
            throw new Error("Failed to generate MD5 : " + e.getMessage());
        }
        return encodeStr;
    }
    /*
     * 计算 HMAC-SHA1
     */
    public static String HMACSha1(String data, String key) {
        String result;
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            
            Encoder encoder = Base64.getEncoder();
            result = encoder.encodeToString(rawHmac);
//            result = (new BASE64Encoder()).encode(rawHmac);
        } catch (Exception e) {
            throw new Error("Failed to generate HMAC : " + e.getMessage());
        }
        return result;
    }
}
