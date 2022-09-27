package org.example.sbnetty.netty.initializer;

import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.OptionalSslHandler;
import io.netty.handler.ssl.SslHandler;
import org.example.sbnetty.netty.handler.HttpServerHandler;
import org.example.sbnetty.netty.handler.NettyProxyServerHandler;
import org.example.sbnetty.netty.util.SslUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLEngine;

/**
 * @author zhaol
 */
@Component
public class HttpsServerInitializer extends ChannelInitializer<NioSocketChannel> {

    @Autowired
    private HttpServerHandler httpServerHandler;

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        /*if (sslCtx != null) {
            // SSLEngine sslEngine = sslCtx.newEngine(UnpooledByteBufAllocator.DEFAULT);
            // 仅支持https
            // SSLEngine sslEngine = sslCtx.newEngine(ch.alloc());
            // pipeline.addLast(new SslHandler(sslEngine));
            // 同时支持http与https
            pipeline.addLast(new OptionalSslHandler(sslCtx));
        }*/
        // 仅支持https
        pipeline.addLast(SslUtil.getServerSslCtx().newHandler(ch.alloc()));
        // 同时支持http与https
        // pipeline.addLast(new OptionalSslHandler(SslUtil.getServerSslCtx()));
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024 * 1024));
        pipeline.addLast(new HttpServerExpectContinueHandler());
        pipeline.addLast(httpServerHandler);
        // pipeline.addLast(new NettyProxyServerHandler(SslUtil.getClientSslCtx()));

    }

}
