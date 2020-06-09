package com.ai.system.service;


import com.ai.system.entity.SysUser;
import com.ai.system.entity.SysUserDepart;
import com.ai.system.model.DepartIdModel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * SysUserDpeart用户组织机构service
 *
 *
 */
public interface ISysUserDepartService extends IService<SysUserDepart> {
	

	/**
	 * 根据指定用户id查询部门信息
	 * @param userId
	 * @return
	 */
	List<DepartIdModel> queryDepartIdsOfUser(String userId);
	

	/**
	 * 根据部门id查询用户信息
	 * @param depId
	 * @return
	 */
	List<SysUser> queryUserByDepId(String depId);
}
