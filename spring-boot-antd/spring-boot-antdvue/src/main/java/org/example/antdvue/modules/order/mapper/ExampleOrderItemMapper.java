package org.example.antdvue.modules.order.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.example.antdvue.modules.order.entity.ExampleOrderItem;

/**
 * @Description: 例子订单明细
 * @Author: jeecg-boot
 * @Date:   2019-12-26
 * @Version: V1.0
 */
public interface ExampleOrderItemMapper extends BaseMapper<ExampleOrderItem> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<ExampleOrderItem> selectByMainId(@Param("mainId") String mainId);
}
