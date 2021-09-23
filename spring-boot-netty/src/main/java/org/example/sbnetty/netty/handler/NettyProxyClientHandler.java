package org.example.sbnetty.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.util.CharsetUtil;

/**
 * 代理 Client channel 处理函数
 * @author sky_han
 *
 */
public class NettyProxyClientHandler extends ChannelInboundHandlerAdapter {
	private Channel inBoundChannel;
	
	public NettyProxyClientHandler(Channel outBoundChannel) {
		this.inBoundChannel = outBoundChannel;
	}
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof FullHttpResponse) {
			FullHttpResponse response = (FullHttpResponse) msg;
			System.out.println(response.content().toString(CharsetUtil.UTF_8));
		}
    	if ( inBoundChannel.isActive() ) {
    		inBoundChannel.writeAndFlush(msg);
    	} else {
    		ctx.close();
    	}
    	
    }
	
}
