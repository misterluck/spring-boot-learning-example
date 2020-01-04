package org.example.antdvue.modules.order.service.impl;

import org.example.antdvue.modules.order.entity.ExampleOrder;
import org.example.antdvue.modules.order.entity.ExampleOrderItem;
import org.example.antdvue.modules.order.mapper.ExampleOrderItemMapper;
import org.example.antdvue.modules.order.mapper.ExampleOrderMapper;
import org.example.antdvue.modules.order.service.IExampleOrderService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 例子订单
 * @Author: jeecg-boot
 * @Date:   2019-12-26
 * @Version: V1.0
 */
@Service
public class ExampleOrderServiceImpl extends ServiceImpl<ExampleOrderMapper, ExampleOrder> implements IExampleOrderService {

	@Autowired
	private ExampleOrderMapper exampleOrderMapper;
	@Autowired
	private ExampleOrderItemMapper exampleOrderItemMapper;
	
	@Override
	@Transactional
	public void saveMain(ExampleOrder exampleOrder, List<ExampleOrderItem> exampleOrderItemList) {
		exampleOrderMapper.insert(exampleOrder);
		if(exampleOrderItemList!=null && exampleOrderItemList.size()>0) {
			for(ExampleOrderItem entity:exampleOrderItemList) {
				//外键设置
				entity.setOrderFkId(exampleOrder.getId());
				exampleOrderItemMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(ExampleOrder exampleOrder,List<ExampleOrderItem> exampleOrderItemList) {
		exampleOrderMapper.updateById(exampleOrder);
		
		//1.先删除子表数据
		exampleOrderItemMapper.deleteByMainId(exampleOrder.getId());
		
		//2.子表数据重新插入
		if(exampleOrderItemList!=null && exampleOrderItemList.size()>0) {
			for(ExampleOrderItem entity:exampleOrderItemList) {
				//外键设置
				entity.setOrderFkId(exampleOrder.getId());
				exampleOrderItemMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		exampleOrderItemMapper.deleteByMainId(id);
		exampleOrderMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			exampleOrderItemMapper.deleteByMainId(id.toString());
			exampleOrderMapper.deleteById(id);
		}
	}
	
}
