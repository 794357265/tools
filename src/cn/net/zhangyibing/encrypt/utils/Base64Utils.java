package cn.net.zhangyibing.encrypt.utils;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

/**
 * BASE64算法实现加解密
 * @author zhangyibing
 *
 */
public class Base64Utils {

    /**
     * base64算法加密
     * @param data 需要加密的字符串
     */
    public static String base64Encrypt(String data){
        String result = new BASE64Encoder().encode(data.getBytes());
        return result;
    }

    /**
     * base64算法解密
     * @param data 需要加密的字符串
     */
    public static String base64Decrypt(String data) throws Exception{
        byte[] resultBytes = new BASE64Decoder().decodeBuffer(data);
        return new String(resultBytes);
    }
}
