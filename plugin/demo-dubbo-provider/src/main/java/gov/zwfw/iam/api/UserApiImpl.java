package gov.zwfw.iam.api;

import gov.zwfw.iam.entity.User;
import gov.zwfw.iam.plugin.PluginApi;
import gov.zwfw.iam.vo.CommonResult;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = UserApi.class)
public class UserApiImpl implements UserApi {

    private Logger logger = LoggerFactory.getLogger(UserApiImpl.class);
    @Reference(check = false)
    private PluginApi pluginApi;

    @Override
    public User saveUser(User user) {
        logger.info("User info:"+user.toString());
        user.setSex("女");
        CommonResult<User> result = pluginApi.getUser(user);
        return result.getData();
    }
}
