package org.example.sbwebsocket.server2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MyWebSocketHandler implements WebSocketHandler {
    private static Logger log = LoggerFactory.getLogger(MyWebSocketHandler.class);

    // 在线用户列表
    private static final Map<String, WebSocketSession> clients;

    static {
        clients = new ConcurrentHashMap<>();
    }

    // 新建Socket连接
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        log.info("成功建立连接");
        String httpSessionId = (String) webSocketSession.getAttributes().get(Constant.SPRING_SESSION_ID_ATTR_NAME);
        if (httpSessionId != null) {
            clients.put(httpSessionId, webSocketSession);
            webSocketSession.sendMessage(new TextMessage("成功建立socket连接"));
        }
        System.out.println("当前在线人数："+clients.size());

    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
