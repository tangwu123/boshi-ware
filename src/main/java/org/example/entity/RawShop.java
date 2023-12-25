package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;



@Data
@TableName("raw_shop")
public class RawShop {


    /**
     *
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 原料id
     */
    @TableField("raw_id")
    private Long rawId;

    /**
     * 供应商名字
     */
    @TableField("shop")
    private String shop;

}
