package org.example.antdvue.modules.example.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.common.api.vo.Result;
import org.example.common.system.query.QueryGenerator;
import org.example.common.util.oConvertUtils;
import org.example.antdvue.modules.example.entity.Example;
import org.example.antdvue.modules.example.service.IExampleService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.example.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 测试代码生成
 * @Author: jeecg-boot
 * @Date:   2019-12-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="测试代码生成")
@RestController
@RequestMapping("/example/example")
public class ExampleController extends JeecgController<Example, IExampleService> {
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
	@ApiOperation(value="测试代码生成-分页列表查询", notes="测试代码生成-分页列表查询")
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
	 * 添加
	 *
	 * @param example
	 * @return
	 */
	@ApiOperation(value="测试代码生成-添加", notes="测试代码生成-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Example example) {
		exampleService.save(example);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param example
	 * @return
	 */
	@ApiOperation(value="测试代码生成-编辑", notes="测试代码生成-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Example example) {
		exampleService.updateById(example);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value="测试代码生成-通过id删除", notes="测试代码生成-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		exampleService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@ApiOperation(value="测试代码生成-批量删除", notes="测试代码生成-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.exampleService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value="测试代码生成-通过id查询", notes="测试代码生成-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Example example = exampleService.getById(id);
		return Result.ok(example);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param example
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Example example) {
      return super.exportXls(request, example, Example.class, "测试代码生成");
  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, Example.class);
  }

}
