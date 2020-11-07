package com.hand.demo.app.service;

import com.hand.demo.domain.entity.Order;

import java.math.BigDecimal;

/**
 * 应用服务
 *
 * @author xiaofeng.he 2020-11-02 17:15:32
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param order
     */
    void create(Order order);

    /**
     * 修改订单状态
     * @param userId
     * @param money
     * @param status
     */
    void update(Long userId, BigDecimal money, Integer status);
}
