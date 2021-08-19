package gov.zwfw.iam.utils;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class SJJSessionPool {

    private SJJPoolConfig poolConfig; // 连接池配置
    private LinkedBlockingQueue<SJJConnection> freeConnections; // 空闲池连接
    
    public SJJPoolConfig getPoolConfig() {
        return poolConfig;
    }

    public LinkedBlockingQueue<SJJConnection> getFreeConnections() {
        return freeConnections;
    }

    /**
     * 功能描述：连接池构造方法
     * @param config
     */
    public SJJSessionPool(SJJPoolConfig config){
        try {
            if(config == null){
                return;
            }
            
            this.poolConfig = config;
            this.freeConnections = new LinkedBlockingQueue<SJJConnection>(config.getMaxConn());  //构造指定大小的阻塞队列
            //初始化
            init();
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (ConnectException e){
            e.printStackTrace();
        } catch (SocketTimeoutException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            //心跳检测守护线程
	        if(config != null){
		        Thread checkThread = new Thread();
		        checkThread.setDaemon(true);
		        checkThread.start();
	        }
        }
    }
    
    /**
     * 功能描述：初始化连接池
     * 
     * @return
     *
     */
    public void init() throws Exception{
        //初始化密码机参数
        for(int i=0; i<poolConfig.getMinConn(); i++){
            SJJConnection session = new SJJConnection();
            freeConnections.offer(session);
        }
        
    }
    
    /**
     * 功能描述：新建连接
     *
     */
    public SJJConnection newConnection() throws Exception{
        SJJConnection client = new SJJConnection();

        return client;
    }
    
    /**
     * 功能描述：获取连接
     * 
     */
    public SJJConnection getConnection() throws Exception{
        if(freeConnections.size()==0){
            synchronized (freeConnections) {
                int freeConnCount = freeConnections.size();
                if(freeConnCount == 0 && freeConnCount < poolConfig.getMaxConn()){
                    SJJConnection client = newConnection();
                    return client;
                }
            }
        }
        SJJConnection client = freeConnections.poll(2000,TimeUnit.MILLISECONDS);
        
        return client;
    }

    /**
     * 功能描述：将连接还回连接池
     */
    public void freeConnection(SJJConnection client) throws Exception{
        if(null != client && !client.isClosed()){
            freeConnections.offer(client);
        }
    }
    
}

class SJJPoolConfig {
	
	private int maxConn;
	private int minConn;

	public SJJPoolConfig(int minConn, int maxConn) {
		super();
		this.minConn = minConn;
		this.maxConn = maxConn;
	}

	public int getMaxConn() {
		return maxConn;
	}

	public void setMaxConn(int maxConn) {
		this.maxConn = maxConn;
	}

	public int getMinConn() {
		return minConn;
	}

	public void setMinConn(int minConn) {
		this.minConn = minConn;
	}
}



