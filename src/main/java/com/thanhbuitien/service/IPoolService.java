package com.thanhbuitien.service;

import com.thanhbuitien.models.dto.PoolDto;

import java.util.List;

public interface IPoolService {
    int insertOrUpdate(Long poolId, List<Integer> values);
    List<Integer> getValuesById(Long poolId);
}
