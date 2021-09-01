package org.example.sbnetty.netty.server;

import org.example.sbnetty.netty.config.NettyProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhaol
 */
@Component
public class TcpServer {

    private int port;

    public TcpServer(NettyProperties nettyProperties) {
        this.port = nettyProperties.getTcp().getPort();
    }

    /**
     * 开始引导服务器
     * 注意：不带 child 的是设置服务端的 Channel，带 child 的方法是设置每一条连接
     */
    public void start() {

    }

}
