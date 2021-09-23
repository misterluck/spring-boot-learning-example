//package gov.zwfw.iam.utils;
//
//import java.io.UnsupportedEncodingException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
//import org.bouncycastle.util.encoders.Hex;
//
//import gov.zwfw.iam.base.util.SM3Digest;
//
//
//public class SecurityUtil {
//
//
//	/**
//	 * PasswdDigest MD5加密
//	 * @param s
//	 * @return
//	 */
//	public static String MD5(String s) {
//		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
//		try {
//			byte[] btInput = s.getBytes();
//			// 获得MD5摘要算法的 MessageDigest 对象
//			MessageDigest mdInst = MessageDigest.getInstance("MD5");
//			// 使用指定的字节更新摘要
//			mdInst.update(btInput);
//			// 获得密文
//			byte[] md = mdInst.digest();
//			// 把密文转换成十六进制的字符串形式
//			int j = md.length;
//			char str[] = new char[j * 2];
//			int k = 0;
//			for (int i = 0; i < j; i++) {
//				byte byte0 = md[i];
//				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
//				str[k++] = hexDigits[byte0 & 0xf];
//			}
//			return new String(str);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//
//	/**
//	 * 社团法人信息查询 MD5加密
//	 * @param str
//	 * @return
//	 */
//	public static String toMD5(String str) {
//		try {
//			MessageDigest md = MessageDigest.getInstance("MD5");
//			try {
//				md.update(str.getBytes("UTF-8"));
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//			byte[] encryContext = md.digest();
//			int i;
//			StringBuffer buf = new StringBuffer("");
//			for (int offset = 0; offset < encryContext.length; offset++) {
//				i = encryContext[offset];
//				if (i < 0)
//					i += 256;
//				if (i < 16)
//					buf.append("0");
//				buf.append(Integer.toHexString(i));
//			}
//			return buf.toString();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//		return str;
//	}
//    /**
//     * PasswdDigest SM3加密
//     */
//	public static String SM3(String secret){
//		byte[] md = new byte[32];
//		byte[] psw = secret.getBytes();
//		SM3Digest sm3 = new SM3Digest();
//		sm3.update(psw, 0, psw.length);
//		sm3.doFinal(md, 0);
//		String passwdDigest = new String(Hex.encode(md));
//	  return passwdDigest;
//	  }
//}
