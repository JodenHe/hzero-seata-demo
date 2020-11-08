package com.hand.demo.app.service.impl;

import com.hand.demo.app.service.AccountService;
import com.hand.demo.domain.entity.Account;
import com.hand.demo.domain.repository.AccountRepository;
import com.hand.demo.infra.feign.OrderApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 应用服务默认实现
 *
 * @author xiaofeng.he 2020-11-02 17:14:32
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OrderApi orderApi;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("------->扣减账户开始account中");
        // 模拟超时异常，全局事务回滚
        /*try {
            Thread.sleep(40*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        Account condition = new Account();
        condition.setUserId(userId);
        Account account = accountRepository.selectOne(condition);
        account.setUsed(account.getUsed().add(money));
        account.setResidue(account.getResidue().subtract(money));
        accountRepository.updateOptional(account, Account.FIELD_RESIDUE, Account.FIELD_USED);
        log.info("------->扣减账户结束account中");

        // 修改订单状态，此调用会导致调用成环
        log.info("修改订单状态开始");
        String mes = orderApi.update(userId, money.multiply(new BigDecimal("0.09")), 0);
        log.info("修改订单状态结束：{}",mes);
    }
}
