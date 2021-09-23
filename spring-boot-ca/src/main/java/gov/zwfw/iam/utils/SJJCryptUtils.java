//package gov.zwfw.iam.utils;
//
//
//import com.cn.gotech.sjj.SJJApi;
//import com.cn.gotech.sjj.exception.SJJException;
//import com.cn.gotech.sjj.handle.DeviceHandle;
//import com.cn.gotech.sjj.model.DecryptResult;
//import com.cn.gotech.sjj.model.EncryptResult;
//import com.cn.gotech.sjj.util.HexUtil;
//import com.cn.gotech.sjj.util.SJJConstant.AlgID;
//import gov.zwfw.iam.base.util.Util;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import java.io.UnsupportedEncodingException;
//import java.nio.ByteBuffer;
//
//public class SJJCryptUtils {
//	private final static Logger logger = LoggerFactory.getLogger(EncryUtil.class);
//
//	public static SJJApi api = new SJJApi();
//	public static DeviceHandle deviceHandle = null;
//	private static String PASSWORD ;
//	private static String SJJ_URL ;
//	private static int SJJ_PORT;
//	private static int TIMEOUT;
//	private static int MIN_CONN;
//	private static int MAX_CONN;
//	private static final int RETRY_TIMES = 2;
//	public static SJJSessionPool sessionPool;
//	public static SJJPoolConfig config;
//
//	public static void initParam(String passwd, String url, int port, int timeOut,int minConn,int maxConn){
//		PASSWORD = passwd;
//		SJJ_URL = url;
//		SJJ_PORT = port;
//		TIMEOUT = timeOut;
//		MIN_CONN = minConn;
//		MAX_CONN = maxConn;
//		deviceHandle = api.openDevice(SJJ_URL, SJJ_PORT, TIMEOUT);
//		//初始化连接池
//		config = new SJJPoolConfig(MIN_CONN, MAX_CONN);
//		sessionPool = new SJJSessionPool(config);
//	}
//
//	public static void initParam(String passwd, String url, int port, int timeOut){
//		PASSWORD = passwd;
//		SJJ_URL = url;
//		SJJ_PORT = port;
//		TIMEOUT = timeOut;
//		MIN_CONN = 100;
//		MAX_CONN = 500;
//		deviceHandle = api.openDevice(SJJ_URL, SJJ_PORT, TIMEOUT);
//		//初始化连接池
//		config = new SJJPoolConfig(MIN_CONN, MAX_CONN);
//		sessionPool = new SJJSessionPool(config);
//	}
//
//	/**
//	 * 指定SM4索引1密钥加密
//	 */
//	public static String encryptSM4ByIndex(String data) {
//		return encryptSM4ByIndex(data,201);
//	}
//
//	/**
//	 * 指定索引密钥加密
//	 * @param data
//	 * @param index
//	 * @return
//	 */
//	public static String encryptSM4ByIndex(String data, int index) {
//		String encData = "";
//		SJJConnection connection = getConnection();
//		try {
//			ByteBuffer dataBuffer = ByteBuffer.allocate(calcuateLength(data));
//			dataBuffer.put(data.getBytes());
//			if(connection==null || connection.getSessionHandle()==null) {
//				connection = getConnection();
//			}
//			EncryptResult encResult = api.internalEncrypt(connection.getSessionHandle(), index, AlgID.SGD_SM4_ECB, null, dataBuffer);
//			encData = HexUtil.hexString(encResult.getEncData().array());
//			return encData.trim();
//		}catch (SJJException e) {
//			connection.closeSession(connection.getSessionHandle());
//			connection.setClosed(true);
//			logger.error("SJJ加密时出现异常:", e);
//			return null;
//		}   catch (Exception e) {
//			connection.closeSession(connection.getSessionHandle());
//			connection.setClosed(true);
//			return null;
//		} finally {
//			freeSessionHandle(connection);
//		}
//	}
//
//	/**
//	 * 指定SM4索引1密钥解密
//	 */
//	public static String decryptSM4ByIndex(String cipherData) {
//		return decryptSM4ByIndex(cipherData, 201);
//	}
//
//	/**
//	 * 指定索引密钥解密
//	 * @param cipherData
//	 * @param index
//	 * @return
//	 */
//	public static String decryptSM4ByIndex(String cipherData, int index) {
//		String data = "";
//		SJJConnection connection = getConnection();
//		try {
//			byte[] ciByte = HexUtil.hex2byte(cipherData);
//			ByteBuffer dataBuffer = ByteBuffer.allocate(ciByte.length);
//			dataBuffer.put(ciByte);
//			if(connection==null || connection.getSessionHandle()==null) {
//				connection = getConnection();
//			}
//			DecryptResult dec = api.internalDecrypt(connection.getSessionHandle(), index, AlgID.SGD_SM4_ECB, null, dataBuffer);
//			data = new String(dec.getData().array());
//			return data.trim();
//		} catch (SJJException e) {
//			connection.closeSession(connection.getSessionHandle());
//			connection.setClosed(true);
//			logger.error("密码机发生异常:", e);
//			return null;
//		} catch (Exception e) {
//			connection.closeSession(connection.getSessionHandle());
//			connection.setClosed(true);
//			return null;
//		} finally {
//			freeSessionHandle(connection);
//		}
//	}
//
//	/**
//	 * 获取会话连接
//	 */
//	public static SJJConnection getConnection(){
//		SJJConnection connection =null;
//			try {
//				connection = sessionPool.getConnection();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		return connection;
//	}
//
//	/**
//	 * 计算hash SM3算法
//	 *
//	 * @param data
//	 * @return
//	 */
//	public static String SM3(String data) {
//		ByteBuffer dataInput = ByteBuffer.allocate(data.getBytes().length);
//		dataInput.put(data.getBytes());
//		byte[] sm3 = SM3(null, dataInput);
//		return Util.getHexString(sm3);
//	}
//
//	/**
//	 * 计算hash SM3算法
//	 *
//	 * @param id
//	 * @param dataInput
//	 * @return
//	 */
//	public static byte[] SM3(byte[] id, ByteBuffer dataInput) {
//		byte[] bytes = null;
//		SJJConnection connection = getConnection();
//		try {
//			bytes = new byte[32];
//			if(connection==null || connection.getSessionHandle()==null) {
//				connection = getConnection();
//			}
//			boolean init = api.hashInit(connection.getSessionHandle(), AlgID.SGD_SM3, null, id);
//			if (init) {
//				boolean update = api.hashUpdate(connection.getSessionHandle(), dataInput);
//				if (update) {
//					bytes = api.hashFinal(connection.getSessionHandle());
//				}
//			}
//		} catch (Exception e) {
//			if (connection == null){
//				connection.closeSession(connection.getSessionHandle());
//				connection.setClosed(true);
//			}
//			throw e;
//		} finally {
//			freeSessionHandle(connection);
//		}
//		return bytes;
//	}
//
//	/**
//	 * 释放会话连接
//	 */
//	public static void freeSessionHandle(SJJConnection connection) {
//		try {
//			sessionPool.freeConnection(connection);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static int calcuateLength(String dataInput) {
//		try {
//			int y = dataInput.getBytes("utf-8").length % 16;
//			int c = dataInput.getBytes("utf-8").length / 16;
//			int len = y == 0 ? dataInput.getBytes("utf-8").length : (c + 1) * 16;
//			return len;
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		return dataInput.length();
//	}
//
//	private static String retryEncryptSM4(SJJConnection connection, String data, int index) {
//		ByteBuffer dataBuffer = ByteBuffer.allocate(calcuateLength(data));
//		dataBuffer.put(data.getBytes());
//		if(connection==null||connection.getSessionHandle()==null) {
//			 connection = getConnection();
//		}
//		EncryptResult encResult = api.internalEncrypt(connection.getSessionHandle(), index, AlgID.SGD_SM4_ECB, null, dataBuffer);
//		return HexUtil.hexString(encResult.getEncData().array());
//	}
//
//	private static String retryDecryptSM4(SJJConnection connection, String cipherData, int index) {
//		byte[] ciByte = HexUtil.hex2byte(cipherData);
//		ByteBuffer dataBuffer = ByteBuffer.allocate(ciByte.length);
//		dataBuffer.put(ciByte);
//		DecryptResult dec = api.internalDecrypt(connection.getSessionHandle(), index, AlgID.SGD_SM4_ECB, null, dataBuffer);
//		return new String(dec.getData().array());
//	}
//
//}
