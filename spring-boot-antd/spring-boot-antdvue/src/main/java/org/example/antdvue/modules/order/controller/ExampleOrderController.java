package org.example.antdvue.modules.order.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.example.antdvue.modules.order.entity.ExampleOrder;
import org.example.antdvue.modules.order.entity.ExampleOrderItem;
import org.example.antdvue.modules.order.service.IExampleOrderItemService;
import org.example.antdvue.modules.order.service.IExampleOrderService;
import org.example.antdvue.modules.order.vo.ExampleOrderPage;
import com.ai.common.api.vo.Result;
import com.ai.common.system.query.QueryGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 例子订单
 * @Author: jeecg-boot
 * @Date:   2019-12-26
 * @Version: V1.0
 */
@RestController
@RequestMapping("/order/exampleOrder")
@Slf4j
public class ExampleOrderController {
	@Autowired
	private IExampleOrderService exampleOrderService;
	@Autowired
	private IExampleOrderItemService exampleOrderItemService;
	
	/**
	 * 分页列表查询
	 *
	 * @param exampleOrder
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ExampleOrder exampleOrder,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ExampleOrder> queryWrapper = QueryGenerator.initQueryWrapper(exampleOrder, req.getParameterMap());
		Page<ExampleOrder> page = new Page<ExampleOrder>(pageNo, pageSize);
		IPage<ExampleOrder> pageList = exampleOrderService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param exampleOrderPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ExampleOrderPage exampleOrderPage) {
		ExampleOrder exampleOrder = new ExampleOrder();
		BeanUtils.copyProperties(exampleOrderPage, exampleOrder);
		exampleOrderService.saveMain(exampleOrder, exampleOrderPage.getExampleOrderItemList());
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param exampleOrderPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ExampleOrderPage exampleOrderPage) {
		ExampleOrder exampleOrder = new ExampleOrder();
		BeanUtils.copyProperties(exampleOrderPage, exampleOrder);
		ExampleOrder exampleOrderEntity = exampleOrderService.getById(exampleOrder.getId());
		if(exampleOrderEntity==null) {
			return Result.error("未找到对应数据");
		}
		exampleOrderService.updateMain(exampleOrder, exampleOrderPage.getExampleOrderItemList());
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
		exampleOrderService.delMain(id);
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
		this.exampleOrderService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ExampleOrder exampleOrder = exampleOrderService.getById(id);
		if(exampleOrder==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(exampleOrder);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryExampleOrderItemByMainId")
	public Result<?> queryExampleOrderItemListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ExampleOrderItem> exampleOrderItemList = exampleOrderItemService.selectByMainId(id);
		return Result.ok(exampleOrderItemList);
	}

}
