package com.ai.system.service;

import com.ai.system.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 用户角色表 服务类
 *
 */
public interface ISysUserRoleService extends IService<SysUserRole> {
	
	/**
	 * 查询所有的用户角色信息
	 * @return
	 */
	Map<String,String> queryUserRole();
}
