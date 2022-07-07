package com.thanhbuitien.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class PoolDto {
    private Long poolId;
    private List<Integer> poolValues;
}
