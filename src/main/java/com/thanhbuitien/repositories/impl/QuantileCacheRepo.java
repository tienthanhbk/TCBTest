package com.thanhbuitien.repositories.impl;

import com.thanhbuitien.entity.QuantileCacheEntity;
import com.thanhbuitien.repositories.IQuantileCacheRepo;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class QuantileCacheRepo implements IQuantileCacheRepo {

    private Map<Long, QuantileCacheEntity> poolId2QuantileEntity;

    public QuantileCacheRepo() {
        poolId2QuantileEntity = new ConcurrentHashMap<>();
    }

    @Override
    public QuantileCacheEntity getQuantile(Long poolId, Double percentile) {
        return poolId2QuantileEntity.getOrDefault(poolId, null);
    }

    @Override
    public void clearQuantile(Long poolId) {
        poolId2QuantileEntity.remove(poolId);
    }

    @Override
    public void save(Long poolId, Integer poolSize, Double percentile, Double quantile) {
        QuantileCacheEntity quantileCacheEntity = poolId2QuantileEntity.getOrDefault(poolId, null);
        if (quantileCacheEntity == null) {
            quantileCacheEntity = new QuantileCacheEntity(poolId, poolSize);
            quantileCacheEntity.getPercentile2Quantile().put(percentile, quantile);
            poolId2QuantileEntity.put(poolId, quantileCacheEntity);
        } else {
            quantileCacheEntity.getPercentile2Quantile().put(percentile, quantile);
        }
    }
}
