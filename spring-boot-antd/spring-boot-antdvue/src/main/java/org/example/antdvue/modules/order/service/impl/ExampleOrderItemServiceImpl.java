package org.example.antdvue.modules.order.service.impl;

import org.example.antdvue.modules.order.entity.ExampleOrderItem;
import org.example.antdvue.modules.order.mapper.ExampleOrderItemMapper;
import org.example.antdvue.modules.order.service.IExampleOrderItemService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 例子订单明细
 * @Author: jeecg-boot
 * @Date:   2019-12-26
 * @Version: V1.0
 */
@Service
public class ExampleOrderItemServiceImpl extends ServiceImpl<ExampleOrderItemMapper, ExampleOrderItem> implements IExampleOrderItemService {
	
	@Autowired
	private ExampleOrderItemMapper exampleOrderItemMapper;
	
	@Override
	public List<ExampleOrderItem> selectByMainId(String mainId) {
		return exampleOrderItemMapper.selectByMainId(mainId);
	}
}
