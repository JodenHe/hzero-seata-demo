package com.hand.demo.app.service.impl;

import com.hand.demo.app.service.StorageService;
import com.hand.demo.domain.entity.Storage;
import com.hand.demo.domain.repository.StorageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 应用服务默认实现
 *
 * @author xiaofeng.he 2020-11-02 17:17:25
 */
@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger log = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Autowired
    private StorageRepository storageRepository;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info("------->扣减库存开始");
        Storage condition = new Storage();
        condition.setProductId(productId);
        Storage storage = storageRepository.selectOne(condition);
        storage.setUsed(storage.getUsed() + count);
        storage.setResidue(storage.getResidue() - count);
        storageRepository.updateOptional(storage, Storage.FIELD_USED, Storage.FIELD_RESIDUE);
        log.info("------->扣减库存结束");
    }
}
