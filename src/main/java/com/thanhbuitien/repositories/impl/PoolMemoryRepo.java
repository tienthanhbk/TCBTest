package com.thanhbuitien.repositories.impl;

import com.thanhbuitien.entity.PoolEntity;
import com.thanhbuitien.repositories.IPoolRepo;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class PoolMemoryRepo implements IPoolRepo {
    private final Map<Long, PoolEntity> poolId2Entity;

    public PoolMemoryRepo() {
        poolId2Entity = new HashMap<>();
    }

    @Override
    public PoolEntity getById(Long id) {
        return poolId2Entity.getOrDefault(id, null);
    }

    @Override
    public void create(Long id, List<Integer> values) {
        poolId2Entity.put(id, new PoolEntity(id, values));
    }

    @Override
    public void update(Long id, List<Integer> values) {
        poolId2Entity.get(id).setValues(values);
    }
}
