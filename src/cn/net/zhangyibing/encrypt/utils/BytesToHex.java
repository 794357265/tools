package cn.net.zhangyibing.encrypt.utils;

/**
 * 字节码转化十六进制
 * @author zhangyibing
 *
 */
public class BytesToHex {

//	public static String bytesToHex(byte[] resultBytes){
//		StringBuilder builder = new StringBuilder();
//		for(int i = 0; i < resultBytes.length; i++){
//			if(Integer.toHexString(0xFF & resultBytes[i]).length() == 1){
//				builder.append("0").append(Integer.toHexString(0xFF & resultBytes[i]));
//			}else{
//				builder.append(Integer.toHexString(0xFF & resultBytes[i]));
//			}
//		}
//		return builder.toString();
//	}

	//流转字符串 辅助函数
	public static String parseByteToHexStr(byte[] buf) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	//字符串转流 辅助函数
	public static byte[] parseHexStrToByte(String hexStr) {
		if (hexStr.length() < 1) {
			return null;
		}
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}
