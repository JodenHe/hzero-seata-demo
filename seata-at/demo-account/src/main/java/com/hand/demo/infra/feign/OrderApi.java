/**
 * Copyright Hand China Co.,Ltd.
 */
package com.hand.demo.infra.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * order api
 * <p>
 * Created by xiaofeng.he on 2020/11/02
 *
 * @author xiaofeng.he
 */
@FeignClient(value = "demo-order", path = "/v1/orders")
public interface OrderApi {


    /**
     * 修改订单金额
     * @param userId
     * @param money
     * @param status
     * @return
     */
    @PostMapping("/update")
    String update(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money, @RequestParam("status") Integer status);
}
