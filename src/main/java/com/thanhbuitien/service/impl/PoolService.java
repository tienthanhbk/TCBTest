package com.thanhbuitien.service.impl;

import com.thanhbuitien.common.Utils;
import com.thanhbuitien.constant.Constants;
import com.thanhbuitien.entity.PoolEntity;
import com.thanhbuitien.entity.QuantileCacheEntity;
import com.thanhbuitien.exception.ClientErrorException;
import com.thanhbuitien.models.dto.QuantileQueryRes;
import com.thanhbuitien.repositories.IPoolRepo;
import com.thanhbuitien.repositories.IQuantileCacheRepo;
import com.thanhbuitien.repositories.impl.PoolMemoryRepo;
import com.thanhbuitien.repositories.impl.QuantileCacheRepo;
import com.thanhbuitien.service.IPoolService;
import org.apache.commons.math3.stat.StatUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PoolService implements IPoolService {

    private final IPoolRepo poolRepo;
    private final IQuantileCacheRepo quantileCacheRepo;

    public PoolService(PoolMemoryRepo poolMemoryRepo,
                       QuantileCacheRepo quantileCacheRepo) {
        this.poolRepo = poolMemoryRepo;
        this.quantileCacheRepo = quantileCacheRepo;
    }

    @Override
    public int insertOrUpdate(Long poolId, List<Integer> values) {
        PoolEntity existedPoolEntity = poolRepo.getById(poolId);
        if (existedPoolEntity == null) {
            poolRepo.create(poolId, values);
            return Constants.FLAG_INSERT;
        }

        existedPoolEntity.getValues().addAll(values);

        poolRepo.update(poolId, existedPoolEntity.getValues());
        // Clear pool cached result when update
        quantileCacheRepo.clearQuantile(poolId);
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

    @Override
    public QuantileQueryRes queryQuantile(Long poolId, Double percentile) {
        Integer size;
        Double quantile;

        // Try to get result in cache
        QuantileCacheEntity quantileCacheEntity = quantileCacheRepo.getQuantile(poolId, percentile);
        if (quantileCacheEntity != null && quantileCacheEntity.getPercentile2Quantile().containsKey(percentile)) {
//            System.out.println("Found result in cache");
            size = quantileCacheEntity.getPoolSize();
            quantile = quantileCacheEntity.getPercentile2Quantile().get(percentile);
            return new QuantileQueryRes(size, quantile);
        }

        // Calculate result if not cached
        PoolEntity poolEntity = poolRepo.getById(poolId);
        if (poolEntity == null) {
            throw new ClientErrorException("Pool is not exist");
        }
        List<Integer> values = getValuesById(poolId);
        size= values.size();
        if (size > 100) {
            quantile = StatUtils.percentile(values.stream().mapToDouble(i -> i).toArray(), percentile);
        } else {
            List<Integer> sortedValues = values.stream().sorted().collect(Collectors.toList());
            quantile = Utils.calPercentile(sortedValues, percentile);
        }

        // Save result to cache
        quantileCacheRepo.save(poolId, size, percentile, quantile);

        return new QuantileQueryRes(size, quantile);
    }
}
