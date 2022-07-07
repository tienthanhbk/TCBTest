package com.thanhbuitien.service;

import com.thanhbuitien.models.dto.PoolDto;

public interface IPoolService {
    int insertOrUpdate(PoolDto poolDto);
}
