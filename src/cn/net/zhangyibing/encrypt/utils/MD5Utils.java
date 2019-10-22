package cn.net.zhangyibing.encrypt.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    public static String getMd5(String data) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data.getBytes());
        return BytesToHex.parseByteToHexStr(md5.digest());
    }

}
