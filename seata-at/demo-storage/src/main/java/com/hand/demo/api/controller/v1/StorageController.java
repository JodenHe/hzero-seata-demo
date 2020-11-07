package com.hand.demo.api.controller.v1;

import com.hand.demo.app.service.StorageService;
import io.swagger.annotations.Api;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;
import com.hand.demo.domain.entity.Storage;
import com.hand.demo.domain.repository.StorageRepository;
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

/**
 * 管理 API
 *
 * @author xiaofeng.he 2020-11-02 17:17:25
 */
@Api(tags = "storage api")
@RestController("storageController.v1")
@RequestMapping("/v1/storages")
public class StorageController extends BaseController {

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private StorageService storageService;

    @ApiOperation(value = "列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping
    public ResponseEntity<Page<Storage>> list(Storage storage, @ApiIgnore @SortDefault(value = Storage.FIELD_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<Storage> list = storageRepository.pageAndSort(pageRequest, storage);
        return Results.success(list);
    }

    @ApiOperation(value = "明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{id}")
    public ResponseEntity<Storage> detail(@PathVariable Long id) {
        Storage storage = storageRepository.selectByPrimaryKey(id);
        return Results.success(storage);
    }

    @ApiOperation(value = "创建")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<Storage> create(@RequestBody Storage storage) {
        validObject(storage);
        storageRepository.insertSelective(storage);
        return Results.success(storage);
    }

    @ApiOperation(value = "修改")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping
    public ResponseEntity<Storage> update(@RequestBody Storage storage) {
        SecurityTokenHelper.validToken(storage);
        storageRepository.updateByPrimaryKeySelective(storage);
        return Results.success(storage);
    }

    @ApiOperation(value = "删除")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody Storage storage) {
        SecurityTokenHelper.validToken(storage);
        storageRepository.deleteByPrimaryKey(storage);
        return Results.success();
    }

    /**
     * 扣减库存
     * @param productId 产品id
     * @param count 数量
     * @return
     */
    @RequestMapping("/decrease")
    public String decrease(@RequestParam("productId") Long productId,@RequestParam("count") Integer count){
        storageService.decrease(productId, count);
        return "Decrease storage success";
    }

}
