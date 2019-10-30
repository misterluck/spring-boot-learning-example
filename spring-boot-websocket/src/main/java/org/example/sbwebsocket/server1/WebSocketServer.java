/*
package org.example.sbwebsocket.server1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint(value = "/websocket/{username}")
public class WebSocketServer {
    private static Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    @OnOpen
    public void open(@PathParam(value = "username") String username, Session session) {
        WebSocketUtils.add(username, session);
    }

    @OnMessage
    public void onMessage(@PathParam("username") String username, String message) {
        WebSocketUtils.receive(username, message);
    }

    @OnClose
    public void onClose(@PathParam("username") String username, Session session) {
        WebSocketUtils.remove(username);
    }

    @OnError
    public void onError(@PathParam("username") String username, Throwable throwable, Session session) {
        throwable.printStackTrace();
        WebSocketUtils.remove(username);
    }

}
*/
