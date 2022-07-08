package com.thanhbuitien.models.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PoolDto {
    private Long poolId;
    private List<Integer> poolValues;
    public PoolDto(Long poolId, List<Integer> poolValues) {
        this.poolId = poolId;
        this.poolValues = new ArrayList<>();
        this.poolValues.addAll(poolValues);
    };
}
