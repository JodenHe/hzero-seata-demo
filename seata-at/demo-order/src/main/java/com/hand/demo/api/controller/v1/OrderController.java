package com.hand.demo.api.controller.v1;

import com.hand.demo.app.service.OrderService;
import io.swagger.annotations.Api;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;
import com.hand.demo.domain.entity.Order;
import com.hand.demo.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.hzero.mybatis.helper.SecurityTokenHelper;

import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.choerodon.swagger.annotation.Permission;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;

/**
 * 管理 API
 *
 * @author xiaofeng.he 2020-11-02 17:15:32
 */
@Api(tags = "order api")
@RestController("orderController.v1")
@RequestMapping("/v1/orders")
public class OrderController extends BaseController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping
    public ResponseEntity<Page<Order>> list(Order order, @ApiIgnore @SortDefault(value = Order.FIELD_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<Order> list = orderRepository.pageAndSort(pageRequest, order);
        return Results.success(list);
    }

    @ApiOperation(value = "明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{id}")
    public ResponseEntity<Order> detail(@PathVariable Long id) {
        Order order = orderRepository.selectByPrimaryKey(id);
        return Results.success(order);
    }

    @ApiOperation(value = "创建")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order) {
        validObject(order);
        orderRepository.insertSelective(order);
        return Results.success(order);
    }

    @ApiOperation(value = "修改")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping
    public ResponseEntity<Order> update(@RequestBody Order order) {
        SecurityTokenHelper.validToken(order);
        orderRepository.updateByPrimaryKeySelective(order);
        return Results.success(order);
    }

    @ApiOperation(value = "删除")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody Order order) {
        SecurityTokenHelper.validToken(order);
        orderRepository.deleteByPrimaryKey(order);
        return Results.success();
    }

    /**
     * 创建订单
     * @param order
     * @return
     */
    @ApiOperation(value = "创建订单")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping("/create")
    public String createOrder(Order order) {
        orderService.create(order);
        return "Create order success";
    }

    /**
     * 修改订单状态
     * @param userId
     * @param money
     * @param status
     * @return
     */
    @ApiOperation(value = "修改订单状态")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @RequestMapping("/update")
    String update(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money, @RequestParam("status") Integer status){
        orderService.update(userId, money, status);
        return "订单状态修改成功";
    }

}
