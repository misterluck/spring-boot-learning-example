package org.example.dubbo.api;

import org.apache.dubbo.config.annotation.DubboService;
import org.example.dubbo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@DubboService(interfaceClass = UserApi.class)
public class UserApiImpl implements UserApi {

    private Logger logger = LoggerFactory.getLogger(UserApiImpl.class);

    @Override
    public User saveUser(User user) {
        logger.info("User info:"+user.toString());
        user.setSex("男");
        return user;
    }
}
