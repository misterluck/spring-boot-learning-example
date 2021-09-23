//package gov.zwfw.iam.utils;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Configuration;
//
//// @Configuration
//// @ConditionalOnProperty(name = "sjj.switch", havingValue = "1")
//public class SjjCryptConfig implements InitializingBean{
//	private final static Logger logger = LoggerFactory.getLogger(SjjCryptConfig.class);
//
//	@Value("${server.sjj1604.accessCode}")
//    private String accessCode;
//	@Value("${server.sjj1604.url}")
//	private String url;
//	@Value("${server.sjj1604.port}")
//	private int port;
//	@Value("${server.sjj1604.timeout}")
//	private int timeout;
//	@Value("${server.minConn}")
//	private int minConn;
//	@Value("${server.maxConn}")
//	private int maxConn;
//	@Value("${server.sjj1604.conFlag}")
//	private boolean conFlag;
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//    	if(StringUtils.isAnyBlank(accessCode, url)) {
//    		return;
//    	}
//    	if(conFlag) {
//			logger.info("开始初始化密码机....", "");
//    		SJJCryptUtils.initParam(accessCode, url, port, timeout,minConn,maxConn);
//    	}
//
//    }
//}