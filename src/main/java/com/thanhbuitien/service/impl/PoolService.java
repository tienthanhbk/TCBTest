package com.thanhbuitien.service.impl;

import com.thanhbuitien.constant.Constants;
import com.thanhbuitien.entity.PoolEntity;
import com.thanhbuitien.models.dto.PoolDto;
import com.thanhbuitien.repositories.IPoolRepo;
import com.thanhbuitien.repositories.impl.PoolMemoryRepo;
import com.thanhbuitien.service.IPoolService;
import org.springframework.stereotype.Component;

@Component
public class PoolService implements IPoolService {

    private final IPoolRepo poolRepo;

    public PoolService(PoolMemoryRepo poolMemoryRepo) {
        this.poolRepo = poolMemoryRepo;
    }

    @Override
    public int insertOrUpdate(PoolDto poolDto) {
        PoolEntity existedPoolEntity = poolRepo.getById(poolDto.getPoolId());
        if (existedPoolEntity == null) {
            poolRepo.create(poolDto.getPoolId(), poolDto.getPoolValues());
            return Constants.FLAG_INSERT;
        }

        existedPoolEntity.getValues().addAll(poolDto.getPoolValues());

        // Currently not necessary because of using in-memory repo, but in the future may use database to store pool values
        poolRepo.update(poolDto.getPoolId(), existedPoolEntity.getValues());
        return Constants.FLAG_APPEND;
    }
}
