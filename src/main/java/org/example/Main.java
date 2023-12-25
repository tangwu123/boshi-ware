package org.example;

import java.util.HashMap;

/**
 * @author tangwu
 * @data 2023/12/25
 * @apiNote
 */
public class Main {
    /**
     *  等待下订单的原料，value为数量
     */
    private static HashMap<String, Integer> watingGoods = new HashMap<>(4);

    public static void main(String[] args) {
        //  下单(不考虑缓存，并发，事务)
        // er图略
        purchaseOrder(1L, 1L, 1, true);
    }

    /**
     * 下单
     *
     * @param id      产品id
     * @param userId  顾客id
     * @param nums    采购数量
     * @param isFirst 是否是购买成品，如果是购买成品，则需要在order下单，如果是购买原料，则不需要（看具体业务）
     * @return void
     * @author TangWu
     * @date 2023/12/25 15:49
     */
    public static void purchaseOrder(Long id, Long userId, Integer nums, Boolean isFirst) {
        // 检查成品库存表，看是否有存货  goods_inventory
        Integer remain = checkGoodsInventory(id);
        // 如果有存货，直接扣除库存
        if (remain >= nums) {
            if (remain.equals(nums)) {
                // 0.一样多，删除库存
                deleteGoodsInventory(id);
            }
            // 1. 扣除库存
            updateGoodsInventory(id, remain - nums);
            if (!isFirst) {
                // 2. 生成订单
                createOrder(id, userId, nums, OrderStatus.WATING_PRODUCE.getCode());
            }

        } else if (remain > 0) {
            // 3. 库存清空，此处不一定要清空，看具体业务，可以设置为0
            deleteGoodsInventory(id);
            nums = nums - remain;
        }
        // 看是不是需要采购的
        //      good_id,nums
        HashMap<Long, Integer> map = findSubGoods(id);
        // 是最终原料，找采购
        if (map.isEmpty()) {
            // 找采购商
            String name = findShop(id);
            // 其他产品也需要这个原料，先放入map中，后面的直接更新数量，相当于等待批处理并合并
            watingGoods.put(name, watingGoods.getOrDefault(name, 0) + nums);
        }
        // 继续找原料,此处bfs可能更好。
        // 这里是认为有多层关系，即产品的原料也可以是产品，可以通过其他原料生产而非直接购买。
        // 这里不能有环。
        map.entrySet().forEach(entry -> purchaseOrder(entry.getKey(), userId, entry.getValue(), false));
        if (isFirst) {
            //生成订单
            createOrder(id, userId, nums, OrderStatus.WATING_GOODS.getCode());
            watingGoods.entrySet().forEach(entry -> {
                // 找采购商采购，异步，插入表定时任务，消息队列，或者直接调用采购商接口等等
            });
        }
    }

    /**
     * select shop from raw_shop where raw_id = 1
     *
     *
     * @param id 产品id
     * @return java.lang.String
     * @author TangWu
     * @date 2023/12/25 16:38
     */
    private static String findShop(Long id) {
        return "";
    }

    /**
     * 查其原料，如果无法生产，则返回空hashmap
     * select raw_id,nums from finished_raw_relation where finished_id = #{id};
     *
     * @param id 产品id
     * @return void
     * @author TangWu
     * @date 2023/12/25 16:31
     */
    private static HashMap<Long, Integer> findSubGoods(Long id) {

        return new HashMap<>(4);
    }

    /**
     * 删除库存
     * DELETE FROM goods_inventory WHERE good_id=#{id};
     *
     * @param id 产品id
     * @return void
     * @author TangWu
     * @date 2023/12/25 16:11
     */
    private static void deleteGoodsInventory(Long id) {
    }

    /**
     * 插入订单                                                                                                          雪花id
     * INSERT INTO ware_order (good_id, nums, status, customer_id, id) VALUES(#{id},#{nums} , #{code}, #{userId}, 0);
     *
     * @param id     产品id
     * @param userId 顾客id
     * @param nums   采购数量
     * @param code   订单状态
     * @return void
     * @author TangWu
     * @date 2023/12/25 16:07
     */
    private static void createOrder(Long id, Long userId, Integer nums, Integer code) {

    }

    /**
     * 更新库存
     * update goods_inventory set nums = #{nums} where good_id = #{id} ;
     *
     * @param id   good_id
     * @param nums 更新后数量
     * @return void
     * @author TangWu
     * @date 2023/12/25 16:01
     */
    private static void updateGoodsInventory(Long id, int nums) {

    }

    /**
     * 看剩余多少
     * select nums from goods_inventory where good_id = #{id} ;
     *
     * @param id good_id
     * @return java.lang.Integer
     * @author TangWu
     * @date 2023/12/25 15:51
     */
    private static Integer checkGoodsInventory(Long id) {
        return 0;
    }
}