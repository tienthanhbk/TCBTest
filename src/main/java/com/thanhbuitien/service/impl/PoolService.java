package com.thanhbuitien.service.impl;

import com.thanhbuitien.constant.Constants;
import com.thanhbuitien.entity.PoolEntity;
import com.thanhbuitien.exception.ClientErrorException;
import com.thanhbuitien.models.dto.PoolDto;
import com.thanhbuitien.repositories.IPoolRepo;
import com.thanhbuitien.repositories.impl.PoolMemoryRepo;
import com.thanhbuitien.service.IPoolService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PoolService implements IPoolService {

    private final IPoolRepo poolRepo;

    public PoolService(PoolMemoryRepo poolMemoryRepo) {
        this.poolRepo = poolMemoryRepo;
    }

    @Override
    public int insertOrUpdate(Long poolId, List<Integer> values) {
        PoolEntity existedPoolEntity = poolRepo.getById(poolId);
        if (existedPoolEntity == null) {
            poolRepo.create(poolId, values);
            return Constants.FLAG_INSERT;
        }

        existedPoolEntity.getValues().addAll(values);

        // Currently not necessary because of using in-memory repo, but in the future may use database to store pool values
        poolRepo.update(poolId, existedPoolEntity.getValues());
        return Constants.FLAG_APPEND;
    }

    @Override
    public List<Integer> getValuesById(Long poolId) throws ClientErrorException {
        PoolEntity poolEntity = poolRepo.getById(poolId);
        if (poolEntity == null) {
            throw new ClientErrorException("Pool is not exist");
        }
        return poolEntity.getValues();
    }
}
