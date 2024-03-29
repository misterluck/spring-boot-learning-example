package org.example.sbnetty.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;

/**
 * @author zhaol
 */
@ChannelHandler.Sharable
@Component
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
        System.out.println(fullHttpRequest);
        ByteBuf reqBuf = fullHttpRequest.content();
        System.out.println(fullHttpRequest.uri());
        System.out.println(fullHttpRequest.method());
        System.out.println(reqBuf.toString(CharsetUtil.UTF_8));

        FullHttpResponse response = null;
        if(fullHttpRequest.method() == HttpMethod.GET){
            String data = "GET method over";
            ByteBuf buf = Unpooled.copiedBuffer(data, CharsetUtil.UTF_8);
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);
        }else if(fullHttpRequest.method() == HttpMethod.POST){
            String data = "POST method over";
            ByteBuf buf = Unpooled.copiedBuffer(data, CharsetUtil.UTF_8);
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);
        }else{
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.INTERNAL_SERVER_ERROR);
        }
        // 发送响应
        channelHandlerContext.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
