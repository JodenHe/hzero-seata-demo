/**
 * Copyright Hand China Co.,Ltd.
 */
package com.hand.demo.infra.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * account api
 * <p>
 * Created by xiaofeng.he on 2020/11/02
 *
 * @author xiaofeng.he
 */
@FeignClient(value = "demo-account", path = "/v1/accounts")
public interface AccountApi {

    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     */
    @PostMapping("/decrease")
    void decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
