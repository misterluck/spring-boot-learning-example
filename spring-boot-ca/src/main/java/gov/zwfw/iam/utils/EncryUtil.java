package gov.zwfw.iam.utils;

import com.cn.gotech.sjj.exception.SJJException;
import com.cn.gotech.sjj.model.DecryptResult;
import com.cn.gotech.sjj.model.EncryptResult;
import com.cn.gotech.sjj.util.HexUtil;
import com.cn.gotech.sjj.util.SJJConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;


/**
 * 加、解密工具类
 * 
 * @ClassName: EncryUtil
 * @Description:通用加密方法
 * @author: 亚信安全NSG dy
 * @date: 2018年5月16日 下午2:19:03
 * @Copyright: 2018
 */
@Component
public class EncryUtil {
	private final static Logger logger = LoggerFactory.getLogger(EncryUtil.class);
	
	@Value("${sjjIndex}")
	private int index;
	

	public String sm4Encrypt(String data){
		SJJConnection connection = SJJCryptUtils.getConnection();
		try {
			if(connection==null || connection.getSessionHandle()==null) {
				connection = SJJCryptUtils.getConnection();
			}
			int c = data.getBytes("utf-8").length / 16;
			int len = data.getBytes("utf-8").length % 16 == 0 ?
					data.getBytes("utf-8").length : (c + 1) * 16;
			ByteBuffer dataBuffer = ByteBuffer.allocate(len);
			dataBuffer.put(data.getBytes());
			EncryptResult encResult = SJJCryptUtils.api.internalEncrypt(connection.getSessionHandle(), index, SJJConstant.AlgID.SGD_SM4_ECB, null, dataBuffer);
			String sm4Data = HexUtil.hexString(encResult.getEncData().array());
			return sm4Data;
		}catch (SJJException e) {
			if (connection != null) {
				connection.closeSession(connection.getSessionHandle());
				connection.setClosed(true);
			}
			logger.error("SJJ加密时出现异常:", e);
			return null;
		}   catch (Exception e) {
			if (connection != null) {
				connection.closeSession(connection.getSessionHandle());
				connection.setClosed(true);
			}
			return null;
		} finally {
			SJJCryptUtils.freeSessionHandle(connection);
		}
	}

	public String sm4Decrypt(String data){
		SJJConnection connection = SJJCryptUtils.getConnection();
		try {
			byte[] ciByte = HexUtil.hex2byte(data);
			ByteBuffer dataBuffer = ByteBuffer.allocate(ciByte.length);
			dataBuffer.put(ciByte);
			if(connection==null || connection.getSessionHandle()==null) {
				connection = SJJCryptUtils.getConnection();
			}
			DecryptResult dec = SJJCryptUtils.api.internalDecrypt(connection.getSessionHandle(), index, SJJConstant.AlgID.SGD_SM4_ECB, null, dataBuffer);
			data = new String(dec.getData().array(), Charset.forName("UTF-8"));
			return data.trim();
		}catch (SJJException e) {
			if (connection != null) {
				connection.closeSession(connection.getSessionHandle());
				connection.setClosed(true);
			}
			logger.error("SJJ加密时出现异常:", e);
			return null;
		}   catch (Exception e) {
			if (connection != null) {
				connection.closeSession(connection.getSessionHandle());
				connection.setClosed(true);
			}
			return null;
		} finally {
			SJJCryptUtils.freeSessionHandle(connection);
		}
	}
}
