package org.example.sbnetty.netty.handler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

/**
 * Server channel 处理函数
 * @author sky_han
 *
 */
@ChannelHandler.Sharable
@Component
public class NettyProxyServerHandler extends ChannelInboundHandlerAdapter {

	private String remoteHost = "10.21.16.21";
	private int remotePort = 8092;

	private SslContext sslCtx = null;
	
	/*private String remoteHost = "10.21.171.65";
	private int remotePort = 8000;*/

	private Channel outBoundChannel;

	public NettyProxyServerHandler(SslContext sslCtx) {
		super();
		this.sslCtx = sslCtx;
	}
	
	public NettyProxyServerHandler() {
		super();
	}

    public NettyProxyServerHandler(String remoteHost, int remotePort) {
		super();
		this.remoteHost = remoteHost;
		this.remotePort = remotePort;
	}

	@Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception {

		if(outBoundChannel==null || !outBoundChannel.isActive() || !ctx.channel().isActive()) {
        	/* 创建netty client,连接到远程地址 */
			Bootstrap bootstrap = new Bootstrap();
        	bootstrap.group(ctx.channel().eventLoop())
        	         .channel(ctx.channel().getClass())
					 .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
        	         .handler(new ChannelInitializer<SocketChannel>(){
 						@Override
 						protected void initChannel(SocketChannel ch)
 								throws Exception {
 							ChannelPipeline pipeline = ch.pipeline();
							if (sslCtx != null) {
								pipeline.addLast(sslCtx.newHandler(ch.alloc()));
							}
 							pipeline.addLast(new HttpClientCodec());
 							pipeline.addLast(new HttpObjectAggregator(1024 * 1024 * 1024));
 							pipeline.addLast(new NettyProxyClientHandler(ctx.channel()));
 						}});
        	ChannelFuture future = bootstrap.connect(remoteHost,remotePort);
        	outBoundChannel = future.channel();

        	/* channel建立成功后,将请求发送给远程主机 */
        	future.addListener(new ChannelFutureListener(){
    			@Override
    			public void operationComplete(ChannelFuture future) throws Exception {
    				if (future.isSuccess()) {
		                future.channel().writeAndFlush(msg);
    				} else {
    					future.channel().close();
    				}
    			}
        	});
    	} else {
			System.out.println("channel:"+outBoundChannel.isActive());
    		outBoundChannel.writeAndFlush(msg);
    	}
    }
}
