package com.thanhbuitien.entity;

import lombok.Data;

import java.util.List;

@Data
public class PoolEntity {
    private Long id;
    private List<Integer> values;

    public PoolEntity(Long id, List<Integer> values) {
        this.id = id;
        this.values = values;
    }
}
