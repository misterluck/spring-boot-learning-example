package org.example.sbnetty.netty.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import org.example.sbnetty.netty.handler.HttpServerHandler;
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

        // pipeline.addLast(new HttpRequestDecoder());
        // pipeline.addLast(new HttpResponseEncoder());
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));
        pipeline.addLast(httpServerHandler);

    }
}
