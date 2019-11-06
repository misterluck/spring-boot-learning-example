package org.example.sbwebsocket.server2;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


//实现接口来配置Websocket请求的路径和拦截器。
@Configuration
@EnableWebSocket
public class WebSocketH5Config implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        //handler是webSocket的核心，配置入口
        // webSocketHandlerRegistry.addHandler(new MyWebSocketHandler(), "/testWebSocket/{username}").setAllowedOrigins("*").addInterceptors(new CustomSessionAuthHandshakeInterceptor());
        webSocketHandlerRegistry.addHandler(new MyWebSocketHandler(), "/testWebSocket").setAllowedOrigins("*").addInterceptors(new CustomSessionAuthHandshakeInterceptor());
    }
}
