package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;



@Data
@TableName("finished_raw_relation")
public class FinishedRawRelation {


    /**
     *
     */
    @TableField("id")
    private Integer id;

    /**
     * 原料id
     */
    @TableId(value = "row_id")
    private Integer rowId;

    /**
     * 成品id
     */
    @TableField("finish_id")
    private Integer finishId;

    /**
     * 需要数量
     */
    @TableField("nums")
    private String nums;

}
