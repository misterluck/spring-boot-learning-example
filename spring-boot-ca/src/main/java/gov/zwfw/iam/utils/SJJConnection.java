//package gov.zwfw.iam.utils;
//
//import com.cn.gotech.sjj.SJJApi;
//import com.cn.gotech.sjj.handle.SessionHandle;
//
//public class SJJConnection {
//	private SJJApi api = new SJJApi();
//	private  SessionHandle sessionHandle;
//	private boolean isClosed ;
//
//	public SJJConnection(){
//		//创建连接会话
//		sessionHandle = api.openSession(SJJCryptUtils.deviceHandle);
//		isClosed = false;
//	}
//
//	public boolean isClosed() {
//		return isClosed;
//	}
//
//	public void setClosed(boolean isClosed) {
//		this.isClosed = isClosed;
//	}
//
//	public void closeSession(SessionHandle sessionHandle) {
//		api.closeSession(sessionHandle);
//	}
//
//	public SessionHandle getSessionHandle() {
//		return sessionHandle;
//	}
//
//	public void setSessionHandle(SessionHandle sessionHandle) {
//		this.sessionHandle = sessionHandle;
//	}
//}
