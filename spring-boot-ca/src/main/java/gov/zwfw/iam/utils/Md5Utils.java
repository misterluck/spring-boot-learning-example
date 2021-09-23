//package gov.zwfw.iam.utils;
//
//
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
//public class Md5Utils {
//
//	public static String getMd5(String Encryp) {
//		try {
//            MessageDigest digest = MessageDigest.getInstance("md5");
//            byte[] result = digest.digest(Encryp.getBytes());
//            StringBuffer buffer = new StringBuffer();
//            for (byte b : result) {
//                // 与运算
//                int number = b & 0xff;
//                String str = Integer.toHexString(number);
//                if (str.length() == 1) {
//                    buffer.append("0");
//                }
//                buffer.append(str);
//            }
//            // 标准的md5加密后的结果
//            return buffer.toString();
//        } catch (NoSuchAlgorithmException e) {
//            return "";
//        }
//	}
//}
