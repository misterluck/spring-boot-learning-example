package org.example.sbwebsocket.server2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MyWebSocketHandler implements WebSocketHandler {
    private static Logger log = LoggerFactory.getLogger(MyWebSocketHandler.class);

    // 在线用户列表
    private static final Map<String, WebSocketSession> clients;
    private static final Map<String, String> sessionIds;

    static {
        clients = new ConcurrentHashMap<>();
        sessionIds = new ConcurrentHashMap<>();
    }

    // 新建Socket连接
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {

        String username = (String) webSocketSession.getAttributes().get(Constant.USERNAME);
        String httpSessionId = (String) webSocketSession.getAttributes().get(Constant.SPRING_SESSION_ID_ATTR_NAME);
        if (httpSessionId != null) {
            clients.put(username, webSocketSession);
            sessionIds.put(httpSessionId, username);
            webSocketSession.sendMessage(new TextMessage("成功建立socket连接"));
            log.info("用户：{}成功建立WebSocket连接", username);
        }
        log.info("当前在线人数：{}", clients.size());

    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        String httpSessionId = getClientId(webSocketSession);
        String username = sessionIds.get(httpSessionId);
        log.info("服务端接收消息：{}", webSocketMessage.getPayload());
        sendMessageToUser(username, "服务器收到消息，Hello World。");
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if (webSocketSession.isOpen()) {
            webSocketSession.close();
        }
        String httpSessionId = getClientId(webSocketSession);
        String username = sessionIds.get(httpSessionId);
        clients.remove(username);
        sessionIds.remove(httpSessionId);
        log.info("用户：{}, 客户端：{}, 连接出错", username, httpSessionId);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        String httpSessionId = getClientId(webSocketSession);
        String username = sessionIds.get(httpSessionId);
        clients.remove(username);
        sessionIds.remove(httpSessionId);
        log.info("用户：{}, 客户端：{}, 连接已关闭：{}", username, httpSessionId, closeStatus);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private String getClientId(WebSocketSession webSocketSession) {
        try {
            String httpSessionId = (String) webSocketSession.getAttributes().get(Constant.SPRING_SESSION_ID_ATTR_NAME);
            return httpSessionId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 发送消息给指定用户
     * @param username
     * @param message
     * @return
     */
    public boolean sendMessageToUser(String username, String message) {
        if (clients.get(username) == null) return false;
        WebSocketSession session = clients.get(username);
        if (!session.isOpen()) return false;
        try {
            session.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 广播消息
     * @param message
     * @return
     */
    public boolean sendMessageToAllUsers(String message) {
        boolean sendAllSuccess = true;

        for (Map.Entry<String, WebSocketSession> entry: clients.entrySet()) {
            try {
                if (entry.getValue().isOpen()) {
                    entry.getValue().sendMessage(new TextMessage(message));
                }
            } catch (IOException e) {
                e.printStackTrace();
                sendAllSuccess = false;
            }
        }

        return  sendAllSuccess;
    }

}
