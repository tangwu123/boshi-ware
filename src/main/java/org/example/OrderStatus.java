package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态
 *
 * @author tangwu
 * @data 2023/12/25
 * @apiNote
 */
@Getter
@AllArgsConstructor
public enum OrderStatus {
    WATING_GOODS(1, "原料不足"),
    WATING_PRODUCE(2, "等待生产");
    private Integer code;
    private String desc;
}
