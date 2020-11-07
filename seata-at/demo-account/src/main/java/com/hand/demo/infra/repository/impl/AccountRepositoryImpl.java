package com.hand.demo.infra.repository.impl;

import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import com.hand.demo.domain.entity.Account;
import com.hand.demo.domain.repository.AccountRepository;
import org.springframework.stereotype.Component;

/**
 *  资源库实现
 *
 * @author xiaofeng.he 2020-11-02 17:14:32
 */
@Component
public class AccountRepositoryImpl extends BaseRepositoryImpl<Account> implements AccountRepository {


}
