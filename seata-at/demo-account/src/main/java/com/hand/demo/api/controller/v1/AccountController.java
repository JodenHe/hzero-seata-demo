package com.hand.demo.api.controller.v1;

import com.hand.demo.app.service.AccountService;
import io.swagger.annotations.Api;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;
import com.hand.demo.domain.entity.Account;
import com.hand.demo.domain.repository.AccountRepository;
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
 * @author xiaofeng.he 2020-11-02 17:14:32
 */
@Api(tags = "account api")
@RestController("accountController.v1")
@RequestMapping("/v1/accounts")
public class AccountController extends BaseController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping
    public ResponseEntity<Page<Account>> list(Account account, @ApiIgnore @SortDefault(value = Account.FIELD_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<Account> list = accountRepository.pageAndSort(pageRequest, account);
        return Results.success(list);
    }

    @ApiOperation(value = "明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{id}")
    public ResponseEntity<Account> detail(@PathVariable Long id) {
        Account account = accountRepository.selectByPrimaryKey(id);
        return Results.success(account);
    }

    @ApiOperation(value = "创建")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<Account> create(@RequestBody Account account) {
        validObject(account);
        accountRepository.insertSelective(account);
        return Results.success(account);
    }

    @ApiOperation(value = "修改")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping
    public ResponseEntity<Account> update(@RequestBody Account account) {
        SecurityTokenHelper.validToken(account);
        accountRepository.updateByPrimaryKeySelective(account);
        return Results.success(account);
    }

    @ApiOperation(value = "删除")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody Account account) {
        SecurityTokenHelper.validToken(account);
        accountRepository.deleteByPrimaryKey(account);
        return Results.success();
    }

    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     * @return
     */
    @ApiOperation(value = "扣减账户余额")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping("/decrease")
    public String decrease(@RequestParam("userId") Long userId,@RequestParam("money") BigDecimal money){
        accountService.decrease(userId, money);
        return "Account decrease success";
    }

}
