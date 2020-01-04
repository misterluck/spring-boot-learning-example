package org.example.antdvue.modules.order.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 例子订单
 * @Author: jeecg-boot
 * @Date:   2019-12-26
 * @Version: V1.0
 */
@Data
@TableName("example_order")
public class ExampleOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
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
}
