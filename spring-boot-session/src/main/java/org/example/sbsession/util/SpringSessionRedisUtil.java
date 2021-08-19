package org.example.sbsession.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.stereotype.Component;

@Component
public class SpringSessionRedisUtil {
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private RedisOperationsSessionRepository redisOperationsSessionRepository;

    public Session createRedisSession(String user) {
        Session redisSession = redisOperationsSessionRepository.createSession();
        redisSession.setAttribute("user", user);
        redisSession.setAttribute("toUser", "toUser");
        ChannelUtil.bindUser(user, redisSession);



        return redisSession;
    }

}
