package com.thanhbuitien.service;

import com.thanhbuitien.models.dto.QuantileQueryRes;

import java.util.List;

public interface IPoolService {
    int insertOrUpdate(Long poolId, List<Integer> values);
    List<Integer> getValuesById(Long poolId);
    QuantileQueryRes queryQuantile(Long poolId, Double percentile);
}
