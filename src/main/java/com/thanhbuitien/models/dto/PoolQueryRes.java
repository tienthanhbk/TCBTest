package com.thanhbuitien.models.dto;

import lombok.Data;

@Data
public class PoolQueryRes {
    private Integer size;
    private Double quantile;

    public PoolQueryRes(Integer size, Double quantile) {
        this.size = size;
        this.quantile = quantile;
    }
}
