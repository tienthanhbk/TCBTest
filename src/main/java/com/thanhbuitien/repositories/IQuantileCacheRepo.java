package com.thanhbuitien.repositories;

import com.thanhbuitien.entity.QuantileCacheEntity;
import org.springframework.stereotype.Component;

@Component
public interface IQuantileCacheRepo {
    QuantileCacheEntity getQuantile(Long poolId, Double percentile);
    void clearQuantile(Long poolId);
    void save(Long poolId, Integer poolSize, Double percentile, Double quantile);
}
