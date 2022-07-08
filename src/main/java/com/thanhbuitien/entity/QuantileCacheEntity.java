package com.thanhbuitien.entity;

import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class QuantileCacheEntity {
    private Long poolId;
    private Integer poolSize;
    private Map<Double, Double> percentile2Quantile;

    public QuantileCacheEntity(Long poolId, Integer poolSize) {
        this.poolId = poolId;
        this.poolSize = poolSize;
        this.percentile2Quantile = new ConcurrentHashMap<>();
    }
}
