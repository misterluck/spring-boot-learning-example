package org.example.sbnetty.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.example.sbnetty.netty.config.NettyProperties;
import org.example.sbnetty.netty.initializer.HttpServerInitializer;
import org.springframework.stereotype.Component;

/**
 * @author zhaol
 */
@Component
public class HttpServer {

    private int port;
    private final HttpServerInitializer httpServerInitializer;

    public HttpServer(NettyProperties nettyProperties, HttpServerInitializer httpServerInitializer) {
        this.port = nettyProperties.getHttp().getPort();
        this.httpServerInitializer = httpServerInitializer;
    }

    /**
     * 开始引导服务器
     * 注意：不带 child 的是设置服务端的 Channel，带 child 的方法是设置每一条连接
     */
    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                // 指定线程模型，这里是主从线程模型
                .group(bossGroup, workerGroup)
                // 指定服务端的 Channel 的 I/O 模型
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                // .option(ChannelOption.TCP_NODELAY, true)
                // 指定处理新连接数据的读写处理逻辑:每次有新连接到来，都会去执行ChannelInitializer.initChannel()，并new一大堆handler。所以如果handler中无成员变量，则可写成单例
                .childHandler(httpServerInitializer);

        serverBootstrap.bind(port).addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                System.out.println("http端口绑定成功 port = " + port);
            } else {
                System.out.println("http端口绑定失败");
            }
        });
    }
}
