package com.thanhbuitien.models.dto;

import lombok.Data;

@Data
public class QuantileQueryRes {
    private Integer size;
    private Double quantile;

    public QuantileQueryRes(Integer size, Double quantile) {
        this.size = size;
        this.quantile = quantile;
    }
}
