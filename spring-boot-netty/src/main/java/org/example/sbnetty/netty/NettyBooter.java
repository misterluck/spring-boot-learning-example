package org.example.sbnetty.netty;

import org.example.sbnetty.netty.config.NettyProperties;
import org.example.sbnetty.netty.server.HttpServer;
import org.example.sbnetty.netty.server.TcpServer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * NettyServer启动器
 *
 * @author feng
 * @date 2019-04-22
 */
@Component
public class NettyBooter {

    private static final String HTTP = "http";
    private static final String TCP = "tcp";

    /**
     * Netty属性配置
     */
    private final NettyProperties nettyProperties;

    /**
     * Http服务端启动器
     */
    private final HttpServer httpServer;

    /**
     * Tcp服务端启动器
     */
    private final TcpServer tcpServer;

    public NettyBooter(HttpServer httpServer, TcpServer tcpServer, NettyProperties nettyProperties) {
        this.httpServer = httpServer;
        this.tcpServer = tcpServer;
        this.nettyProperties = nettyProperties;
    }

    @PostConstruct
    private void nettyServerStart() {
        // 根据netty配置协议，运行不同的启动器
        if (HTTP.equals(nettyProperties.getProtocol().toLowerCase())) {
            httpServer.start();
        } else {
            tcpServer.start();
        }
    }
}
