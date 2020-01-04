package org.example.antdvue.modules.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.antdvue.modules.order.entity.ExampleOrderItem;

import java.util.List;

/**
 * @Description: 例子订单明细
 * @Author: jeecg-boot
 * @Date:   2019-12-26
 * @Version: V1.0
 */
public interface IExampleOrderItemService extends IService<ExampleOrderItem> {

	public List<ExampleOrderItem> selectByMainId(String mainId);
}
