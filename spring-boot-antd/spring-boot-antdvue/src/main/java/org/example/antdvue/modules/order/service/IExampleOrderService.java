package org.example.antdvue.modules.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.antdvue.modules.order.entity.ExampleOrder;
import org.example.antdvue.modules.order.entity.ExampleOrderItem;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 例子订单
 * @Author: jeecg-boot
 * @Date:   2019-12-26
 * @Version: V1.0
 */
public interface IExampleOrderService extends IService<ExampleOrder> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(ExampleOrder exampleOrder, List<ExampleOrderItem> exampleOrderItemList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ExampleOrder exampleOrder, List<ExampleOrderItem> exampleOrderItemList);
	
	/**
	 * 删除一对多
	 */
	public void delMain(String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);
	
}
