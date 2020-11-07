package com.hand.demo.app.service;

/**
 * 应用服务
 *
 * @author xiaofeng.he 2020-11-02 17:17:25
 */
public interface StorageService {

    /**
     * 扣减库存
     * @param productId 产品id
     * @param count 数量
     * @return
     */
    void decrease(Long productId, Integer count);
}
