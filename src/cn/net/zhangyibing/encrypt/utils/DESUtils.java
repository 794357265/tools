package cn.net.zhangyibing.encrypt.utils;

import cn.net.zhangyibing.encrypt.config.EncryptConfig;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * DES加密解密
 * @author zhangyibing
 *
 */
public class DESUtils {

	/**
	 * 生成密钥
	 */
	public static byte[] initKey() throws Exception{
		return EncryptConfig.DES_KEY.getBytes();
	}

	/**
	 * 加密
	 * @param data 待加密的字符串
	 */
	public static String encryptDES(String data) throws Exception {
		return encryptDES(data, initKey());
	}

	/**
	 * 加密
	 * @param data 待加密的字符串
	 * @param key 密钥
	 */
	public static String encryptDES(String data, byte[] key) throws Exception{
		//获得密钥
		SecretKey secretKey = new SecretKeySpec(key, "DES");
		//Cipher完成加密
		Cipher cipher = Cipher.getInstance("DES");
		//初始化cipher
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		//加密
		byte[] encrypt = cipher.doFinal(data.getBytes());
		return BytesToHex.parseByteToHexStr(encrypt);
	}

	/**
	 * 解密
	 * @param data 待解密的字符串
	 */
	public static String decryptDES(String data) throws Exception {
		return decryptDES(data, initKey());
	}

	/**
	 * 解密
	 * @param data 待解密的字符串
	 * @param key 密钥
	 */
	public static String decryptDES(String data, byte[] key) throws Exception{
		//恢复密钥
		SecretKey secretKey = new SecretKeySpec(key, "DES");
		//Cipher完成解密
		Cipher cipher = Cipher.getInstance("DES");
		//初始化cipher
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		//解密
		byte[] plain = cipher.doFinal(BytesToHex.parseHexStrToByte(data));
		return new String(plain);
	}
}
