package cn.net.zhangyibing.encrypt.utils;

import cn.net.zhangyibing.encrypt.config.EncryptConfig;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密解密
 * @author zhangyibing
 *
 */
public class AESUtils {

	/**
	 * 生成密钥
	 */
	public static byte[] initKey() throws Exception{
		return EncryptConfig.AES_KEY.getBytes();
	}

	/**
	 *加密
	 */
	public static String encryptAES(String data) throws Exception {
		return encryptAES(data, initKey());
	}
	
	/**
	 * 加密
	 */
	public static String encryptAES(String data, byte[] key) throws Exception{
		//恢复密钥
		SecretKey secretKey = new SecretKeySpec(key, "AES");
		//Cipher完成加密
		Cipher cipher = Cipher.getInstance("AES");
		//根据密钥对cipher进行初始化
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		//加密
		byte[] encrypt = cipher.doFinal(data.getBytes());
		return BytesToHex.parseByteToHexStr(encrypt);
	}

	/**
	 * 解密
	 */
	public static String decryptAES(String data) throws Exception {
		return decryptAES(data, initKey());
	}
	/**
	 * 解密
	 */
	public static String decryptAES(String data, byte[] key) throws Exception{
		//恢复密钥生成器
		SecretKey secretKey = new SecretKeySpec(key, "AES");
		//Cipher完成解密
		Cipher cipher = Cipher.getInstance("AES");
		//根据密钥对cipher进行初始化
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] plain = cipher.doFinal(BytesToHex.parseHexStrToByte(data));
		return new String(plain);
	}
}
