package com.thanhbuitien.repositories;

import com.thanhbuitien.entity.PoolEntity;

import java.util.List;

public interface IPoolRepo {
    PoolEntity getById(Long id);
    void create(Long id, List<Integer> values);

    void update(Long id, List<Integer> values);
}
