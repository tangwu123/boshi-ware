package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;



@Data
@TableName("ware_order")
public class WareOrder {


    /**
     * 产品id
     */
    @TableField("good_id")
    private Long goodId;

    /**
     * 下单数量
     */
    @TableField("nums")
    private Integer nums;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 顾客id
     */
    @TableField("customer_id")
    private Long customerId;

    /**
     *
     */
    @TableField("id")
    private Long id;

}
