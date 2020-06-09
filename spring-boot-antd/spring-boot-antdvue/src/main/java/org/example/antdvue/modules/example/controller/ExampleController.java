package org.example.antdvue.modules.example.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.example.antdvue.modules.example.entity.Example;
import org.example.antdvue.modules.example.service.IExampleService;
import com.ai.common.api.vo.Result;
import com.ai.common.system.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

 /**
 * @Description: 测试单表
 * @Author: jeecg-boot
 * @Date:   2019-12-25
 * @Version: V1.0
 */
@RestController
@RequestMapping("/example/example")
@Slf4j
public class ExampleController {
	@Autowired
	private IExampleService exampleService;
	
	/**
	 * 分页列表查询
	 *
	 * @param example
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Example example,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Example> queryWrapper = QueryGenerator.initQueryWrapper(example, req.getParameterMap());
		Page<Example> page = new Page<Example>(pageNo, pageSize);
		IPage<Example> pageList = exampleService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param example
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Example example) {
		exampleService.save(example);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param example
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Example example) {
		exampleService.updateById(example);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		exampleService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.exampleService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Example example = exampleService.getById(id);
		if(example==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(example);
	}

}
