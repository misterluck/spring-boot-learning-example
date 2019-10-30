/*
package org.example.sbwebsocket.server1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketUtils {
    private static Logger log = LoggerFactory.getLogger(WebSocketUtils.class);

    private static Map<String, Session> clients = new ConcurrentHashMap<>();
    private static Map<String, String> sessionIds = new ConcurrentHashMap<>();

    public static void add(String username, Session session) {
        clients.put(username, session);
        sessionIds.put(session.getId(), username);
        log.info("当前连接数 = " + clients.size());

    }

    public static void receive(String username, String message) {
        log.info("收到消息 : Username = " + username + " , Message = " + message);
        Session session = clients.get(username);
        if (session != null) {
            try {
                session.getBasicRemote().sendText(message);
                log.info("服务端回消息成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        clients.get(username).getAsyncRemote().sendText("服务端接收到消息");
        log.info("当前连接数 = " + clients.size());
    }

    public static void remove(String username) {
        clients.remove(username);
        log.info("当前连接数 = " + clients.size());

    }

    public static boolean sendMessage(String username , String message) {
        log.info("当前连接数 = " + clients.size());
        if(clients.get(username) == null){
            return false;
        }else{
            clients.get(username).getAsyncRemote().sendText(message);
            return true;
        }

    }

}
*/
