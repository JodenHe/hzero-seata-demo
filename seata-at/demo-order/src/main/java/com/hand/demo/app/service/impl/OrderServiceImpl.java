package com.hand.demo.app.service.impl;

import com.hand.demo.app.service.OrderService;
import com.hand.demo.domain.entity.Order;
import com.hand.demo.domain.repository.OrderRepository;
import com.hand.demo.infra.feign.AccountApi;
import com.hand.demo.infra.feign.StorageApi;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 应用服务默认实现
 *
 * @author xiaofeng.he 2020-11-02 17:15:32
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StorageApi storageApi;

    @Autowired
    private AccountApi accountApi;

    @Override
    @GlobalTransactional(name = "demo-create-order", rollbackFor = Exception.class, timeoutMills = 600000)
    public void create(Order order) {
        log.info("------->交易开始");
        // 本地方法
        order.setStatus(0);
        orderRepository.insertSelective(order);

        // 远程方法 扣减库存
        storageApi.decrease(order.getProductId(),order.getCount());

        // 远程方法 扣减账户余额
        log.info("------->扣减账户开始order中");
        accountApi.decrease(order.getUserId(),order.getMoney());
        log.info("------->扣减账户结束order中");

        log.info("------->交易结束");
    }

    @Override
    public void update(Long userId, BigDecimal money, Integer status) {
        log.info("修改订单状态，入参为：userId = {}, money = {}, status = {}", userId, money, status);
        Order condition = new Order();
        condition.setUserId(userId);
        condition.setStatus(status);
        Order order = orderRepository.selectOne(condition);
        order.setMoney(order.getMoney().subtract(money));
        order.setStatus(1);
        orderRepository.updateOptional(order, Order.FIELD_MONEY, Order.FIELD_STATUS);

    }
}
