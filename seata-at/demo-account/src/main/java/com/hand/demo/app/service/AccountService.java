package com.hand.demo.app.service;

import java.math.BigDecimal;

/**
 * 应用服务
 *
 * @author xiaofeng.he 2020-11-02 17:14:32
 */
public interface AccountService {

    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     */
    void decrease(Long userId, BigDecimal money);
}
