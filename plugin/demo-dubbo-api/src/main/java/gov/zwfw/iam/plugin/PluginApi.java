package gov.zwfw.iam.plugin;

import gov.zwfw.iam.entity.User;
import gov.zwfw.iam.vo.CommonResult;

public interface PluginApi {

    CommonResult<User> getUser(User user);

}
