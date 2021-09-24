package org.example.sbnetty.netty.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.OptionalSslHandler;
import org.example.sbnetty.netty.handler.HttpServerHandler;
import org.example.sbnetty.netty.handler.NettyProxyServerHandler;
import org.example.sbnetty.netty.util.SslUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhaol
 */
@Component
public class HttpServerInitializer extends ChannelInitializer<NioSocketChannel> {

    @Autowired
    private HttpServerHandler httpServerHandler;

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(SslUtil.getServerSslCtx().newHandler(ch.alloc()));
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024 * 1024));
        pipeline.addLast(new HttpServerExpectContinueHandler());
        pipeline.addLast(httpServerHandler);
        // pipeline.addLast(new NettyProxyServerHandler());

    }
}
