package com.ai.system.controller;

import com.ai.common.api.vo.Result;
import com.ai.common.constant.CacheConstant;
import com.ai.common.system.query.QueryGenerator;
import com.ai.common.system.vo.LoginUser;
import com.ai.system.entity.SysDepart;
import com.ai.system.model.DepartIdModel;
import com.ai.system.service.ISysDepartService;
import com.ai.system.vo.SysDepartTreeModel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

// import org.apache.shiro.SecurityUtils;

/**
 * 部门表 前端控制器
 *
 */
@RestController
@RequestMapping("/sys/sysDepart")
@Slf4j
public class SysDepartController {

	@Autowired
	private ISysDepartService sysDepartService;

	/**
	 * 查询数据 查出所有部门,并以树结构数据格式响应给前端
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryTreeList", method = RequestMethod.GET)
	public Result<List<SysDepartTreeModel>> queryTreeList() {
		Result<List<SysDepartTreeModel>> result = new Result<>();
		try {
			// 从内存中读取
//			List<SysDepartTreeModel> list =FindsDepartsChildrenUtil.getSysDepartTreeList();
//			if (CollectionUtils.isEmpty(list)) {
//				list = sysDepartService.queryTreeList();
//			}
			List<SysDepartTreeModel> list = sysDepartService.queryTreeList();
			result.setResult(list);
			result.setSuccess(true);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return result;
	}

	/**
	 * 添加新数据 添加用户新建的部门对象数据,并保存到数据库
	 * 
	 * @param sysDepart
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@CacheEvict(value= {CacheConstant.SYS_DEPARTS_CACHE, CacheConstant.SYS_DEPART_IDS_CACHE}, allEntries=true)
	public Result<SysDepart> add(@RequestBody SysDepart sysDepart, HttpServletRequest request) {
		Result<SysDepart> result = new Result<SysDepart>();
		// String username = JwtUtil.getUserNameByToken(request);
		String username = "admin";
		try {
			sysDepart.setCreateBy(username);
			sysDepartService.saveDepartData(sysDepart, username);
			//清除部门树内存
			// FindsDepartsChildrenUtil.clearSysDepartTreeList();
			// FindsDepartsChildrenUtil.clearDepartIdModel();
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	 * 编辑数据 编辑部门的部分数据,并保存到数据库
	 * 
	 * @param sysDepart
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	@CacheEvict(value= {CacheConstant.SYS_DEPARTS_CACHE, CacheConstant.SYS_DEPART_IDS_CACHE}, allEntries=true)
	public Result<SysDepart> edit(@RequestBody SysDepart sysDepart, HttpServletRequest request) {
		// String username = JwtUtil.getUserNameByToken(request);
		System.out.println("##############"+sysDepart.toString());
		String username = "admin";
		sysDepart.setUpdateBy(username);
		Result<SysDepart> result = new Result<SysDepart>();
		SysDepart sysDepartEntity = sysDepartService.getById(sysDepart.getId());
		if (sysDepartEntity == null) {
			result.error500("未找到对应实体");
		} else {
			boolean ok = sysDepartService.updateDepartDataById(sysDepart, username);
			// TODO 返回false说明什么？
			if (ok) {
				//清除部门树内存
				//FindsDepartsChildrenUtil.clearSysDepartTreeList();
				//FindsDepartsChildrenUtil.clearDepartIdModel();
				result.success("修改成功!");
			}
		}
		return result;
	}
	
	 /**
     *   通过id删除
    * @param id
    * @return
    */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@CacheEvict(value= {CacheConstant.SYS_DEPARTS_CACHE, CacheConstant.SYS_DEPART_IDS_CACHE}, allEntries=true)
   public Result<SysDepart> delete(@RequestParam(name="id",required=true) String id) {

       Result<SysDepart> result = new Result<SysDepart>();
       SysDepart sysDepart = sysDepartService.getById(id);
       if(sysDepart==null) {
           result.error500("未找到对应实体");
       }else {
           boolean ok = sysDepartService.delete(id);
           if(ok) {
	            //清除部门树内存
	   		   //FindsDepartsChildrenUtil.clearSysDepartTreeList();
	   		   // FindsDepartsChildrenUtil.clearDepartIdModel();
               result.success("删除成功!");
           }
       }
       return result;
   }


	/**
	 * 批量删除 根据前端请求的多个ID,对数据库执行删除相关部门数据的操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.DELETE)
	@CacheEvict(value= {CacheConstant.SYS_DEPARTS_CACHE, CacheConstant.SYS_DEPART_IDS_CACHE}, allEntries=true)
	public Result<SysDepart> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {

		Result<SysDepart> result = new Result<SysDepart>();
		if (ids == null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		} else {
			this.sysDepartService.deleteBatchWithChildren(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	 * 查询数据 添加或编辑页面对该方法发起请求,以树结构形式加载所有部门的名称,方便用户的操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryIdTree", method = RequestMethod.GET)
	public Result<List<DepartIdModel>> queryIdTree() {
//		Result<List<DepartIdModel>> result = new Result<List<DepartIdModel>>();
//		List<DepartIdModel> idList;
//		try {
//			idList = FindsDepartsChildrenUtil.wrapDepartIdModel();
//			if (idList != null && idList.size() > 0) {
//				result.setResult(idList);
//				result.setSuccess(true);
//			} else {
//				sysDepartService.queryTreeList();
//				idList = FindsDepartsChildrenUtil.wrapDepartIdModel();
//				result.setResult(idList);
//				result.setSuccess(true);
//			}
//			return result;
//		} catch (Exception e) {
//			log.error(e.getMessage(),e);
//			result.setSuccess(false);
//			return result;
//		}
		Result<List<DepartIdModel>> result = new Result<>();
		try {
			List<DepartIdModel> list = sysDepartService.queryDepartIdTreeList();
			result.setResult(list);
			result.setSuccess(true);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return result;
	}
	 
	/**
	 * <p>
	 * 部门搜索功能方法,根据关键字模糊搜索相关部门
	 * </p>
	 * 
	 * @param keyWord
	 * @return
	 */
	@RequestMapping(value = "/searchBy", method = RequestMethod.GET)
	public Result<List<SysDepartTreeModel>> searchBy(@RequestParam(name = "keyWord", required = true) String keyWord) {
		Result<List<SysDepartTreeModel>> result = new Result<List<SysDepartTreeModel>>();
		try {
			List<SysDepartTreeModel> treeList = this.sysDepartService.searhBy(keyWord);
			if (treeList.size() == 0 || treeList == null) {
				throw new Exception();
			}
			result.setSuccess(true);
			result.setResult(treeList);
			return result;
		} catch (Exception e) {
			e.fillInStackTrace();
			result.setSuccess(false);
			result.setMessage("查询失败或没有您想要的任何数据!");
			return result;
		}
	}

}
