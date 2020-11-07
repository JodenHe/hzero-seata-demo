/**
 * Copyright Hand China Co.,Ltd.
 */
package com.hand.demo.infra.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * storage api
 * <p>
 * Created by xiaofeng.he on 2020/11/02
 *
 * @author xiaofeng.he
 */
@FeignClient(value = "demo-storage", path = "/v1/storages")
public interface StorageApi {

    /**
     * 扣减库存
     * @param productId
     * @param count
     * @return
     */
    @PostMapping(value = "/decrease")
    void decrease(@RequestParam("productId") Long productId, @RequestParam("count") Long count);
}
