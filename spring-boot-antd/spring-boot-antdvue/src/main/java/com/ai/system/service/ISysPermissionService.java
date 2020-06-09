package com.ai.system.service;

import com.ai.common.exception.CustomBootException;
import com.ai.system.entity.SysPermission;
import com.ai.system.model.TreeModel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 菜单权限表 服务类
 *
 */
public interface ISysPermissionService extends IService<SysPermission> {
	
	public List<TreeModel> queryListByParentId(String parentId);
	
	/**真实删除*/
	public void deletePermission(String id) throws CustomBootException;
	/**逻辑删除*/
	public void deletePermissionLogical(String id) throws CustomBootException;
	
	public void addPermission(SysPermission sysPermission) throws CustomBootException;
	
	public void editPermission(SysPermission sysPermission) throws CustomBootException;
	
	public List<SysPermission> queryByUser(String username);
	
	/**
	 * 根据permissionId删除其关联的SysPermissionDataRule表中的数据
	 * 
	 * @param id
	 * @return
	 */
	// public void deletePermRuleByPermId(String id);
	
	/**
	  * 查询出带有特殊符号的菜单地址的集合
	 * @return
	 */
	public List<String> queryPermissionUrlWithStar();
}
