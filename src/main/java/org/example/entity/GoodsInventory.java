package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;



@Data
@TableName("goods_inventory")
public class GoodsInventory {


    /**
     * 商品id
     */
    @TableId(value = "good_id")
    private Long goodId;

    /**
     * 数量
     */
    @TableField("nums")
    private Integer nums;

}
