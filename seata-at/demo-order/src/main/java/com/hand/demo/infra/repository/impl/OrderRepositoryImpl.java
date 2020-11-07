package com.hand.demo.infra.repository.impl;

import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import com.hand.demo.domain.entity.Order;
import com.hand.demo.domain.repository.OrderRepository;
import org.springframework.stereotype.Component;

/**
 *  资源库实现
 *
 * @author xiaofeng.he 2020-11-02 17:15:32
 */
@Component
public class OrderRepositoryImpl extends BaseRepositoryImpl<Order> implements OrderRepository {


}
