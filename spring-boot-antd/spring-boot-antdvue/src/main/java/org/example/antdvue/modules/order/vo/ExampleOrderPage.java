package org.example.antdvue.modules.order.vo;

import java.util.List;
import lombok.Data;
import org.example.antdvue.modules.order.entity.ExampleOrderItem;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 例子订单
 * @Author: jeecg-boot
 * @Date:   2019-12-26
 * @Version: V1.0
 */
@Data
public class ExampleOrderPage {
	
	/**主键*/
	private String id;
	/**创建人*/
	private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**更新人*/
	private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	/**所属部门*/
	private String sysOrgCode;
	/**订单编码*/
	private String orderCode;
	/**下单时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date orderTime;
	/**订单描述*/
	private String orderDesc;
	
	@ExcelCollection(name="例子订单明细")
	private List<ExampleOrderItem> exampleOrderItemList;
	
}
