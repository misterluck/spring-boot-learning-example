package gov.zwfw.iam.plugin;

import gov.zwfw.iam.entity.User;
import gov.zwfw.iam.service.PluginBusinessService;
import gov.zwfw.iam.vo.CommonResult;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = PluginApi.class)
public class PluginApiImpl implements PluginApi{

    private Logger logger = LoggerFactory.getLogger(PluginApiImpl.class);
    @Autowired
    private PluginBusinessService pluginBusinessService;

    @Override
    public CommonResult<User> getUser(User user) {
        logger.info("User info:"+user.toString());
        user.setName("明星");
        user.setNick(pluginBusinessService.getNick(user.getNick()));
        return CommonResult.success(user);
    }

}
