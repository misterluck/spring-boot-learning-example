package org.example.sbwebsocket.server2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

/**
 * 用于WebSocket的安全验证
 */
public class CustomSessionAuthHandshakeInterceptor implements HandshakeInterceptor {
    private static Logger log = LoggerFactory.getLogger(CustomSessionAuthHandshakeInterceptor.class);

    // 在握手之前执行该方法, 继续握手返回true, 中断握手返回false. 通过attributes参数设置WebSocketSession的属性
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {

        if(serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest)serverHttpRequest;
            HttpServletRequest httpServletRequest = servletRequest.getServletRequest();
            String username = httpServletRequest.getParameter("username");
            // String ID = servletRequest.getURI().toString().split("ID=")[1];
            HttpSession session = httpServletRequest.getSession();
            if(session != null) {
                attributes.put(Constant.SPRING_SESSION_ID_ATTR_NAME, session.getId());
                attributes.put(Constant.USERNAME, username);
            }
        }
        /*if (attributes.containsKey(HTTP_SESSION_ID_ATTR_NAME)) {
            attributes.put(SPRING_SESSION_ID_ATTR_NAME, attributes.get(HTTP_SESSION_ID_ATTR_NAME));
        }*/
        return true;
    }

    // 在握手之后执行该方法
    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        log.info("进入WebSocket的afterHandshake拦截器");
    }
}
