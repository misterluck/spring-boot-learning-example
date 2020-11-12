package org.example.dubbo.provider.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import org.example.dubbo.api.entity.User;
import org.example.dubbo.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = UserService.class)
public class UserServiceProvider implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceProvider.class);

    @Override
    public User saveUser(User user) {
        logger.info("User info:"+user.toString());
        user.setSex("男");
        return user;
    }
}
