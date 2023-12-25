package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;



@Data
@TableName("good_name")
public class GoodName {


    /**
     * 产品或者原料id
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 名字
     */
    @TableField("name")
    private String name;

}
